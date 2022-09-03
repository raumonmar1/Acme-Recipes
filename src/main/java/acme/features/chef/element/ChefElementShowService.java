package acme.features.chef.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefElementShowService implements AbstractShowService<Chef, Element> {

	@Autowired
	protected ChefElementRepository repository;


	@Override
	public boolean authorise(final Request<Element> request) {
		assert request != null;

		boolean result;
		int elementId;
		Element element;

		elementId = request.getModel().getInteger("id");
		element = this.repository.findElementById(elementId);
		result = element != null && element.getChef().getId() == request.getPrincipal().getActiveRoleId();

		return result;

	}

	@Override
	public Element findOne(final Request<Element> request) {
		assert request != null;

		Element result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findElementById(id);
		return result;
	}

	@Override
	public void unbind(final Request<Element> request, final Element entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "amountUnit", "description", "retailPrice", "link", "chef.userAccount.username", "published");

	}
}