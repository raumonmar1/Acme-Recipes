package acme.forms;


import java.io.Serializable;
import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.fineDishes.FineDishStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long serialVersionUID = 1L;
	
	
//	INGREDIENTS: Total number, average, deviation, minimum and maximum retail.
	
	int totalNumberOfIngredients;
	Map<String,Double> averageRetailPriceOfIngredientsByCurrency;
	Map<String,Double> deviationRetailPriceOfIngredientsByCurrency;
	Map<String,Double> minimumRetailPriceOfIngredientsByCurrency;
	Map<String,Double> maximumRetailPriceOfIngredientsByCurrency;



//	KITCHEN UTENDILS: Total number, average, deviation, minimum and maximum retail.
	
	int totalNumberOfKitchenUtensils;
	Map<String,Double> averageRetailPriceOfKitchenUtensilsByCurrency;
	Map<String,Double> deviationRetailPriceOfKitchenUtensilsByCurrency;
	Map<String,Double> minimumRetailPriceOfKitchenUtensilsByCurrency;
	Map<String,Double> maximumRetailPriceOfKitchenUtensilsByCurrency;

	
//	FINE DISHES: Total number, average, deviation, minimum and maximum retail.
	
	Map<FineDishStatus, Integer> totalNumberOfProposedFineDishes;
	Map<Pair<FineDishStatus,String>, Double> averageBudgetOfProposedFineDishes;
	Map<Pair<FineDishStatus,String>, Double> deviationBudgetOfProposedFineDishes;
	Map<Pair<FineDishStatus,String>, Double> minimumBudgetOfProposedFineDishes;
	Map<Pair<FineDishStatus,String>, Double> maximumBudgetOfProposedFineDishes;

}
