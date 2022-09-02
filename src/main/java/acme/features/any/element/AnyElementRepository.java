package acme.features.any.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyElementRepository extends AbstractRepository{
	
	@Query("select e from Element e where e.published = :published")
	Collection<Element> findManyPublishedElements(@Param("published")boolean published);

	@Query("select e from Element e where e.id = :id")
	Element findOneElementById(@Param("id")int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
}
