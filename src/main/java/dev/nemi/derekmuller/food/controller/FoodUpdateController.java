package dev.nemi.derekmuller.food.controller;

import dev.nemi.derekmuller.food.FoodService;
import dev.nemi.derekmuller.food.dto.FoodWholeUpdateDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food/update"})
public class FoodUpdateController extends HttpServlet {

  private FoodService service;

  @Override
  public void init() throws ServletException {
    super.init();
    service = new FoodService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    try {
      String idString = req.getParameter("id");
      long id = Long.parseLong(idString);
      String name = req.getParameter("name");
      String description = req.getParameter("description");
      String priceString = req.getParameter("price");
      long price = Long.parseLong(priceString);
      service.update(FoodWholeUpdateDTO.builder().id(id).name(name).description(description).price(price).build());
      PrintWriter out = resp.getWriter();
      resp.setStatus(HttpServletResponse.SC_OK);
      out.println("200 OK");
    } catch (NumberFormatException ne) {
      ne.printStackTrace();
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException e) {
      e.printStackTrace();
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
  }
}
