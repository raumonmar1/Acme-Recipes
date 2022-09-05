package acme.features.any.recipe;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListPublishedService implements AbstractListService<Any, Recipe> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyRecipeRepository repository;
	
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		
		assert request != null;

		Collection<Recipe> result;

		result = this.repository.findManyPublishedRecipes(true);

		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
	
		request.unbind(entity, model, "code", "heading", "link");		
		
	}

}