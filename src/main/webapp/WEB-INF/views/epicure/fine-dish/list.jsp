<%@page language="java"%> 
 
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%> 
 
<acme:list> 
	<acme:list-column code="epicure.fine-dish.list.label.code" path="code"/> 
	<acme:list-column code="epicure.fine-dish.list.label.start-date" path="startDate"/>	 
	<acme:list-column code="epicure.fine-dish.list.label.finish-date" path="finishDate"/>
	<acme:list-column code="epicure.fine-dish.list.label.status" path="status"/>
	<acme:list-column code="epicure.fine-dish.list.label.info" path="info"/>
</acme:list>                                                                                             