package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository{
		
		@Query("select r from Recipe r where r.published = :published")
		Collection<Recipe> findManyPublishedRecipes(boolean published);

		@Query("select q.element from Quantity q where q.recipe.id = :recipeId")
		Collection<Element> findManyElementsByRecipeId(int recipeId);

		@Query("select r from Recipe r where r.id = :id")
		Recipe findOneRecipeById(int id);
		
		@Query("select q from Quantity q where q.recipe.id = :id")
		Collection<Quantity> findQuantityByRecipeId(int id);
		
		@Query("Select q.element from Quantity q where q.id = :id")
		 Collection<Element> findManyElementByQuantityId(int id);
		
		@Query("select sc.systemCurrency from SystemConfiguration sc")
		String findSystemCurrency();		
}