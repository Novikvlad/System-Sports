<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit event</h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesEvent}"
		modelAttribute="formModel">
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
				<form:select path="stadiumId" disabled="${readonly}">
					<form:options items="${stadiumsChoices}" />
				</form:select>
				<form:errors path="stadiumId" cssClass="red-text" />
				<label for="stadiumId">stadium</label>
			</div>
		</div>

		<div class="row">
			<div class="input-field  col s12">
				<form:select path="subscriptionIds" disabled="${readonly}"
					multiple="true">
					<option value=""disabled ${emptyformModel.engineIds? "selected":""}>выберите
						поддерживаемые двигатели</option>
					<form:options items="${subscriptionChoices}" />
				</form:select>
				<form:errors path="subscriptionIds" cssClass="red-text" />
				<label for="subscriptionIds" class="multiselect-label">поддерживаемые
					двигатели</label>
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
				<a class="btn waves-effect waves-light right" href="${pagesEvent}">к
					списку<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
