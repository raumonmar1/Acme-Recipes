package acme.features.epicure.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository{
	
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
