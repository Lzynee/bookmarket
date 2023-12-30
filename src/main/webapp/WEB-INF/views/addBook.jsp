<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 도서 등록 화면 --%>
<html>
<head>
  <link href="<c:url value="/resources/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title>도서 등록</title>
</head>
<body>
<nav class="navbar navbar-expand  navbar-dark bg-dark">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="./home">Home</a>
    </div>
  </div>
</nav>
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">
      <spring:message code="addBook.form.title.label"/>
    </h1>
  </div>
</div>

<div class="container">
  <div class="float-right">
    <%-- 로그아웃을 위한 요청 경로 설정 --%>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
      <input type="submit" class="btn btn-sm btn-success" value="Logout">
    </form:form>
  </div>
  <br><br>
  <form:form modelAttribute="NewBook"
             action="./add?${_csrf.parameterName}=${_csrf.token}"
             cssClass="form-horizontal"
             enctype="multipart/form-data">
    <fieldset>
      <%-- 유효성 검사 추가 --%>
      <legend><spring:message code="addBook.form.subtitle.label"/></legend>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <%-- 오류 메시지 출력 --%>
            <spring:message code="addBook.form.bookId.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="bookId" cssClass="form-control"/>
        </div>
        <div class="col-sm-6">
          <form:errors path="bookId" cssClass="text-danger" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.name.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="name" cssClass="form-control"/>
        </div>
        <div class="col-sm-6">
          <form:errors path="name" cssClass="text-danger" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.unitPrice.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="unitPrice" cssClass="form-control"/>
        </div>
        <div class="col-sm-6">
          <form:errors path="unitPrice" cssClass="text-danger" />
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.author.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="author" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.description.label"/>
        </label>
        <div class="col-sm-3">
          <form:textarea path="description" cols="50" rows="2"
                         cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.publisher.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="publisher" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.category.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="category" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.unitsInStock.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="unitsInStock" cssClass="form-control"/>
        </div>
        <div class="col-sm-6">
          <form:errors path="unitsInStock" cssClass="text-danger"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.releaseDate.label"/>
        </label>
        <div class="col-sm-3">
          <form:input path="releaseDate" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.condition.label"/>
        </label>
        <div class="col-sm-3">
          <form:radiobutton path="condition" value="New"/>New
          <form:radiobutton path="condition" value="Old"/>Old
          <form:radiobutton path="condition" value="E-Book"/>E-Book
        </div>
      </div>
      <div class="form-group row">
        <label class="col-sm-2 custom-control-label">
          <spring:message code="addBook.form.bookImage.label"/>
        </label>
        <div class="col-sm-7">
          <form:input type="file" path="bookImage" cssClass="form-control"/>
        </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
          <input type="submit" class="btn btn-primary"
                 value="<spring:message code="addBook.form.button.label"/>">
        </div>
      </div>
    </fieldset>
  </form:form>
  <hr>
  <footer>
    <p>&copy; BookMarket</p>
  </footer>
</div>
</body>
</html>
