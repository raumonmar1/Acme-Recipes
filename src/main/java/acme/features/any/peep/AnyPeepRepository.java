
package acme.features.any.peep; 
 
import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.peeps.Peep;
import acme.framework.repositories.AbstractRepository; 
 
@Repository 
public interface AnyPeepRepository extends AbstractRepository{ 
	 
	@Query("Select p from Peep p where p.moment > :fechaLimite") 
	Collection<Peep> findRecentPeeps(@Param("fechaLimite")Date fechaLimite); 

	@Query("Select p from Peep p where p.id = :id") 
	Peep findChirpById(@Param("id")int id);
} 