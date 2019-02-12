<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h4 class="header"><mytaglib:i18n key="views.userAccount"/></h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="id"><mytaglib:i18n key="table.column.id"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="name"><mytaglib:i18n key="table.column.name"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="email"><mytaglib:i18n key="table.column.email"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="country"><mytaglib:i18n key="table.column.country"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="funOrganisation"><mytaglib:i18n key="table.column.funOrganisation"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="role"><mytaglib:i18n key="table.column.role"/></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="created"><mytaglib:i18n key="table.column.created"/></mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="userAccount" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${userAccount.id}" /></td>
                <td><c:out value="${userAccount.name}" /></td>
                <td><c:out value="${userAccount.email}" /></td>
                <td><c:out value="${userAccount.countryName}" /></td>
                <td><c:out value="${userAccount.funOrganisationName}" /></td>
                <td><c:out value="${userAccount.role}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${userAccount.created}" /></td>
               <sec:authorize access="hasRole('admin')"> <td class="right"><a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesUserAccount}/${userAccount.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red " href="${pagesUserAccount}/${userAccount.id}/delete"><i class="material-icons">delete</i></a></td>
               </sec:authorize>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')"> <a class="waves-effect waves-light btn right" href="${pagesUserAccount}/add"><i class="material-icons">add</i></a></sec:authorize>
