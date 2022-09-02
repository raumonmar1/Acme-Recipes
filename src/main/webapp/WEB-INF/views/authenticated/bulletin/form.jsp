<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.bulletin.form.label.heading" path="heading"/>
	<acme:input-textbox code="authenticated.bulletin.form.label.moment" path="moment"/>	
	<acme:input-textarea code="authenticated.bulletin.form.label.pieceOfText" path="pieceOfText"/>	
	<acme:input-select code="authenticated.bulletin.form.label.flag" path="flag">
		<acme:input-option code="authenticated.bulletin.form.label.flag.true" value="true" selected="${flag}"/>
		<acme:input-option code="authenticated.bulletin.form.label.flag.false" value="false" selected="${!flag}"/>
	</acme:input-select>
	<acme:input-url code="authenticated.bulletin.form.label.info" path="info"/>
</acme:form>