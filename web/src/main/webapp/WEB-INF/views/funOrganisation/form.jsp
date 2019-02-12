<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit fun organisation</h4>
<div class="row">

	<form:form class="col s12" method="POST"
		action="${pagesFunOrganisation}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />
		<div class="row">
			<div class="input-field col s12">
				<form:input path="name" type="text" disabled="${readonly}" />
				<form:errors path="name" cssClass="red-text" />
				<label for="name">название</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="clubId" disabled="${readonly}">
					<form:options items="${clubsChoices}" />
				</form:select>
				<form:errors path="clubId" cssClass="red-text" />
				<label for="clubId">club</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field col s12">
				<form:select path="cityId" disabled="${readonly}">
					<form:options items="${citiesChoices}" />
				</form:select>
				<form:errors path="cityId" cssClass="red-text" />
				<label for="cityId">city</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="deposit" type="text" disabled="${readonly}" />
				<form:errors path="deposit" cssClass="red-text" />
				<label for="deposit">deposit</label>
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
					href="${pagesFunOrganisation}">к списку<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>