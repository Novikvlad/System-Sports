<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header"><mytaglib:i18n key="edit.event"/></h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesEvent}/save"
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
				<form:input path="date" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="date" cssClass="red-text" />
				<label for="date"><mytaglib:i18n key="table.column.date"/></label>
			</div>
			<div class="input-field col s6">
				<form:input path="time" type="text" disabled="${readonly}" cssClass="timepicker" />
				<form:errors path="time" cssClass="red-text" />
				<label for="time"><mytaglib:i18n key="table.column.time"/></label>
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
<sec:authorize access="hasAnyRole('admin','moderator')">
		<div class="row">
			<div class="input-field  col s12">
				<form:select path="seasonTicketIds" disabled="${readonly}"
					multiple="true">
					<option value=""disabled ${emptyformModel.seasonTicketIds? "selected":""}>Season Ticket</option>
					<form:options items="${seasonTicketsChoices}" />
				</form:select>
				<form:errors path="seasonTicketIds" cssClass="red-text" />
				<label for="seasonTicketIds" class="multiselect-label"><mytaglib:i18n key="views.seasonTicket"/></label>
			</div>
		</div>
</sec:authorize>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="views.save"/>Œ</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesEvent}"><mytaglib:i18n key="toList"/><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>
