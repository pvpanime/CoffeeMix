package dev.nemi.derekmuller.patron;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.nemi.derekmuller.Mapp;
import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import dev.nemi.derekmuller.patron.dto.PatronSignupDTO;
import org.modelmapper.ModelMapper;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class PatronService {
  private final PatronDI patronDAO;
  private final ModelMapper mapper;

  public PatronService() {
    this.patronDAO = new PatronDAO();
    this.mapper = Mapp.er;
  }

  public boolean create(PatronSignupDTO patron) throws SQLException {
    byte[] secret = BCrypt.withDefaults().hash(12, patron.getRawPassword().getBytes(StandardCharsets.UTF_8));
    try {
      patronDAO.create(PatronVO.builder()
        .userid(patron.getUserid())
        .secret(secret)
        .username(patron.getUsername())
        .email(patron.getEmail())
        .build());
      return true;
    } catch (SQLException e) {
      if (e instanceof SQLIntegrityConstraintViolationException) {
        return false;
      }
      throw e;
    }
  }

  public Patron login(String userid, String rawPassword) throws SQLException {
    PatronVO auth = patronDAO.getAuth(userid);
    if (auth == null) return null;

    BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.getBytes(StandardCharsets.UTF_8), auth.getSecret());
    if (!result.verified) return null;

    return Patron.builder().userid(auth.getUserid()).build();
  }

  public String createTicketFor(String userid) throws SQLException {
    return patronDAO.setTicket(userid);
  }


  public String challengeTicket(String ticket) throws SQLException {
//    String stored = patronDAO.getTicket(userid);
//    return stored != null && stored.equals(ticket);
    return patronDAO.getPatronByTicket(ticket);
  }

  public void purgeTicket(String userid) throws SQLException {
    patronDAO.deleteTicket(userid);
  }

  public PatronProfileDTO getProfile(String userid) throws SQLException {
    return mapper.map(patronDAO.getProfile(userid), PatronProfileDTO.class);
  }

  public boolean updateProfile(PatronProfileDTO patron) throws SQLException {
    int rows = patronDAO.update(mapper.map(patron, Patron.class));
    return rows > 0;
  }
}
