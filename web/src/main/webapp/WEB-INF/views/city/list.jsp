<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<h4 class="header">
	<mytaglib:i18n key="views.city" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="name">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="country">
					<mytaglib:i18n key="table.column.country" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<sec:authorize access="hasAnyRole('admin','moderator')">
				<th><mytaglib:sort-link pageUrl="${pagesCity}" column="updated">
						<mytaglib:i18n key="table.column.updated" />
					</mytaglib:sort-link></th>
			</sec:authorize>
		</tr>
		<c:forEach var="city" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${city.id}" /></td>
				<td><c:out value="${city.name}" /></td>
				<td><c:out value="${city.countryName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${city.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')">
					<td><fmt:formatDate pattern="yyyy-MM-dd"
							value="${city.updated}" /></td>
					<td class="right"><a class="btn-floating"
						href="${pagesCity}/${city.id}"><i class="material-icons">info</i></a>
						<a class="btn-floating" href="${pagesCity}/${city.id}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red "
						href="${pagesCity}/${city.id}/delete"><i
							class="material-icons">delete</i></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('ROLE_admin')">
	<a class="waves-effect waves-light btn right" href="${pagesCity}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
