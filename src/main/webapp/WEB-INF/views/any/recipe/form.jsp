<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<acme:input-textbox code="any.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="any.recipe.form.label.heading" path="heading"/>	
	<acme:input-textarea code="any.recipe.form.label.description" path="description"/>	
	<acme:input-textarea code="any.recipe.form.label.preparation-notes" path="preparationNotes"/>
	<acme:input-textbox code="any.recipe.form.label.info" path="info"/>	
	<acme:input-textbox code="any.recipe.form.label.retail-price" path="retailPrice"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="any.recipe.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="any.recipe.form.label.chef" path="chef.userAccount.username"/>
	
	<acme:button code="any.recipe.form.button.elements" action="/any/element/listRecipeElements?id=${id}"/>
</acme:form>