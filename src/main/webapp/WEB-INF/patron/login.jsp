<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-27
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp"/>
  <title>Login</title>
</head>
<body data-bs-theme="dark" class="d-flex align-items-center py-4 bg-body-tertiary">
<main class="w-100 m-auto" style="max-width: 350px;">

<form action="/login" method="POST">

  <h1 class="h3 mb-3 fw-normal text-center">Login</h1>

  <div class="form-floating">
    <input type="text" name="userid" class="form-control" id="floatingInput" placeholder="&lt;Your ID&gt;" required>
    <label for="floatingInput">User ID</label>
  </div>
  <div class="form-floating mt-1">
    <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password" required>
    <label for="floatingPassword">Password</label>
  </div>

  <div class="form-check text-start my-3">
    <input class="form-check-input" type="checkbox" value="remember-me" id="flexCheckDefault">
    <label class="form-check-label" for="flexCheckDefault">
      Remember me
    </label>
  </div>
  <button class="btn btn-primary w-100 py-2" type="submit">Login</button>
  <div> have no account? </div>
  <a class="btn btn-outline-light w-100 py-2 mt-3" href="${pageContext.request.contextPath}/signup">Create new Account</a>
</form>
</main>
</body>
</html>
