package me.byteful.lib.enderengine.loader;

public class EngineLoadException extends RuntimeException {
  public EngineLoadException() {
    super("Failed to load EnderEngine. Please check to make sure the loader module used can properly load.");
  }
}
