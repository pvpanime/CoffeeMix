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
    <a class="list-group-item" href="${pageContext.request.contextPath}/login">Login</a>
  </div>
  <form action="${pageContext.request.contextPath}/logout" method="post">
    <input type="submit" value="Logout">
  </form>
</div>
</body>
</html>