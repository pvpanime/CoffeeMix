package dev.nemi.derekmuller.board.controller;

import dev.nemi.derekmuller.board.BoardService;
import dev.nemi.derekmuller.board.dto.BoardAddDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "BoardWriteController", urlPatterns = {"/board/write"})
public class BoardWriteController extends HttpServlet {

  private BoardService boardService;

  @Override
  public void init() throws ServletException {
    super.init();
    boardService = new BoardService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/board/write.jsp");
    dispatcher.forward(req, resp);
  }

  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      boardService.add(BoardAddDTO.builder()
        .title(req.getParameter("title"))
        .content(req.getParameter("content"))
        .build());
      resp.sendRedirect("/board");
    } catch (IOException | SQLException ioe) {
      ioe.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}

