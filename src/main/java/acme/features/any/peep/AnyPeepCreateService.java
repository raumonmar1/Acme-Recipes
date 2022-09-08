package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peeps.Peep;
import acme.features.administrator.systemConfiguration.AdministratorSystemConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep> {
	
	// Internal state ---------------------------------------------------------
		@Autowired
		protected AdministratorSystemConfigurationRepository scRepository;
		@Autowired
		protected AnyPeepRepository repository;

	// AbstractCreateService<Administrator, Chirp> interface --------------


	@Override
	public boolean authorise(final Request<Peep> request) {
		
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "heading", "pieceOfText", "writer", "email");
		
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "heading", "pieceOfText", "writer", "email");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", false);
		
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request != null;

		final Peep result;
		Date moment;

		result = new Peep();
		moment = new Date(System.currentTimeMillis() - 1);
		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
//		if (!errors.hasErrors("title")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getTitle(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "title", "form.error.spam");
//        }
//		if (!errors.hasErrors("body")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getBody(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "body", "form.error.spam");
//        }
//		if (!errors.hasErrors("author")) {
//            errors.state(request, SpamFilter.spamValidator(entity.getAuthor(), this.scRepository.findWeakSpamWords(), this.scRepository.findStrongSpamWords(),this.scRepository.findWeakSpamThreshold(),this.scRepository.findStrongSpamThreshold()), "author", "form.error.spam");
//        }
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "any.Peep.confirmation.error");
		
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
		
	}

}