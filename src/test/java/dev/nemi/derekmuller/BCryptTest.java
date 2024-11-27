package dev.nemi.derekmuller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

@Log4j2
public class BCryptTest {
  @Test
  public void testBCrypt() {

    byte[] hash = BCrypt.withDefaults().hash(12, "password".getBytes(StandardCharsets.UTF_8));
    log.info(hash);

    BCrypt.Result result = BCrypt.verifyer().verify("password".getBytes(StandardCharsets.UTF_8), hash);
    log.info(result.toString());
    Assertions.assertTrue(result.verified);
  }
}
