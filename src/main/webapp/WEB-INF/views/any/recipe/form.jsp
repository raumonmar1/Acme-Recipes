<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<acme:input-textbox code="any.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="any.recipe.form.label.heading" path="heading"/>	
	<acme:input-textbox code="any.recipe.form.label.description" path="description"/>	
	<acme:input-textbox code="any.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-textbox code="any.recipe.form.label.info" path="info"/>	
	<acme:input-textbox code="any.recipe.form.label.retailPrice" path="retailPrice"/>
	<acme:input-textbox code="any.recipe.form.label.chef" path="chef.userAccount.username"/>
	
	<acme:button code="any.recipe.form.button.elements" action="/any/element/listRecipeElements?id=${id}"/>
</acme:form>