package acme.features.chef.memorandum;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.memorandums.Memorandum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
 
@Repository 
public interface ChefMemorandumRepository extends AbstractRepository{ 
	 
	@Query("Select m from Memorandum m where m.fineDish.chef.id = :id") 
	Collection<Memorandum> findMemorandumsByChefId(@Param("id")int id); 
	@Query("Select c from Chef c where c.userAccount.id = :id")
	Chef findChefByUserAccountId(@Param("id")int id);
	@Query("Select m from Memorandum m where m.id = :id") 
	Memorandum findMemorandumById(@Param("id")int id); 
} 
