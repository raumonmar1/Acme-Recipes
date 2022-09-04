package acme.forms;

import java.util.Map;

import org.springframework.data.util.Pair;

import acme.entities.fineDishes.FineDishStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard {
	
	// Serialisation identifier ----------------------------------------------
	
		protected static final long		serialVersionUID 		= 1L;
				
	// Atributes -----------------------------------------------------
		
		Map<FineDishStatus, Integer> totalNumberOfFineDishesByStatus;
		Map<Pair<FineDishStatus,String>, Double> averageBudgetOfFineDishesByStatus;
		Map<Pair<FineDishStatus,String>, Double> deviationBudgetOfFineDishesByStatus;
		Map<Pair<FineDishStatus,String>, Double> minimumBudgetOfFineDishesByStatus;
		Map<Pair<FineDishStatus,String>, Double> maximumBudgetOfFineDishesByStatus;
}
