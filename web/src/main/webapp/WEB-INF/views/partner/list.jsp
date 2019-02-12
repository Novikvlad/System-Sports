<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<h4 class="header"><mytaglib:i18n key="views.partner"/></h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPartner}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPartner}" column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPartner}" column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="partner" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${partner.id}" /></td>
				<td><c:out value="${partner.name}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"value="${partner.created}" /></td>
				<td class="right"><a class="btn-floating"href="${pagesPartner}/${partner.id}"><i class="material-icons">info</i></a>
					<a class="btn-floating" href="${pagesPartner}/${partner.id}/edit"><i class="material-icons">edit</i></a> 
					<a class="btn-floating red"	href="${pagesPartner}/${partner.id}/delete"><i class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesPartner}/add"><i
	class="material-icons">add</i></a>
