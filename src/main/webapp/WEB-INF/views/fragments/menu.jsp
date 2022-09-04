<%--
- menu.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.roles.Provider,acme.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
      		<acme:menu-suboption code="master.menu.anonymous.favourite-link-raul" action="http://www.twitch.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-daniel" action="http://www.twitter.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.systemConfiguration" action="/administrator/system-configuration/show"/>
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/administrator-dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.authenticated.bulletin" action="/authenticated/bulletin/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.any" access="hasRole('Any')">
			<acme:menu-suboption code="master.menu.any.user-accounts" action="/any/user-account/list"/>
			<acme:menu-suboption code="master.menu.any.peep" action="/any/peep/list"/>
			<acme:menu-suboption code="master.menu.any.element" action="/any/element/list"/>
			<acme:menu-suboption code="master.menu.any.recipe" action="/any/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.chef" access="hasRole('Chef')">
			<acme:menu-suboption code="master.menu.chef.fine-dish" action="/chef/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.chef.memorandum" action="/chef/memorandum/list"/>
			<acme:menu-suboption code="master.menu.chef.element" action="/chef/element/list"/>
			<acme:menu-suboption code="master.menu.chef.recipe" action="/chef/recipe/list"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.epicure" access="hasRole('Epicure')">
			<acme:menu-suboption code="master.menu.epicure.fine-dish" action="/epicure/fine-dish/list"/>
			<acme:menu-suboption code="master.menu.epicure.dashboard" action="/epicure/epicure-dashboard/show"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

