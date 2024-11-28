<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
</head>
<body>
<script>
    const success = ${success};
    if (success) {
        alert("사용자 프로필이 업데이트되었습니다.");
        window.history.back();
    } else {
        alert("사용자 프로필 업데이트에 실패했습니다! 뭔가 문제가 있나봅니다!")
        window.history.back();
    }
</script>
</body>
</html>
