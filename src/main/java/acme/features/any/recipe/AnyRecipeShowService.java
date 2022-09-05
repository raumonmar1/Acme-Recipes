package acme.features.any.recipe;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyRecipeRepository repository;
			
		@Override
		public boolean authorise(final Request<Recipe> request) {
			assert request != null;
			int id;
			id=request.getModel().getInteger("id");
			final Recipe recipe = this.repository.findOneRecipeById(id);
			return	recipe.isPublished();
		}

		@Override
		public Recipe findOne(final Request<Recipe> request) {
			assert request != null;

			Recipe result;
			int id;
			
			id = request.getModel().getInteger("id");
			result = this.repository.findOneRecipeById(id);
			result.setRetailPrice(this.price(id));
			
			return result;
		}
		
		public MoneyExchange convertir(final Money money) {
			
			final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();
			MoneyExchange m = new MoneyExchange();
			final String systemCurrency = this.repository.findSystemCurrency();

			if(!money.getCurrency().equals(systemCurrency)) {
				m = this.repository.findMoneyExchange(money.getCurrency(), money.getAmount(),systemCurrency);
				if(m == null) {
					m = moneyExchange.computeMoneyExchange(money, systemCurrency);
					this.repository.save(m);
				}
			}else {
				m.setSource(money);
				m.setTarget(money);
				m.setCurrency(systemCurrency);
				m.setDate(new Date(System.currentTimeMillis()));
			}
			return m;
		}
		
		public Money price(final int recipeId) {
			final Money result = new Money();
			result.setAmount(0.);
			result.setCurrency(this.repository.findSystemCurrency());
			final Collection<Quantity> quantities = this.repository.findQuantityByRecipeId(recipeId);
					
			for(final Quantity q: quantities) {
					final Double c;
					final Money money = q.getElement().getRetailPrice();
					final double number = q.getAmount();
					
					c = this.convertir(money).getTarget().getAmount();
					
					final Double newAmount = Math.round((result.getAmount() + c * number) * 100)/100.0;
					result.setAmount(newAmount);
			}
			
			return result;
		}

		@Override
		public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("chef", entity.getChef().getUserAccount().getUsername());
			model.setAttribute("retailPrice",entity.getRetailPrice());
			
			request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "published",
				"link", "retailPrice", "chef.userAccount.username");
			
		}
		
		
		
		

}