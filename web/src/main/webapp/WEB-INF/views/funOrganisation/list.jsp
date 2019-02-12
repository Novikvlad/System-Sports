<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header"><mytaglib:i18n key="views.funOrganisation"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="club"><mytaglib:i18n key="table.column.club"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="city"><mytaglib:i18n key="table.column.city"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="deposit"><mytaglib:i18n key="table.column.deposit"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesFunOrganisation}"column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="funOrganisation" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${funOrganisation.id}" /></td>
				<td><c:out value="${funOrganisation.name}" /></td>
				<td><c:out value="${funOrganisation.clubName}" /></td>
				<td><c:out value="${funOrganisation.cityName}" /></td>
				<td><c:out value="${funOrganisation.deposit}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${funOrganisation.created}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesFunOrganisation}/${funOrganisation.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesFunOrganisation}/${funOrganisation.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesFunOrganisation}/${funOrganisation.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesFunOrganisation}/add"><i
	class="material-icons">add</i></a>
