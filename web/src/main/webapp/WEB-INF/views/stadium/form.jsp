<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.stadium"/></h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesStadium}"
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
				<form:input path="capacity" type="text" disabled="${readonly}" />
				<form:errors path="capacity" cssClass="red-text" />
				<label for="capacity"><mytaglib:i18n key="table.column.capacity"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="address" type="text" disabled="${readonly}" />
				<form:errors path="address" cssClass="red-text" />
				<label for="address"><mytaglib:i18n key="table.column.address"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="based" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="based" cssClass="red-text" />
				<label for="based"><mytaglib:i18n key="table.column.based"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="cityId" disabled="${readonly}">
					<form:options items="${citiesChoices}" />
				</form:select>
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId"><mytaglib:i18n key="table.column.city"/></label>
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
				<a class="btn waves-effect waves-light right" href="${pagesStadium}"><mytaglib:i18n key="toList"/>ƒ<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>