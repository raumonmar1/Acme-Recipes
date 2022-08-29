package acme.entities.systemConfiguration;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemConfiguration extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	public List<String> acceptedCurrencies;
	
	@NotBlank
	public String systemCurrency;

	@NotBlank
	public Map<String, Double> spamTuples;
	
	@NotNull
	@Range(min = 0, max = 1)
	@Digits(integer = 1, fraction = 2)
	public Double spamThreshold;

}

