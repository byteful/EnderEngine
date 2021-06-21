package me.byteful.lib.enderengine.loader.impl;

import lombok.RequiredArgsConstructor;
import me.byteful.lib.enderengine.loader.LoaderModule;
import org.bukkit.plugin.PluginManager;

import java.io.File;

@RequiredArgsConstructor
public class SpigotLoaderModule implements LoaderModule {
  private final PluginManager pm;
  private final File pluginsFolder;

  @Override
  public void load() {
    if (!pm.isPluginEnabled("EnderEngine")) {
      File pluginFile = new File(pluginsFolder, "EnderEngine.jar");
    }
  }

  @Override
  public boolean canLoad() {
    return pm != null && pluginsFolder != null && pluginsFolder.exists();
  }
}
