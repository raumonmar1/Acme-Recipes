<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.element.list.label.type" path="type" width="30%"/>	
	<acme:list-column code="any.element.list.label.name" path="name" width="40%"/>
	<acme:list-column code="any.element.list.label.code" path="code" width="30%"/>	
</acme:list>