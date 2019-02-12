<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.ticket"/></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesTicket}"
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
			<div class="input-field col s12">
				<form:input path="sector" type="text" disabled="${readonly}" />
				<form:errors path="sector" cssClass="red-text" />
				<label for="sector"><mytaglib:i18n key="table.column.sector"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="row" type="text" disabled="${readonly}" />
				<form:errors path="row" cssClass="red-text" />
				<label for="row"><mytaglib:i18n key="table.column.row"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="seat" type="text" disabled="${readonly}" />
				<form:errors path="seat" cssClass="red-text" />
				<label for="seat"><mytaglib:i18n key="table.column.seat"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="price" type="typenumber" disabled="${readonly}" />
				<form:errors path="price" cssClass="red-text" />
				<label for="price"><mytaglib:i18n key="table.column.price"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="presence" disabled="${readonly}">
					<form:options items="${presenceChoices}" />
				</form:select>
				<form:errors path="presence" cssClass="red-text" />
				<label for="presence"><mytaglib:i18n key="table.column.presence"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="eventId" disabled="${readonly}">
					<form:options items="${eventsChoices}" />
				</form:select>
				<form:errors path="eventId" cssClass="red-text" />
				<label for="eventId"><mytaglib:i18n key="table.column.event"/></label>
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
				<a class="btn waves-effect waves-light right" href="${pagesTicket}"><mytaglib:i18n key="toList"/>ƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>


<c:if test='${param["showAlerts"]}'>
	<!-- checks the URL parameter -->


	<script src="${contextPath}/resources/js/sample-alert-with-params.js"></script>
	<script>
		showMessage('${contextPath}'); // execute function defined somewhere above
	</script>

</c:if>

