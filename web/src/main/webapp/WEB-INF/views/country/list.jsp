<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header"><mytaglib:i18n key="views.country"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}" column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}"column="region"><mytaglib:i18n key="table.column.region"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCountry}"column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="country" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${country.id}" /></td>
				<td><c:out value="${country.name}" /></td>
				<td><c:out value="${country.regionName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${country.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')"><td class="right"><a class="btn-floating"
					href="${pagesCountry}/${country.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesCountry}/${country.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red "
					href="${pagesCountry}/${country.id}/delete"><i
						class="material-icons">delete</i></a></td></sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','moderator')"><a class="waves-effect waves-light btn right" href="${pagesCountry}/add"><i
	class="material-icons">add</i></a></sec:authorize>
