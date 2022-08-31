package acme.features.any.recipe;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
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
		
		final String payload;
		final StringBuilder bufferName = new StringBuilder();
		final StringBuilder bufferCode = new StringBuilder();
		final StringBuilder bufferDescription = new StringBuilder();
		final StringBuilder bufferAmountUnit = new StringBuilder();
		
		final int recipeId = entity.getId(); 
		final Collection<Element> elements = this.repository.findManyElementsByRecipeId(recipeId);
		
		int size = elements.size(); 
		
		for(final Element i: elements) {
			bufferName.append(i.getName());
			bufferCode.append(i.getCode());
			bufferDescription.append(i.getDescription());
			bufferAmountUnit.append(i.getAmountUnit());
			
			if(size > 1) {
				bufferName.append(",");
				bufferCode.append(",");
				bufferDescription.append(",");
				bufferAmountUnit.append(",");
				
				size--;
			}	
		}
		
		payload = String.format(//
			"%s; %s; %s; %s", //
			bufferName.toString(),bufferCode.toString(),bufferDescription.toString(),bufferAmountUnit.toString());
	
		request.unbind(entity, model, "code","heading", 
			"description","preparationNotes", "optionalLink", 
			"published", "retailPrice");
		
		model.setAttribute("payload", payload);
		
		
		
		
	}

}