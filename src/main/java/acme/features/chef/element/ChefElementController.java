package acme.features.chef.element;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.elements.Element;
import acme.features.chef.recipe.ChefRecipeElementListService;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefElementController extends AbstractController<Chef, Element> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefElementListMineService	elementListMineService;

	@Autowired
	protected ChefElementShowService	elementShowService;
	
	@Autowired
	protected ChefRecipeElementListService recipeElementListService;
	
	@Autowired
	protected ChefElementDeleteService deleteService;
	
	@Autowired
	protected ChefElementUpdateService updateService;
	
	@Autowired
	protected ChefElementPublishService publishService;
	
	@Autowired
	protected ChefElementCreateService createService;


	@PostConstruct
	protected void initialise() {
		super.addCommand( "list", this.elementListMineService);
		super.addCommand("show", this.elementShowService);
		super.addCommand("listRecipeElements", "list", this.recipeElementListService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
		super.addCommand("create", this.createService); 
		
	}
	
}
