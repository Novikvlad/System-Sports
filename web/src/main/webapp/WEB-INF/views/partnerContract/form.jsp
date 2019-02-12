<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.partnerContract"/></h4>
<div class="row">

	<form:form class="col s12" method="POST"
		action="${pagesPartnerContract}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="clubId" disabled="${readonly}">
					<form:options items="${clubsChoices}" />
				</form:select>
				<form:errors path="clubId" cssClass="red-text" />
				<label for="clubId"><mytaglib:i18n key="table.column.club"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:select path="partnerId" disabled="${readonly}">
					<form:options items="${partnersChoices}" />
				</form:select>
				<form:errors path="partnerId" cssClass="red-text" />
				<label for="partnerId"><mytaglib:i18n key="table.column.partner"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="dateSigning" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="dateSigning" cssClass="red-text" />
				<label for="dateSigning"><mytaglib:i18n key="table.column.dateSigning"/></label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="contractTerm" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="contractTerm" cssClass="red-text" />
				<label for="contractTerm"><mytaglib:i18n key="table.column.contractTerm"/>ƒ</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<form:input path="contractValue" type="text" disabled="${readonly}" />
				<form:errors path="contractValue" cssClass="red-text" />
				<label for="contractValue"><mytaglib:i18n key="table.column.contractValue"/>ƒ</label>
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
				<a class="btn waves-effect waves-light right"
					href="${pagesPartnerContract}"><mytaglib:i18n key="toList"/>ƒ<i
					class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>