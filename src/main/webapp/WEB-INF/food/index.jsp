<%--
  Created by IntelliJ IDEA.
  User: kdt
  Date: 2024-11-25
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="../include/style.jsp"/>
  <title>Foods</title>
</head>
<body data-bs-theme="dark">
<style>
    #the-modal-dialog {
        border-radius: 8px;
        width: max(45vw, 300px);
    }
    tbody tr {
        cursor: pointer;
    }
    .use-pre-wrap {
        white-space: pre-wrap;
    }
    tbody button.btn {
        padding: 0;
        width: 100%;
        height: 100%;
        /*padding: 0.2rem 0.5rem;*/
    }
    tbody td:has(button.btn, input[type=checkbox]) {
        padding: 0;
        align-content: center;
    }
    tbody td input[type=checkbox] {
        width: 100%;
        height: 100%;
    }
</style>
<dialog id="the-modal-dialog">
    <form method="dialog" action="#" id="my-fucking-form">
      <input id="food-id" type="hidden" name="id"/>
      <div class="row mb-3">
        <label for="food-name" class="col-sm-4 col-form-label">Food Name:</label>
        <div class="width-100">
          <input id="food-name" class="form-control" type="text" name="name"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="food-price" class="col-sm-4 col-form-label">Price:</label>
        <div class="width-100">
          <input id="food-price" class="form-control" type="number" name="price"/>
        </div>
      </div>
      <div class="row mb-3">
        <label for="food-desc" class="col-sm-4 col-form-label">Description:</label>
        <div class="width-100">
          <textarea id="food-desc" class="form-control" name="description" rows="6"></textarea>
        </div>
      </div>
      <button class="btn btn-primary" id="submit-button">Submit</button>
      <button class="btn btn-danger" id="cancel-button">Cancel</button>
    </form>
</dialog>
<div class="jumbotron text-light text-center py-5">
  <h1 class="display-1">Foods</h1>
</div>
<div class="container">
  <table id="main-table" class="table table-hover">
    <thead class="text-center">
    <tr>
      <th>이름</th>
      <th>가격(원)</th>
      <th>설명</th>
      <th>판매 여부</th>
      <th>삭제</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="food" items="${foods}">
      <tr data-id="${food.id}">
        <td>${food.name}</td>
        <td class="text-end">${food.getPriceView()}</td>
        <td class="use-pre-wrap">${food.description}</td>
        <td><input class="status-checkbox" type="checkbox" ${(food.getStatus() > 0) ? "checked" : ""} ></td>
        <td><button class="btn btn-danger">삭제</button></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <button type="button" class="btn btn-primary" onclick="openDialogForNew()">New</button>
</div>
<script src="${pageContext.request.contextPath}/script/food.js"></script>
</body>
</html>
