
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="any.element.form.label.tipo" path="tipo">
		<acme:input-option code="INGREDIENT" value="INGREDIENT" selected="${tipo == 'INGREDIENT'}"/>
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL" selected="${tipo == 'KITCHEN_UTENSIL'}"/>
	</acme:input-select>
	<acme:input-textbox code="any.element.form.label.name" path="name"/>
	<acme:input-textbox code="any.element.form.label.code" path="code"/>	
	<acme:input-textbox code="any.element.form.label.amount-unit" path="amountUnit"/>
	<acme:input-textarea code="any.element.form.label.description" path="description"/>	
	<acme:input-money code="any.element.form.label.retail-price" path="retailPrice"/>
	<acme:input-url code="any.element.form.label.info" path="info"/>	
	<acme:input-textbox code="any.element.form.label.chef" path="chef.userAccount.username"/>
	<acme:input-textbox code="any.element.form.label.published" path="published"/>
</acme:form>