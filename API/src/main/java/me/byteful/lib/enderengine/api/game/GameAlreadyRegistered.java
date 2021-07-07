package me.byteful.lib.enderengine.api.game;

public class GameAlreadyRegistered extends RuntimeException {
  public GameAlreadyRegistered(String name) {
    super("The game " + name + " has already been registered.");
  }
}
