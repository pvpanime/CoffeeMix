<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp"/>
  <title>Title</title>
</head>
<body data-bs-theme="dark">
<div class="container py-5">
  <div class="list-group">
    <a class="list-group-item" href="${pageContext.request.contextPath}/board">Board</a>
    <a class="list-group-item" href="${pageContext.request.contextPath}/food">Food</a>

  </div>
  <div class="py-3 row">
    <c:choose>
      <c:when test="${patron == null}">
        <a class="col btn btn-secondary" href="${pageContext.request.contextPath}/login">Login</a>
      </c:when>
      <c:otherwise>
        <a class="col btn btn-primary" href="${pageContext.request.contextPath}/user">User info</a>
        <jsp:include page="/WEB-INF/include/logoutButton.jsp" />
      </c:otherwise>
    </c:choose>
<%--    <form class="col" action="${pageContext.request.contextPath}/logout" method="post">--%>
<%--      <input class="btn btn-danger" type="submit" value="Logout">--%>
<%--    </form>--%>
  </div>
</div>

</body>
</html>