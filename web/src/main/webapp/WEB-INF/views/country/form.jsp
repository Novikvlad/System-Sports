<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<h4 class="header"><mytaglib:i18n key="edit.сity"/></h4>
<div class="row">

	<form:form class="col s12" method="POST" action="${pagesCountry}"
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
				<form:select path="regionId" disabled="${readonly}">
					<form:options items="${regionsChoices}" />
				</form:select>
				<form:errors path="regionId" cssClass="red-text" />
				<label for="regionId"><mytaglib:i18n key="table.column.region"/></label>
			</div>
		</div>

		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit"><mytaglib:i18n key="views.save"/></button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesCountry}"><mytaglib:i18n key="toList"/><i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div>