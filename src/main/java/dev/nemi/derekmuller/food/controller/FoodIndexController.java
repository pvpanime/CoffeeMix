package dev.nemi.derekmuller.food.controller;

import dev.nemi.derekmuller.food.FoodService;
import dev.nemi.derekmuller.food.dto.FoodAddDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food"})
public class FoodIndexController extends HttpServlet {

  private FoodService service;

  @Override
  public void init() throws ServletException {
    super.init();
    this.service = new FoodService();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      req.setAttribute("foods", service.listAll());
      RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/food/index.jsp");
      dispatcher.forward(req, resp);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String name = req.getParameter("name");
      String description = req.getParameter("description");
      long price = Long.parseLong(req.getParameter("price"));
      service.add(FoodAddDTO.builder().name(name).description(description).price(price).build());
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  }
}
