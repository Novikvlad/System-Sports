<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header">
	<mytaglib:i18n key="views.stadium" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="id"><mytaglib:i18n key="table.column.id" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="name"><mytaglib:i18n key="table.column.name" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="capacity"><mytaglib:i18n key="table.column.capacity" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="address"><mytaglib:i18n key="table.column.address" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="based"><mytaglib:i18n key="table.column.based" />	</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="city"><mytaglib:i18n key="table.column.city" /></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="created"><mytaglib:i18n key="table.column.created" /></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="stadium" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${stadium.id}" /></td>
				<td><c:out value="${stadium.name}" /></td>
				<td><c:out value="${stadium.capacity}" /></td>
				<td><c:out value="${stadium.address}" /></td>
				<td><fmt:formatDate value="${stadium.based}" /></td>
				<td><c:out value="${stadium.cityName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${stadium.created}" /></td>
				<sec:authorize access="hasRole('admin')"><td class="right"><a class="btn-floating"
					href="${pagesStadium}/${stadium.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesStadium}/${stadium.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesStadium}/${stadium.id}/delete"><i
						class="material-icons">delete</i></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
	<tt>
		<a class="waves-effect waves-light btn right"href="${pagesStadium}/add"><i class="material-icons">add</i></a>
	</tt>
</sec:authorize>
