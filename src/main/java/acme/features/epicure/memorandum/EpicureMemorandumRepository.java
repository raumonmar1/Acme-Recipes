package acme.features.epicure.memorandum;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.memorandums.Memorandum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure;
 
@Repository 
public interface EpicureMemorandumRepository extends AbstractRepository{ 
	 
	@Query("Select m from Memorandum m where m.fineDish.epicure.id = :id") 
	Collection<Memorandum> findMemorandumsByEpicureId(@Param("id")int id); 
	@Query("Select e from Epicure e where e.userAccount.id = :id")
	Epicure findEpicureByUserAccountId(@Param("id")int id);
	@Query("Select m from Memorandum m where m.id = :id") 
	Memorandum findMemorandumById(@Param("id")int id); 
} 
