<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="administrator.systemConfiguration.form.label.accepted-currencies" path="acceptedCurrencies"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.system-currency" path="systemCurrency"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.spam-tuples" path="spamTuples"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.spam-threshold" path="spamThreshold"/>
	<acme:input-textbox code="administrator.systemConfiguration.form.label.money-exchange-name" path="moneyExchangeName"/>
	<acme:input-url code="administrator.systemConfiguration.form.label.money-exchange-link" path="moneyExchangeLink"/>
</acme:form>