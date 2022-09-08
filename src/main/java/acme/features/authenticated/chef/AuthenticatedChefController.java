/*
 * AuthenticatedPatronController.java
 *
 * Copyright (C) 2012-2022 Alejandro Baños.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.chef;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.controllers.AbstractController;
import acme.framework.roles.Authenticated;
import acme.roles.Chef;

@Controller
@RequestMapping("/authenticated/chef/")
public class AuthenticatedChefController extends AbstractController<Authenticated, Chef> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedChefCreateService	createService;

	@Autowired
	protected AuthenticatedChefUpdateService	updateService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
	}

}
