package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefFineDishRepository extends AbstractRepository {

	@Query("Select f from FineDish f where f.chef.id = :id") 
	Collection<FineDish> findFineDishByChefId(@Param("id")int id); 
	@Query("Select c from Chef c where c.userAccount.id = :id")
	Chef findChefByUserAccountId(@Param("id")int id); 
	@Query("Select f from FineDish f where f.id = :id") 
	FineDish findFineDishById(@Param("id")int id); 
	
}
