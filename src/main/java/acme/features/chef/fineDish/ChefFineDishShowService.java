package acme.features.chef.fineDish;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish> {

	@Autowired 
	protected ChefFineDishRepository repository; 
	
	@Override 
	public boolean authorise(final Request<FineDish> request) { 
		assert request != null;
		boolean result;
		final int fineDishID;
		Chef chef;
		FineDish fDish;
		
		fineDishID = request.getModel().getInteger("id");
		fDish = this.repository.findFineDishById(fineDishID);
		chef =this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		result = fDish.getChef().equals(chef);
		
		return result; 
	} 
 
	@Override 
	public FineDish findOne(final Request<FineDish> request) { 
		assert request != null; 
		 
		FineDish result; 
		int id; 
		 
		id=request.getModel().getInteger("id"); 
		result=this.repository.findFineDishById(id);
		
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
 
	@Override 
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		final boolean dif = !entity.getBudget().getCurrency().equals(this.repository.findSystemCurrency());
		model.setAttribute("dif", dif);
		model.setAttribute("convertir", this.convertir(entity.getBudget()).getTarget());
		 
		request.unbind(entity, model, "code", "budget", "info","request","startDate","finishDate","status","epicure.userAccount.username","epicure.organisation","epicure.info","epicure.assertion");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
}
