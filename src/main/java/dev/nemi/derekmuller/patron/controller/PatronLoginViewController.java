package dev.nemi.derekmuller.patron.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(urlPatterns = {"/login"})
public class PatronLoginViewController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession sess = req.getSession();

    if (sess != null && sess.getAttribute("patron") != null) {
      resp.sendRedirect("/");
      return;
    }

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/patron/login.jsp");
    rd.forward(req, resp);
  }


}
