<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<display:table name="pointsCards" id="row" requestURI="pointsCard/user/listByUser.do" pagesize="5" class="displaytag">
	
	<acme:column code="pointsCard.expirationMoment" property="expirationMoment" format="{0,date,dd/MM/yyyy}" />
	<acme:column code="pointsCard.points" property="points" />
	<acme:column code="pointsCard.airline" property="airline.name" />
	
</display:table>

