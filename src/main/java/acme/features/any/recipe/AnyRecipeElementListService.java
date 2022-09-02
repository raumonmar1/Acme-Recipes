package acme.features.any.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service 
public class AnyRecipeElementListService implements AbstractListService<Any, Element>{
	
	@Autowired
	protected AnyRecipeRepository repository;
	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;
		int id;
		id=request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		return	recipe.isPublished();
	}

	@Override
	public Collection<Element> findMany(final Request<Element> request) {
		 final Collection<Element> result = new HashSet<Element>();
		int recipeid;
		recipeid = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByRecipeId(recipeid);
		
		
		for(final Quantity quantity: quantities) {
			final int id=quantity.getId();
			final Collection<Element> elements=this.repository.findManyElementByQuantityId(id);
			result.addAll(elements);
		}
		
		return result;
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		
 
		request.unbind(entity, model, "type", "name","code", "amountUnit", "retailPrice"); 
		 
	}

}