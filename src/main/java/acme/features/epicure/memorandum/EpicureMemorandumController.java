package acme.features.epicure.memorandum;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandums.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure; 
 
@Controller 
public class EpicureMemorandumController extends AbstractController<Epicure, Memorandum> { 
	//Internal State 
	 
	@Autowired 
	protected EpicureMemorandumListService listRecentService; 
	@Autowired 
	protected EpicureMemorandumShowService showService; 
	 
	 
	//Constructors 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
	} 
 
} 
