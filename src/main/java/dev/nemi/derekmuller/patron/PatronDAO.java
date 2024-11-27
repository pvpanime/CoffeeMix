package dev.nemi.derekmuller.patron;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatronDAO {

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

  public Patron authenticate(String userid, String rawPassword) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement stmt = conn.prepareStatement(
      "SELECT userid, secret FROM Patron WHERE userid = ?;"
    );
    stmt.setString(1, userid);
    @Cleanup ResultSet rs = stmt.executeQuery();
    if (!rs.next()) return null;
    byte[] secret = rs.getBytes("secret");
    BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.getBytes(StandardCharsets.UTF_8), secret);
    if (!result.verified) return null;
    return new Patron(userid);
  }

}
