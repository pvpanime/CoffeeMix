package dev.nemi.derekmuller;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public final class M {
  private M() {}

  public static final ModelMapper apper;

  static {
    apper = new ModelMapper();
    apper.getConfiguration()
      .setFieldMatchingEnabled(true)
      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
      .setMatchingStrategy(MatchingStrategies.STRICT);
  }

}
