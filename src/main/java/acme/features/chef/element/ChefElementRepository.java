package acme.features.chef.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef; 
 
@Repository 
public interface ChefElementRepository extends AbstractRepository{ 
	 
	@Query("select e from Element e where e.chef.id = :id")
	Collection<Element> findManyElementsByChefId(@Param("id")int id);
	 
	@Query("Select c from Chef c where c.userAccount.id = :id")
	Chef findChefByUserAccountId(@Param("id")int id);
	
	@Query("select e from Element e where e.id = :id")
	Element findElementById(@Param("id")int id);
} 