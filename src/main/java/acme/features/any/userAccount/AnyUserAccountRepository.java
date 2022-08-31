package acme.features.any.userAccount;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select distinct(ua) from UserAccount ua join ua.roles r where ua.enabled = true "
		+ "and (type(r) = Chef or type(r) = Epicure) and Administrator not in "
		+ "(select type(r) from UserAccount ua2 join ua2.roles r where ua2.id = ua.id)")
	Collection<UserAccount> findEnabledUserAccounts();
	

}