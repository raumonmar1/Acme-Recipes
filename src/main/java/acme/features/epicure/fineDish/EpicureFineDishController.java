package acme.features.epicure.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDishes.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure; 
 
@Controller 
public class EpicureFineDishController extends AbstractController<Epicure, FineDish> { 
	//Internal State 
	 
	@Autowired 
	protected EpicureFineDishListService listRecentService; 
	@Autowired 
	protected EpicureFineDishShowService showService; 
	 
	 
	//Constructors 
	@PostConstruct 
	protected void initialise() { 
		super.addCommand("show", this.showService); 
		super.addCommand("list", this.listRecentService); 
	} 
 
} 
