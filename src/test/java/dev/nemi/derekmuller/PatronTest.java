package dev.nemi.derekmuller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.nemi.derekmuller.patron.Patron;
import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.dto.PatronSignupDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

@Log4j2
public class PatronTest {

  private PatronService service;

  @BeforeEach
  public void setUp() {
    service = new PatronService();
  }

  @Test
  public void testBCrypt() {

    byte[] hash = BCrypt.withDefaults().hash(12, "password".getBytes(StandardCharsets.UTF_8));
    log.info(hash);

    BCrypt.Result result = BCrypt.verifyer().verify("password".getBytes(StandardCharsets.UTF_8), hash);
    log.info(result.toString());
    Assertions.assertTrue(result.verified);
  }

  @Test
  public void testSignup() throws SQLException {
    boolean a = service.create(PatronSignupDTO.builder()
        .userid("me")
        .rawPassword("12345yu")
        .username("Jane Doe")
        .email("zzz@example.com")
      .build());
    Assertions.assertTrue(a);
  }

  @Test
  public void testLogin() throws SQLException {
    Patron patron;

    patron = service.login("me", "invalidPassword");
    Assertions.assertNull(patron);
    log.info(patron);
    
    patron = service.login("me", "12345yu");
    Assertions.assertNotNull(patron);
    log.info(patron);


  }
}
