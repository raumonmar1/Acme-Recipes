
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="any.peep.form.label.heading" path="heading"/>
	<acme:input-textbox code="any.peep.form.label.writer" path="writer"/>	
	<acme:input-textarea code="any.peep.form.label.pieceOfText" path="pieceOfText"/>	
	<acme:input-email code="any.peep.form.label.email" path="email" placeholder="example@gmail.com" />	
	
	<jstl:if test="${!readonly}">
		<acme:input-checkbox code="any.peep.form.label.confirmation" path="confirmation"/>
		<acme:submit code="any.peep.form.button.create" action="/any/peep/create"/>
	</jstl:if>
		
</acme:form>