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
	
	@Query("SELECT avg(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> averageRetailPriceOfComponents();
	
	@Query("SELECT stddev(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> deviationRetailPriceOfComponents();

	@Query("SELECT min(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> minimumRetailPriceOfComponents();

	@Query("SELECT max(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'INGREDIENT' GROUP BY e.retailPrice.currency")
	Collection<Tuple> maximumRetailPriceOfComponents();
	
	//Kitchen Utensils
	@Query("SELECT count(e) FROM Element e WHERE e.type = 'KITCHEN UTENSIL'")
	int totalNumberOfKitchenUtensils();

	@Query("SELECT avg(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> averageRetailPriceOfTools();

	@Query("SELECT stddev(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> deviationRetailPriceOfTools();

	@Query("SELECT min(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> minimumRetailPriceOfTools();

	@Query("SELECT max(e.retailPrice.amount), e.retailPrice.currency FROM Element e WHERE e.type = 'KITCHEN UTENSIL' GROUP BY e.retailPrice.currency")
	Collection<Tuple> maximumRetailPriceOfTools();
	
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
	
}
