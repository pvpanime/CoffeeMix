package dev.nemi.derekmuller;

import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class Jay {
  private Jay() {}
  public static void son(HttpServletRequest req, HttpServletResponse res, JSONObject obj)
    throws JSONException, IOException {
    res.setContentType("application/json");
    PrintWriter out = res.getWriter();
    out.println(obj.toString());
  }
}
