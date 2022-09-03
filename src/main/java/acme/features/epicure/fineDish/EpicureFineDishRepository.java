package acme.features.epicure.fineDish;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.FineDish;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure; 
 
@Repository 
public interface EpicureFineDishRepository extends AbstractRepository{ 
	 
	@Query("Select f from FineDish f where f.epicure.id = :id") 
	Collection<FineDish> findFineDishesByEpicureId(@Param("id")int id); 
	@Query("Select e from Epicure e where e.userAccount.id = :id")
	Epicure findEpicureByUserAccountId(@Param("id")int id);
	@Query("Select f from FineDish f where f.id = :id") 
	FineDish findFineDishesById(@Param("id")int id); 
} 
