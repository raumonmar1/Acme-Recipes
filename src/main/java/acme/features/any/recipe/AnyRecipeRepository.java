package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository{
		
		@Query("select r from Recipe r where r.published = :published")
		Collection<Recipe> findManyPublishedRecipes(@Param("published")boolean published);

		@Query("select q.element from Quantity q where q.recipe.id = :recipeId")
		Collection<Element> findManyElementsByRecipeId(@Param("recipeId")int recipeId);

		@Query("select r from Recipe r where r.id = :id")
		Recipe findOneRecipeById(@Param("id")int id);
		
		@Query("select q from Quantity q where q.recipe.id = :id")
		Collection<Quantity> findQuantityByRecipeId(@Param("id")int id);
		
		@Query("Select q.element from Quantity q where q.id = :id")
		Collection<Element> findManyElementByQuantityId(@Param("id")int id);
					
		@Query("select sc.systemCurrency from SystemConfiguration sc")
		String findSystemCurrency();		
		
		@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
		MoneyExchange findMoneyExchange(@Param("currency")String currency, @Param("amount")Double amount,
			@Param("systemCurrency")String systemCurrency);
}