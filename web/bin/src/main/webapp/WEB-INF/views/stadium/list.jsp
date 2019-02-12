<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">Stadium</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}"
					column="capacity">capacity</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}"
					column="address">address</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="based">based</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}" column="city">city</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}"
					column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesStadium}"
					column="updated">updated</mytaglib:sort-link></th>
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
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${stadium.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesStadium}/${stadium.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesStadium}/${stadium.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesStadium}/${stadium.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesStadium}/add"><i
	class="material-icons">add</i></a>
