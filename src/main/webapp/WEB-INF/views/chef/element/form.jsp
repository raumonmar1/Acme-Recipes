<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-select code="chef.element.form.label.type" path="type">
		<acme:input-option code="KITCHEN_UTENSIL" value="KITCHEN_UTENSIL"
			selected="${type == 'KITCHEN_UTENSIL'}" />
		<acme:input-option code="INGREDIENT" value="INGREDIENT"
			selected="${type == 'INGREDIENT'}" />
	</acme:input-select>
	<acme:input-textbox code="chef.element.form.label.name" path="name" />
	<acme:input-textbox code="chef.element.form.label.code" path="code" />
	<acme:input-textbox code="chef.element.form.label.description" path="description" />
	<acme:input-textbox code="chef.element.form.label.retail-price" path="retailPrice" />
	<acme:input-textbox code="chef.element.form.label.amount-unit" path="amountUnit" />
	<acme:input-textbox code="chef.element.form.label.link" path="link" />
	<acme:input-textbox code="chef.element.form.label.chef" path="chef.userAccount.username" />
	<acme:input-textbox code="chef.element.form.label.published" path="published" />
</acme:form>