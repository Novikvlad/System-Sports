<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header">City</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="id">id</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="name">name</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="country">country</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="created">created</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesCity}" column="updated">updated</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="city" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${city.id}" /></td>
				<td><c:out value="${city.name}" /></td>
				<td><c:out value="${city.countryName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${city.created}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${city.updated}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesCity}/${city.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesCity}/${city.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red "
					href="${pagesCity}/${city.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesCity}/add"><i
	class="material-icons">add</i></a>
