<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="comment/user/create.do" modelAttribute="comment">

		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="airline"/>
		<form:hidden path="user"/>
		<form:hidden path="creationMoment"/>
		
		<form:label path="type">
				<spring:message code="comment.type" />
			</form:label>	
			<form:select path="type">
				<form:option value="Neutral" label="Neutral" />		
				<form:option value="Positive" label="Positive" />
				<form:option value="Negative" label="Negative" />
			</form:select>
				<form:errors path="type" cssClass="error" />

		<acme:textarea code="comment.comment" path="commentText"/>
		
		<fieldset>
		<legend>
			<spring:message code="comment.rating"/>
		</legend>
		
			<form:label path="rating.flight">
				<spring:message code="comment.rating.flight" />
			</form:label>	
			<form:select path="rating.flight">
				<form:option value="0" label="0" />		
				<form:option value="1" label="1" />
				<form:option value="2" label="2" />
				<form:option value="3" label="3" />
				<form:option value="4" label="4" />
				<form:option value="5" label="5" />
			</form:select>
				<form:errors path="rating.flight" cssClass="error" />
			
			<form:label path="rating.airline">
				<spring:message code="comment.rating.airline" />
			</form:label>	
			<form:select path="rating.airline">
				<form:option value="0" label="0" />		
				<form:option value="1" label="1" />
				<form:option value="2" label="2" />
				<form:option value="3" label="3" />
				<form:option value="4" label="4" />
				<form:option value="5" label="5" />
			</form:select>
				<form:errors path="rating.airline" cssClass="error" />
			
			<form:label path="rating.service">
				<spring:message code="comment.rating.service" />
			</form:label>	
			<form:select path="rating.service">
				<form:option value="0" label="0" />		
				<form:option value="1" label="1" />
				<form:option value="2" label="2" />
				<form:option value="3" label="3" />
				<form:option value="4" label="4" />
				<form:option value="5" label="5" />
			</form:select>
				<form:errors path="rating.service" cssClass="error" />
			
			<form:label path="rating.comfort">
				<spring:message code="comment.rating.comfort" />
			</form:label>	
			<form:select path="rating.comfort">
				<form:option value="0" label="0" />		
				<form:option value="1" label="1" />
				<form:option value="2" label="2" />
				<form:option value="3" label="3" />
				<form:option value="4" label="4" />
				<form:option value="5" label="5" />
			</form:select>
				<form:errors path="rating.comfort" cssClass="error" />
		
		</fieldset>
		
		<acme:submit name="save" code="comment.save" />

</form:form>