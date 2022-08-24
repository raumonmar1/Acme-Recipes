package acme.entities.fineDishes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.FineDishStatus;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import acme.roles.Epicure;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FineDish extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected FineDishStatus status;
	
	@Column(unique = true)
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$") 
	protected String code;

	@NotBlank
	@Length(min=1, max=255)
	protected String request;
	
	@Positive
	protected Money budget;

	protected Date period;
	
	@URL
	protected String info;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Chef chef;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Epicure epicure;
	
}
