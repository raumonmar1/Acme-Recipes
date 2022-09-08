package acme.features.chef.element;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.elements.Element;
import acme.entities.quantities.Quantity;
import acme.forms.MoneyExchange;
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
	
	@Query("select e from Element e where e.code = :code")
	Element findOneElementByCode(@Param("code")String code);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
	@Query("select sc.acceptedCurrencies from SystemConfiguration sc")
	String findAcceptedCurrencies();
	
	@Query("select e from Element e")
	Collection<Element> findManyElements();

	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(@Param("currency")String currency, @Param("amount")Double amount,
		@Param("systemCurrency")String systemCurrency);
	
	@Query("select e from Element e where e.published = true and e not in (select q.element from Quantity q where q.recipe.id = :recipeId)")
	Collection<Element> findAssignableeElementsToRecipe(@Param("recipeId")int recipeId);
	
	@Query("select q from Quantity q where q.element.id = :elementId")
	Collection<Quantity> findQuantityByElementId(@Param("elementId")int elementId);
} 