package acme.testing.epicure.fineDish;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class EpicureFineDishShowTest extends TestHarness {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/epicure/fine-dish/positive-show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveFineDish(final int recordIndex, final String code, final String budget, final String startDate, final String finishDate, 
		final String status, final String request, final String info,final String chef_username, final String chef_organisation, final String chef_info, final String chef_assertion) {
		
		
		super.signIn("epicure2", "epicure2");
		super.clickOnMenu("Epicure", "List Fine Dishes");
		super.checkListingExists();
		super.sortListing(0, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("startDate", startDate);
		super.checkInputBoxHasValue("finishDate", finishDate);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("info", info);
		super.checkInputBoxHasValue("chef.userAccount.username", chef_username);
		super.checkInputBoxHasValue("chef.organisation", chef_organisation);
		super.checkInputBoxHasValue("chef.info", chef_info);
		super.checkInputBoxHasValue("chef.assertion", chef_assertion);
	
		super.signOut();
	}
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Epicure");
		super.navigate("/epicure/fineDish/show");
		super.checkPanicExists();
	}
	// Ancillary methods ------------------------------------------------------

}