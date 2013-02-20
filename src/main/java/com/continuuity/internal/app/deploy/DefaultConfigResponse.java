package com.continuuity.internal.app.deploy;

import com.continuuity.app.deploy.ConfigResponse;
import com.google.common.io.InputSupplier;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * This is implementation of {@link ConfigResponse}.
 * <p>
 * Immutable class that hold exit code and stream of input.
 * </p>
 */
public final class DefaultConfigResponse implements ConfigResponse {
  private final int exit;
  private final InputSupplier<? extends Reader> input;

  /**
   * Constructor.
   *
   * @param exit  code returned from processing a command.
   * @param input that streams in data generated by running command.
   */
  public DefaultConfigResponse(int exit, InputSupplier<? extends Reader> input) {
    this.exit = exit;
    this.input = input;
  }

  /**
   * @return A handle to {@link InputStream} if execution was successfully; else null.
   */
  @Override
  @Nullable
  public Reader get() throws IOException {
    if(input != null) {
      return input.getInput();
    }
    return null;
  }

  /**
   * @return Exit code of command that was executed.
   */
  @Override
  public int getExitCode() {
    return exit;
  }
}
