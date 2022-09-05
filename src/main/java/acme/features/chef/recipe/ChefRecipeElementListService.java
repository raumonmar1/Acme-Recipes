package acme.features.chef.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service 
public class ChefRecipeElementListService implements AbstractListService<Chef, Element>{
	
	@Autowired
	protected ChefRecipeRepository repository;
	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;
		boolean res=false;
		int id;
		int chefId;
		int userId;
		id=request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		chefId = recipe.getChef().getId();
		userId= request.getPrincipal().getAccountId();
		final Chef chef = this.repository.findChefByUserAccountId(userId);
		final int chefIdUser=chef.getId();

		if(chefId == chefIdUser) {
			res=true;
		}

		
		return res;
	}

	@Override
	public Collection<Element> findMany(final Request<Element> request) {
		 final Collection<Element> result = new HashSet<Element>();
		int recipeId;
		recipeId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findQuantityByRecipeId(recipeId);
		
		
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
		
		
 
		request.unbind(entity, model, "type", "name", "code"); 
		 
	}

}
