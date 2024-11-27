package dev.nemi.derekmuller.board.controller;

import dev.nemi.derekmuller.board.BoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardDeleteController", urlPatterns = {"/board/delete/*"})
public class BoardDeleteController extends HttpServlet {

  private BoardService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new BoardService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      service.remove(req.getPathInfo());
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/delete.jsp");
      dispatcher.forward(req, resp);
    } catch (SQLException sex) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
