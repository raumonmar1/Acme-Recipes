<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.bulletin.list.label.moment" path="moment" width="40%"/>
	<acme:list-column code="authenticated.bulletin.list.label.heading" path="heading" width="40%"/>
	<acme:list-column code="authenticated.bulletin.list.label.flag" path="flag"  width="20%"/>
</acme:list>	