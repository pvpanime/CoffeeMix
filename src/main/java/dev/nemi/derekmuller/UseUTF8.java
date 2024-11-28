package dev.nemi.derekmuller;

import dev.nemi.derekmuller.patron.PatronService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(urlPatterns = {"/*"})
@Log4j2
public class UseUTF8 implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;
    req.setCharacterEncoding("UTF-8");
    resp.setCharacterEncoding("UTF-8");

    if (req.getSession().getAttribute("patron") == null) {
      Cookie ckTicket = Coo.key(req, "PatronTicket");
      if (ckTicket != null) {
        try {
          PatronService svc = new PatronService();
          String userid = svc.challengeTicket(ckTicket.getValue());
          if (userid != null) {
            req.getSession().setAttribute("patron", svc.getProfile(userid));
          }
        } catch (SQLException e) {
          // eh, fugget about it.
        }
      }
    }

    if (req.getRequestURI().equals("/")) {
      req.getRequestDispatcher("/index.jsp").forward(req, resp);
      return;
    }
    
    filterChain.doFilter(req, resp);
  }

}
