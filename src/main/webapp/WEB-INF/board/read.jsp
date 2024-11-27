<%--@elvariable id="board" type="dev.nemi.tomscott.board.dto.BoardViewDTO"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp"/>
  <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
  <title>${board.getTitle()} - Board</title>
</head>
<body data-bs-theme="dark">
<div class="container py-4">
  <main>
    <div class="jumbotron bg-dark text-light text-center py-5">
      <h1 class="display-1">${board.getTitle()}</h1>
    </div>
    <div id="board-md" class="p-3 rounded"></div>
  </main>
  <nav class="mt-4">
    <div class="d-flex justify-content-between">
      <a href="${pageContext.request.contextPath}/board" class="btn btn-secondary">Board List</a>
      <a href="${pageContext.request.contextPath}/board/edit/${board.getId()}" class="btn btn-warning">Edit</a>
      <form action="${pageContext.request.contextPath}/board/delete/${board.getId()}" method="post" class="mb-0">
        <input type="submit" class="btn btn-danger" value="Delete">
      </form>
    </div>
  </nav>
</div>
<script id="content" type="text/template"><c:out value="${board.getContent()}" /></script>
<script>
    var board = document.getElementById("board-md");
    board.innerHTML = marked.parse(document.getElementById("content").innerHTML);
</script>
</body>
</html>
