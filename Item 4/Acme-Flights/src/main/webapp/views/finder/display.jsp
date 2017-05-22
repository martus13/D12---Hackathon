<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
	<jstl:choose>
		<jstl:when test="${finder==null}">
			<a href="finder/user/create.do"><spring:message code="finder.create" /></a>
		</jstl:when>
		<jstl:otherwise>
			<ul>
				<li>
					<b><spring:message code="finder.departure" />:</b>
					<jstl:out value="${finder.departure.iataCode}" /> - <jstl:out value="${finder.departure.city}" />
				</li>
				
				<li>
					<b><spring:message code="finder.destination" />:</b>
					<jstl:out value="${finder.destination.iataCode}" /> - <jstl:out value="${finder.destination.city}" />
				</li>
				
				<li>
					<b><spring:message code="finder.departureDate" />:</b>
					<fmt:formatDate value="${finder.departureDate}" pattern="dd/MM/yyyy" />
				</li>
				
				<li>
					<b><spring:message code="finder.returnFlight" />:</b>
					<jstl:out value="${finder.returnFlight}" />
				</li>
				
				<jstl:if test="${finder.returnFlight }">
					<li>
						<b><spring:message code="finder.returnDate"/>:</b>
						<jstl:out value="${finder.returnDate}"/>
					</li>
				</jstl:if>
				
				<li>
					<b><spring:message code="finder.passengersNumber"/>:</b>
					<jstl:out value="${finder.passengersNumber}"/>
				</li>
				
				<li>
					<b><spring:message code="finder.childrenNumber"/>:</b>
					<jstl:out value="${finder.childrenNumber}"/>
				</li>
				
				<li>
					<b><spring:message code="finder.isBusiness"/>:</b>
					<jstl:out value="${finder.isBusiness}"/>
				</li>
				
			</ul>
			<a href="finder/user/findByFinder.do?finderId=${finder.id }"><spring:message code="finder.search" /></a>
		</jstl:otherwise>
	</jstl:choose>
	<br>
	
</div>
	