package acme.testing.chef.ingredient;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class ChefIngredientListMineTest extends TestHarness {
	
	// Lifecycle management ---------------------------------------------------

	// Test cases -------------------------------------------------------------

	@ParameterizedTest
	@CsvFileSource(resources = "/chef/ingredient/list-mine.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positive(final int recordIndex, final String published, final String name, final String code,
		final String description, final String retailPrice, final String link, final String type, 
		final String amountUnit, final String chef) {
		super.signIn("chef1", "chef1");
		
		super.clickOnMenu("Chef", "List Elements");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, type);
		super.checkColumnHasValue(recordIndex, 1, name);
		super.checkColumnHasValue(recordIndex, 2, code);
		super.checkColumnHasValue(recordIndex, 3, retailPrice);
		super.checkColumnHasValue(recordIndex, 4, amountUnit);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("type", type);
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("amountUnit", amountUnit);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("published", published);
		
		super.signOut();
	}
	
	@Test
	@Order(20)
	public void hackingTest() {
		super.checkNotLinkExists("Account");
		super.navigate("/chef/element/list");
		super.checkPanicExists();

		super.signIn("administrator", "administrator");
		super.navigate("/chef/element/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("epicure1", "epicure1");
		super.navigate("/chef/element/list");
		super.checkPanicExists();
		super.signOut();
	}
}
