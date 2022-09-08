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
	<acme:input-textbox code="chef.fine-dish.form.label.code" path="code"/>
	<acme:input-moment code="chef.fine-dish.form.label.startDate" path="startDate"/>
	<acme:input-moment code="chef.fine-dish.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="chef.fine-dish.form.label.info" path="info"/>
	<acme:input-textbox code="chef.fine-dish.form.label.budget" path="budget"/>
	<acme:input-textbox code="chef.fine-dish.form.label.username" path="chef.userAccount.username"/>
	<acme:input-textbox code="chef.fine-dish.form.label.organisation" path="chef.organisation"/>
	<acme:input-textbox code="chef.fine-dish.form.label.chef-info" path="chef.info"/>
	<acme:input-textbox code="chef.fine-dish.form.label.assertion" path="chef.assertion"/>
	<jstl:if test="${dif == true}">
		<acme:input-money code="chef.fine-dish.form.label.convertir" path="convertir" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status!='PROPOSED'}">
		<acme:input-textbox code="chef.fine-dish.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	<jstl:if test="${status == 'PROPOSED'}">
		<acme:input-select path="status" code="chef.fine-dish.form.label.status">
			<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
			<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
			<acme:input-option code="DENIED" value="DENIED"/>
		</acme:input-select>		
	</jstl:if>
	
	<jstl:if test="${status == 'ACCEPTED'}">	
	<acme:button code="master.menu.epicure.create-fine-dish.memorandum" action="/chef/memorandum/create?memorandumId=${id}"/>
	</jstl:if>
	

	<acme:submit test="${command == 'show' && status == 'PROPOSED'}" code="chef.fine-dish.form.button.update" action="/chef/fine-dish/update"/>


</acme:form>
