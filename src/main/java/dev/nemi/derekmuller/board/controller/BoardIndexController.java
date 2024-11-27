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

@WebServlet(name = "BoardIndexController", urlPatterns = {"/board", "/board/page/*"})
public class BoardIndexController extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    super.init();
    boardService = new BoardService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String pathInfo = req.getPathInfo();
      long currentPage;
      if (pathInfo != null) {
        currentPage = pathInfo.startsWith("/") ? Long.parseLong(pathInfo.substring(1)) : Long.parseLong(pathInfo);
      } else {
        currentPage = 1;
      }
      req.setAttribute("currentPage", currentPage);
      req.setAttribute("list", boardService.list((currentPage - 1) * 10 + 1, 10));
      req.setAttribute("maxPage", boardService.getMaxPage(10));
      RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/board/index.jsp");
      rd.forward(req, resp);
    } catch (NumberFormatException ne) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }


  }
}
