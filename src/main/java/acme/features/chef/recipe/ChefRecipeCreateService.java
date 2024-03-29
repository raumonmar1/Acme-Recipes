package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe>{
	@Autowired 
	protected AdministratorSystemConfigurationRepository scRepository; 
	
	@Autowired
	protected ChefRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request !=null;
		return true;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","heading", 
			"description","preparationNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code","heading", 
			"description","preparationNotes","published", "link", 
			"chef.userAccount.username","retailPrice");
		
	}

	@Override
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;
		Recipe result;
		final Chef chef;
		
		chef=this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		result= new Recipe();
		result.setPublished(false);
		result.setChef(chef);
		return result;
	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
//		if (!errors.hasErrors("title")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getTitle(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "title", "form.error.spam");
//        }
//		if (!errors.hasErrors("assemblyNotes")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getAssemblyNotes(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "assemblyNotes", "form.error.spam");
//        }
//		if (!errors.hasErrors("description")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getDescription(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "description", "form.error.spam");
//        }
//		if(!errors.hasErrors("code")) {
			Recipe existing;
			
			existing = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.recipe.form.error.duplicated");
		}
		


	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		entity.setChef(this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId()));
		entity.setPublished(false);
		this.repository.save(entity);
		
	}

}