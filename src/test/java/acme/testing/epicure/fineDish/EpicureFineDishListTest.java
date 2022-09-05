package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishListTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/positive-list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveFineDish(final int recordIndex, final String code, final String budget, final String startDate, final String finishDate, 
		final String status, final String request, final String info, final String chef_username, final String inventor_organisation, final String chef_info, final String chef_assertion) {
		
		super.signIn("epicure2", "epicure2");
		
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, budget);
		super.checkColumnHasValue(recordIndex, 2, startDate);
		super.checkColumnHasValue(recordIndex, 3, finishDate);
		super.checkColumnHasValue(recordIndex, 4, status);

		super.signOut();
	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Epicure");
		super.navigate("/epicure/fineDish/list");
		super.checkPanicExists();
	}
	// Ancillary methods ------------------------------------------------------

}
