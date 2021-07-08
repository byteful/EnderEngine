package me.byteful.lib.enderengine.loader;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

@UtilityClass
public final class EnderLoader {
  private static final String GITHUB_DOWNLOAD_URL = "https://github.com/byteful/EnderEngine/releases/download/{version}/EnderEngine-{version1}.jar";
  private static final Method ADD_URL_METHOD;

  static {
    try {
      ADD_URL_METHOD = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
      ADD_URL_METHOD.setAccessible(true);
    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Failed to setup EnderEngine injector...", e);
    }
  }

  /**
   * Loads the provided version of EnderEngine, if not loaded already, and reads from the directory provided.
   * EnderEngine will be downloaded if not found already.
   * <p>
   * Version Format Example: "v1.0.0" or "1.0.0"
   *
   * @param version    the version of EnderEngine to load
   * @param enginesDir the folder in which you want to store all instances of EnderEngine
   * @param loader     the classloader to inject EnderEngine into (recommended to use class loader of plugin/module)
   */
  public static void load(@NotNull String version, @NotNull final File enginesDir, @NotNull final URLClassLoader loader) throws EngineLoadException {
    version = version.toLowerCase(Locale.ROOT);
    version = version.replace("_", ".");
    if (!version.startsWith("v")) {
      version = "v" + version;
    }

    System.out.println("Loading EnderEngine " + version + "...");

    final String version1 = version.substring(1);
    final File engineFile = new File(enginesDir, "EnderEngine-" + version1 + ".jar");

    if (!engineFile.exists()) {
      System.out.println("Engine not found... Preparing to download from the GitHub repository...");

      try {
        try (InputStream in = new URL(GITHUB_DOWNLOAD_URL.replace("{version}", version).replace("{version1}", version1)).openStream()) {
          System.out.println("Found EnderEngine " + version + " in the GitHub repository! Downloading...");
          Files.copy(in, engineFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
          System.out.println("EnderEngine " + version + " has been downloaded.");
        }
      } catch (IOException e) {
        throw new EngineLoadException("Failed to download EnderEngine...", e);
      }
    } else {
      System.out.println("EnderEngine " + version + " found!");
    }

    try {
      System.out.println("Injecting EnderEngine...");
      ADD_URL_METHOD.invoke(loader, engineFile.toURI().toURL());
    } catch (MalformedURLException | IllegalAccessException | InvocationTargetException e) {
      throw new EngineLoadException("Failed to inject EnderEngine into the provided ClassLoader...", e);
    }

    System.out.println("EnderEngine " + version + " has fully loaded! All library classes are now available.");
  }
}
