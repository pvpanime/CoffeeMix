package dev.nemi.derekmuller.patron;

import java.sql.SQLException;

public interface PatronDI {
  void create(PatronVO patron) throws SQLException;

  PatronVO getAuth(String userid) throws SQLException;

  String setTicket(String userid) throws SQLException;

  String getPatronByTicket(String ticket) throws SQLException;

  String getTicket(String userid) throws SQLException;

  void deleteTicket(String userid) throws SQLException;

  PatronVO getProfile(String userid) throws SQLException;

  int update(Patron patron) throws SQLException;
}
