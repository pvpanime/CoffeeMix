<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp"/>
  <title>My Profile</title>
</head>
<body data-bs-theme="dark">
  <div class="container py-4">
    <div class="jumbotron bg-dark text-light text-center py-5">
      <h1 class="display-1">My Profile</h1>
    </div>
    <div>
      <div class="form-floating mt-1">
        <input type="text" name="userid" class="form-control" id="ui" disabled required value="${patron.userid}">
        <label for="ui">User ID</label>
      </div>
      <div class="form-floating">
        <input type="text" name="username" class="form-control" id="uname" placeholder="Public name" required>
        <label for="uname">Public name</label>
      </div>
      <div class="form-floating mb-1">
        <input type="email" name="email" class="form-control" id="ue" placeholder="janedoe@zzz.com" required>
        <label for="ue">Email</label>
      </div>
    </div>
  </div>
</body>
</html>
