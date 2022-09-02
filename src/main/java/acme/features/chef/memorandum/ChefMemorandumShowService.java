package acme.features.chef.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef; 
 
@Service 
public class ChefMemorandumShowService implements AbstractShowService<Chef, Memorandum>{ 
	 
	 
	@Autowired 
	protected ChefMemorandumRepository repository; 
	
	@Override 
	public boolean authorise(final Request<Memorandum> request) { 
		assert request != null;
		boolean result;
		int memorandumID;
		final int userId;
		
		final Memorandum mem;
		
		userId = request.getPrincipal().getActiveRoleId();
		memorandumID = request.getModel().getInteger("id");
		mem = this.repository.findMemorandumById(memorandumID);
		result = mem.getFineDish().getChef().getId() == userId;
		return result; 
	} 
 
	@Override 
	public Memorandum findOne(final Request<Memorandum> request) { 
		assert request != null; 
		 
		Memorandum result; 
		int id; 
		 
		id=request.getModel().getInteger("id"); 
		result=this.repository.findMemorandumById(id);
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		 
		request.unbind(entity, model, "instantiationMoment","sequenceNumber", "report", "info");	 
		model.setAttribute("confirmation", false); 
		model.setAttribute("readonly", true); 
	} 
 
} 
