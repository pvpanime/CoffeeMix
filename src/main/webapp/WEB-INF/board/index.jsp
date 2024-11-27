<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp" />
  <title>Board</title>
</head>
<body data-bs-theme="dark">
<div class="container py-4">
  <main>
    <div class="jumbotron bg-dark text-light text-center py-5">
      <h1 class="display-1">Boards</h1>
    </div>
    <div class="list-group">
      <c:forEach var="board" items="${list}">
        <a href="/board/read/${board.getId()}" class="list-group-item list-group-item-action text-body text-decoration-none">
          <div class="d-flex justify-content-between">
            <span class="flex-grow-1">${board.getTitle()}</span>
            <span class="text-body-secondary">${board.getCreatedAt()}</span>
          </div>
        </a>
      </c:forEach>
    </div>
      <nav class="mt-4" aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <c:if test="${currentPage > 1}">
            <li class="page-item">
              <a class="page-link" href="/board/page/${currentPage - 1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
              </a>
            </li>
          </c:if>
          <c:forEach begin="1" end="${maxPage}" var="i">
            <c:choose>
              <c:when test="${currentPage != i}">
                <li class="page-item"><a class="page-link" href="/board/page/${i}">${i}</a></li>
              </c:when>
              <c:otherwise>
                <li class="page-item"><span class="page-link disabled">${i}</span></li>
              </c:otherwise>
            </c:choose>
          </c:forEach>
          <c:if test="${currentPage < maxPage}">
            <li class="page-item">
              <a class="page-link" href="/board/page/${currentPage + 1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
              </a>
            </li>
          </c:if>
        </ul>
      </nav>
    <div class="mt-4 text-center">
      <a href="${pageContext.request.contextPath}/board/write">
        <button type="button" class="btn btn-primary">Write</button>
      </a>
    </div>
  </main>
</div>

</body>
</html>