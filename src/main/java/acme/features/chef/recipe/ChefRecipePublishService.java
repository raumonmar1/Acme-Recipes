package acme.features.chef.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipePublishService implements AbstractUpdateService<Chef, Recipe>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		final boolean result;
		int recipeId;
		final Recipe recipe;
		final Chef chef;
		
		recipeId=request.getModel().getInteger("id");
		recipe=this.repository.findOneRecipeById(recipeId);
		chef=recipe.getChef();
		
		result= !recipe.isPublished() && request.isPrincipal(chef);
		
		
		return result;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","heading", 
			"description","preaparationNotes", "link", 
			"chef.userAccount.username","retailPrice");
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));

		request.unbind(entity, model,"code","heading", 
			"description","preparationNotes","published", "link", 
			"chef.userAccount.username","retailPrice");
		
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findOneRecipeById(id);
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByRecipeId(recipeId);
		final Collection<Element> elements = new HashSet<Element>();
		Boolean publishElement = true;
		
		if(!errors.hasErrors("code")) {
			Recipe existing;
			
			existing = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "chef.recipe.form.error.duplicated");
		}
		
//		
//		if (!errors.hasErrors("title")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getTitle(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "title", "form.error.spam");
//        }
//		if (!errors.hasErrors("assemblyNotes")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getAssemblyNotes(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "assemblyNotes", "form.error.spam");
//        }
//		if (!errors.hasErrors("description")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getDescription(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "description", "form.error.spam");
//        }
		
		for(final Quantity quantity: quantities) {
			final int id=quantity.getId();
			final Collection<Element> element=this.repository.findManyElementByQuantityId(id);
			elements.addAll(element);
		}
		errors.state(request, !elements.isEmpty(), "*", "chef.recipe.form.error.no-elements");
		
		for (final Element element : elements) {
			publishElement= publishElement && element.isPublished();
		}
		
		
		errors.state(request, publishElement, "*", "chef.recipe.form.error.no-elements-published");
		
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}

}