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
		
	<!--</tr>
	<tr>
		<td>
			<div>
				<canvas id="totalNumberOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.average-budget-of-fine-dishes-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="averageBudgetOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.deviation-budget-of-fine-dishes-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="deviationBudgetOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.minimum-budget-of-fine-dishes-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="minimumBudgetOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr>
	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.form.label.maximum-budget-of-fine-dishes-by-status"/>
		</th>
		
	</tr>
	<tr>
		<td>
			<div>
				<canvas id="maximumBudgetOfFineDishesByStatus"></canvas>
			</div>
		</td>
	</tr> -->
	
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
	
	let averageIngredientsLabel = [
		<jstl:forEach items="${averageRetailPriceOfIngredientsByCurrency}" var="averageRetailPriceOfIngredientsByCurrency">
			<acme:print value="${averageRetailPriceOfIngredientsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let averageIngredientsData = [
		<jstl:forEach items="${averageRetailPriceOfIngredientsByCurrency}" var="averageRetailPriceOfIngredientsByCurrency">
			<acme:print value="${averageRetailPriceOfIngredientsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(averageIngredientsLabel, averageIngredientsData, "averageRetailPriceOfIngredientsByCurrency");
	
	let deviationIngredientsLabel = [
		<jstl:forEach items="${deviationRetailPriceOfIngredientsByCurrency}" var="deviationRetailPriceOfIngredientsByCurrency">
			<acme:print value="${deviationRetailPriceOfIngredientsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach> 
	]
	let deviationIngredientsData = [
		<jstl:forEach items="${deviationRetailPriceOfIngredientsByCurrency}" var="deviationRetailPriceOfIngredientsByCurrency">
			<acme:print value="${deviationRetailPriceOfIngredientsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(deviationIngredientsLabel, deviationIngredientsData, "deviationRetailPriceOfIngredientsByCurrency");
	
	let minimumIngredientsLabel = [
		<jstl:forEach items="${minimumRetailPriceOfIngredientsByCurrency}" var="minimumRetailPriceOfIngredientsByCurrency">
			<acme:print value="${minimumRetailPriceOfIngredientsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let minimumIngredientsData = [
		<jstl:forEach items="${minimumRetailPriceOfIngredientsByCurrency}" var="minimumRetailPriceOfIngredientsByCurrency">
			<acme:print value="${minimumRetailPriceOfIngredientsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(minimumIngredientsLabel, minimumIngredientsData, "minimumRetailPriceOfIngredientsByCurrency");
	
	let maximumIngredientsLabel = [
		<jstl:forEach items="${maximumRetailPriceOfIngredientsByCurrency}" var="maximumRetailPriceOfIngredientsByCurrency">
			<acme:print value="${maximumRetailPriceOfIngredientsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let maximumIngredientsData = [
		<jstl:forEach items="${maximumRetailPriceOfIngredientsByCurrency}" var="maximumRetailPriceOfIngredientsByCurrency">
			<acme:print value="${maximumRetailPriceOfIngredientsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(maximumIngredientsLabel, maximumIngredientsData, "maximumRetailPriceOfIngredientsByCurrency");
	
	let averageKitchenUtensilsLabel = [
		<jstl:forEach items="${averageRetailPriceOfKitchenUtensilsByCurrency}" var="averageRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${averageRetailPriceOfKitchenUtensilsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let averageKitchenUtensilsData = [
		<jstl:forEach items="${averageRetailPriceOfKitchenUtensilsByCurrency}" var="averageRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${averageRetailPriceOfKitchenUtensilsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(averageKitchenUtensilsLabel, averageKitchenUtensilsData, "averageRetailPriceOfKitchenUtensilsByCurrency");
			
	let deviationKitchenUtensilsLabel = [
		<jstl:forEach items="${deviationRetailPriceOfKitchenUtensilsByCurrency}" var="deviationRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${deviationRetailPriceOfKitchenUtensilsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let deviationKitchenUtensilsData = [
		<jstl:forEach items="${deviationRetailPriceOfKitchenUtensilsByCurrency}" var="deviationRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${deviationRetailPriceOfKitchenUtensilsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(deviationKitchenUtensilsLabel, deviationKitchenUtensilsData, "deviationRetailPriceOfKitchenUtensilsByCurrency");
			
	let minimumKitchenUtensilsLabel = [
		<jstl:forEach items="${minimumRetailPriceOfKitchenUtensilsByCurrency}" var="minimumRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${minimumRetailPriceOfKitchenUtensilsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let minimumKitchenUtensilsData = [
		<jstl:forEach items="${minimumRetailPriceOfKitchenUtensilsByCurrency}" var="minimumRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${minimumRetailPriceOfKitchenUtensilsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(minimumKitchenUtensilsLabel, minimumKitchenUtensilsData, "minimumRetailPriceOfKitchenUtensilsByCurrency");
			
	let maximumKitchenUtensilsLabel = [
		<jstl:forEach items="${maximumRetailPriceOfKitchenUtensilsByCurrency}" var="maximumRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${maximumRetailPriceOfKitchenUtensilsByCurrency['key']}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	let maximumKitchenUtensilsData = [
		<jstl:forEach items="${maximumRetailPriceOfKitchenUtensilsByCurrency}" var="maximumRetailPriceOfKitchenUtensilsByCurrency">
			<acme:print value="${maximumRetailPriceOfKitchenUtensilsByCurrency['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	newChart(maximumKitchenUtensilsLabel, maximumKitchenUtensilsData, "maximumRetailPriceOfKitchenUtensilsByCurrency");
	
	});
</script>

<acme:return/>

