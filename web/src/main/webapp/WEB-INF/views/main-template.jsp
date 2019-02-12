<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"	scope="request" />
<c:set var="pagesUserAccount" value="${contextPath}/userAccount" scope="request" />
<c:set var="pagesRegion" value="${contextPath}/region" scope="request" />
<c:set var="pagesCountry" value="${contextPath}/country" scope="request" />
<c:set var="pagesCity" value="${contextPath}/city" scope="request" />
<c:set var="pagesSeasonTicket" value="${contextPath}/seasonTicket"	scope="request" />
<c:set var="pagesTicket" value="${contextPath}/ticket" scope="request" />
<c:set var="pagesStadium" value="${contextPath}/stadium" scope="request" />
<c:set var="pagesEvent" value="${contextPath}/event" scope="request" />
<c:set var="pagesClub" value="${contextPath}/club" scope="request" />
<c:set var="pagesFunOrganisation" value="${contextPath}/funOrganisation" scope="request" />
<c:set var="pagesPartner" value="${contextPath}/partner" scope="request" />
<c:set var="pagesPartnerContract" value="${contextPath}/partnerContract" scope="request" />


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><tiles:insertAttribute name="title" /></title>
 <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css">
<script src="${contextPath}/resources/js/init-materialize-forms.js"></script>
<script src="${contextPath}/resources/js/init-menu.js"></script>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<main>
	<div class="container">
		<tiles:insertAttribute name="body" />
	</div>
	</main>
	<tiles:insertAttribute name="footer" />
</body>
</html>