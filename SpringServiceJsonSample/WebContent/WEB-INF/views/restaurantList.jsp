<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Restaurants</title>
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
<link
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.js" /></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" /></script>
<script
	src="${pageContext.request.contextPath}/resources/js/docs.min.js" /></script>
<script
	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.10.2.js" /></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.js" /></script>
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
					<li><a
						href="${pageContext.request.contextPath}/restaurant/logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<ul class="nav nav-sidebar">
					<li><a
						href="${pageContext.request.contextPath}/restaurant/showAddRestaurant">Add
							Restaurant</a></li>
					<li><a
						href="${pageContext.request.contextPath}/restaurant/restaurants">Restaurants
							List</a></li>
					<li><a href="#">Third Menu</a></li>
					<li><a href="#">Forth Menu</a></li>
				</ul>

			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				

				<div class="row">
					<div class="col-xs-6 col-sm-10 placeholder">
					<h1 class="page-header">Search Restaurant by Name</h1>
						
						<form action="/FoodieApp/restaurant/search">
							<table>
								<tr>
									<td>Enter Restaurant Name:</td>
									<td></td>
									<td><input type="text" name="findRestaurantName"
										class="form-control" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit"
										class="btn btn-primary btn-lg" value="Search Restaurant" /></td>
										<td></td>
								</tr>
							</table>
						</form>
					</div>
					<div class="info">
						<h4>${message}</h4>
					</div>
				</div>
					<h1 class="page-header">Restaurants List</h1>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<tr class="info">
							<th>Restaurant ID</th>
							<th>Restaurant Name</th>
							<th>Township</th>
							<th>Edit Restaurant</th>
							<th>Event Detail</th>
							<th>Promotion Detail</th>
							<th>Delete</th>
						</tr>
						<c:forEach items="${listRestaurants}" var="restaurant">
							<tr>
								<td>${restaurant.id}</td>
								<td><a
									href="<c:url value='/restaurant/edit/${restaurant.id}' />">${restaurant.restaurantName}</a></td>
								<td>${restaurant.township.townshipName}</td>
								<td><a
									href="<c:url value='/restaurant/edit/${restaurant.id}' />">Edit
										Restaurant</a></td>
								<td><a
									href="<c:url value='/event/showEventDetail/${restaurant.id}' />">Event
										Detail</a></td>
								<td><a
									href="<c:url value='/promotion/showPromotionDetail/${restaurant.id}' />">Promotion
										Detail</a></td>
								<td><a
									href="<c:url value='/restaurant/remove/${restaurant.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>