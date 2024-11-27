package dev.nemi.derekmuller.patron;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatronDAO {

//  public void create(PatronSignupDTO patron) throws SQLException {
//    @Cleanup Connection conn = TachibanaHikari.getConnection();
//    @Cleanup PreparedStatement stmt = conn.prepareStatement(
//      "INSERT INTO Patron(userid, username, secret, email) VALUES (?, ?, ?, ?)"
//    );
//    stmt.setString(1, patron.getUserid());
//    stmt.setString(2, patron.getUsername());
//    BCrypt.Hasher hasher = BCrypt.withDefaults();
//    hasher.hash()
//    byte[] secret =
//  }
}
