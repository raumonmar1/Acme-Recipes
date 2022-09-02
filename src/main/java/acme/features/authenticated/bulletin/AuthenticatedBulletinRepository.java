package acme.features.authenticated.bulletin;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedBulletinRepository extends AbstractRepository {

	@Query("Select b from Bulletin b where b.moment > :fechaLimite") 
	Collection<Bulletin> findRecentBulletins(@Param("fechaLimite") Date fechaLimite);

	@Query("Select b from Bulletin b where b.id = :id")
	Bulletin findOneBulletinById(@Param("id")int id); 

}
