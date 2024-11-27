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
  <title>Signup</title>
</head>
<body data-bs-theme="dark" class="d-flex align-items-center py-4 bg-body-tertiary">
<main class="w-100 m-auto" style="max-width: 350px;">

  <form>
    <h1 class="h3 mb-3 fw-normal text-center">Signup</h1>

    <div class="form-floating mt-1">
      <input type="text" name="userid" class="form-control" id="ui" placeholder="&lt;Your ID&gt;" required>
      <label for="ui">User ID</label>
    </div>
    <div class="form-floating">
      <input type="password" name="password" class="form-control" id="pw" placeholder="Password" required>
      <label for="pw">Password</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="confirm" placeholder="Password confirm" required>
      <label for="confirm">Password Confirm</label>
    </div>
    <div class="form-floating">
      <input type="text" name="username" class="form-control" id="uname" placeholder="Public name" required>
      <label for="uname">Public name</label>
    </div>
    <div class="form-floating mb-1">
      <input type="email" name="email" class="form-control" id="ue" placeholder="janedoe@zzz.com" required>
      <label for="ue">Email</label>
    </div>


    <button class="btn btn-primary w-100 py-2" type="submit">Signup</button>
    <div class="text-center mt-3">already have account?</div>
    <a class="btn btn-outline-light w-100 py-2 mt-1" href="${pageContext.request.contextPath}/login">
      Back to Login
    </a>
  </form>
</main>
<script>
    const form = document.querySelector('form');
    async function doSignup() {
        try {
            const body = new URLSearchParams(new FormData(form))
            const response = await fetch('${pageContext.request.contextPath}/api/signup', {
                method: 'POST',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                body
            })
            const json = await response.json()
            if (response.status === 400) {
                window.alert(json.error);
                return
            } else if (!response.ok) throw new Error(response.status + " " + response.statusText)
            window.alert("가입이 완료되었습니다! 환영합니다!")
            window.location.replace("/login");
        } catch (error) {
            window.alert("이런!! 오류가 발생했습니다.")
            console.error(error)
        }
    }
    const pw = document.getElementById('pw');
    const confirm = document.getElementById('confirm');
    form.addEventListener('submit', e => {
        e.preventDefault();
        if (pw.value !== confirm.value) {
            return window.alert("입력한 패스워드가 일치하지 않습니다.")
        }
        doSignup()
        return false;
    })
</script>
</body>
</html>
