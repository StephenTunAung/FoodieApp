<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Restaurant</title>
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
				<h1 class="page-header">Edit Restaurant</h1>


						<c:url var="editAction" value="/restaurant/editing"></c:url>
						<div class="row list-group">
							<form:form action="${editAction}" commandName="restaurant"
								method="POST" enctype="multipart/form-data"
								onsubmit="selectAllOptions('selectedFacilitiesId');selectAllOptions('selectedRestaurantTypeId');selectAllOptions('selectedCuisineTypeId');selectAllOptions('selectedSuitableId');">
								<table class="table table-striped table-bordered">
									<c:if test="${!empty restaurant.restaurantName}">
										<tr>
											<td><form:label path="id">
													<spring:message text="ID" />
												</form:label></td>
											<td><form:input path="id" readonly="true" size="20"
													disabled="true" /> <form:hidden path="id" /></td>
											<td></td>
											<td></td>
										</tr>
									</c:if>
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
											</form:label></td>

										<td><select multiple="multiple" id="cuisineTypes"
											name="cuisineTypes" size="10" style="width: 150">
												<c:forEach items="${cuisineTypes}" var="cuisineType">
													<option value="${cuisineType.cuisineTypeId}">${cuisineType.cuisineName}</option>
												</c:forEach>
										</select></td>
										<td align="center" valign="middle"><br /> <br /> <input
											type="button"
											onClick="moveOptions(this.form.cuisineTypes,this.form.selectedCuisineTypeId);"
											value="->"><br /> <br /> <input type="button"
											onClick="moveOptions(this.form.selectedCuisineTypeId,this.form.cuisineTypes);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedCuisineTypeId" style="width: 150">
												<c:if test="${!empty restaurant.cuisineTypes}">
													<c:forEach items="${restaurant.cuisineTypes}" var="cuisine">
														<form:option value="${cuisine.cuisineTypeId}">${cuisine.cuisineName}</form:option>
													</c:forEach>
												</c:if>
											</form:select></td>

									</tr>

									<tr>
										<td><form:label path="restaurantTypes">
												<spring:message text="Type of Restaurant" />
											</form:label></td>

										<td><select multiple="multiple" id="restaurantTypes"
											name="restaurantTypes" size="10" style="width: 150">
												<c:forEach items="${restaurantTypes}" var="restaurantType">
													<option value="${restaurantType.restaurantTypeId}">${restaurantType.restaurantTypeName}</option>
												</c:forEach>
										</select></td>
										<td align="center" valign="middle"><br /> <br /> <input
											type="button"
											onClick="moveOptions(this.form.restaurantTypes,this.form.selectedRestaurantTypeId);"
											value="->"><br /> <br /> <input type="button"
											onClick="moveOptions(this.form.selectedRestaurantTypeId,this.form.restaurantTypes);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedRestaurantTypeId" style="width: 150">
												<c:if test="${!empty restaurant.restaurantTypes}">
													<c:forEach items="${restaurant.restaurantTypes}" var="type">
														<form:option value="${type.restaurantTypeId}">${type.restaurantTypeName}</form:option>
													</c:forEach>
												</c:if>
											</form:select></td>

									</tr>

									<tr>
										<td><form:label path="paymentMethod">
												<spring:message text="Accepted Payment Method" />
											</form:label></td>
										<td><c:if test="${restaurant.paymentMethod eq '1'}">
												<form:select path="paymentMethod" cssClass="form-control">
													<form:option value="1">Both</form:option>
													<form:option value="2">Cash Only</form:option>
													<form:option value="3">Cards</form:option>
												</form:select>
											</c:if> <c:if test="${restaurant.paymentMethod eq '2'}">
												<form:select path="paymentMethod" cssClass="form-control">
													<form:option value="2">Cash Only</form:option>
													<form:option value="1">Both</form:option>
													<form:option value="3">Cards</form:option>
												</form:select>
											</c:if> <c:if test="${restaurant.paymentMethod eq '3'}">
												<form:select path="paymentMethod" cssClass="form-control">
													<form:option value="3">Cards</form:option>
													<form:option value="1">Both</form:option>
													<form:option value="2">Cash Only</form:option>
												</form:select>
											</c:if></td>
									</tr>

									<tr>
										<td><form:label path="facilities">
												<spring:message text="Other Facilities Available" />
											</form:label></td>
										<td><select multiple="multiple" id="facilities"
											name="facilities" size="10" style="width: 150">
												<c:forEach items="${facilities}" var="facility">
													<option value="${facility.otherFacilityId}">${facility.otherFacilityName}</option>
												</c:forEach>
										</select></td>
										<td align="center" valign="middle"><br />
										<br />
										<input type="button"
											onClick="moveOptions(this.form.facilities,this.form.selectedFacilitiesId);"
											value="->"><br />
										<br /> <input type="button"
											onClick="moveOptions(this.form.selectedFacilitiesId,this.form.facilities);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedFacilitiesId" style="width: 150">
												<c:if test="${!empty restaurant.facilities}">
													<c:forEach items="${restaurant.facilities}" var="fac">
														<form:option value="${fac.otherFacilityId}">${fac.otherFacilityName}</form:option>
													</c:forEach>
												</c:if>
											</form:select></td>

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

										<td colspan="3"><input type="text"
											name="restOperationHour1"
											value="${operationHour1.restOperationHours}"
											class="form-control" /></td>
									</tr>


									<tr>
										<td></td>
										<td colspan="3"><input type="text"
											name="restOperationHour2"
											value="${operationHour2.restOperationHours}"
											class="form-control" /></td>

									</tr>
									<tr>
										<td></td>
										<td colspan="3"><input type="text"
											name="restOperationHour3"
											value="${operationHour3.restOperationHours}"
											class="form-control" /></td>

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
											</form:label></td>

										<td><select multiple="multiple" id="suitables"
											name="suitables" size="10" style="width: 150">
												<c:forEach items="${suitables}" var="suitable">
													<option value="${suitable.suitableId}">${suitable.suitableName}</option>
												</c:forEach>
										</select></td>
										<td align="center" valign="middle"><br />
										<br />
										<input type="button"
											onClick="moveOptions(this.form.suitables,this.form.selectedSuitableId);"
											value="->"><br />
										<br /> <input type="button"
											onClick="moveOptions(this.form.selectedSuitableId,this.form.suitables);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="10"
												path="selectedSuitableId" style="width: 150">
												<c:if test="${!empty restaurant.suitables}">
													<c:forEach items="${restaurant.suitables}" var="suitable">
														<form:option value="${suitable.suitableId}">${suitable.suitableName}</form:option>
													</c:forEach>
												</c:if>
											</form:select></td>

									</tr>
									<tr>
										<td colspan="4"><form:label path="suitables">
												<spring:message text="Recommended Dish(es)" />
											</form:label></td>
									</tr>
									<tr>
										<td colspan="4">1.<input type="text"
											value="${recommendedDish1.recommendDishName}"
											name="recommendedDishName1" class="form-control" /></td>
									</tr>
									<tr>
										<td colspan="4">2.<input type="text"
											value="${recommendedDish2.recommendDishName}"
											name="recommendedDishName2" class="form-control" /></td>
									</tr>
									<tr>
										<td colspan="4">3.<input type="text"
											value="${recommendedDish3.recommendDishName}"
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
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/image${id}"
											height="60px" title="Image for ${restaurant.restaurantName}">
										</td>
										<td><form:input id="restaurantImage" type="file" path="restaurantImage"
												size="50" /></td>
										<td><img id="restImage" src="#" height="60px"
											alt="your image" /></td>
									</tr>

									<tr>
										<td><form:label path="restaurantThumbnail">
												<spring:message text="Restaurant Thumbnail Image" />
											</form:label></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/thumb${id}"
											height="60px"
											title="Thumbnail for ${restaurant.restaurantName}"></td>
										<td><form:input id="restaurantThumbnail" type="file" path="restaurantThumbnail"
												size="50" /></td>
										<td><img id="restThumb" src="#" height="60px"
											alt="your image" /></td>
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
										<td colspan="2"><input type="submit"
											class="btn btn-primary btn-lg"
											value="<spring:message text="Edit Restaurant"/>" /></td>
										<td colspan="2"><input type="reset" value="Clear"
											class="btn btn-primary btn-lg"></td>
									</tr>
								</table>
							</form:form>
						</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.10.2.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function readURL1(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#restImage').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#restaurantImage").change(function() {
		readURL1(this);
	});
</script>
<script type="text/javascript">
	function readURL2(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#restThumb').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#restaurantThumbnail").change(function() {
		readURL2(this);
	});
</script>
</html>