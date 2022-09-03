package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{

	//Ingredients
	@Query("SELECT count(e) FROM Element e WHERE e.type = 'INGREDIENT'")
	Integer totalNumberOfIngredients();
	
	@Query("SELECT e.retailPrice.currency, avg(e.retailPrice.amount)  FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> averageRetailPriceOfIngredientsByCurrency();
	
	@Query("SELECT e.retailPrice.currency, stddev(e.retailPrice.amount) FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> deviationRetailPriceOfIngredientsByCurrency();

	@Query("SELECT e.retailPrice.currency, min(e.retailPrice.amount) FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> minimumRetailPriceOfIngredientsByCurrency();

	@Query("SELECT e.retailPrice.currency, max(e.retailPrice.amount) FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> maximumRetailPriceOfIngredientsByCurrency();
	
	//Kitchen Utensils
	@Query("SELECT count(e) FROM Element e WHERE e.type = 'KITCHEN UTENSIL'")
	int totalNumberOfKitchenUtensils();

	@Query("SELECT e.retailPrice.currency, avg(e.retailPrice.amount) FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> averageRetailPriceOfKitchenUtensilsByCurrency();

	@Query("SELECT e.retailPrice.currency, stddev(e.retailPrice.amount) FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> deviationRetailPriceOfKitchenUtensilsByCurrency();

	@Query("SELECT e.retailPrice.currency, min(e.retailPrice.amount) FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> minimumRetailPriceOfKitchenUtensilsByCurrency();

	@Query("SELECT e.retailPrice.currency, max(e.retailPrice.amount) FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> maximumRetailPriceOfKitchenUtensilsByCurrency();
	
	//Fine Dishes
	@Query("SELECT f.status, count(f) FROM FineDish f GROUP BY f.status")
	Collection<Tuple> totalNumberOfFineDishesByStatus();

	@Query("SELECT f.status, f.budget.currency, avg(f.budget.amount) FROM FineDish f GROUP BY f.status")
	Collection<Tuple> averageBudgetOfFineDishesByStatus();

	@Query("SELECT f.status, f.budget.currency, stddev(f.budget.amount) FROM FineDish f GROUP BY f.status")
	Collection<Tuple> deviationBudgetOfFineDishesByStatus();

	@Query("SELECT f.status, f.budget.currency, min(f.budget.amount) FROM FineDish f GROUP BY f.status")
	Collection<Tuple> minimumBudgetOfFineDishesByStatus();

	@Query("SELECT f.status, f.budget.currency, max(f.budget.amount) FROM FineDish f GROUP BY f.status")
	Collection<Tuple> maximumBudgetOfFineDishesByStatus();
	
	//SystemConfiguration
	@Query("SELECT s.acceptedCurrencies FROM SystemConfiguration s")
	String findSystemCurrencies();
}
