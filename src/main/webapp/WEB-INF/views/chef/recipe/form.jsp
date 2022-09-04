<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>	
	<acme:input-textbox code="chef.recipe.form.label.description" path="description"/>	
	<acme:input-textbox code="chef.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-textbox code="chef.recipe.form.label.link" path="Link"/>	
	<acme:input-textbox code="chef.recipe.form.label.published" path="published"/>	
	<acme:input-textbox code="chef.recipe.form.label.retail-price" path="retailPrice"/>
	<acme:input-textbox code="chef.recipe.form.label.chef" path="chef.userAccount.username"/>
	<acme:button code="chef.recipe.form.button.elements" action="/chef/element/listRecipeElements?id=${id}"/>
</acme:form>