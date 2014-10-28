<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Restaurant Registration Form</title>
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
<script
	src="${pageContext.request.contextPath}/resources/js/move_selected.js" /></script>

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
				<h1 class="page-header">Restaurant Registration Form</h1>

				<div class="row">
					<div class="col-xs-6 col-sm-3 placeholder">

						<c:url var="addAction" value="/restaurant/add"></c:url>
						<div id="restaurant" class="row list-group">
							<form:form action="${addAction}" commandName="restaurant"
								method="POST" enctype="multipart/form-data"
								onsubmit="selectAllOptions('selectedFacilitiesId');selectAllOptions('selectedRestaurantTypeId');selectAllOptions('selectedCuisineTypeId');selectAllOptions('selectedSuitableId');">
								<table class="table table-striped table-bordered">
									<tr>
										<td><form:label path="restaurantName">
												<spring:message text="Name of Business" />
											</form:label></td>
										<td><form:input path="restaurantName"
												cssClass="form-control" /></td>

										<td><form:label path="address">
												<spring:message text="Address" />
											</form:label></td>
										<td><form:textarea path="address" cssClass="form-control"
												rows="3" cols="10" /></td>
									</tr>
									<tr>
										<td><form:label path="township.townshipId">
												<spring:message text="Township Name" />
											</form:label></td>
										<td><form:select path="township.townshipId"
												cssClass="form-control">
												<c:forEach items="${townships}" var="town">
													<form:option value="${town.townshipId}">${town.townshipName}</form:option>
												</c:forEach>
											</form:select></td>
										<td><form:label path="contactNo">
												<spring:message text="Contact No" />
											</form:label></td>
										<td><form:input path="contactNo" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td><form:label path="emailAddress">
												<spring:message text="Email Address" />
											</form:label></td>
										<td><form:input path="emailAddress"
												cssClass="form-control" /></td>

										<td><form:label path="webAddress">
												<spring:message text="Web Address" />
											</form:label></td>
										<td><form:input path="webAddress" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td><form:label path="facebookAddress">
												<spring:message text="Facebook Address" />
											</form:label></td>
										<td><form:input path="facebookAddress"
												cssClass="form-control" /></td>

										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><form:label path="inCharge">
												<spring:message text="Name of Person Incharge" />
											</form:label></td>
										<td><form:input path="inCharge" cssClass="form-control" /></td>

										<td><form:label path="inChargeContactNo">
												<spring:message text="InCharge Contact No." />
											</form:label></td>
										<td><form:input path="inChargeContactNo"
												cssClass="form-control" /></td>
									</tr>
									<tr>
										<td><form:label path="designation">
												<spring:message text="Designation" />
											</form:label></td>
										<td><form:input path="designation"
												cssClass="form-control" /></td>

										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><form:label path="cuisineTypes">
												<spring:message text="Type of Cuisine" />
											</form:label> <form:select multiple="multiple" path="cuisineTypes"
												size="10" style="width:150">
												<c:forEach items="${cuisineTypes}" var="cuisineType">
													<form:option value="${cuisineType.cuisineTypeId}">${cuisineType.cuisineName}</form:option>
												</c:forEach>
											</form:select></td>
										<td align="center" valign="middle"><input type="button"
											onClick="moveOptions(this.form.cuisineTypes,this.form.selectedCuisineTypeId);"
											value="->"><br /> <input type="button"
											onClick="moveOptions(this.form.selectedCuisineTypeId,this.form.cuisineTypes);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedCuisineTypeId" style="width: 150">
											</form:select></td>
										<td></td>
									</tr>

									<tr>
										<td><form:label path="restaurantTypes">
												<spring:message text="Type of Restaurant" />
											</form:label> <form:select multiple="multiple" path="restaurantTypes"
												size="10" style="width:150">
												<c:forEach items="${restaurantTypes}" var="restaurantType">
													<form:option value="${restaurantType.restaurantTypeId}">${restaurantType.restaurantTypeName}</form:option>
												</c:forEach>
											</form:select></td>
										<td align="center" valign="middle"><input type="button"
											onClick="moveOptions(this.form.restaurantTypes,this.form.selectedRestaurantTypeId);"
											value="->"><br /> <input type="button"
											onClick="moveOptions(this.form.selectedRestaurantTypeId,this.form.restaurantTypes);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedRestaurantTypeId" style="width: 150">
											</form:select></td>
										<td></td>
									</tr>

									<tr>
										<td><form:label path="paymentMethod">
												<spring:message text="Accepted Payment Method" />
											</form:label></td>
										<td><form:select path="paymentMethod"
												cssClass="form-control">
												<form:option value="1">Both</form:option>
												<form:option value="2">Cash Only</form:option>
												<form:option value="3">Cards</form:option>

											</form:select></td>
									</tr>

									<tr>
										<td><form:label path="facilities">
												<spring:message text="Other Facilities Available" />
											</form:label> <form:select multiple="multiple" path="facilities" size="10"
												style="width:150">
												<c:forEach items="${facilities}" var="facility">
													<form:option value="${facility.otherFacilityId}">${facility.otherFacilityName}</form:option>
												</c:forEach>
											</form:select></td>
										<td align="center" valign="middle"><input type="button"
											onClick="moveOptions(this.form.facilities,this.form.selectedFacilitiesId);"
											value="->"><br /> <input type="button"
											onClick="moveOptions(this.form.selectedFacilitiesId,this.form.facilities);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedFacilitiesId" style="width: 150">
											</form:select></td>
										<td></td>
									</tr>
									<tr>
										<td><form:label path="fromPriceRange">
												<spring:message text="Price Range Per Head" />
											</form:label></td>
										<td><form:input path="fromPriceRange"
												cssClass="form-control" placeholder="Ks" /></td>

										<td><form:label path="toPriceRange">
												<spring:message text="To" />
											</form:label></td>
										<td><form:input path="toPriceRange"
												cssClass="form-control" placeholder="Ks" /></td>
									</tr>

									<tr>
										<td><form:label path="fromPriceRange">
												<spring:message text="Operation Hours" />
											</form:label></td>
										<td><input type="text" name="fromOpHour1" /></td>
										<td>to</td>
										<td><input type="text" name="toOpHour1" /> M<input
											type="checkbox" name="monday1" /> T<input type="checkbox"
											name="tuesday1" /> W<input type="checkbox" name="wednesday1" />
											T<input type="checkbox" name="thursday1" /> F<input
											type="checkbox" name="friday1" /> S<input type="checkbox"
											name="satursday1" /> S<input type="checkbox" name="sunday1" />
										</td>

									</tr>
									<tr>
										<td></td>
										<td><input type="text" name="fromOpHour2" /></td>
										<td>to</td>
										<td><input type="text" name="toOpHour2" /> M<input
											type="checkbox" name="monday2" /> T<input type="checkbox"
											name="tuesday2" /> W<input type="checkbox" name="wednesday2" />
											T<input type="checkbox" name="thursday2" /> F<input
											type="checkbox" name="friday2" /> S<input type="checkbox"
											name="satursday2" /> S<input type="checkbox" name="sunday2" /></td>

									</tr>
									<tr>
										<td></td>
										<td><input type="text" name="fromOpHour3" /></td>
										<td>to</td>
										<td><input type="text" name="toOpHour3" /> M<input
											type="checkbox" name="monday3" /> T<input type="checkbox"
											name="tuesday3" /> W<input type="checkbox" name="wednesday3" />
											T<input type="checkbox" name="thursday3" /> F<input
											type="checkbox" name="friday3" /> S<input type="checkbox"
											name="satursday3" /> S<input type="checkbox" name="sunday3" /></td>

									</tr>
									<tr>
										<td><form:label path="closedOnModay">
												<spring:message text="Closed On" />
											</form:label></td>
										<td colspan="3">M<form:checkbox path="closedOnModay" />
											T<form:checkbox path="closedOnTuesday" /> W<form:checkbox
												path="closedOnWednesday" /> T<form:checkbox
												path="closedOnThursday" /> F<form:checkbox
												path="closedOnFriday" /> S<form:checkbox
												path="closedOnSatursday" /> S<form:checkbox
												path="closedOnSunday" /> PH<form:checkbox path="closedOnPH" /></td>
									</tr>
									<tr>
										<td><form:label path="noOfSeats">
												<spring:message text="No of Seats Available" />
											</form:label></td>
										<td><form:input path="noOfSeats" cssClass="form-control" /></td>

										<td><form:label path="alcoholicAvailable">
												<spring:message text="Alcoholic Drinks Available" />
											</form:label></td>
										<td><form:checkbox path="alcoholicAvailable" />Checked:
											Yes</td>
									</tr>

									<tr>
										<td><form:label path="suitables">
												<spring:message text="Suitable For" />
											</form:label> <form:select multiple="multiple" path="suitables" size="10"
												style="width:150">
												<c:forEach items="${suitables}" var="suitable">
													<form:option value="${suitable.suitableId}">${suitable.suitableName}</form:option>
												</c:forEach>
											</form:select></td>
										<td align="center" valign="middle"><input type="button"
											onClick="moveOptions(this.form.suitables,this.form.selectedSuitableId);"
											value="->"><br /> <input type="button"
											onClick="moveOptions(this.form.selectedSuitableId,this.form.suitables);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedSuitableId" style="width: 150">
											</form:select></td>
										<td></td>
									</tr>

									<tr>
										<td colspan="4"><form:label path="suitables">
												<spring:message text="Recommended Dish(es)" />
											</form:label></td>
									</tr>
									<tr>
										<td colspan="4">1.<input type="text"
											name="recommendedDishName1" class="form-control" /></td>
									</tr>
									<tr>
										<td colspan="4">2.<input type="text"
											name="recommendedDishName2" class="form-control" /></td>
									</tr>
									<tr>
										<td colspan="4">3.<input type="text"
											name="recommendedDishName3" class="form-control" /></td>
									</tr>
									<tr>
										<td colspan="4"><form:label path="overview">
												<spring:message text="Restaurant Overview" />
											</form:label> <form:textarea path="overview" cssClass="form-control"
												rows="20" cols="50" /></td>
									</tr>
									<tr>
										<td><form:label path="restaurantImage">
												<spring:message text="Restaurant Image" />
											</form:label></td>
										<td></td>
										<td><form:input type="file" path="restaurantImage"
												size="50" /></td>
										<td></td>
									</tr>

									<tr>
										<td><form:label path="restaurantThumbnail">
												<spring:message text="Restaurant Thumbnail Image" />
											</form:label></td>
										<td></td>
										<td><form:input type="file" path="restaurantThumbnail"
												size="50" /></td>
										<td></td>
									</tr>
									<tr>
										<td colspan="4"><form:label path="longitude">
												<spring:message text="Restaurant Location" />
											</form:label></td>
									</tr>
									<tr>
										<td><form:label path="longitude">
												<spring:message text="Longitude" />
											</form:label></td>
										<td><form:input path="longitude" cssClass="form-control" /></td>
										<td><form:label path="lattitude">
												<spring:message text="Lattitude" />
											</form:label></td>
										<td><form:input path="lattitude" cssClass="form-control" /></td>
									</tr>
									<tr>
										<td colspan="2"><input id="mySubmit" type="submit"
											class="btn btn-primary btn-lg" value="Add Restaurant" /></td>
										<td colspan="2"><input type="reset" value="Clear"
											class="btn btn-primary btn-lg"></td>
									</tr>
								</table>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>