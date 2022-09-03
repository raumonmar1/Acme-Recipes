package acme.features.chef.recipe;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Recipe;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefRecipeController extends AbstractController<Chef, Recipe>{
	// Internal state ---------------------------------------------------------

			@Autowired
			protected ChefRecipeListMineService	recipeListMineService;
			

			@Autowired
			protected ChefRecipeShowService	showService;
			
			@PostConstruct
			protected void initialise() {
				super.addCommand("list", this.recipeListMineService);
				super.addCommand("show", this.showService);
			}
}
