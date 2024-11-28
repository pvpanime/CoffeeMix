package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.Coo;
import dev.nemi.derekmuller.Jay;
import dev.nemi.derekmuller.patron.Patron;
import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(urlPatterns = {"/api/login"})
public class PatronLoginActionController extends HttpServlet {

  private static final int life = 3600 * 24 * 7;
  private PatronService service;

  @Override
  public void init() throws ServletException {
    super.init();
    this.service = new PatronService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String userid = req.getParameter("userid");
    String rawPassword = req.getParameter("password");
    String rememberMe = req.getParameter("remember-me");
    log.info("rememberMe= {}", rememberMe);

    try {
      Patron patron = service.login(userid, rawPassword);
      if (patron == null) {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Jay.son(req, resp, new JSONObject().put("error", "Invalid username or password"));
        return;
      }
      PatronProfileDTO profile = service.getProfile(userid);
      req.getSession().setAttribute("patron", profile);

      if (rememberMe != null) {
        String ticketString = service.createTicketFor(userid);
        resp.addCookie(Coo.kie("PatronTicket", ticketString, life));
      }

      Object backTo = req.getSession().getAttribute("from");
      String backToString = backTo instanceof String ? (String) backTo : "/";
      resp.setStatus(HttpServletResponse.SC_OK);
      Jay.son(req, resp, new JSONObject().put("backTo", backToString));

    } catch (SQLException e) {
      e.printStackTrace();
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      Jay.son(req, resp, new JSONObject().put("error", e.getMessage()));
    }


  }
}
