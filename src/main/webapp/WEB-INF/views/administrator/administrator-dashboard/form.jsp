<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-ingredients"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfIngredients}"/>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price-of-ingredients-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageRetailPriceOfIngredientsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-ingredients-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationRetailPriceOfIngredientsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retail-price-of-ingredients-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minimumRetailPriceOfIngredientsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retail-price-of-ingredients-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maximumRetailPriceOfIngredientsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-kitchen-utensils"/>
		</th>
		<td>
			<acme:print value="${totalNumberOfKitchenUtensils}"/>
		</td>
	</tr>
	
		<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-retail-price-of-kitchen-utensils-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageRetailPriceOfKitchenUtensilsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-kitchen-utensils-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationRetailPriceOfKitchenUtensilsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-retail-price-of-kitchen-utensils-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minimumRetailPriceOfKitchenUtensilsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-retail-price-of-kitchen-utensils-by-currency"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maximumRetailPriceOfKitchenUtensilsByCurrency"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.total-number-of-fine-dishes-by-status"/>
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
				<acme:message code="administrator.dashboard.form.status.average.${key.getFirst()}.${key.getSecond()}" />
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
				<acme:message code="administrator.dashboard.form.status.deviation.${key.getFirst()}.${key.getSecond()}" />
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
				<acme:message code="administrator.dashboard.form.status.minimum.${key.getFirst()}.${key.getSecond()}" />
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
				<acme:message code="administrator.dashboard.form.status.maximum.${key.getFirst()}.${key.getSecond()}" />
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
	
	var averageIngredients = {
		<jstl:forEach items="${averageRetailPriceOfIngredientsByCurrency}" var="item" varStatus="loop">
 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
		</jstl:forEach>
	};
	newChart(Object.keys(averageIngredients), Object.values(averageIngredients), "averageRetailPriceOfIngredientsByCurrency");
	
	var deviationIngredients = {
			<jstl:forEach items="${deviationRetailPriceOfIngredientsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(deviationIngredients), Object.values(deviationIngredients), "deviationRetailPriceOfIngredientsByCurrency");
	
	var minimumIngredients = {
			<jstl:forEach items="${minimumRetailPriceOfIngredientsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(minimumIngredients), Object.values(minimumIngredients), "minimumRetailPriceOfIngredientsByCurrency");
		
	var maximumIngredients = {
			<jstl:forEach items="${maximumRetailPriceOfIngredientsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(maximumIngredients), Object.values(maximumIngredients), "maximumRetailPriceOfIngredientsByCurrency");
	
	var averageKitchenUtensils = {
			<jstl:forEach items="${averageRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(averageKitchenUtensils), Object.values(averageKitchenUtensils), "averageRetailPriceOfKitchenUtensilsByCurrency");
			
	var deviationKitchenUtensils = {
			<jstl:forEach items="${deviationRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(deviationKitchenUtensils), Object.values(deviationKitchenUtensils), "deviationRetailPriceOfKitchenUtensilsByCurrency");
			
	var minimumKitchenUtensils = {
			<jstl:forEach items="${minimumRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(minimumKitchenUtensils), Object.values(minimumKitchenUtensils), "minimumRetailPriceOfKitchenUtensilsByCurrency");
				
	var maximumKitchenUtensils = {
			<jstl:forEach items="${maximumRetailPriceOfKitchenUtensilsByCurrency}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(maximumKitchenUtensils), Object.values(maximumKitchenUtensils), "maximumRetailPriceOfKitchenUtensilsByCurrency");
	
	var totalFineDishes = {
			<jstl:forEach items="${totalNumberOfFineDishesByStatus}" var="item" varStatus="loop">
	 	      ${item.key}: '${item.value}' ${not loop.last ? ',' : ''}
			</jstl:forEach>
		};
		newChart(Object.keys(totalFineDishes), Object.values(totalFineDishes), "totalNumberOfFineDishesByStatus");

	});
</script>

<acme:return/>

