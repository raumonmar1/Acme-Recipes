package acme.features.any.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyRecipeRepository repository;
			
		@Override
		public boolean authorise(final Request<Recipe> request) {
			assert request != null;
			int id;
			id=request.getModel().getInteger("id");
			final Recipe recipe = this.repository.findOneRecipeById(id);
			return	recipe.isPublished();
		}

		@Override
		public Recipe findOne(final Request<Recipe> request) {
			assert request != null;

			Recipe result;
			int id;
			
			id = request.getModel().getInteger("id");
			result = this.repository.findOneRecipeById(id);
//			result.setRetailPrice(this.retailPriceOfRecipe(id));
			return result;
		}

		@Override
		public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("chef", entity.getChef().getUserAccount().getUsername());
			
//			model.setAttribute("retailPrice",entity.getRetailPrice());
			
			request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "published",
				"info", "chef.userAccount.username");
			
		}
		
		
		
		

}