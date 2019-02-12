<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<h4 class="header">
	<mytaglib:i18n key="views.event" />
</h4>
<div class="row">
    <div class="col s12 m10">
        <div class="card-panel blue lighten-5">
            <div class="row">
                <form:form method="POST" action="${pagesEvent}" modelAttribute="searchFormModel">
                    <div class="input-field col s12">
                        <form:input path="name" type="text" />
                        <label for="name">Search</label>
                    </div>
                     <div class="col s12">
                        <button class="btn waves-effect waves-light right" type="submit">search</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="id">
					<mytaglib:i18n key="table.column.id" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="name">
					<mytaglib:i18n key="table.column.name" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="date">
					<mytaglib:i18n key="table.column.date" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="stadium">
					<mytaglib:i18n key="table.column.stadium" />
				</mytaglib:sort-link></th>
			<th><mytaglib:sort-link pageUrl="${pagesEvent}" column="created">
					<mytaglib:i18n key="table.column.created" />
				</mytaglib:sort-link></th>
			<th></th>
		</tr>
		<c:forEach var="event" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td><c:out value="${event.id}" /></td>
				<td><c:out value="${event.name}" /></td>
				<td><c:out value="${event.date}" /></td>
				<td><c:out value="${event.stadiumName}" /></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd"
						value="${event.created}" /></td>
				<sec:authorize access="hasAnyRole('admin','moderator')">
					<td class="right"><a class="btn-floating"
						href="${pagesEvent}/${event.id}"><i class="material-icons">info</i></a>
						<a class="btn-floating" href="${pagesEvent}/${event.id}/edit"><i
							class="material-icons">edit</i></a> <a class="btn-floating red"
						href="${pagesEvent}/${event.id}/delete"><i
							class="material-icons">delete</i></a></td>
				</sec:authorize>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasAnyRole('admin','moderator')">
	<a class="waves-effect waves-light btn right" href="${pagesEvent}/add"><i
		class="material-icons">add</i></a>
</sec:authorize>
