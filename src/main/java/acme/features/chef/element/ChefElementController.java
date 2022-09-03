package acme.features.chef.element;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.elements.Element;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefElementController extends AbstractController<Chef, Element> {
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefElementListMineService	itemListMineService;

	@Autowired
	protected ChefElementShowService	itemShowService;
	
//	@Autowired
//	protected ChefRecipeElementListService RecipeElementListService;


	@PostConstruct
	protected void initialise() {
		super.addCommand( "list", this.itemListMineService);
		super.addCommand("show", this.itemShowService);
//		super.addCommand("listToolkitItems", "list", this.toolkitItemListService);
		
	}
	
}
