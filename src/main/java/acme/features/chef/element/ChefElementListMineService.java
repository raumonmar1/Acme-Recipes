package acme.features.chef.element; 
 
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;
 
@Service 
public class ChefElementListMineService implements AbstractListService<Chef, Element> { 
	// Internal state --------------------------------------------------------- 
 
	@Autowired 
	protected ChefElementRepository repository; 
	 
	 
	@Override 
	public boolean authorise(final Request<Element> request) { 
		assert request != null; 
 
		return true; 
	} 
 
	@Override 
	public Collection<Element> findMany(final Request<Element> request) { 
		assert request != null; 
 
		Collection<Element> result; 
		final int UAId = request.getPrincipal().getAccountId();
		final int ChefId = this.repository.findChefByUserAccountId(UAId).getId();
		result = this.repository.findManyElementsByChefId(ChefId); 
 
		return result; 
	} 
 
	@Override 
	public void unbind(final Request<Element> request, final Element entity, final Model model) { 
		assert request != null; 
		assert entity != null; 
		assert model != null; 
		
		request.unbind(entity, model, "type", "name", "code"); 
		 
		 
	} 
 
} 
