package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.patron.Patron;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/user"})
public class PatronUserViewController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    HttpSession session = req.getSession();
    if (session.getAttribute("patron") == null) {
      session.setAttribute("from", req.getRequestURL().toString());
      resp.sendRedirect("/login");
      return;
    }
    Patron patron = (Patron) session.getAttribute("patron");
    req.setAttribute("patron", patron);
    req.getRequestDispatcher("/WEB-INF/patron/user.jsp").forward(req, resp);
  }
}
