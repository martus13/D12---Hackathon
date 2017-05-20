<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<jstl:choose>
		<jstl:when test="${empty finder }">
			<a href="finder/user/create.do"><spring:message code="finder.create" /></a>
		</jstl:when>
		<jstl:otherwise>
			<ul>
				<li>
					<b><spring:message code="finder.departure" />:</b>
					<jstl:out value="${finder.departure}" />
				</li>
				
				<li>
					<b><spring:message code="finder.destination" />:</b>
					<jstl:out value="${finder.destination}" />
				</li>
				
				<li>
					<b><spring:message code="finder.returnFlight" />:</b>
					<jstl:out value="${finder.returnFlight}" />
				</li>
				
				<li>
					<b><spring:message code="finder.departureDate" />:</b>
					<jstl:out value="${finder.departureDate}" />
				</li>
				
				<li>
					<b><spring:message code="finder.returnDate"/>:</b>
					<jstl:out value="${finder.returnDate}"/>
				</li>
				
				<li>
					<b><spring:message code="finder.isBusiness"/>:</b>
					<jstl:out value="${finder.isBusiness}"/>
				</li>
				
				<li>
					<b><spring:message code="finder.passengersNumber"/>:</b>
					<jstl:out value="${finder.passengersNumber}"/>
				</li>
				
				<li>
					<b><spring:message code="finder.childrenNumber"/>:</b>
					<jstl:out value="${finder.childrenNumber}"/>
				</li>
				
						
			</ul>
			
		</jstl:otherwise>
	</jstl:choose>
	<br>
	
</div>
	