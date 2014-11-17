<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event Detail Information</title>
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
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Event Detail</a>
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
				<h1 class="page-header">Event Detail</h1>
				<div class="row">
					<div class="col-xs-6 col-sm-3 placeholder">
						<c:url var="addAction" value="/event/add"></c:url>
						<div id="restaurant" class="row list-group">
							<form:form action="${addAction}" commandName="event"
								method="POST" enctype="multipart/form-data"
								onsubmit="selectAllOptions('selectedDressCodeId');">
								<table class="table table-striped table-bordered">
									<form:hidden path="eventId" />
									<tr>
										<td><input type="hidden" name="restId"
											value="${restaurant.id}"> <spring:message
												text="Name of Business" /></td>
										<td><input value="${restaurant.restaurantName}"
											disabled="disabled" /></td>
										<td><spring:message text="Address" /></td>
										<td><textarea rows="3" cols="30" disabled="disabled">${restaurant.address}</textarea>
										</td>
									</tr>
									<tr>
										<td><form:label path="eventName">
												<spring:message text="Name of Event" />
											</form:label></td>
										<td><form:textarea path="eventName" cols="30" rows="3" /></td>
										<td><form:label path="eventDescription">
												<spring:message text="Description" />
											</form:label></td>
										<td><form:textarea path="eventDescription" rows="3"
												cols="30" /></td>
									</tr>

									<tr>
										<td><form:label path="eventDate">
												<spring:message text="Event Date" />
											</form:label></td>
										<td>
											<div class="form-group">
												<div class="input-group date form_date"
													data-date-format="yyyy-MM-dd" data-link-field="eventDate"
													data-link-format="yyyy-MM-dd">
													<form:input class="form-control" size="5" path="eventDate"
														readonly="true" />
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-remove"></span></span> <span
														class="input-group-addon"><span
														class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>

										</td>

										<td><form:label path="eventTime">
												<spring:message text="Event Time" />
											</form:label></td>
										<td>
											<div class="form-group">
												<div class="input-group date form_time"
													data-date-format="hh:ii" data-link-field="eventTime"
													data-link-format="hh:ii">
													<form:input class="form-control" size="5" path="eventTime"
														readonly="true" />
													<span class="input-group-addon"><span
														class="glyphicon glyphicon-remove"></span></span> <span
														class="input-group-addon"><span
														class="glyphicon glyphicon-time"></span></span>
												</div>
											</div>
										</td>
									</tr>

									<tr>
										<td><form:label path="coverCharges">
												<spring:message text="Cover Charges" />
											</form:label></td>
										<td><form:input path="coverCharges" size="5"
												placeholder="Ks" /></td>

										<td></td>
										<td></td>
									</tr>

									<tr>
										<td><form:label path="dressCodes">
												<spring:message text="Dress Code" />
											</form:label></td>
										<td><form:select multiple="multiple" path="dressCodes"
												size="5" style="width:150">
												<c:forEach items="${dressCodes}" var="dressCode">
													<form:option value="${dressCode.dressCodeId}">${dressCode.dressCodeName}</form:option>
												</c:forEach>
											</form:select></td>
										<td align="center" valign="middle"><input type="button"
											onClick="moveOptions(this.form.dressCodes,this.form.selectedDressCodeId);"
											value="->"><br /> <input type="button"
											onClick="moveOptions(this.form.selectedDressCodeId,this.form.dressCodes);"
											value="<-"></td>
										<td><form:select multiple="multiple" size="5"
												path="selectedDressCodeId" style="width: 150">
												<c:if test="${!empty event.dressCodes}">
													<c:forEach items="${event.dressCodes}" var="dressCode">
														<form:option value="${dressCode.dressCodeId}">${dressCode.dressCodeName}</form:option>
													</c:forEach>
												</c:if>
											</form:select></td>
									</tr>

									<tr>
										<td><form:label path="eventImagePart">
												<spring:message text="Event Image" />
											</form:label></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/eventImage${event.eventId}"
											height="60px" title="Image for ${event.eventName}"> <form:input
												id="eventImagePart" type="file" path="eventImagePart"
												size="50" /></td>
										<td></td>
										<td><img id="editImg" src="#" height="60px"
											alt="your image" /></td>
									</tr>

									<tr>
										<td><form:label path="eventThumbPart">
												<spring:message text="Event Thumbnail Image" />
											</form:label></td>
										<td><img
											src="${pageContext.request.contextPath}/resources/temp/eventThumb${event.eventId}"
											height="60px" title="Thumbnail for ${event.eventName}">
											<form:input id="eventThumbPart" type="file"
												path="eventThumbPart" size="50" /></td>
										<td></td>
										<td><img id="editThumb" src="#" height="60px"
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
				$('#editImg').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#eventImagePart").change(function() {
		readURL1(this);
	});
</script>
<script type="text/javascript">
	function readURL2(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#editThumb').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}

	$("#eventThumbPart").change(function() {
		readURL2(this);
	});
</script>
</html>