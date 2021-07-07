package me.byteful.lib.enderengine.api.wrapper;

/**
 * An interface to wrap server-specific methods.
 */
public interface ServerWrapper {
  void loadWorld(String name);

  void unloadWorld(String name);
}
