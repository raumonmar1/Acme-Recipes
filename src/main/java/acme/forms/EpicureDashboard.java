package acme.forms;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EpicureDashboard {
	
		// Serialisation identifier ----------------------------------------------
	
				protected static final long		serialVersionUID 		= 1L;
				
				// Atributes -----------------------------------------------------
				
				int totalNumberOfProposedFineDishes;
				int totalNumberOfAcceptedFineDishes;
				int totalNumberofDeniedFineDishes;
				
				Map<String, Double> averageBudgetOfProposedFineDishesByCurrency;
				Map<String, Double> deviationBudgetOfProposedFineDishesByCurrency;
				Map<String, Double> minimumBudgetOfProposedFineDishesByCurrency;
				Map<String, Double> maximumBudgetOfProposedFineDishesByCurrency;
				
				Map<String, Double> averageBudgetOfAcceptedFineDishesByCurrency;
				Map<String, Double> deviationBudgetOfAcceptedFineDishesByCurrency;
				Map<String, Double> minimumBudgetOfAcceptedFineDishesByCurrency;
				Map<String, Double> maximumBudgetOfAceeptedFineDishesByCurrency;
				
				Map<String, Double> averageBudgetOfDeniedFineDishesByCurrency;
				Map<String, Double> deviationBudgetOfDeniedFineDishesByCurrency;
				Map<String, Double> minimumBudgetOfDeniedFineDishesByCurrency;
				Map<String, Double> maximumBudgetOfDeniedFineDishesByCurrency;
}
