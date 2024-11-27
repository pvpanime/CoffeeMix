package dev.nemi.derekmuller.board.controller;

import dev.nemi.derekmuller.board.BoardService;
import dev.nemi.derekmuller.board.dto.BoardViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardReadController", urlPatterns = {"/board/read/*"})
public class BoardReadController extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    super.init();
    boardService = new BoardService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BoardViewDTO dto;
    try {
      dto = boardService.getByPathInfo(req.getPathInfo());
      if (dto == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
      } else {
        req.setAttribute("board", dto);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/board/read.jsp");
        requestDispatcher.forward(req, resp);
      }
    } catch (NumberFormatException e) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }
}
