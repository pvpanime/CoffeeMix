package dev.nemi.derekmuller.board;

import java.sql.SQLException;
import java.util.List;

public interface BoardDI {
  void insert(BoardVO board) throws SQLException;

  void update(BoardVO board) throws SQLException;

  List<BoardVO> getListAt(long offset, long count) throws SQLException;

  BoardVO getBoardById(long id) throws SQLException;

  void remove(long id) throws SQLException;

  long getMaxPages(int numPerPage) throws SQLException;

}
