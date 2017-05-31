<%--
 * action-1.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="f" uri="http://example.com/functions" %>

<p><b><spring:message code="admin.dashboard.airlinesMostBooks" /></b></p>
<display:table name="airlineMostBooks" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.books" property="[1]" />
	
</display:table>

<p><b><spring:message code="admin.dashboard.airlinesLessBooks" /></b></p>
<display:table name="airlineLessBooks" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.books" property="[1]" />
	
</display:table>

<p><b><spring:message code="admin.dashboard.mostVisitedCities" /></b></p>
<display:table name="mostVisitedCities" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.city" property="[0]" />
	
	<acme:column code="admin.dashboard.visits" property="[1]" />
</display:table>

<p><b><spring:message code="admin.dashboard.LessVisitedCities" /></b></p>
<display:table name="mostVisitedCities" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.city" property="[0]" />
	
	<acme:column code="admin.dashboard.visits" property="[1]" />
</display:table>

<p><b><spring:message code="admin.dashboard.lessFlightsPerAirline" /></b></p>
<display:table name="lessFlightsPerAirline" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.flights" property="[1]" />
</display:table>

<p><b><spring:message code="admin.dashboard.mostFlightsPerAirline" /></b></p>
<display:table name="mostFlightsPerAirline" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.flights" property="[1]" />
</display:table>

<div>
	<b><spring:message code="admin.dashboard.minMaxAvgFlightsOrigin" /></b>
	<ul>
		<li>
			<b><spring:message code="admin.dashboard.min"/>:</b>
			<jstl:out value="${minMaxAvgFlightsOrigin[0]}"/>
		</li>
		<li>
			<b><spring:message code="admin.dashboard.max"/>:</b>
			<jstl:out value="${minMaxAvgFlightsOrigin[1]}"/>
		</li>
		<li>
			<b><spring:message code="admin.dashboard.avg"/>:</b>
			<jstl:out value="${minMaxAvgFlightsOrigin[2]}"/>
		</li>
	</ul>
</div>

<p><b><spring:message code="admin.dashboard.airlinesOrderByBills" /></b></p>
<display:table name="airlinesOrderByBills" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0].name" />
	
	<acme:column code="admin.dashboard.bills" property="[1]" />
</display:table>

<p><b><spring:message code="admin.dashboard.percentageBillsPaid" /></b></p>
<display:table name="percentageBillsPaid" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.billsPaid" property="[1]" /> 
</display:table>

<p><b><spring:message code="admin.dashboard.percentageOffertedFlightsHigh" /></b></p>
<display:table name="percentageOffertedFlightsHigh" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0].name" />
	
	<acme:column code="admin.dashboard.flights" property="[1]" /> 
</display:table>

<p><b><spring:message code="admin.dashboard.percentageOffertedFlightsLow" /></b></p>
<display:table name="percentageOffertedFlightsLow" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.flights" property="[1]" /> 
</display:table>

<p><b><spring:message code="admin.dashboard.findMostPercentageDiscount" /></b></p>
<display:table name="percentageOffertedFlightsLow" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.discount" property="[1]" />
</display:table>

<p><b><spring:message code="admin.dashboard.findLessPercentageDiscount" /></b></p>
<display:table name="findLessPercentageDiscount" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0].name" />
	
	<acme:column code="admin.dashboard.discount" property="[1]" /> 
</display:table>

<p><b><spring:message code="admin.dashboard.findMinMaxAvgRatingByAirline" /></b></p>
<display:table name="findMinMaxAvgRatingByAirline" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.min" property="[1]" />
	
	<acme:column code="admin.dashboard.max" property="[2]" />
	
	<acme:column code="admin.dashboard.min" property="[3]" />
</display:table>

<p><b><spring:message code="admin.dashboard.findPositiveComments" /></b></p>
<display:table name="findPositiveComments" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.comments" property="[1]" /> 
</display:table>

<p><b><spring:message code="admin.dashboard.findMinMaxAvgServiceRatingByAirline" /></b></p>
<display:table name="findMinMaxAvgServiceRatingByAirline" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0]" />
	
	<acme:column code="admin.dashboard.min" property="[1]" />
	
	<acme:column code="admin.dashboard.max" property="[2]" />
	
	<acme:column code="admin.dashboard.min" property="[3]" />
</display:table>

<p><b><spring:message code="admin.dashboard.findMinMaxAvgComfortRatingByAirline" /></b></p>
<display:table name="findMinMaxAvgComfortRatingByAirline" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">
	
	<acme:column code="admin.dashboard.airline" property="[0].name" />
	
	<acme:column code="admin.dashboard.min" property="[1]" />
	
	<acme:column code="admin.dashboard.max" property="[2]" />
	
	<acme:column code="admin.dashboard.min" property="[3]" />
</display:table>


