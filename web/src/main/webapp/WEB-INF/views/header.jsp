<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<header>
	<ul id="ru" class="dropdown-content">
		<li><a href="${contextPath}?language=ru">RU</a></li>
		<li><a href="${contextPath}?language=en">EN</a></li>
	</ul>
	<ul id="region" class="dropdown-content">
		<li><a href="${pagesRegion}"><mytaglib:i18n
					key="views.region" /></a></li>
		<li><a href="${pagesCountry}"><mytaglib:i18n
					key="views.country" /></a></li>
		<li><a href="${pagesCity}"><mytaglib:i18n key="views.city" /></a></li>
	</ul>
	<ul id="event" class="dropdown-content">
		<li><a href="${pagesEvent}"><mytaglib:i18n key="views.event" /></a></li>
		<li><a href="${pagesTicket}"><mytaglib:i18n
					key="views.ticket" /></a></li>
		<li><a href="${pagesSeasonTicket}"><mytaglib:i18n
					key="views.seasonTicket" /></a></li>
	</ul>
	<ul id="partner" class="dropdown-content">
		<li><a href="${pagesPartner}"><mytaglib:i18n
					key="views.partner" /></a></li>
		<li><a href="${pagesPartnerContract}"><mytaglib:i18n
					key="views.partnerContract" /></a></li>
	</ul>
	<ul id="club" class="dropdown-content">
		<li><a href="${pagesClub}"><mytaglib:i18n key="views.club" /></a></li>
		<li><a href="${pagesStadium}"><mytaglib:i18n
					key="views.stadium" /></a></li>
		<li><a href="${pagesFunOrganisation}"><mytaglib:i18n
					key="views.funOrganisation" /></a></li>
	</ul>
	<nav>
		<div class="left hide-on-med-and-down">
			<li><a class="dropdown-trigger" href="#!" data-target="ru">RU<i
					class="material-icons right">arrow_drop_down</i></a></li>
		</div>
		<div>
			<a class="right hide-on-med-and-down" href="${contextPath}/login"name"><i
				class="material-icons">arrow_forward</i></a>
		</div>
		<div class="nav-wrapper container">
			<ul class="left hide-on-med-and-down">
				<sec:authorize access="hasAnyRole('admin')">
					<li><a href="${pagesUserAccount}"><mytaglib:i18n
								key="views.userAccount" /></a></li>
				</sec:authorize>
				<li><a class="dropdown-trigger" href="#!" data-target="region"><mytaglib:i18n
							key="views.region" /><i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a class="dropdown-trigger" href="#!" data-target="event"><mytaglib:i18n
							key="views.event" /><i class="material-icons right">arrow_drop_down</i></a></li>
				<li><a class="dropdown-trigger" href="#!" data-target=club><mytaglib:i18n
							key="views.club" /><i class="material-icons right">arrow_drop_down</i></a></li>
				<sec:authorize access="hasAnyRole('admin, owner')">
					<li><a class="dropdown-trigger" href="#!"
						data-target="partner"><mytaglib:i18n key="views.partner" /><i
							class="material-icons right">arrow_drop_down</i></a></li>
				</sec:authorize>
			</ul>
			
			<ul class="rigth hide-on-med-and-down"><sec:authentication property="name" /></ul>
			
		</div>
	</nav>
</header>