package dev.nemi.derekmuller.patron;

import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PatronDAO implements PatronDI {

  @Override
  public void create(PatronVO patron) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "INSERT INTO Patron(userid, username, secret, email) VALUES (?, ?, ?, ?)"
    );
    stmt.setString(1, patron.getUserid());
    stmt.setString(2, patron.getUsername());
    stmt.setBytes(3, patron.getSecret());
    stmt.setString(4, patron.getEmail());
    stmt.executeUpdate();
  }

  @Override
  public PatronVO getAuth(String userid) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT userid, secret FROM Patron WHERE userid = ?;"
    );
    stmt.setString(1, userid);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (!rs.next()) return null;
    return PatronVO.builder()
      .userid(rs.getString("userid"))
      .secret(rs.getBytes("secret"))
      .build();
  }

  @Override
  public String setTicket(String userid) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "UPDATE Patron SET ticket = ? WHERE userid = ?"
    );
    String uuid = UUID.randomUUID().toString();
    stmt.setString(1, uuid);
    stmt.setString(2, userid);
    stmt.executeUpdate();

    return uuid;
  }

  @Override
  public String getPatronByTicket(String ticket) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT userid FROM Patron WHERE ticket = ?"
    );
    stmt.setString(1, ticket);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (!rs.next()) return null;
    return rs.getString("userid");
  }

  @Override
  public String getTicket(String userid) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT ticket FROM Patron WHERE userid = ?"
    );
    stmt.setString(1, userid);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (!rs.next()) return null;
    return rs.getString("ticket");
  }

  @Override
  public void deleteTicket(String userid) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "UPDATE Patron SET ticket = NULL WHERE userid = ?"
    );
    stmt.setString(1, userid);
    stmt.executeUpdate();
  }

  @Override
  public PatronVO getProfile(String userid) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT userid, username, email FROM Patron WHERE userid = ?;"
    );
    stmt.setString(1, userid);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (!rs.next()) return null;

    return PatronVO.builder()
      .userid(rs.getString("userid"))
      .username(rs.getString("username"))
      .email(rs.getString("email"))
      .build();
  }

  @Override
  public int update(Patron patron) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "UPDATE Patron SET username = ?, email = ? WHERE userid = ?;"
    );
    stmt.setString(1, patron.getUsername());
    stmt.setString(2, patron.getEmail());
    stmt.setString(3, patron.getUserid());
    return stmt.executeUpdate();
  }

}
