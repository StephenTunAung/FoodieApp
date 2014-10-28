<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Login here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Restaurant</title>
<%@ page isELIgnored="false"%>
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.jpg">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"
	type="text/css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/dashboard.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js" /></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" /></script>

</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Restaurant</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">About Us</a></li>
				</ul>
			</div>
		</div>
	</div>
	<hr>
	<c:url var="addAction" value="/restaurant/login"></c:url>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Please Login</h3>
					</div>
					<div class="panel-body">
						<div class="info">
							<h4>${message}</h4>
						</div>
						<form:form action="${addAction}" commandName="adminUser"
							method="post">

							<div class="form-group">
								<form:label path="userName">
									<spring:message text="Name" />
								</form:label>
								<form:input path="userName" cssClass="form-control" />
							</div>
							<div class="form-group">
								<form:label path="password">
									<spring:message text="Password" />
								</form:label>
								<form:password path="password" cssClass="form-control" rows="3"
									cols="10" />
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-primary btn-lg"
									value="Login" />&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset"
									value="Clear" class="btn btn-primary btn-lg">
							</div>

						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>