<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
	<ul>
		<li>
			<b><spring:message code="offer.startMoment" />:</b>
			<fmt:formatDate value="${offer.startMoment}" pattern="dd/MM/yyyy HH:mm:ss" />
		</li>
		
		<li>
			<b><spring:message code="offer.endMoment" />:</b>
			<fmt:formatDate value="${offer.endMoment}" pattern="dd/MM/yyyy HH:mm:ss" />
		</li>
		
		<li>
			<b><spring:message code="offer.discount" />:</b>
			<jstl:out value="${offer.discount }" />
		</li>
		
		<li>
			<jstl:choose>
				<jstl:when test="${not empty airline }">
					<b><spring:message code="offer.airline" />:</b>
					<jstl:out value="${airline.name }" />
				</jstl:when>
				<jstl:otherwise>
					<b><spring:message code="offer.offertables"/>:</b>
					<display:table name="${flights }" id="row" requestURI="${requestURI }" class="displaytag">
					
						<acme:column code="offer.flight.departureDate" property="[0].departureDate" format="{0,date,dd/MM/yyyy HH:mm}" />
						<acme:column code="offer.flight.arrivalDate" property="[0].arrivalDate" format="{0,date,dd/MM/yyyy HH:mm}" />
						
						<spring:message code="offer.flight.departure" var="departureHeader" />
						<display:column title="${departureHeader}" sortable="true" >
							<jstl:out value="${row[0].departure.iataCode }" /> - <jstl:out value="${row[0].departure.city }" />
						</display:column> 
						
						<spring:message code="offer.flight.destination" var="destinationHeader" />
						<display:column title="${destinationHeader}" sortable="true" >
							<jstl:out value="${row[0].destination.iataCode }" /> - <jstl:out value="${row[0].destination.city }" />
						</display:column> 
						
						<acme:column code="offer.flight.businessPrice" property="[0].businessPrice" />
						<acme:column code="offer.flight.economyPrice" property="[0].economyPrice" />
						<acme:column code="offer.flight.availableBusinessSeats" property="[0].availableBusinessSeats" />
						<acme:column code="offer.flight.availableEconomySeats" property="[0].availableEconomySeats" />
						
						<spring:message code="offer.flight.season" var="seasonHeader" />
						<display:column title="${seasonHeader }">
							<jstl:if test="${not empty row[1]}">
								<jstl:choose>
									<jstl:when test="${row[1].type=='increase' }">
										<jstl:out value="+${row[1].pricePercentage }%" />
									</jstl:when>
									<jstl:otherwise>
										<jstl:out value="-${row[1].pricePercentage }%" />
									</jstl:otherwise>
								</jstl:choose>
							</jstl:if>
						</display:column>
						
					</display:table>
				</jstl:otherwise>
			</jstl:choose>
		</li>
	</ul>
</div>
	