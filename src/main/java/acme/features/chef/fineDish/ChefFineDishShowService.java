package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish> {

	@Autowired 
	protected ChefFineDishRepository repository; 
	
	@Override 
	public boolean authorise(final Request<FineDish> request) { 
		assert request != null;
		boolean result;
		final int fineDishID;
		Chef chef;
		FineDish fDish;
		
		fineDishID = request.getModel().getInteger("id");
		fDish = this.repository.findFineDishById(fineDishID);
		chef =this.repository.findChefByUserAccountId(request.getPrincipal().getAccountId());
		result = fDish.getChef().equals(chef);
		return result; 
	} 
 
	@Override 
	public FineDish findOne(final Request<FineDish> request) { 
		assert request != null; 
		 
		FineDish result; 
		int id; 
		 
		id=request.getModel().getInteger("id"); 
		result=this.repository.findFineDishById(id);
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		 
		request.unbind(entity, model, "code", "budget", "info", "startDate","finishDate","status","chef.userAccount.username","chef.organisation","chef.info","chef.assertion");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
}
