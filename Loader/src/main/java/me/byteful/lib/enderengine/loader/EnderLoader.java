package me.byteful.lib.enderengine.loader;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public final class EnderLoader {
  private static final String DOWNLOAD_URL = "";

  public static void load(LoaderModule module) {
    if(!module.canLoad()) {
      throw new EngineLoadException();
    }

    module.load();
  }

  public static void downloadLatestEngine(File downloadTo) {

  }

  public static void downloadEngine(File downloadTo, String version) {

  }
}
