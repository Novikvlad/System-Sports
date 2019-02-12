<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.userAccount"/></h4>
<div class="row">
    <form:form class="col s12" method="POST" action="${pagesUserAccount}" modelAttribute="formModel">
        <form:input path="id" type="hidden" />
        <div class="row">
            <div class="input-field col s12">
                <form:input path="name" type="text" disabled="${readonly}" />
                <form:errors path="name" cssClass="red-text" />
                <label for="name"><mytaglib:i18n key="name"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="email" type="text" disabled="${readonly}" />
                <form:errors path="email" cssClass="red-text" />
                <label for="email"><mytaglib:i18n key="table.column.email"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="password" type="text" disabled="${readonly}" />
                <form:errors path="password" cssClass="red-text" />
                <label for="password"><mytaglib:i18n key="table.column.password"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:select path="countryId" disabled="${readonly}">
                    <form:options items="${countriesChoices}" />
                </form:select>
                <form:errors path="countryId" cssClass="red-text" />
                <label for="countryId"><mytaglib:i18n key="table.column.country"/></label>
            </div>
        </div>
         <div class="row">
            <div class="input-field col s12">
                <form:select path="funOrganisationId" disabled="${readonly}">
                    <form:options items="${funOrganisationsChoices}" />
                </form:select>
                <form:errors path="funOrganisationId" cssClass="red-text" />
                <label for="funOrganisationId"><mytaglib:i18n key="table.column.funOrganisation"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field  col s12">
                <form:select path="clubIds" disabled="${readonly}" multiple="true">
                    <option value="" disabled ${empty formModel.clubIds? "selected":""}><mytaglib:i18n key="use.club"/></option>
                    <form:options items="${clubsChoices}" />
                </form:select>
                <form:errors path="clubIds" cssClass="red-text" />
                <label for="clubIds" class="multiselect-label"><mytaglib:i18n key="table.column.club"/></label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <form:input path="phone" type="text" disabled="${readonly}" />
                <form:errors path="phone" cssClass="red-text" />
                <label for="phone"><mytaglib:i18n key="table.column.phone"/></label>
            </div>
        </div>
        <div class="row">
			<div class="input-field col s12">
				<form:input path="birthday" type="text" disabled="${readonly}"
					cssClass="datepicker" />
				<form:errors path="birthday" cssClass="red-text" />
				<label for="birthday"><mytaglib:i18n key="table.column.birthday"/></label>
			</div>
		</div>
		<div class="row">
            <div class="input-field col s12">
                <form:select path="role" disabled="${readonly}">
                    <form:options items="${typeChoices}" />
                </form:select>
                <form:errors path="role" cssClass="red-text" />
                <label for="role"><mytaglib:i18n key="table.column.role"/></label>
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
                <a class="btn waves-effect waves-light right" href="${pagesUserAccount}"><mytaglib:i18n key="toList"/>ƒ<i class="material-icons right"></i>
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