package acme.entities.recipes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
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
public class Recipe extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	protected boolean published;
	
	@Column(unique = true)
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$") 
	protected String code;

	@NotBlank
	@Length(min=1, max=100)
	protected String heading;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String description;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String preparationNotes;
	
	@URL
	protected String link;
	
	@Valid
	@Transient
	protected Money retailPrice;
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Chef chef;
	
}
