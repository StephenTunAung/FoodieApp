<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Promotion Detail Information</title>
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.jpg">

<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	type="text/css" rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/dashboard.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/resources/js/move_selected.js" /></script>
</head>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Promotion Detail</a>
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
				<h1 class="page-header">Promotion Detail</h1>

				<div class="row">
					<div class="col-xs-6 col-sm-3 placeholder">

						<c:url var="addAction" value="/promotion/add"></c:url>
						<div id="restaurant" class="row list-group">
							<form:form action="${addAction}" commandName="promotion"
								method="POST" enctype="multipart/form-data"
								onsubmit="selectAllOptions('selectedDressCodeId');">
								<table class="table table-striped table-bordered">
									<form:hidden path="promotionId" />
									<tr>
										<td><input type="hidden" name="restId"
											value="${restaurant.id}"> <spring:message
												text="Name of Business" /></td>
										<td><input value="${restaurant.restaurantName}"
											disabled="disabled" /></td>

										<td><spring:message text="Address" /></td>
										<td><textarea rows="3" cols="30" disabled="disabled">${restaurant.address}</textarea></td>
									</tr>
									<tr>
										<td><form:label path="promoName">
												<spring:message text="Name of Promotion or Deal" />
											</form:label></td>
										<td><form:textarea path="promoName"
												cssClass="form-control" /></td>

										<td><form:label path="promoDescription">
												<spring:message text="Description" />
											</form:label></td>
										<td><form:textarea path="promoDescription" rows="3"
												cols="10" cssClass="form-control" /></td>
									</tr>

									<tr>
										<td><form:label path="validDate">
												<spring:message text="Valid Until" />
											</form:label></td>
										<td>
											<div class="form-group">
												<div class="input-group date form_date"
													data-date-format="yyyy-MM-dd" data-link-field="validDate"
													data-link-format="yyyy-MM-dd">
													<form:input class="form-control" size="5" path="validDate"
														readonly="true" />
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-remove"></span></span> <span
														class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>

										</td>

										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><form:label path="validDate">
												<spring:message text="Only Valid On" />
											</form:label></td>
										<td>M<c:if test="${not promotion.validOnMonday}">
												<input type="checkbox" name="validOnMonday" />
											</c:if> <c:if test="${promotion.validOnMonday}">
												<input type="checkbox" checked="checked"
													name="validOnMonday" />
											</c:if> T<c:if test="${not promotion.validOnTuesday}">
												<input type="checkbox" name="validOnTuesday" />
											</c:if> <c:if test="${promotion.validOnTuesday}">
												<input type="checkbox" checked="checked"
													name="validOnTuesday" />
											</c:if> W<c:if test="${not promotion.validOnWednesday}">
												<input type="checkbox" name="validOnWednesday" />
											</c:if> <c:if test="${promotion.validOnWednesday}">
												<input type="checkbox" checked="checked"
													name="validOnWednesday" />
											</c:if> T<c:if test="${not promotion.validOnThursday}">
												<input type="checkbox" name="validOnThursday" />
											</c:if> <c:if test="${promotion.validOnThursday}">
												<input type="checkbox" checked="checked"
													name="validOnThursday" />
											</c:if> F<c:if test="${not promotion.validOnFriday}">
												<input type="checkbox" name="validOnFriday" />
											</c:if> <c:if test="${promotion.validOnFriday}">
												<input type="checkbox" checked="checked"
													name="validOnFriday" />
											</c:if> S<c:if test="${not promotion.validOnSatursday}">
												<input type="checkbox" name="validOnSatursday" />
											</c:if> <c:if test="${promotion.validOnSatursday}">
												<input type="checkbox" checked="checked"
													name="validOnSatursday" />
											</c:if> S<c:if test="${not promotion.validOnSunday}">
												<input type="checkbox" name="validOnSunday" />
											</c:if> <c:if test="${promotion.validOnSunday}">
												<input type="checkbox" checked="checked"
													name="validOnSunday" />
											</c:if> PH<c:if test="${not promotion.validOnPH}">
												<input type="checkbox" name="validOnPH" />
											</c:if> <c:if test="${promotion.validOnPH}">
												<input type="checkbox" checked="checked" name="validOnPH" />
											</c:if> N/A<c:if test="${not promotion.validNA}">
												<input type="checkbox" name="validNA" />
											</c:if> <c:if test="${promotion.validNA}">
												<input type="checkbox" checked="checked" name="validNA" />
											</c:if>
										</td>
									</tr>


									<tr>
										<td><form:label path="promoImagePart">
												<spring:message text="Promotion Image" />
											</form:label></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/proImage${promotion.promotionId}"
											height="60px" title="Image for ${promotion.promoName}">
										<form:input id="promoImagePart" type="file" path="promoImagePart" size="50" /></td>
										<td></td>
										<td><img id="promoImage" src="#" height="60px"
											alt="your image" /></td>
									</tr>

									<tr>
										<td><form:label path="promoThumbPart">
												<spring:message text="Promotion Thumbnail Image" />
											</form:label></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/proThumb${promotion.promotionId}"
											height="60px" title="Thumbnail for ${promotion.promoName}">
										<form:input id="promoThumbPart" type="file" path="promoThumbPart" size="50" /></td>
										<td></td>
										<td><img id="promoThumb" src="#" height="60px"
											alt="your image" /></td>
									</tr>

									<tr>
										<td colspan="2"><input id="mySubmit" type="submit"
											class="btn btn-primary btn-lg" value="Submit your Event" /></td>
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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/jquery/jquery-1.10.2.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.js"
	charset="UTF-8"></script>
<script type="text/javascript">
	//this is for Date only	
	$('.form_date').datetimepicker({
		language : 'en',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	//this is for Time Only	
	$('.form_time').datetimepicker({
		language : 'en',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 1,
		minView : 0,
		maxView : 1,
		forceParse : 0
	});
</script>
<script type="text/javascript">
	function readURL1(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#promoImage').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#promoImagePart").change(function() {
		readURL1(this);
	});
</script>
<script type="text/javascript">
	function readURL2(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#promoThumb').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#promoThumbPart").change(function() {
		readURL2(this);
	});
</script>
</html>