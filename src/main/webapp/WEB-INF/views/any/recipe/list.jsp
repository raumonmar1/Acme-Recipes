<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.recipe.list.label.code" path="code" width="30%"/>
	<acme:list-column code="any.recipe.list.label.heading" path="heading" width="30%"/>
	<acme:list-column code="any.recipe.list.label.link" path="link" width="40%"/>
</acme:list>