package dev.nemi.derekmuller.board.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/board/write/*", "/board/edit/*"})
public class BoardGate implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse resp = (HttpServletResponse) servletResponse;

    HttpSession session = req.getSession();
    if (session.getAttribute("patron") == null) {
      if (req.getMethod().equals("GET")) {
        session.setAttribute("from", req.getRequestURL().toString());
        resp.sendRedirect("/login");
        return;
      } else if (req.getMethod().equals("POST")) {
        resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        return;
      }
    }

    chain.doFilter(req, resp);
  }
}
