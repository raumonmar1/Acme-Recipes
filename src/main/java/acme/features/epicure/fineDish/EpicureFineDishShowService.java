package acme.features.epicure.fineDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure; 
 
@Service 
public class EpicureFineDishShowService implements AbstractShowService<Epicure, FineDish>{ 
	 
	 
	@Autowired 
	protected EpicureFineDishRepository repository; 
	
	@Override 
	public boolean authorise(final Request<FineDish> request) { 
		assert request != null;
		boolean result;
		int fineDishID;
		Epicure epicure;
		FineDish fDish;
		
		fineDishID = request.getModel().getInteger("id");
		fDish = this.repository.findFineDishesById(fineDishID);
		epicure =this.repository.findEpicureByUserAccountId(request.getPrincipal().getAccountId());
		result = fDish.getEpicure().equals(epicure);
		return result; 
	} 
 
	@Override 
	public FineDish findOne(final Request<FineDish> request) { 
		assert request != null; 
		 
		FineDish result; 
		int id; 
		 
		id=request.getModel().getInteger("id"); 
		result=this.repository.findFineDishesById(id);
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		 
		request.unbind(entity, model, "code", "budget", "request", "info", "startDate","finishDate","status","chef.userAccount.username","chef.organisation","chef.info","chef.assertion");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
 
} 