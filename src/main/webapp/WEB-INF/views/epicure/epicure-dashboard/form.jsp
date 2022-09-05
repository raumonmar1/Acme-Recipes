<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="epicure.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm" aria-describedby="dashboard">
	<tr>
		<th scope="row">
			<acme:message code="epicure.dashboard.form.label.total-number-of-fine-dishes-by-status"/>
		</th> 
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="totalNumberOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<jstl:forEach items="${averageBudgetOfFineDishesByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${averageBudgetOfFineDishesByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="epicure.dashboard.form.status.average.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${deviationBudgetOfFineDishesByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${deviationBudgetOfFineDishesByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="epicure.dashboard.form.status.deviation.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${minimunBudgetOfFineDishesByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${minimunBudgetOfFineDishesByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="epicure.dashboard.form.status.minimum.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
	<jstl:forEach items="${maximumBudgetOfFineDishesByStatus.keySet() }" var="key">
		<tr>
			<jstl:set value="${maximumBudgetOfFineDishesByStatus.get(key) }"
				var="amount" />
			<jstl:if test="${amount > 0}">
				<th scope="row">
				<acme:message code="epicure.dashboard.form.status.maximum.${key.getFirst()}.${key.getSecond()}" />
				<td><acme:print value="${ amount }" /></td>
			</jstl:if>
		</tr>
	</jstl:forEach>
	
</table>

<script type="text/javascript">
$(document).ready(function() {
	
	function newChart(labels, data, id) {
	
		var data = {
			labels : labels,
			datasets : [
				{
					data : data
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 1.0
						}
					}
				]
			},
			legend : {
				display : false
			}
		};
	
		var canvas, context;
	
		canvas = document.getElementById(id);
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
	
	}
	
	var totalFineDishes = {
			<jstl:forEach items="${totalNumberOfFineDishesByStatus}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(totalFineDishes), Object.values(totalFineDishes), "totalNumberOfFineDishesByStatus");
		
	});
</script>

<acme:return/>

