package acme.features.chef.memorandum;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandums.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef; 
 
@Controller 
public class ChefMemorandumController extends AbstractController<Chef, Memorandum> { 
	//Internal State 
	 
	@Autowired 
	protected ChefMemorandumListService listRecentService; 
	@Autowired 
	protected ChefMemorandumShowService showService; 
	 
	 
	//Constructors 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
	} 
 
} 
