<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.club"/></h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesClub}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="table.column.name"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<form:input path="based" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="based" cssClass="red-text" />
				<label for="based"><mytaglib:i18n key="table.column.based"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="stadiumId" disabled="${readonly}">
					<form:options items="${stadiumsChoices}" />
				</form:select>
				<form:errors path="stadiumId" cssClass="red-text" />
				<label for="stadiumId"><mytaglib:i18n key="table.column.stadium"/></label>
			</div>
		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="views.save"/></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesClub}"><mytaglib:i18n key="toList"/><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
<div id="piechart"></div>

<script type="text/javascript">
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
var arrWirthHeader = ${jsData};
var data = google.visualization.arrayToDataTable( arrWirthHeader );

var options = {
title: 'Контракты'
};

var chart = new google.visualization.PieChart(document.getElementById('piechart'));

chart.draw(data, options);
}
</script>
