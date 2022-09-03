package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefRecipeRepository extends AbstractRepository{
	
	@Query("select r from Recipe r where r.chef.id= :id")
	Collection<Recipe> findManyRecipeByChefId(@Param("id")int id);
	
	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneRecipeById(@Param("id")int id);
	
	@Query("Select c from Chef c where c.userAccount.id = :id")
	Chef findChefByUserAccountId(@Param("id")int id);
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findQuantityByRecipeId(@Param("id")int id);
	
	@Query("Select q.element from Quantity q where q.id = :id")
	 Collection<Element> findManyElementByQuantityId(@Param("id")int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();

 
}
