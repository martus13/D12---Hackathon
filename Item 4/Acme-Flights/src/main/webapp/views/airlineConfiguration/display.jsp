<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



	<jstl:choose>
		<jstl:when test="${empty airlineConfiguration}">
			<a href="airlineConfiguration/manager/edit.do">
				<spring:message code="airlineConfig.create" />
			</a>
		</jstl:when>
	
	
	<jstl:otherwise>
	<div>
	<ul>
		<li>
			<b><spring:message code="airlineConfig.cancellationDays" />:</b>
			<jstl:out value="${airlineConfiguration.maxCancellationDays}" />
		</li>
	
		<li>
			<b><spring:message code="airlineConfig.childrenAge" />:</b>
			<jstl:out value="${airlineConfiguration.maxChildrenAge}" />
		</li>
		
		<li>
			<b><spring:message code="airlineConfig.childrenDiscount" />:</b>
			<jstl:out value="${airlineConfiguration.childrenDiscount}" />
		</li>
		
		<li>
			<b><spring:message code="airlineConfig.maxBagWeight" />:</b>
			<jstl:out value="${airlineConfiguration.maxBagWeight}" />
		</li>
		
		<li>
			<b><spring:message code="airlineConfig.overweightBagPrice" />:</b>
			<jstl:out value="${airlineConfiguration.overweightBagPrice}" />
		</li>
	</ul>
	</div>	

		<a href="airlineConfiguration/manager/edit.do?airlineConfigurationId=${airlineConfiguration.id}">
			<spring:message code="airlineConfig.edit" />
		</a>
		
	</jstl:otherwise>
</jstl:choose>
	
	