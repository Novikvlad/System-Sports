<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header"><mytaglib:i18n key="views.club"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesClub}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesClub}" column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesClub}" column="based"><mytaglib:i18n key="table.column.based"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesClub}" column="stadium"><mytaglib:i18n key="table.column.stadium"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesClub}" column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="club" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${club.id}" /></td>
				<td><c:out value="${club.name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${club.based}" /></td>
				<td><c:out value="${club.stadiumName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"value="${club.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')"><td class="right"><a class="btn-floating"
					href="${pagesClub}/${club.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesClub}/${club.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesClub}/${club.id}/delete"><i class="material-icons">delete</i></a></td></sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','moderator')"><a class="waves-effect waves-light btn right" href="${pagesClub}/add"><i
	class="material-icons">add</i></a></sec:authorize>
