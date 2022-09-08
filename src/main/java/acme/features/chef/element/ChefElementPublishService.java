package acme.features.chef.element;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.elements.ElementType;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefElementPublishService implements AbstractUpdateService<Chef, Element>{

	@Autowired
	protected ChefElementRepository repository;
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;
		
		final boolean result;
		int elementId;
		final Element element;
		final Chef chef;
		
		elementId=request.getModel().getInteger("id");
		element=this.repository.findElementById(elementId);
		chef=element.getChef();
		
		result= !element.isPublished() && request.isPrincipal(chef);
		
		
		return result;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "type", "name", "code", "amountUnit", "description", "retailPrice", "link"); 
		
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "amountUnit", "description", "retailPrice", "link", "chef.userAccount.username", "published");
		
	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;
		
		Element result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findElementById(id);
		return result;
	}
	
	public boolean validateCurrencyRetailPrice(final Money retailPrice) {
		final boolean acceptedCurrency;
		
		final String currencies = this.repository.findAcceptedCurrencies().replace(" ", "");
		final List<Object> listCurrencies = Arrays.asList(currencies.split(","));
		acceptedCurrency = listCurrencies.contains(retailPrice.getCurrency());
		
		return acceptedCurrency;
		
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Money retailPrice = entity.getRetailPrice();
		
//		if (!errors.hasErrors("name")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getName(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "name", "form.error.spam");
//        }
//		if (!errors.hasErrors("technology")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getTechnology(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "technology", "form.error.spam");
//        }
//		if (!errors.hasErrors("description")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getDescription(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "description", "form.error.spam");
//        }
	
		
		if (!errors.hasErrors("code")) {
			Element existing;

			existing = this.repository.findOneElementByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "chef.element.form.error.duplicated");
		}
		
		if(!errors.hasErrors("retailPrice")){
			
			final boolean acceptedCurrency = this.validateCurrencyRetailPrice(retailPrice);
			errors.state(request, acceptedCurrency, "retailPrice", "chef.element.form.error.retail-price-currency-not-accepted");
			
			if(entity.getType().equals(ElementType.INGREDIENT)) {
				
				final boolean retailPriceComponentPositive = retailPrice.getAmount() > 0.;
				errors.state(request, retailPriceComponentPositive, "retailPrice", "chef.element.form.error.retail-price-component-positive");
				
			} else if(entity.getType().equals(ElementType.KITCHEN_UTENSIL)) {
				final boolean retailPriceToolZeroOrPositive = retailPrice.getAmount() >= 0.;
				errors.state(request, retailPriceToolZeroOrPositive, "retailPrice", "chef.element.form.error.retail-price-tool-zero-or-positive");
				
			} 
			
		}
		
	}

	@Override
	public void update(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}