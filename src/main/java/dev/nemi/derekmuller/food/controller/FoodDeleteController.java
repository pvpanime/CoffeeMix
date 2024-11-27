package dev.nemi.derekmuller.food.controller;

import dev.nemi.derekmuller.food.FoodService;
import dev.nemi.derekmuller.food.dto.FoodViewDTO;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/food/delete"})
public class FoodDeleteController extends HttpServlet {

  private FoodService foodService;

  @Override
  public void init() throws ServletException {
    foodService = new FoodService();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try {
      String idString = req.getParameter("id");
      long id = Long.parseLong(idString);
      foodService.delete(id);
      resp.setContentType("text/plain");
      PrintWriter out = resp.getWriter();
      out.print("200 OK");
    } catch (NumberFormatException nex) {
      nex.printStackTrace();
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } catch (SQLException sex) {
      sex.printStackTrace();
      if (sex.getErrorCode() == 1264) {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
      } else {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      }
    }
  }
}
