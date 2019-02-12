<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">
	<mytaglib:i18n key="views.partnerContract" />
</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="club">
					<mytaglib:i18n key="table.column.club" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="partner">
					<mytaglib:i18n key="table.column.partner" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="dateSigning">
					<mytaglib:i18n key="table.column.dateSigning" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="contractTerm">
					<mytaglib:i18n key="table.column.contractTerm" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="contractValue">
					<mytaglib:i18n key="table.column.contractValue" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesPartnerContract}"
					column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="partnerContract" items="${gridItems}"
			varStatus="loopCounter">
			<tr>
				<td><c:out value="${partnerContract.id}" /></td>
				<td><c:out value="${partnerContract.clubName}" /></td>
				<td><c:out value="${partnerContract.partnerName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${partnerContract.dateSigning}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${partnerContract.contractTerm}" /></td>
				<td><c:out value="${partnerContract.contractValue}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${partnerContract.created}" /></td>
				<td class="right"><a class="btn-floating"
					href="${pagesPartnerContract}/${partnerContract.id}"><i
						class="material-icons">info</i></a> <a class="btn-floating"
					href="${pagesPartnerContract}/${partnerContract.id}/edit"><i
						class="material-icons">edit</i></a> <a class="btn-floating red"
					href="${pagesPartnerContract}/${partnerContract.id}/delete"><i
						class="material-icons">delete</i></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right"
	href="${pagesPartnerContract}/add"><i class="material-icons">add</i></a>

<div id="chart_div"></div>