<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.seasonTicket"/></h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesSeasonTicket}"
		modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
			<div class="row">
			<div class="input-field col s12">
				<form:input path="sector" type="text" disabled="${readonly}" />
				<form:errors path="sector" cssClass="red-text" />
				<label for="sector"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="row" type="text" disabled="${readonly}" />
				<form:errors path="row" cssClass="red-text" />
				<label for="row"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="seat" type="text" disabled="${readonly}" />
				<form:errors path="seat" cssClass="red-text" />
				<label for="seat"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="price" type="typenumber" disabled="${readonly}" />
				<form:errors path="price" cssClass="red-text" />
				<label for="price"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="presence" disabled="${readonly}">
					<form:options items="${presenceChoices}" />
				</form:select>
				<form:errors path="presence" cssClass="red-text" />
				<label for="presence"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s6">
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date"><mytaglib:i18n key="table.column.event"/></label>
			</div>
		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">сохранить</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right"
					href="${pagesSeasonTicket}"><mytaglib:i18n key="table.column.event"/><i
					class="material-icons right"></i>
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

