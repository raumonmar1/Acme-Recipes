package acme.features.epicure.fineDish;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure; 
 
@Service 
public class EpicureFineDishListService implements AbstractListService<Epicure, FineDish> { 
	 
	@Autowired 
	protected EpicureFineDishRepository repository; 
 
	@Override 
	public boolean authorise(final Request<FineDish> request) { 
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<FineDish> findMany(final Request<FineDish> request) { 
		assert request != null; 
		 
		final Collection<FineDish> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int epicureId = this.repository.findEpicureByUserAccountId(UAId).getId();
		result=this.repository.findFineDishesByEpicureId(epicureId); 
		
		return result;
	} 
 
	@Override 
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		request.unbind(entity, model, "code","budget","request", "info", "startDate","finishDate","status");		 
	} 
 
} 
