<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.recipe.list.label.code" path="code" width="25%"/>
	<acme:list-column code="any.recipe.list.label.heading" path="heading" width="25%"/>
	<acme:list-column code="any.recipe.list.label.description" path="description" width="25%"/>
	<acme:list-column code="any.recipe.list.label.preparation-notes" path="preparationNotes" width="25%"/>	
</acme:list>