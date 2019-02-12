<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>


<h4 class="header">Events</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="date">date</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="stadium">stadium</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="event" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd" value="${event.date}" /></td>
				<td><c:out value="${event.stadiumName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesEvent}/${event.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesEvent}/${event.id}/edit"><i
						class="material-icons">edit</i></a> <a
					class="btn-floating red disabled"
					href="${pagesEvent}/${event.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesEvent}/add"><i
	class="material-icons">add</i></a>
