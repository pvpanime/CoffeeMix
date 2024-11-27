package dev.nemi.derekmuller;

import dev.nemi.derekmuller.board.BoardDAO;
import dev.nemi.derekmuller.board.BoardDI;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

@Log4j2
public class BoardTest {

  BoardDI boardData = new BoardDAO();

  @Test
  public void test() throws SQLException {
    long count = boardData.getMaxPages(10);
    log.info(count);
  }
}
