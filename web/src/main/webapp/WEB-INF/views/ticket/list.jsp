<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header"><mytaglib:i18n key="views.ticket"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="sector"><mytaglib:i18n key="table.column.sector"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="row"><mytaglib:i18n key="table.column.row"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="seat"><mytaglib:i18n key="table.column.seat"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="price"><mytaglib:i18n key="table.column.price"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="presence"><mytaglib:i18n key="table.column.presence"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="event"><mytaglib:i18n key="table.column.event"/></mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesTicket}" column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="ticket" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${ticket.id}" /></td>
				<td><c:out value="${ticket.name}" /></td>
				<td><c:out value="${ticket.sector}" /></td>
				<td><c:out value="${ticket.row}" /></td>
				<td><c:out value="${ticket.seat}" /></td>
				<td><c:out value="${ticket.price}" /></td>
				<td><c:out value="${ticket.presence}" /></td>
				<td><a href="${pagesEvent}/${ticket.eventId}" > ${ticket.eventName}" </a></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"value="${ticket.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')"><td class="right"><a class="btn-floating"href="${pagesTicket}/${ticket.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesTicket}/${ticket.id}/edit"><i class="material-icons">edit</i>
					</a> <a class="btn-floating red"href="${pagesTicket}/${ticket.id}/delete"><i class="material-icons">delete</i></a></td></sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','moderator')"><a class="waves-effect waves-light btn right" href="${pagesTicket}/add"><i
	class="material-icons">add</i></a></sec:authorize>
