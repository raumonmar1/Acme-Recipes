<%@page language="java"%> 
 
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%> 
 
<acme:list> 
	<acme:list-column code="chef.fine-dish.list.label.code" path="code"/> 
	<acme:list-column code="chef.fine-dish.list.label.startDate" path="startDate"/>	 
	<acme:list-column code="chef.fine-dish.list.label.finishDate" path="finishDate"/>
	<acme:list-column code="chef.fine-dish.list.label.status" path="status"/>
	<acme:list-column code="chef.fine-dish.list.label.info" path="info"/>
</acme:list>                                                                                             