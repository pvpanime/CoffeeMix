package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.Coo;
import dev.nemi.derekmuller.patron.PatronService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(urlPatterns = {"/api/logout"})
public class PatronLogoutController extends HttpServlet {
  private PatronService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new PatronService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    if (session != null) {
      session.removeAttribute("patron");
      session.invalidate();
      Cookie ticket = Coo.key(req, "PatronTicket");
      if (ticket != null) {
        ticket.setPath("/");
        ticket.setMaxAge(0);
        resp.addCookie(ticket);
      }
    }
    log.info("Logout completed");
    resp.sendRedirect(req.getContextPath() + "/");
  }
}
