package acme.features.epicure.fineDish;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.FineDish;
import acme.forms.MoneyExchange;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Epicure; 
 
@Repository 
public interface EpicureFineDishRepository extends AbstractRepository{ 
	 
	@Query("Select f from FineDish f where f.epicure.id = :id") 
	Collection<FineDish> findFineDishesByEpicureId(@Param("id")int id); 
	
	@Query("Select e from Epicure e where e.userAccount.id = :id")
	Epicure findEpicureByUserAccountId(@Param("id")int id);
	
	@Query("Select f from FineDish f where f.id = :id") 
	FineDish findFineDishesById(@Param("id")int id); 
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();

	@Query("select m from MoneyExchange m where m.source.currency = :currency and m.source.amount = :amount and m.target.currency = :systemCurrency")
	MoneyExchange findMoneyExchange(@Param("currency")String currency, @Param("amount")Double amount,
		@Param("systemCurrency")String systemCurrency);
} 
