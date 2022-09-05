package acme.features.administrator.dashboard;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDishStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result;
		
		final Integer totalNumberOfIngredients;
		final Map<String,Double> averageRetailPriceOfIngredientsByCurrency = new HashMap<>();
		final Map<String,Double> deviationRetailPriceOfIngredientsByCurrency = new HashMap<>();
		final Map<String,Double> minimumRetailPriceOfIngredientsByCurrency = new HashMap<>();
		final Map<String,Double> maximumRetailPriceOfIngredientsByCurrency = new HashMap<>();

		final int totalNumberOfKitchenUtensils;
		final Map<String,Double> averageRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		final Map<String,Double> deviationRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		final Map<String,Double> minimumRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		final Map<String,Double> maximumRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();

		final EnumMap<FineDishStatus, Integer> totalNumberOfFineDishesByStatus = new EnumMap<>(FineDishStatus.class);
		final Map<Pair<FineDishStatus,String>, Double> averageBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> deviationBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> minimumBudgetOfFineDishesByStatus = new HashMap<>();
		final Map<Pair<FineDishStatus,String>, Double> maximumBudgetOfFineDishesByStatus = new HashMap<>();

		final String acceptedCurrencies = this.repository.findSystemCurrencies();
		final String[] split = acceptedCurrencies.split(","); 
		final List<String> currencies = Arrays.asList(split);
		
		totalNumberOfIngredients = this.repository.totalNumberOfIngredients();
		for(final String currency: currencies) {
			this.repository.averageRetailPriceOfIngredientsByCurrency().stream()
			.forEach(x-> averageRetailPriceOfIngredientsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationRetailPriceOfIngredientsByCurrency().stream()
			.forEach(x-> deviationRetailPriceOfIngredientsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumRetailPriceOfIngredientsByCurrency().stream()
			.forEach(x-> minimumRetailPriceOfIngredientsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumRetailPriceOfIngredientsByCurrency().stream()
			.forEach(x-> maximumRetailPriceOfIngredientsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		
		totalNumberOfKitchenUtensils = this.repository.totalNumberOfKitchenUtensils();
		for(final String currency: currencies) {
			this.repository.averageRetailPriceOfKitchenUtensilsByCurrency().stream()
			.forEach(x-> averageRetailPriceOfKitchenUtensilsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.deviationRetailPriceOfKitchenUtensilsByCurrency().stream()
			.forEach(x-> deviationRetailPriceOfKitchenUtensilsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.minimumRetailPriceOfKitchenUtensilsByCurrency().stream()
			.forEach(x-> minimumRetailPriceOfKitchenUtensilsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
		for(final String currency: currencies) {
			this.repository.maximumRetailPriceOfKitchenUtensilsByCurrency().stream()
			.forEach(x-> maximumRetailPriceOfKitchenUtensilsByCurrency
				.put(currency, Double.valueOf(x.get(1).toString())));
		}
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
		
		result = new AdministratorDashboard();
		
		result.setTotalNumberOfIngredients(totalNumberOfIngredients);
		result.setAverageRetailPriceOfIngredientsByCurrency(averageRetailPriceOfIngredientsByCurrency);
		result.setDeviationRetailPriceOfIngredientsByCurrency(deviationRetailPriceOfIngredientsByCurrency);
		result.setMinimumRetailPriceOfIngredientsByCurrency(minimumRetailPriceOfIngredientsByCurrency);
		result.setMaximumRetailPriceOfIngredientsByCurrency(maximumRetailPriceOfIngredientsByCurrency);
		
		result.setTotalNumberOfKitchenUtensils(totalNumberOfKitchenUtensils);
		result.setAverageRetailPriceOfKitchenUtensilsByCurrency(averageRetailPriceOfKitchenUtensilsByCurrency);
		result.setDeviationRetailPriceOfKitchenUtensilsByCurrency(deviationRetailPriceOfKitchenUtensilsByCurrency);
		result.setMinimumRetailPriceOfKitchenUtensilsByCurrency(minimumRetailPriceOfKitchenUtensilsByCurrency);
		result.setMaximumRetailPriceOfKitchenUtensilsByCurrency(maximumRetailPriceOfKitchenUtensilsByCurrency);
		
		result.setTotalNumberOfFineDishesByStatus(totalNumberOfFineDishesByStatus);
		result.setAverageBudgetOfFineDishesByStatus(averageBudgetOfFineDishesByStatus);
		result.setDeviationBudgetOfFineDishesByStatus(deviationBudgetOfFineDishesByStatus);
		result.setMinimumBudgetOfFineDishesByStatus(minimumBudgetOfFineDishesByStatus);
		result.setMaximumBudgetOfFineDishesByStatus(maximumBudgetOfFineDishesByStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "totalNumberOfIngredients", "averageRetailPriceOfIngredientsByCurrency", 
			"deviationRetailPriceOfIngredientsByCurrency", "minimumRetailPriceOfIngredientsByCurrency",
			"maximumRetailPriceOfIngredientsByCurrency", "totalNumberOfKitchenUtensils",
			"averageRetailPriceOfKitchenUtensilsByCurrency", "deviationRetailPriceOfKitchenUtensilsByCurrency",
			"minimumRetailPriceOfKitchenUtensilsByCurrency", "maximumRetailPriceOfKitchenUtensilsByCurrency",
			"totalNumberOfFineDishesByStatus", "averageBudgetOfFineDishesByStatus",
			"deviationBudgetOfFineDishesByStatus", "minimumBudgetOfFineDishesByStatus",
			"maximumBudgetOfFineDishesByStatus");
		
	}

}
