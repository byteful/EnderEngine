package me.byteful.lib.enderengine.api;

import lombok.Getter;
import me.byteful.lib.enderengine.api.game.Game;
import me.byteful.lib.enderengine.api.game.GameAlreadyRegistered;
import me.byteful.lib.enderengine.api.wrapper.ServerWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EnderEngine {
  @NotNull
  private static final Map<String, Game> REGISTERED_GAMES = new ConcurrentHashMap<>();
  @Getter
  @Nullable
  private static ServerWrapper wrapper;

  public static void initialize(@NotNull ServerWrapper wrapper) {
    if (EnderEngine.wrapper != null) {
      throw new RuntimeException("EnderEngine has already been initialized. You cannot initialize EnderEngine again until it has been shutdown.");
    }

    EnderEngine.wrapper = wrapper;
    REGISTERED_GAMES.clear();
  }

  public static void registerGame(@NotNull Game game) {
    if (isGameRegistered(game)) {
      throw new GameAlreadyRegistered(game.getName());
    }

    REGISTERED_GAMES.put(game.getName().toLowerCase(Locale.ROOT), game);
  }

  public static boolean isGameRegistered(@NotNull Game game) {
    return REGISTERED_GAMES.containsKey(game.getName().toLowerCase(Locale.ROOT));
  }

  public static void shutdown() {
    wrapper = null;
    //TODO: shut down active arenas
  }
}
