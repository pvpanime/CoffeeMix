package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(urlPatterns = {"/user"})
public class PatronUserViewController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    PatronProfileDTO patron = (PatronProfileDTO) session.getAttribute("patron");
    req.setAttribute("patron", patron);
    req.getRequestDispatcher("/WEB-INF/patron/user.jsp").forward(req, resp);
  }
}
