package dev.nemi.derekmuller;

import dev.nemi.derekmuller.patron.Patron;
import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.PatronVO;
import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import dev.nemi.derekmuller.patron.dto.PatronSignupDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Log4j2
public class PatronTest {

  private PatronService service;

  @BeforeEach
  public void setUp() {
    service = new PatronService();
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
  public void loginTest() throws SQLException {
    Patron patron;

    patron = service.login("me", "invalidPassword");
    Assertions.assertNull(patron);
    log.info(patron);
    
    patron = service.login("me", "12345yu");
    Assertions.assertNotNull(patron);
    log.info(patron);

  }

  @Test
  public void testProfile() throws SQLException {
    PatronProfileDTO dto = service.getProfile("me");
    Assertions.assertNotNull(dto);
    log.info(dto);

    dto = service.getProfile("user");
    Assertions.assertNotNull(dto);
    log.info(dto);
  }

  @Test
  public void testUpdate() throws SQLException {
    PatronProfileDTO dto = service.getProfile("user");
    boolean success = service.updateProfile(PatronProfileDTO.builder()
      .userid(dto.getUserid())
        .username("초능력자")
      .email("superhero@gmail.com")
      .build());
    Assertions.assertTrue(success);
  }

  @Test
  public void voValueMapperTest() {
    PatronVO vo = PatronVO.builder().userid("alice").secret(null).username("Alice").email("alice@example.com").build();
    PatronProfileDTO dto = Mapp.er.map(vo, PatronProfileDTO.class);
    log.info(dto);

    PatronVO back = Mapp.er.map(dto, PatronVO.class);
    log.info(back);
  }

}
