package acme.features.chef.element;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefElementDeleteService implements AbstractDeleteService<Chef, Element>{

	@Autowired
	protected ChefElementRepository repository;
	
	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;
		
		final boolean result;
		int elementId;
		final Element element;
		final Chef chef;
		
		elementId=request.getModel().getInteger("id");
		element=this.repository.findElementById(elementId);
		chef=element.getChef();
		
		result= !element.isPublished() && request.isPrincipal(chef);
		
		
		return result;
	}

	@Override
	public void bind(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "type", "name", "code", "amountUnit", "description", "retailPrice", "link"); 
		
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "amountUnit", "description", "retailPrice", "link", "chef.userAccount.username", "published"); 
		
	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;
		
		Element result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findElementById(id);
		return result;
	}

	@Override
	public void validate(final Request<Element> request, final Element entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Element> request, final Element entity) {
		assert request != null;
		assert entity != null;
		
		final Collection<Quantity> quantities = this.repository.findQuantityByElementId(entity.getId());
		
		for(final Quantity q: quantities) {
			this.repository.delete(q);
		}
		
		
		this.repository.delete(entity);
	}

}