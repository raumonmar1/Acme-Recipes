<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.element.list.label.tipo" path="tipo" width="20%"/>	
	<acme:list-column code="any.element.list.label.name" path="name" width="20%"/>
	<acme:list-column code="any.element.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="any.element.list.label.amount-unit" path="amountUnit" width="20%"/>
	<acme:list-column code="any.element.list.label.retail-price" path="retailPrice" width="20%"/>
	
</acme:list>