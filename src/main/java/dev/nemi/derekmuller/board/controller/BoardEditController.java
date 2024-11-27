package dev.nemi.derekmuller.board.controller;

import dev.nemi.derekmuller.board.BoardService;
import dev.nemi.derekmuller.board.dto.BoardUpdateDTO;
import dev.nemi.derekmuller.board.dto.BoardViewDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardEditController", urlPatterns = {"/board/edit/*"})
public class BoardEditController extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    super.init();
    boardService = new BoardService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    BoardViewDTO dto = null;
    try {
      dto = boardService.getByPathInfo(req.getPathInfo());
      if (dto == null) {
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        return;
      }
      req.setAttribute("board", dto);

      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/edit.jsp");
      dispatcher.forward(req, resp);
    } catch (NumberFormatException n) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      long id = Long.parseLong(req.getParameter("id"));
      boardService.update(BoardUpdateDTO.builder()
          .id(id)
          .title(req.getParameter("title"))
          .content(req.getParameter("content"))
          .build());
      resp.sendRedirect("/board/read/" + id);
    } catch (NumberFormatException n) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }


}

