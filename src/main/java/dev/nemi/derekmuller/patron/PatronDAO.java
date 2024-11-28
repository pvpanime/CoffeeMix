package dev.nemi.derekmuller.patron;

import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
