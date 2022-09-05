<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>
	<acme:input-moment code="epicure.fine-dish.form.label.start-date" path="startDate"/>
	<acme:input-moment code="epicure.fine-dish.form.label.finish-date" path="finishDate"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.info" path="info"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.status" path="status"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.budget" path="budget"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="epicure.fine-dish.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	<acme:input-textbox code="epicure.fine-dish.form.label.username" path="chef.userAccount.username"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.organisation" path="chef.organisation"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef-info" path="chef.info"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.assertion" path="chef.assertion"/>

</acme:form>
