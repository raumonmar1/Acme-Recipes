package acme.features.any.element;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.elements.Element;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyElementController extends AbstractController<Any, Element>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyElementListPublishedService		elementListPublishedService;
	

	@Autowired
	protected AnyElementShowService	showService;
	
	
//	@Autowired
//	protected AnyRecipeElementListService recipeElementListService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.elementListPublishedService);
//		super.addCommand("listRecipeElements", "list", this.recipeElementListService);
		super.addCommand("show", this.showService);
	}

}
