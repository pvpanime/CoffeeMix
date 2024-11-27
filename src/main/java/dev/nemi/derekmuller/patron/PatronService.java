package dev.nemi.derekmuller.patron;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.nemi.derekmuller.Mapp;
import dev.nemi.derekmuller.patron.dto.PatronSignupDTO;
import org.modelmapper.ModelMapper;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class PatronService {
  private final PatronDAO patronDAO;
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
    return patronDAO.authenticate(userid, rawPassword);
  }
}
