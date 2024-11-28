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
    <form id="user-form" action="/user/update" method="POST">
      <input type="hidden" name="userid" value="${patron.userid}">
      <div class="form-floating mt-1">
        <input type="text" class="form-control" id="ui" autocomplete="off" disabled required value="${patron.userid}">
        <label for="ui">User ID</label>
      </div>
      <div class="form-floating">
        <input type="text" name="username" class="form-control" id="uname" autocomplete="off" placeholder="Public name" required value="${patron.username}">
        <label for="uname">Public name</label>
      </div>
      <div class="form-floating mb-1">
        <input type="email" name="email" class="form-control" id="ue" autocomplete="off"  placeholder="janedoe@zzz.com" required value="${patron.email}">
        <label for="ue">Email</label>
      </div>
      <div class="row py-5 gap-5">
        <input type="submit" class="col btn btn-primary" value="Apply change">
        <input type="reset" class="col btn btn-outline-primary" value="Reset to default">
      </div>
    </form>
  </div>
</body>
</html>
