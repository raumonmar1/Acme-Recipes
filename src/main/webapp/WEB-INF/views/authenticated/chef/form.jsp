<%--
- form.jsp
-
- Copyright (C) 2012-2022 Alejandro Ba�os.
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
	<acme:input-textbox code="authenticated.chef.chef.form.label.organisation" path="organisation"/>
	<acme:input-textbox code="authenticated.chef.chef.form.label.assertion" path="assertion"/>
	<acme:input-textbox code="authenticated.chef.chef.form.label.info" path="info"/>	
	
	<acme:submit test="${command == 'create'}" code="authenticated.chef.chef.form.button.create" action="/authenticated/chef/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.chef.chef.form.button.update" action="/authenticated/chef/update"/>
</acme:form>
