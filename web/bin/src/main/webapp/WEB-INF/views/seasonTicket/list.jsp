<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">Season Ticket</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="sector">sector</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="row">row</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="seat">seat</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="price">price</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="presence">presence</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesSeasonTicket}"
					column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="seasonTicket" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${seasonTicket.id}" /></td>
				<td><c:out value="${seasonTicket.name}" /></td>
				<td><c:out value="${seasonTicket.sector}" /></td>
				<td><c:out value="${seasonTicket.row}" /></td>
				<td><c:out value="${seasonTicket.seat}" /></td>
				<td><c:out value="${seasonTicket.price}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${seasonTicket.date}" /></td>
				<td><c:out value="${seasonTicket.presence}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${seasonTicket.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${seasonTicket.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesSeasonTicket}/${seasonTicket.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesSeasonTicket}/${seasonTicket.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesSeasonTicket}/${seasonTicket.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesSeasonTicket}/add"><i
	class="material-icons">add</i></a>
