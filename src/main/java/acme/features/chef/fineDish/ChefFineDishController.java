package acme.features.chef.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDishes.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef; 

@Controller
public class ChefFineDishController extends AbstractController<Chef, FineDish>{
	//Internal State 
	 
		@Autowired 
		protected ChefFineDishListService listPublishedAcceptedAndDeniedService; 
		@Autowired 
		protected ChefFineDishShowService showService;
		@Autowired 
		protected ChefFineDishListProposedService listPublishedProposedService; 
		@Autowired 
		protected ChefFineDishUpdateService updateService; 
		 
		 
		//Constructors 
		@PostConstruct 
		protected void initialise() { 
			super.addCommand("show", this.showService); 
			super.addCommand("list", this.listPublishedAcceptedAndDeniedService); 
			super.addCommand("list-proposed","list", this.listPublishedProposedService); 
			super.addCommand("update", this.updateService);
		} 
	 
	} 
