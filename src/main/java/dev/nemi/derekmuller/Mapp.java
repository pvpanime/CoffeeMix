package dev.nemi.derekmuller;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public final class Mapp {
  private Mapp() {}

  public static final ModelMapper er;

  static {
    er = new ModelMapper();
    er.getConfiguration()
      .setFieldMatchingEnabled(true)
      .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
      .setMatchingStrategy(MatchingStrategies.STRICT);
  }

}
