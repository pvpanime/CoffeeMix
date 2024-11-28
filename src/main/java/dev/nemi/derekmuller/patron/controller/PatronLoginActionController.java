package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.Jay;
import dev.nemi.derekmuller.patron.Patron;
import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@Log4j2
@WebServlet(urlPatterns = {"/api/login"})
public class PatronLoginActionController extends HttpServlet {

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
    try {
      Patron patron = service.login(userid, rawPassword);
      if (patron == null) {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Jay.son(req, resp, new JSONObject().put("error", "Invalid username or password"));
        return;
      }
      PatronProfileDTO profile = service.getProfile(userid);
      HttpSession sess = req.getSession();
      sess.setAttribute("patron", profile);
      Object backTo = sess.getAttribute("from");
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
