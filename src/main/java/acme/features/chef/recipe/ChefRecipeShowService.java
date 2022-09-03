
package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {

	@Autowired
	protected ChefRecipeRepository repository;


	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		boolean res = false;
		int id;
		int chefId;
		int userId;
		id = request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneRecipeById(id);
		chefId = recipe.getChef().getId();
		userId = request.getPrincipal().getAccountId();
		final Chef chef = this.repository.findChefByUserAccountId(userId);
		final int chefIdUser = chef.getId();

		if (chefId == chefIdUser) {
			res = true;
		}

		return res;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneRecipeById(id);
		
//		result.setRetailPrice(this.retailPriceOfRecipe(id));
		
		
		
		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", 
			"description","preparationNotes","published", "link", 
			"chef.userAccount.username");

	}

}
