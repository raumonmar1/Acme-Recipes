/*
 * AuthenticatedProviderRepository.java
 *
 * Copyright (C) 2012-2022 Alejandro Baños.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.epicure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;

@Repository
public interface AuthenticatedEpicureRepository extends AbstractRepository {

	@Query("select e from Epicure e where e.userAccount.id = :id")
	Epicure findOneEpicureByUserAccountId(@Param("id")int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(@Param("id")int id);

}
