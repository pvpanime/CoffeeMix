package dev.nemi.derekmuller.board;


import dev.nemi.derekmuller.TachibanaHikari;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BoardDAO implements BoardDI {

  private static BoardVO from(ResultSet rs) throws SQLException {
    return BoardVO.builder()
      .id(rs.getLong("id"))
      .title(rs.getString("title"))
      .content(rs.getString("content"))
      .added(rs.getTimestamp("added").toInstant())
      .updated(rs.getTimestamp("updated").toInstant())
      .userId(rs.getObject("userid", Long.class))
      .build();
  }

  @Override
  public void insert(BoardVO board) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("INSERT INTO Board(id, title, content) VALUES (weeb.create_id(), ?, ?)");
    ps.setString(1, board.getTitle());
    ps.setString(2, board.getContent());
    ps.executeUpdate();
  }

  @Override
  public void update(BoardVO board) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("UPDATE Board SET title = ?, content = ?, updated = now() WHERE id = ?");
    ps.setString(1, board.getTitle());
    ps.setString(2, board.getContent());
    ps.setLong(3, board.getId());
    ps.executeUpdate();
  }

  @Override
  public List<BoardVO> getListAt(long offset, long count) throws SQLException {
    List<BoardVO> list = new ArrayList<>();
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Board ORDER BY added DESC LIMIT ? OFFSET ?");
    ps.setLong(1, count);
    ps.setLong(2, offset);
    @Cleanup ResultSet rs = ps.executeQuery();
    while (rs.next()) {
      list.add(from(rs));
    }
    return list;
  }

  @Override
  public BoardVO getBoardById(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT * FROM Board WHERE id = ?");
    ps.setLong(1, id);
    @Cleanup ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      return from(rs);
    }
    return null;
  }

  @Override
  public void remove(long id) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("DELETE FROM Board WHERE id = ?");
    ps.setLong(1, id);
    ps.executeUpdate();
  }

  @Override
  public long getMaxPages(int numPerPage) throws SQLException {
    @Cleanup Connection conn = TachibanaHikari.getConnection();
    @Cleanup PreparedStatement ps = conn.prepareStatement("SELECT CEIL(COUNT(*) / ?) as pages FROM Board;");
    ps.setInt(1, numPerPage);
    @Cleanup ResultSet rs = ps.executeQuery();
    if (rs.next()) {
      return rs.getLong("pages");
    }
    log.warn("Wait, how it's even possible?");
    return 0;
  }
}
