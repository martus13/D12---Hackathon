<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<ul>
		<li>
			<b><spring:message code="airline.picture" />:</b>
			<img src="${airline.picture}" style="max-height: 120px;" />
		</li>
		
		<li>
			<b><spring:message code="airline.name" />:</b>
			<jstl:out value="${airline.name}" />
		</li>
		
		<li>
			<b><spring:message code="airline.contactPhone" />:</b>
			<jstl:out value="${airline.contactPhone}" />
		</li>
		
		<li>
			<b><spring:message code="airline.email"/>:</b>
			<jstl:out value="${airline.email}"/>
		</li>
		
		<li>
			<b><spring:message code="airline.rating"/>:</b>
			<jstl:out value="${airline.rating}"/>
		</li>
		
		<li>
			<b><spring:message code="airline.comments"/>:</b>
			<display:table name="comments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
				<acme:column code="comment.type" property="type"/>
				<acme:column code="comment.comment" property="commentText"/>
				<acme:column code="comment.date"	property="creationMoment" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
				<acme:column code="comment.rating.flight" property="rating.flight"/>
				<acme:column code="comment.rating.airline" property="rating.airline"/>
				<acme:column code="comment.rating.service" property="rating.service"/>
				<acme:column code="comment.rating.comfort" property="rating.comfort"/>
				<acme:column code="comment.sender" property="user.name"/>
				
			</display:table>
			<security:authorize access="hasRole('USER')">
				<a href="comment/user/create.do?airlineId=${airline.id }">
					<spring:message code="airline.comment" />
				</a>
			
			</security:authorize>
		</li>
		
		<li>
			<b><spring:message code="airline.configuration"/>:</b>
			<display:table name="configurations" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
			
				<acme:column code="airlineConfig.cancellationDays" property="maxCancellationDays"/>
				<acme:column code="airlineConfig.childrenAge" property="maxChildrenAge"/>
				<acme:column code="airlineConfig.childrenDiscount"	property="childrenDiscount"/>
				<acme:column code="airlineConfig.maxBagWeight" property="maxBagWeight"/>
				<acme:column code="airlineConfig.overweightBagPrice" property="overweightBagPrice"/>
			
			</display:table>
		</li>
	

	</ul>
	
</div>
	