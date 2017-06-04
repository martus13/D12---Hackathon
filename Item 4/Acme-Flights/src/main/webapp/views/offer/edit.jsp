<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="offer/manager/edit.do?isAirline=${isAirline }" modelAttribute="offer">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:input code="offer.startMoment" path="startMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.endMoment" path="endMoment" placeholder="dd/MM/yyyy HH:mm:ss"/>
	<acme:input code="offer.discount" path="discount" />
	
	<jstl:set var="urlRef" value="offer/manager/edit.do?offerId=${offer.id }&&" />
	<jstl:set var="isChecked" value="" />
	<jstl:if test="${isCreate }">
		<jstl:set var="urlRef" value="offer/manager/create.do?" />
	</jstl:if>
	<jstl:if test="${isAirline}">
		<jstl:set var="checked" value="checked" />
	</jstl:if>
	
	<input type="checkbox" id="checkboxAiriline" name="checkboxAiriline" onchange="window.location.href = '${urlRef}isAirline='+this.checked;" ${checked} >
	<spring:message code="offer.applyToAirline" />: <jstl:out value="${airline.name }" /><br>
	
	<jstl:if test="${isAirline}">
		<form:hidden path="offertables" />
	</jstl:if>
	<jstl:if test="${not isAirline}">
		<display:table name="flights" id="row" requestURI="${requestURI }" pagesize="5" class="displaytag">

	    	<jstl:set var="checked" value="0" />
			<jstl:forEach var="offertable" items="${offer.offertables}">
			  <jstl:if test="${offertable.id==row.id}">
			    <jstl:set var="checked" value="1" />
			  </jstl:if>
			</jstl:forEach>
	    	
	  		<display:column>
	  			<jstl:choose>
	  				<jstl:when test="${checked==1 }">
	  					<form:checkbox path="offertables" value="${row.id}" checked="checked" />
	  				</jstl:when>
	  				<jstl:otherwise>
	  					<form:checkbox path="offertables" value="${row.id}" />
	  				</jstl:otherwise>
	  			</jstl:choose>
	   		</display:column>
	   		
	   		<acme:column code="finder.departureDate" property="departureDate" format="{0,date,dd/MM/yyyy HH:mm}" />
			<acme:column code="finder.arrivalDate" property="arrivalDate" format="{0,date,dd/MM/yyyy HH:mm}" />
	   		
	   		<spring:message code="finder.departure" var="departureHeader" />
			<display:column title="${departureHeader}" sortable="true" >
				<jstl:out value="${row.departure.iataCode }" /> - <jstl:out value="${row.departure.city }" />
			</display:column> 
	   
	   		<spring:message code="finder.destination" var="destinationHeader" />
			<display:column title="${destinationHeader}" sortable="true" >
				<jstl:out value="${row.destination.iataCode }" /> - <jstl:out value="${row.destination.city }" />
			</display:column> 
	   		
	   		<spring:message code="flight.businessPrice" var="priceHeader" />
			<display:column title="${priceHeader }" sortable="true" >
				<jstl:out value="${row.businessPrice}" />
			</display:column> 
			
			<spring:message code="flight.economyPrice" var="priceHeader" />
			<display:column title="${priceHeader}" sortable="true" >
				<jstl:out value="${row.economyPrice}" />
			</display:column> 
	   		
	   		
	    </display:table>
	</jstl:if>
    
    
	<form:errors path="offertables" cssClass="error" />
	

	
	<acme:submit name="save" code="offer.save" />
	<acme:cancel url="offer/manager/list.do" code="offer.cancel" />
		
</form:form>

<script>
	
	//$("#checkboxAiriline").change(function(){
	//	
	//	if($("#checkboxAiriline").prop('checked')){
	//		$("input[name='offertables']").prop( "disabled", true);
	//	}else{
	//		$("input[name='offertables']").prop("disabled", false );
	//	}
	//});
	
</script>
