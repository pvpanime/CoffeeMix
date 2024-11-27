package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.Jay;
import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.dto.PatronSignupDTO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/signup")
public class PatronSignupActionController extends HttpServlet {

  private PatronService service;

  @Override
  public void init() throws ServletException {
    service = new PatronService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userid = req.getParameter("userid");
    String rawPassword = req.getParameter("password");
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    if (userid.isEmpty() || rawPassword.isEmpty() || username.isEmpty() || email.isEmpty()) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      Jay.son(req, resp, new JSONObject().put("success", false).put("error", "empty string submitted"));
      return;
    }

    try {
      boolean success = service.create(PatronSignupDTO.builder()
          .userid(userid)
          .rawPassword(rawPassword)
          .username(username)
          .email(email)
        .build());

      if (!success) {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Jay.son(req, resp, new JSONObject().put("success", false).put("error", "User ID is already taken."));
        return;
      }

      resp.setStatus(HttpServletResponse.SC_OK);
      Jay.son(req, resp, new JSONObject().put("success", true));

    } catch (SQLException e) {
      resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      Jay.son(req, resp, new JSONObject().put("success", false).put("error", e.getMessage()));
    }
  }
}
