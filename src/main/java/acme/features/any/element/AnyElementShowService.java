package acme.features.any.element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.elements.Element;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyElementShowService implements AbstractShowService<Any, Element>{
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyElementRepository repository;
			
		@Override
		public boolean authorise(final Request<Element> request) {
			assert request != null;

			boolean result;
			int id;
			final Element element;

			id = request.getModel().getInteger("id");
			element = this.repository.findOneElementById(id);
			result = element.isPublished();

			return result;
		}

		@Override
		public Element findOne(final Request<Element> request) {
			assert request != null;

			Element result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findOneElementById(id);

			return result;
		}

		@Override
		public void unbind(final Request<Element> request, final Element entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			request.unbind(entity, model, "type", "name", "code",
				"description","retailPrice", "link","amountUnit", "chef.userAccount.username","published");
			
		}

}
