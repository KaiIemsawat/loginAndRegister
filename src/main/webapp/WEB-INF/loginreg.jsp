<!-- JSP -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- JSTL -->
<!-- c: -->
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<!-- Form/Submit workflows -->
<!-- form: -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- The&nbsp;'isErrorPage'&nbsp;attribute indicates that the current JSP can be used as the error page for another JSP. -->
<%@ page isErrorPage="true" %>
<!-- The&nbsp;**errorPage**&nbsp;attribute tells the JSP engine which page to display if there is an error while the current page runs. The value of the errorPage attribute is a relative URL. -->
<%@ page errorPage = "MyErrorPage.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login and Register</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
</head>
<body>
	<div class="container-fluid">
		<h1>Welcome!!</h1>
		<div class="row">
			<div class="col">
				<p class="fs-3">Register</p>
				<form:form action="/register" method="POST" modelAttribute="registerUser">
					<div class="row my-4">
						<form:label path="userName" class="cal-3">User Name :</form:label>
						<form:errors path="userName" class="text-danger"></form:errors>
						<form:input  path="userName"/>
					</div>
					<div class="row my-4">
						<form:label path="email" class="cal-3">Email :</form:label>
						<form:errors path="email" class="text-danger"></form:errors>
						<form:input  path="email" type="email"/>
					</div>
					<div class="row my-4">
						<form:label path="password" class="cal-3">Password :</form:label>
						<form:errors path="password" class="text-danger"></form:errors>
						<form:input  path="password" type="password"/>
					</div>
					<div class="row my-4">
						<form:label path="confirm" class="cal-3">Confirm Password :</form:label>
						<form:errors path="confirm" class="text-danger"></form:errors>
						<form:input  path="confirm" type="password"></form:input>
					</div>
					<input type="submit" value="Register" class="btn btn-primary"/>
				</form:form>
			</div>
			<div class="col">
				<p class="fs-3">Login</p>
				<form:form action="/login" method="POST" modelAttribute="logUser">
					<div class="row my-4">
						<form:label path="logEmail">Email : </form:label>
						<form:errors path="logEmail" class="col-8 text-danger"/>
						<form:input path="logEmail" type="email" />
					</div>
					<div class="row my-4">
						<form:label path="logPassword">Password : </form:label>
						<form:errors path="logPassword" class="col-8 text-danger"/>
						<form:input path="logPassword" type="password"/>
					</div>
				
					<input type="submit" value="Login" class="btn btn-primary"/>
				
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>