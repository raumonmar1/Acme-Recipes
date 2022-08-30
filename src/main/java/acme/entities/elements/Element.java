package acme.entities.elements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Element extends AbstractEntity {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	protected boolean published;
	
	@NotBlank
	@Length(min=1, max=100)
	protected String name;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$") 
	protected String code;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String description;
	
	@Valid
	@NotNull
	protected Money retailPrice;
	
	@URL
	protected String link;
	
	@NotNull
	protected ElementType type;
	
	@NotNull
	protected String amountUnit;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Chef chef;

}
