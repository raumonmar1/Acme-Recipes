package acme.features.epicure.dashboard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDishStatus;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard> {

	@Autowired
	protected EpicureDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;

		final EpicureDashboard result;
		
		final EnumMap<FineDishStatus, Integer> totalNumberOfFineDishesByStatus = new EnumMap<>(FineDishStatus.class);
		final Map<Pair<FineDishStatus,String>, Double> averageBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> deviationBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> minimumBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> maximumBudgetOfFineDishesByStatus = new HashMap<>();

		final String acceptedCurrencies = this.repository.findSystemCurrencies();
		final String[] split = acceptedCurrencies.split(","); 
		final List<String> currencies = Arrays.asList(split);
		
		
		for(int i = 0; i < this.repository.totalNumberOfFineDishesByStatus().size(); i++) {
			this.repository.totalNumberOfFineDishesByStatus().stream()
			.forEach(x -> totalNumberOfFineDishesByStatus.put((FineDishStatus) x.get(0), Integer.parseInt(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.averageBudgetOfFineDishesByStatus().stream()
			.forEach(x-> averageBudgetOfFineDishesByStatus
				.put(Pair.of((FineDishStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationBudgetOfFineDishesByStatus()
			.forEach(x-> deviationBudgetOfFineDishesByStatus
				.put(Pair.of((FineDishStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumBudgetOfFineDishesByStatus().stream()
			.forEach(x-> minimumBudgetOfFineDishesByStatus
				.put(Pair.of((FineDishStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumBudgetOfFineDishesByStatus().stream()
			.forEach(x-> maximumBudgetOfFineDishesByStatus
				.put(Pair.of((FineDishStatus) x.get(0), currency), Double.valueOf(x.get(2).toString())));
		}
		
		result = new EpicureDashboard();
		
		result.setTotalNumberOfFineDishesByStatus(totalNumberOfFineDishesByStatus);
		result.setAverageBudgetOfFineDishesByStatus(averageBudgetOfFineDishesByStatus);
		result.setDeviationBudgetOfFineDishesByStatus(deviationBudgetOfFineDishesByStatus);
		result.setMinimumBudgetOfFineDishesByStatus(minimumBudgetOfFineDishesByStatus);
		result.setMaximumBudgetOfFineDishesByStatus(maximumBudgetOfFineDishesByStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfFineDishesByStatus", 
			"averageBudgetOfFineDishesByStatus", "deviationBudgetOfFineDishesByStatus",
			"minimumBudgetOfFineDishesByStatus", "maximumBudgetOfFineDishesByStatus");
	}

}
