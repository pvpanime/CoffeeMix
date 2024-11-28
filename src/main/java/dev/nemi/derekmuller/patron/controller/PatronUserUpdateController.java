package dev.nemi.derekmuller.patron.controller;

import dev.nemi.derekmuller.patron.PatronService;
import dev.nemi.derekmuller.patron.dto.PatronProfileDTO;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Log4j2
@WebServlet(urlPatterns = "/user/update")
public class PatronUserUpdateController extends HttpServlet {

  private PatronService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new PatronService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userId = req.getParameter("userid");
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    try {
      PatronProfileDTO dto = PatronProfileDTO.builder()
        .userid(userId)
        .username(username)
        .email(email)
        .build();
      boolean success = service.updateProfile(dto);
      if (success) {
        req.getSession().setAttribute("patron", dto);
      }
      req.setAttribute("success", success);
      req.getRequestDispatcher("/WEB-INF/patron/updateResult.jsp").forward(req, resp);
    } catch (SQLException sex) {
      sex.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
