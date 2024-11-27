package dev.nemi.derekmuller.patron;

import java.sql.SQLException;

public interface PatronDI {
  void create(PatronVO patron) throws SQLException;

  Patron authenticate(String userid, String rawPassword) throws SQLException;
}
