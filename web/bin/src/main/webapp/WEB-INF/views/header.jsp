<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<li><a href="${contextPath}/">home</a></li>
				<li><a href="${pagesRegion}">Regions</a></li>
				<li><a href="${pagesPartner}">Partners</a></li>
				<li><a href="${pagesSeasonTicket}">Season Tickets</a></li>
				<li><a href="${pagesCountry}">Countries</a></li>
				<li><a href="${pagesCity}">Cities</a></li>
				<li><a href="${pagesStadium}">Stadiums</a></li>
				<li><a href="${pagesEvent}">Events</a></li>
				<li><a href="${pagesTicket}">Tickets</a></li>
				<li><a href="${pagesPartnerContract}">PartnerContracts</a></li>
				<sec:authorize access="!isAnonymous()">
					<a class="right" href="${contextPath}/execute_logout"
						title="logout"><i class="material-icons">arrow_forward</i></a>
				</sec:authorize>
			</ul>
		</div>
	</nav>
</header>