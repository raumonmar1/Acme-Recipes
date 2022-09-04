package acme.features.epicure.memorandum;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure; 
 
@Service 
public class EpicureMemorandumListService implements AbstractListService<Epicure, Memorandum> { 
	 
	@Autowired 
	protected EpicureMemorandumRepository repository; 
 
	@Override 
	public boolean authorise(final Request<Memorandum> request) { 
		assert request != null; 
		return true; 
	} 
 
	@Override 
	public Collection<Memorandum> findMany(final Request<Memorandum> request) { 
		assert request != null; 
		 
		final Collection<Memorandum> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int epicureId = this.repository.findEpicureByUserAccountId(UAId).getId();
		result=this.repository.findMemorandumsByEpicureId(epicureId); 
		
		return result;
	} 
 
	@Override 
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		request.unbind(entity, model, "instantiationMoment","sequenceNumber", "info");		 
	} 
 
} 
