package acme.features.any.element;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.forms.MoneyExchange;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyElementShowService implements AbstractShowService<Any, Element>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyElementRepository repository;
			
		@Override
		public boolean authorise(final Request<Element> request) {
			assert request != null;

			boolean result;
			int id;
			final Element element;

			id = request.getModel().getInteger("id");
			element = this.repository.findOneElementById(id);
			result = element.isPublished();

			return result;
		}

		@Override
		public Element findOne(final Request<Element> request) {
			assert request != null;

			Element result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneElementById(id);

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
		public void unbind(final Request<Element> request, final Element entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			final boolean dif = !entity.getRetailPrice().getCurrency().equals(this.repository.findSystemCurrency());
			model.setAttribute("dif", dif);
			model.setAttribute("convertir", this.convertir(entity.getRetailPrice()).getTarget());
			
			request.unbind(entity, model, "type", "name", "code",
				"description","retailPrice", "link","amountUnit", "chef.userAccount.username","published");
		}
}
