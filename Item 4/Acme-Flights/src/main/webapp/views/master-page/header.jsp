<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Flights Co., Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<!-- permitAll -->
		<security:authorize access="permitAll">
			<li><a class="fNiv" href="flight/list.do"><spring:message code="master.page.flight.list" /></a></li>
		</security:authorize>
		
		<!-- USER -->
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv" href="finder/user/display.do"><spring:message code="master.page.user.finder" /></a></li>
			<li><a class="fNiv" href="book/user/listByUser.do"><spring:message code="master.page.user.book" /></a></li>
			<li><a class="fNiv" href="creditCard/user/list.do"><spring:message code="master.page.user.creditCard" /></a></li>
		</security:authorize>
		
		<!-- MANAGER -->
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv"><spring:message code="master.page.manager.flight" /></a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="flight/manager/listByAirline.do"><spring:message code="master.page.manager.flight.list" /> </a></li>
					<li><a href="flight/manager/create.do"><spring:message code="master.page.manager.flight.create" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message code="master.page.manager.season" /></a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="season/manager/listByAirline.do"><spring:message code="master.page.manager.season.list" /> </a></li>
					<li><a href="season/manager/create.do"><spring:message code="master.page.manager.season.create" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="banner/manager/list.do"><spring:message code="master.page.manager.banner" /></a></li>
			<li><a class="fNiv" href="offer/manager/list.do"><spring:message code="master.page.manager.offer" /></a></li>
			<li><a class="fNiv" href="campaign/manager/list.do"><spring:message code="master.page.manager.campaign" /></a></li>
			<li><a class="fNiv"><spring:message code="master.page.manager.configuration" /></a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="airlineConfiguration/manager/display.do"><spring:message code="master.page.manager.configuration.display" /> </a></li>
				</ul>
			</li>
			
		</security:authorize>
		
		<!-- ADMIN -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message code="master.page.admin.airport" /></a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="airport/administrator/list.do"><spring:message code="master.page.airport.list" /> </a></li>
					<li><a href="airport/administrator/create.do"><spring:message code="master.page.airport.create" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message code="master.page.admin.airline" /></a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="airline/administrator/list.do"><spring:message code="master.page.airline.list" /> </a></li>
					<li><a href="airline/administrator/create.do"><spring:message code="master.page.airline.create" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="managerUser/administrator/register.do"><spring:message code="master.page.admin.manager" /></a></li>
		</security:authorize>
		
		<!-- isAnonymous -->
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="user/register.do"><spring:message code="master.page.regiter.user" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		</security:authorize>
		
		<!-- isAuthenticated -->
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="javascript:setParam('language', 'en');">en</a> | <a href="javascript:setParam('language', 'es');">es</a>
</div>

<script> 
    function setParam(name, value) {
        var l = window.location;

        /* build params */
        var params = {};        
        var x = /(?:\??)([^=&?]+)=?([^&?]*)/g;        
        var s = l.search;
        for(var r = x.exec(s); r; r = x.exec(s))
        {
            r[1] = decodeURIComponent(r[1]);
            if (!r[2]) r[2] = '%%';
            params[r[1]] = r[2];
        }

        /* set param */
        params[name] = encodeURIComponent(value);

        /* build search */
        var search = [];
        for(var i in params)
        {
            var p = encodeURIComponent(i);
            var v = params[i];
            if (v != '%%') p += '=' + v;
            search.push(p);
        }
        search = search.join('&');

        /* execute search */
        l.search = search;
    }
</script>

