package me.byteful.lib.enderengine.loader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
class EngineLoadException extends RuntimeException {
  EngineLoadException(String message, Throwable cause) {
    super(message, cause);
  }
}
