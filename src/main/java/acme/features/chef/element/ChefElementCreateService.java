package acme.features.chef.element; 

import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.elements.ElementType;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;
 
@Service 
public class ChefElementCreateService implements AbstractCreateService<Chef, Element>{ 
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Autowired 
	protected ChefElementRepository repository; 
	
	@Autowired
	protected ChefRecipeRepository recipeRepo;
	 
	@Override 
	public boolean authorise(final Request<Element> request) { 
		assert request !=null; 
		return true;  
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
	public Element instantiate(final Request<Element> request) {
		assert request != null; 
		Element	 result; 
		Chef chef; 
		 
		chef=this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId()); 
		result= new Element(); 
		result.setPublished(false); 
		result.setChef(chef); 
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
//	
		
		if (!errors.hasErrors("code")) {
			Element existing;

			existing = this.repository.findOneElementByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.element.form.error.duplicated");
		}
		
		if(!errors.hasErrors("retailPrice")){
			
			final boolean acceptedCurrency = this.validateCurrencyRetailPrice(retailPrice);
			errors.state(request, acceptedCurrency, "retailPrice", "chef.element.form.error.retail-price-currency-not-accepted");
			
			if(entity.getType().equals(ElementType.INGREDIENT)) {
				
				final boolean retailPriceIngredientPositive = retailPrice.getAmount() > 0.;
				errors.state(request, retailPriceIngredientPositive, "retailPrice", "chef.element.form.error.retail-price-ingredient-positive");
				
			} else if(entity.getType().equals(ElementType.KITCHEN_UTENSIL)) {
				final boolean retailPriceKitchenUtensilZeroOrPositive = retailPrice.getAmount() >= 0.;
				errors.state(request, retailPriceKitchenUtensilZeroOrPositive, "retailPrice", "chef.element.form.error.retail-price-kitchen-utensil-zero-or-positive");
				
			} 
			
		}
		
	} 
 
	@Override
	public void create(final Request<Element> request, final Element entity) {
		assert request != null; 
		assert entity != null; 
		
		 
		entity.setChef(this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId())); 
		entity.setPublished(false); 
		
		
		this.repository.save(entity); 
		 
		
	}


}