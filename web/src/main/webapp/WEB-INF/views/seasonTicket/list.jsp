<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<h4 class="header"><mytaglib:i18n key="views.seasonTicket"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="sector"><mytaglib:i18n key="table.column.sector"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="row"><mytaglib:i18n key="table.column.row"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="seat"><mytaglib:i18n key="table.column.seat"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="price"><mytaglib:i18n key="table.column.price"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="date"><mytaglib:i18n key="table.column.date"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="presence"><mytaglib:i18n key="table.column.presence"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="seasonTicket" items="${gridItems}"varStatus="loopCounter">
			<tr>
				<td><c:out value="${seasonTicket.id}" /></td>
				<td><c:out value="${seasonTicket.name}" /></td>
				<td><c:out value="${seasonTicket.sector}" /></td>
				<td><c:out value="${seasonTicket.row}" /></td>
				<td><c:out value="${seasonTicket.seat}" /></td>
				<td><c:out value="${seasonTicket.price}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"value="${seasonTicket.date}" /></td>
				<td><c:out value="${seasonTicket.presence}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"value="${seasonTicket.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')"><td class="right"><a class="btn-floating"href="${pagesSeasonTicket}/${seasonTicket.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"href="${pagesSeasonTicket}/${seasonTicket.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"href="${pagesSeasonTicket}/${seasonTicket.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr></sec:authorize>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','moderator')"><a class="waves-effect waves-light btn right" href="${pagesSeasonTicket}/add"><i
	class="material-icons">add</i></a></sec:authorize>
