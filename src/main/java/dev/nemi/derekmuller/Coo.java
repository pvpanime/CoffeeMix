package dev.nemi.derekmuller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class Coo {
  private Coo() {}

  public static Cookie key(HttpServletRequest req, String name) {
    Cookie[] cookies = req.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies)
        if (cookie.getName().equals(name)) return cookie;
    }

    return null;
  }

  public static Cookie kie(String key, String value, int maxAge) {
    Cookie cookie = new Cookie(key, value);
    cookie.setHttpOnly(true);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    return cookie;
  }

  public static Cookie kie(String key, String value) {
    return kie(key, value, 3600 * 24);
  }

}
