package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.entities.recipes.Recipe;
import acme.forms.MoneyExchange;
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
	
	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(@Param("currency")String currency, @Param("amount")Double amount,
		@Param("systemCurrency")String systemCurrency);
	
	@Query("select count (q.element) from Quantity q where q.element.type = acme.entities.elements.ElementType.INGREDIENT and q.recipe.id = :recipeId")
	Integer findNumIngredientsOfRecipe(@Param("recipeId")int recipeId);
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(@Param("id")int id);
	
	@Query("select r from Recipe r where r.code = :code")
	Recipe findOneRecipeByCode(@Param("code")String code);
}
