package acme.features.chef.fineDish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.entities.fineDishes.FineDishStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef; 
 
@Service 
public class ChefFineDishUpdateService implements AbstractUpdateService<Chef, FineDish>{ 
 
	@Autowired 
	protected ChefFineDishRepository repository; 
	  
	@Override 
	public boolean authorise(final Request<FineDish> request) { 
		assert request != null; 
		 
		final boolean result;
		int fineDishId;
		final FineDish fineDish; 
		final Chef chef; 
		
		fineDishId=request.getModel().getInteger("id");
		fineDish=this.repository.findFineDishById(fineDishId); 
		chef=fineDish.getChef(); 
		 
		result= request.isPrincipal(chef) && fineDish.getStatus().equals(FineDishStatus.PROPOSED);
		 
		 
		return result; 
	} 
 
	@Override 
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) { 
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		request.bind(entity, errors,"code", "budget", "request", "info", "startDate","finishDate","status","epicure.userAccount.username","epicure.organisation","epicure.info","epicure.assertion"); 
		 
	} 
 
	@Override 
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		request.unbind(entity, model,"code", "budget", "request", "info", "startDate","finishDate","status","epicure.userAccount.username","epicure.organisation","epicure.info","epicure.assertion"); 
		model.setAttribute("confirmation", false); 
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
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) { 
		
		assert request != null; 
		assert entity != null; 
		assert errors != null; 
		 
	} 
 
	@Override 
	public void update(final Request<FineDish> request, final FineDish entity) { 
		assert request != null; 
		assert entity != null;
		this.repository.save(entity); 
		 
	} 
 
} 