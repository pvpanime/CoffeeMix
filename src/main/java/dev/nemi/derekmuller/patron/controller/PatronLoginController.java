package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.patron.Patron;
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
public class PatronLoginController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession sess = req.getSession();
    if (sess != null) {
      if (sess.getAttribute("patron") != null) {
        log.info(sess.getAttribute("patron"));

        resp.sendRedirect("/");
        return;
      } else {
        log.info("Somehow, the hollow session arisen.");
      }
    } else {
      log.info("Logout worked as intended?!");
    }

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/patron/login.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession sess = req.getSession();
    sess.setAttribute("patron", new Patron(req.getParameter("userid")));
    log.info(sess.getAttribute("patron"));
    Object backTo = sess.getAttribute("from");
    if (backTo instanceof String) {
      resp.sendRedirect(backTo.toString());
    } else {
      resp.sendRedirect("/");
    }
  }
}
