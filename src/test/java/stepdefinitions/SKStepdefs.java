package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SKStepdefs{

	LoginPage loginPage;
	IdentificationPage identificationPage;
	CommonUtils commonUtils;
	CasesPage casePage;
	HomePage homePage;
	Actions builder ; 
	String refNumber;
	String caseNumberOnUI;


	@Managed
	WebDriver driver;

	private static final Logger LOGGER = LoggerFactory.getLogger(SKStepdefs.class);
	EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
	Format formatter = new SimpleDateFormat("dd-MMM-YYYY");
	String customerDateTime;
	String dynamicUserName;

	@Before
	public void openTheApplication() throws IOException {
		LOGGER.info("Before Everything..........");

		System.out.println(System.getProperty("os.name"));

		if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0)
		{
			Runtime.getRuntime().exec("pkill Google Chrome");
		}
		else if(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0)
		{
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe /T");
		}

		else if(System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0)
		{
			Runtime.getRuntime().exec("pkill Chrome");
		}

		builder = new Actions(driver);
		/*String username = EnvironmentSpecificConfiguration.from(variables).getProperty("user.manager.username");
		String REGEX_PATTERN = "[@.\\_]";
		String[] result = username.split(REGEX_PATTERN);
		dynamicUserName = result[0].substring(0, 1).toUpperCase() + result[0].substring(1) + " " + result[1].substring(0, 1).toUpperCase() + result[1].substring(1);;
	*/
	}

	@Then("I have successfully logged in")
	public void loggedIn() {


		LOGGER.info("I have successfully logged in");
	}

	@Given("^I search for Contact with (.+)$")
	public void i_search_for_contact_with(String customerdetails) throws Throwable {

		do
		{
			String value;
			value = customerdetails;
			driver.navigate().refresh();
			commonUtils.checkPageIsReady();
			commonUtils.closeTabs(casePage.btnClose);   	
			commonUtils.clickJS(casePage.headerButton);
			commonUtils.checkPageIsReady();
			commonUtils.clickJS(casePage.homeDropDown);
			commonUtils.checkPageIsReady();
			identificationPage.searchField.click();
			commonUtils.checkPageIsReady();
			commonUtils.waitTime(3);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].value='"+ value +"';", identificationPage.searchEntryField);
			commonUtils.waitTime(2);
			identificationPage.searchEntryField.sendKeys(Keys.ENTER);			
			commonUtils.waitTime(3);
			commonUtils.checkPageIsReady();
		}while(!identificationPage.dataTable.isVisible());
		LOGGER.info("Entered Customer Details - " + customerdetails);

	}

	@And("^I click and verify on (.+) in the contacts result table$")
	public void i_click_and_verify_on_in_the_contacts_result_table(String contactdetails) throws Throwable {

		commonUtils.waitTime(2);
		identificationPage.dataTable.waitUntilVisible();
		driver.navigate().refresh();
		commonUtils.checkPageIsReady();
		identificationPage.dataTable.waitUntilVisible();

		assertThat(identificationPage.foundSearchName.getText()).containsIgnoringCase(contactdetails);
		commonUtils.waitTime(2);
		commonUtils.clickJS(identificationPage.foundSearchName);
		commonUtils.spinnerWait();

	}

	@And("^I verify the \"([^\"]*)\" of the case$")
	public void i_verify_the_something_of_the_case(String personreference) throws Throwable {

		commonUtils.checkPageIsReady();
		LOGGER.info("RefNumber Coming on UI :- " + casePage.personReferenceNumber.get(0).getValue());
		//refNumberOnUI = casePage.personReferenceNumber.get(0).getValue();
		assertThat(casePage.personReferenceNumber.get(0).getValue().trim()).as("refNumber should be ", refNumber).isEqualToIgnoringCase(personreference);
	}

	@And("^Instigate Call flow by clicking Next$")
	public void instigate_call_flow_by_clicking_next() throws Throwable {

		//Fix   commonUtils.closeTabs(casePage.btnClose);  
		if(casePage.btnClose.size() > 0)
		{
			for(int i=0; i<casePage.btnClose.size(); i++ )
				commonUtils.clickJS(casePage.btnClose.get(i));
			commonUtils.waitTime(1);
			commonUtils.checkPageIsReady();
		}

		casePage.covidCheck.waitUntilVisible();
		LOGGER.info("RefNumber Coming on UI :- " + casePage.personReferenceNumber.get(0).getValue());
		//refNumberOnUI = casePage.personReferenceNumber.get(0).getValue();
		//Fix    	assertThat(refNumber).as("refNumber should be ", refNumber).isEqualToIgnoringCase(casePage.personReferenceNumber.get(0).getValue().trim());
		commonUtils.clickJS(casePage.nextButton);
	}

	@And("^I click on the Account Name coming in the Accounts result table$")
	public void i_click_on_the_account_name_coming_in_the_accounts_result_table() throws Throwable {

		commonUtils.waitTime(2);
		identificationPage.dataTable.waitUntilVisible();
		driver.navigate().refresh();
		commonUtils.checkPageIsReady();
		identificationPage.dataTable.waitUntilVisible();

		commonUtils.clickJS(identificationPage.foundSearchAccountName);
		commonUtils.spinnerWait();

	}


	@And("^I Complete ID&V Call Flow Screen with \"([^\"]*)\" Option$")
	public void i_complete_idv_call_flow_screen_with_something_option(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		if(!option.equalsIgnoreCase("NotRequired")) //Exception Only for NotRequired 
		{
			commonUtils.clickJS(casePage.nextButton); 
		}
	}

	@And("^I enter todays date with timestamp as customer data in Update Customer Details Screen$")
	public void i_enter_todays_date_with_timestamp_as_customer_data_in_update_customer_details_screen() throws Throwable {

		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmm");
		customerDateTime = ft.format(dNow);
		casePage.mobilePhoneText.type(customerDateTime);
		casePage.momePhoneText.type(customerDateTime);
		casePage.businessPhoneText.type(customerDateTime);
		casePage.personalEmailText.type(customerDateTime +"@tester.com");
		casePage.workEmailText.type(customerDateTime +"@tester.com");

	}


	@And("^I select \"([^\"]*)\" in Are there further details that need updating section$")
	public void i_select_something_in_are_there_further_details_that_need_updating_section(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I select \"([^\"]*)\" on the Tenancy Person Amend person details changes screen$")
	public void i_select_something_on_the_tenancy_person_amend_person_details_changes_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();	

	}


	@And("^I select \"([^\"]*)\" as reason for not required$")
	public void i_select_something_as_reason_for_not_required(String reason) throws Throwable {

		casePage.selector.waitUntilEnabled();
		List<WebElement> optionsToSelect = driver.findElements(By.tagName("option"));
		for(WebElement option : optionsToSelect){           
			//LOGGER.info(option.getText()); 
			if(option.getText().equals(reason)) {
				LOGGER.info("Trying to select: " + reason);
				option.click();
				break;
			}
		}       
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	@And("^I select \"([^\"]*)\" on Notice Given Screen$")
	public void i_select_something_on_notice_given_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);
	}

	@And("^I click on Next on Notice Given Take Payment Screen$")
	public void i_click_on_next_on_notice_given_take_payment_screen() throws Throwable {

		commonUtils.clickJS(casePage.nextButton);
	}

	@And("^I enter address details phone number notice period with Next Monday Date and reason including subreason on Notice Given Refer Case Screen$")
	public void i_enter_address_details_phone_number_notice_period_with_next_monday_date_and_reason_including_subreason_on_notice_given_refer_case_screen(DataTable datatable) throws Throwable {

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);
		String address = data.get(0).get("Address");
		String phoneNumber = data.get(0).get("PhoneNumber");
		String noticePeriod = data.get(0).get("NoticePeriod");
		String reasonForLeaving = data.get(0).get("ReasonForLeaving");
		String subReasonForLeaving = data.get(0).get("SubReasonForLeaving");
		String detailsOnNoticeGiven = data.get(0).get("DetailsOnNoticeGiven");

		commonUtils.checkPageIsReady();
		casePage.newAddressText.type((address));
		casePage.newContactNumber1Text.type((phoneNumber));
		casePage.selector.waitUntilEnabled();
		commonUtils.selectByOption(noticePeriod);
		casePage.dateNoticeisFromTwoText.type((new SimpleDateFormat("dd-MMM-YYYY").format(commonUtils.nextMonday())));
		commonUtils.waitTime(1);
		commonUtils.selectByOption(reasonForLeaving);
		commonUtils.waitTime(1);
		commonUtils.selectByOption(subReasonForLeaving);
		commonUtils.waitTime(1);
		casePage.caseInput.typeAndTab(detailsOnNoticeGiven);
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I Accept Proactive flag present statement on Call Flow Screen$")
	public void i_accept_proactive_flag_present_statement_on_call_flow_screen() throws Throwable {

		casePage.flagCheck.waitUntilVisible();
		casePage.flagCheck.shouldBeVisible();    	
		commonUtils.clickJS(casePage.nextButton);
	}

	@And("^I click Next on Case Subject Contacting us on behalf of someone else screen$")
	public void i_click_next_on_case_subject_contacting_us_on_behalf_of_someone_else_screen() throws Throwable {

		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I create a new query on Call Flow Screen with New Option Selection$")
	public void i_create_a_new_query_on_call_flow_screen_with_new_option_selection() throws Throwable {

		commonUtils.clickJS(casePage.newSelect);
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I Populate Customer Query on Call Flow Screen with Customer Query Type \"([^\"]*)\" Customer Query Type \"([^\"]*)\" and Case Origin Type \"([^\"]*)\"$")
	public void i_populate_customer_query_on_call_flow_screen_with_customer_query_type_something_customer_query_type_something_and_case_origin_type_something(String strArg1, String strArg2, String strArg3) throws Throwable {

		Select select;
		commonUtils.spinnerWait();
		casePage.selectorAnotherType.waitUntilEnabled();
		select = new Select (casePage.selectorAnotherType);
		LOGGER.info("selectorAnotherType Size " + select.getOptions().size());
		casePage.selectorAnotherType.selectByValue(strArg1);

		assertThat(select.getOptions().size()).as("selectorAnotherType should be ").isEqualTo(15);
		select = new Select (casePage.selectCustomerQueryType.get(1));
		LOGGER.info("CustomerQueryType " + select.getOptions().size());
		casePage.selectCustomerQueryType.get(1).waitUntilClickable();
		casePage.selectCustomerQueryType.get(1).selectByValue(strArg2);

		Select select1 = new Select (casePage.selectCaseOrigin);
		LOGGER.info("CaseOriginType " + select1.getOptions().size());

		List<WebElement> optionsToSelect = driver.findElements(By.tagName("option"));
		for(WebElement option : optionsToSelect){           
			//LOGGER.info(option.getText()); 
			if(option.getText().equals(strArg3)) {
				LOGGER.info("Trying to select: " + strArg3);
				option.click();
				break;
			}
		}
		commonUtils.clickJS(casePage.nextButton);
	}
	
    @And("^I select \"([^\"]*)\" on Request to sublet or lodgers Tenancy Changes Screen$")
    public void i_select_something_on_request_to_sublet_or_lodgers_tenancy_changes_screen(String option) throws Throwable {
       
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		
		if(option.contains("CustomerDoesNotMeetSubletLodgerCriteria"))
			casePage.caseInput.typeAndTab("Comment from SK Automation");
		
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();    	
    }
	
	
    @And("^I select \"([^\"]*)\" and \"([^\"]*)\" on Mutual Exchange for Existing Customers Screen$")
    public void i_select_something_and_something_on_mutual_exchange_for_existing_customers_screen(String option1, String option2) throws Throwable {
     
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option1).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option1));

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option2).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option2));
		
		if(option2.contains("FollowUpEnquiryCreateCaseReferral"))
		{
			casePage.caseInput.typeAndTab("Comment from SK Automation");
		}
		
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
    }

    @And("^I select \"([^\"]*)\" Request for a pet in a property Screen$")
    public void i_select_something_request_for_a_pet_in_a_property_screen(String option) throws Throwable {
        
		commonUtils.spinnerWait();
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		casePage.caseInput.typeAndTab("Comment from SK Automation");
		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.nextButton);
    	
    }
	
    @And("^I select \"([^\"]*)\" from Tenancy Management Instructions for advisor Copy of Tenancy Agreements Screen$")
    public void i_select_something_from_tenancy_management_instructions_for_advisor_copy_of_tenancy_agreements_screen(String option) throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		casePage.caseInput.typeAndTab("Comment from SK Automation");
		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.nextButton);
    }
	
	@And("^I select \"([^\"]*)\" on the If appropriate, offer condolences to the customer Notification of Bereavement Screen$")
	public void i_select_something_on_the_if_appropriate_offer_condolences_to_the_customer_notification_of_bereavement_screen(String option) throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I select \"([^\"]*)\" on Notification of death by a member of the public Screen$")
	public void i_select_something_on_notification_of_death_by_a_member_of_the_public_screen(String option) throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	@And("^I fill in details on Have the police attended site yet Notification of death by a member of the public Screen$")
	public void i_fill_in_details_on_have_the_police_attended_site_yet_notification_of_death_by_a_member_of_the_public_screen() throws Throwable {

		String date = formatter.format(new Date());
		commonUtils.clickJS(casePage.policeAttendedCheckbox);
		commonUtils.clickJS(casePage.publicDateOfDeathInput.type(date));
		commonUtils.clickJS(casePage.publicPoliceLogNumberInput.type("Policy From SK Automation"));
		commonUtils.clickJS(casePage.publicConfirmNextOfKinInformed);
		commonUtils.clickJS(casePage.publicHasThePropertySecured);
		commonUtils.clickJS(casePage.publicKeysCollecting);
		commonUtils.clickJS(casePage.policeInformedCommentsInput.type("Comment from Sk Automation"));

		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I select \"([^\"]*)\" Property Incident - Alarm Sounding in Void Property Determine if In or Out of Hours Screen$")
	public void i_select_something_property_incident_alarm_sounding_in_void_property_determine_if_in_or_out_of_hours_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I select \"([^\"]*)\" on Find a Home Right to Buy and Right to Acquire$")
	public void i_select_something_on_find_a_home_right_to_buy_and_right_to_acquire(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" on Turn on and Test Determine Nature of Query Screen$")
	public void i_select_something_on_turn_on_and_test_determine_nature_of_query_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" on Turn on and Test Supplier Arranged Screen$")
	public void i_select_something_on_turn_on_and_test_supplier_arranged_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I enter \"([^\"]*)\" in Case Subject \"([^\"]*)\" in Case Comments and \"([^\"]*)\" as Case Reason on Find a Home Refer Case Screen$")
	public void i_enter_something_in_case_subject_something_in_case_comments_and_something_as_case_reason_on_find_a_home_refer_case_screen(String caseSubject, String caseComment, String option) throws Throwable {

		casePage.caseSubjectText.typeAndTab(caseSubject);
		casePage.caseInput.typeAndTab(caseComment);
		commonUtils.selectByOption(option);

		commonUtils.clickJS(casePage.nextButton);

	}


	@And("^I select \"([^\"]*)\" on Customer Safeguarding Referral Type$")
	public void i_select_something_on_customer_safeguarding_referral_type(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	

		if(option.equalsIgnoreCase("NoOtherReferral"))
		{
			commonUtils.selectByOption("Other");	
		}

		commonUtils.clickJS(casePage.nextButton);
	}

	@And("^I select \"([^\"]*)\" on Customer Safeguarding - Adult Mental Capacity and Consent$")
	public void i_select_something_on_customer_safeguarding_adult_mental_capacity_and_consent(String option) throws Throwable {

		option= option+"2";
		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		casePage.caseInput.typeAndTab("Customer Safeguarding Done From SK Automation");

		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I enter Incident details along with todays date with Incident setting as \"([^\"]*)\" and Previous Incidents with comments$")
	public void i_enter_incident_details_along_with_todays_date_with_incident_setting_as_something_and_previous_incidents_with_comments(String option) throws Throwable {

		casePage.inputTextArea.get(0).typeAndTab("Incident details Done From SK Automation");
		String date = formatter.format(new Date());
		casePage.dateOfReportedIncident.typeAndTab(date);
		commonUtils.selectByOption(option);	

		casePage.inputTextArea.get(1).typeAndTab("Previous Incidents Done From SK Automation");
		casePage.inputTextArea.get(2).typeAndTab("Comments Done From SK Automation");

		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" as Perpetrator Type \"([^\"]*)\" as Perpetrator Contact ID and \"([^\"]*)\" on Customer Safeguarding - Adult or Child Alleged Person Causing Harm$")
	public void i_select_something_as_perpetrator_type_something_as_perpetrator_contact_id_and_something_on_customer_safeguarding_adult_or_child_alleged_person_causing_harm(String option, String ownerName, String comment) throws Throwable {

		commonUtils.selectByOption(option);
		commonUtils.waitTime(1);
		casePage.searchContacts.type(ownerName);
		commonUtils.waitTime(1);	
		//		casePage.searchContacts.sendKeys(Keys.DOWN);
		//		commonUtils.waitTime(1);	
		//		casePage.searchContacts.sendKeys(Keys.RETURN);
		//		casePage.searchContacts.sendKeys(Keys.TAB);
		//		casePage.searchContacts.sendKeys(Keys.TAB);

		commonUtils.dynamicXpath(casePage.selectDynamicPerpetratorContactID, ownerName).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicPerpetratorContactID, ownerName));
		commonUtils.waitTime(1);
		casePage.complaineeDetailsText.typeAndEnter(comment);
		//		casePage.complaineeDetailsText.typeAndTab(comment);
		commonUtils.waitTime(1);
		commonUtils.clickJS(casePage.nextButton);

	}


	@And("^I select \"([^\"]*)\" on Anonymous Report Screen$")
	public void i_select_something_on_anonymous_report_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));
		commonUtils.clickJS(casePage.nextButton);

	}


	@And("^I click Next on Turn on and Test Arrange Turn On And Test$")
	public void i_click_next_on_turn_on_and_test_arrange_turn_on_and_test() throws Throwable {

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I select \"([^\"]*)\" Within Communal Area & Services screen$")
	public void i_select_something_within_communal_area_services_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" Within Manage Service Delivery screen$")
	public void i_select_something_within_manage_service_delivery_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option)); 	
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I enter \"([^\"]*)\" Within Refer Service Delivery Case screen$")
	public void i_enter_something_within_refer_service_delivery_case_screen(String comment) throws Throwable {

		casePage.caseInput.typeAndTab(comment);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I verify if Determine Compliance Area is getting displayed on Property Safety & Compliance Screen$")
	public void i_verify_if_determine_compliance_area_is_getting_displayed_on_property_safety_compliance_screen() throws Throwable {    


	}

	@And("^I select \"([^\"]*)\" on Property Safety & Compliance Screen$")
	public void i_select_something_on_property_safety_compliance_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I select \"([^\"]*)\" on Property Safety & Compliance Manage Fire Safety Enquiry Screen$")
	public void i_select_something_on_property_safety_compliance_manage_fire_safety_enquiry_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}


	@And("^I select \"([^\"]*)\" on Property Safety & Compliance Determine Compliance Area Screen$")
	public void i_select_something_on_property_safety_compliance_determine_compliance_area_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I verify that Chasing Vouchers offered on Sign Up is displayed on Property Improvements Decoration Allowance$")
	public void i_verify_that_chasing_vouchers_offered_on_sign_up_is_displayed_on_property_improvements_decoration_allowance() throws Throwable {

		casePage.chasingVouchersText.shouldBeVisible();
	}

	@And("^I select \"([^\"]*)\" and enter \"([^\"]*)\" on Property Improvements Decoration Allowance Screen$")
	public void i_select_something_and_enter_something_on_property_improvements_decoration_allowance_screen(String option, String comments) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		casePage.caseInput.typeAndTab(comments);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();


	}

	@And("^I select No on the Rent or Arrears Query Call guide$")
	public void i_select_no_on_the_rent_or_arrears_query_call_guide() throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.selectNo);

	}

	@And("^I fill in the Open Note$")
	public void i_fill_in_the_open_note() throws Throwable {

		casePage.caseInput.typeAndTab("Commented From SK Automation");

	}

	//Future Step
	/*   @And("^I select Call resolved on the Rent or Arrears Query Call guide$")
    public void i_select_call_resolved_on_the_rent_or_arrears_query_call_guide() throws Throwable {

    	commonUtils.clickJS(casePage.callResolved);
    	commonUtils.clickJS(casePage.nextButton);
    }*/


	@And("^I click on Next on the Complaints screen$")
	public void i_click_on_next_on_the_complaints_screen() throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.waitTime(4);
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I select Customer Query type as Ammend Person Details on Call Flow Screen$")
	public void i_select_customer_query_type_as_ammend_person_details_on_call_flow_screen() throws Throwable {

		casePage.selectNatureofEnquiry.waitUntilVisible();
		commonUtils.clickJS(casePage.selectNatureofEnquiry);
		commonUtils.clickJS(casePage.nextButton);
	}


	@And("^I select \"([^\"]*)\" on the Tenancy Person Nature of Enquiry changes screen$")
	public void i_select_something_on_the_tenancy_person_nature_of_enquiry_changes_screen(String option) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, option));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();

	}

	@And("^I select \"([^\"]*)\" as Relationship to lead tenant with UK citizen check along with other details$")
	public void i_select_something_as_relationship_to_lead_tenant_with_uk_citizen_check_along_with_other_details(String option) throws Throwable {

		casePage.caseDDl.waitUntilClickable();
		commonUtils.selectByOption(option);
		commonUtils.clickJS(casePage.checkboxFaux.get(0));
		casePage.nameOfPersonMovingInput.typeAndTab("Sk Automation");

		String date = formatter.format(new Date());
		casePage.dateOfBirthofPersonMovingInput.typeAndTab(date);
		casePage.whatDateThePersonMovingInput.typeAndTab(date);
		casePage.caseInput.typeAndTab("Comment from Sk Automation");

		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" for name change on Tenancy & Person Changes Call Flow Screen$")
	public void i_select_something_for_name_change_on_tenancy_person_changes_call_flow_screen(String customerName) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectCustomer, customerName).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectCustomer, customerName));
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I enter todays date and comments on Tenancy and Person Changes Remove a person Screen$")
	public void i_enter_todays_date_and_comments_on_tenancy_and_person_changes_remove_a_person_screen() throws Throwable {

		String date = formatter.format(new Date());
		casePage.date_they_left.type(date);
		casePage.caseInput.typeAndTab("Comment from Sk Automation");
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I update date of birth and \"([^\"]*)\" on the Tenancy Person update personal data changes screen$")
	public void i_update_date_of_birth_and_something_on_the_tenancy_person_update_personal_data_changes_screen(String updatedName) throws Throwable {

		String date = formatter.format(new Date());
		casePage.update_date_of_birthText.type(date);
		casePage.update_name_other_infoText.typeAndTab(updatedName + date);
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I select \"([^\"]*)\" relevant document to be provided on Tenancy & Person Changes Call Flow Screen$")
	public void i_select_something_relevant_document_to_be_provided_on_tenancy_person_changes_call_flow_screen(String updatedNameByAutomation) throws Throwable {

		String date = formatter.format(new Date());
		casePage.dateChange.typeAndTab(date);
		casePage.fullName.typeAndTab(updatedNameByAutomation  + date);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	///
	@And("^I select no further action on the Call guide$")
	public void i_select_no_further_action_on_the_call_guide() throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.waitTime(2.5);
		casePage.selectNo.waitUntilVisible();
		commonUtils.clickJS(casePage.selectNo);
		commonUtils.waitTime(1);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.waitTime(1.5);
		if(casePage.choiceError.isVisible()) {
			commonUtils.clickJS(casePage.selectNo);
			commonUtils.waitTime(1);
			commonUtils.clickJS(casePage.nextButton);
		}
	}

	@And("^I verify that case is created successfully$")
	public void i_verify_that_case_is_created_successfully() throws Throwable {

		casePage.caseCreated.waitUntilVisible();
		casePage.caseCreated.shouldBeVisible(); 
	}

	@And("^I verify that update personal data message is getting displayed$")
	public void i_verify_that_update_personal_data_message_is_getting_displayed() throws Throwable {

		casePage.updatePersonalData.shouldBeVisible(); 
	}


	@And("^I verify that todays date with timestamp has been updated in Customer Contact Details Section$")
	public void i_verify_that_todays_date_with_timestamp_has_been_updated_in_customer_contact_details_section() throws Throwable {

		assertThat(commonUtils.dynamicXpath(casePage.contactDetailsSectionsDynamic, "Mobile Phone").getText()).as("Mobile Number Should Match").isEqualToIgnoringCase(customerDateTime);
		assertThat(commonUtils.dynamicXpath(casePage.contactDetailsSectionsDynamic, "Home Phone").getText()).as("Home Phone Number Should Match").isEqualToIgnoringCase(customerDateTime);
		assertThat(commonUtils.dynamicXpath(casePage.contactDetailsSectionsDynamic, "Business Phone").getText()).as("Business Phone Number Should Match").isEqualToIgnoringCase(customerDateTime);
		assertThat(commonUtils.dynamicXpath(casePage.contactDetailsSectionsDynamic, "Personal Email").getText()).as("Personal Email Should Match").isEqualToIgnoringCase(customerDateTime+"@tester.com");
		assertThat(commonUtils.dynamicXpath(casePage.contactDetailsSectionsDynamic, "Work Email").getText()).as("Work Email Should Match").isEqualToIgnoringCase(customerDateTime+"@tester.com");

	}


	@And("^I Add details to create a case referral$")
	public void i_add_details_to_create_a_case_referral() throws Throwable {

		casePage.caseInput.typeAndTab("Referred for Action from SK Automation");
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}	

	@And("^I select Finish on the On Finalise Call End Screen$")
	public void i_select_finish_on_the_on_finalise_call_end_screen() throws Throwable {

		casePage.caseCompleted.waitUntilVisible();
		assertThat(casePage.caseCompleted.isDisplayed()).isTrue();
		commonUtils.clickJS(casePage.btnFinish);

		commonUtils.spinnerWait();
		casePage.covidCheck.waitUntilVisible();

	}

	@And("^I close Open Tabs$")
	public void i_close_open_tabs() throws Throwable {

		commonUtils.closeTabs(casePage.btnClose);
	}

	@And("^I select the Related Tab for all Related records to the contact on the Contacts screen$")
	public void i_select_the_related_tab_for_all_related_records_to_the_contact_on_the_contacts_screen() throws Throwable {

		//commonUtils.waitTime(1.5);
		commonUtils.clickJS(casePage.relatedListsTab);
		casePage.relatedListsTab.waitUntilVisible();
		commonUtils.spinnerWait(); 

		commonUtils.closeTabs(casePage.btnClose);
	}


	@And("^I select the Related Tab$")
	public void i_select_the_related_tab() throws Throwable {

		commonUtils.clickJS(casePage.relatedListsTab);
		casePage.relatedListsTab.waitUntilVisible();
		commonUtils.spinnerWait(); 

	}


	@And("^I verify details for \"([^\"]*)\" from Case Table Screen$")
	public void i_verify_details_for_something_from_case_table_screen(String strArg1, DataTable datatable) throws Throwable {

		//Write the code to handle Data Table
		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);
		String caseRecordType = data.get(0).get("CaseRecordType");
		String caseStatus = data.get(0).get("Status");
		String contactName = data.get(0).get("ContactName");

		if(contactName.equalsIgnoreCase("Dynamic Logged in User")) 
			contactName= dynamicUserName;

		commonUtils.clickJS(casePage.headerButton);    	
		commonUtils.clickJS(casePage.caseDropDown);
		casePage.table.waitUntilVisible();
		commonUtils.waitTime(3);
		casePage.casesRecentlyViewedText.shouldBeVisible();

		LOGGER.info("Generated Case ID is :- " + casePage.tableColumn.get(0).getText());
		LOGGER.info("Case Record Type is :- " + casePage.tableColumnValues.get(2).getText());
		LOGGER.info("Contact Name is :- " + casePage.tableColumnValues.get(4).getText());
		LOGGER.info("Owner is :- " + casePage.tableColumnValues.get(7).getText());
		LOGGER.info("Status is :- " + casePage.tableColumnValues.get(9).getText());

		if(!strArg1.equalsIgnoreCase("Complaint"))
		{
			String caseOwnerName = data.get(0).get("OwnerName");

			if(caseOwnerName.equalsIgnoreCase("Dynamic Logged in User")) 
				caseOwnerName= dynamicUserName;

			assertThat(casePage.tableColumnValues.get(2).getText()).as("CaseRecordType Date Should be Todays Date").isEqualTo(caseRecordType);
			assertThat(casePage.tableColumnValues.get(7).getText()).as("CaseOwnerName Should Match").containsIgnoringCase(caseOwnerName);
			assertThat(casePage.tableColumnValues.get(9).getText()).as("CaseStatus Should Match").containsIgnoringCase(caseStatus);
			assertThat(casePage.tableColumnValues.get(4).getText()).as("ContactName Should Match").containsIgnoringCase(contactName);
		}
		else
		{
			assertThat(casePage.tableColumnValues.get(2).getText()).as("CaseRecordType Date Should be Todays Date").isEqualTo(caseRecordType);
			assertThat(casePage.tableColumnValues.get(9).getText()).as("CaseStatus Should Match").containsIgnoringCase(caseStatus);
			assertThat(casePage.tableColumnValues.get(4).getText()).as("ContactName Should Match").containsIgnoringCase(contactName);
		}

		caseNumberOnUI = casePage.tableColumn.get(0).getText();
	}

	@And("^I open the case from Case Table$")
	public void i_open_the_case_from_case_table() throws Throwable {

		commonUtils.clickWithBuilder(casePage.tableColumn.get(0));

	}

	@And("^I verify the details on the Case Details Section$")
	public void i_verify_the_details_on_the_case_details_section(DataTable datatable) throws Throwable {

		//Write the code to handle Data Table
		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);
		String type = data.get(0).get("Type");
		assertThat(casePage.caseType.getValue().trim()).as("Type Should Match").isEqualToIgnoringCase(type);

	}

	@And("^I verify the details on the Case Update Screen$")
	public void i_verify_the_details_on_the_case_update_screen(DataTable datatable) throws Throwable {

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				assertThat(commonUtils.dynamicXpath(casePage.caseUpdateSectionsDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
			}
		}		
	}

	@And("^I verify details on Case Monitoring Details Section$")
	public void i_verify_details_on_case_monitoring_details_section(DataTable datatable) throws Throwable {

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				assertThat(commonUtils.dynamicXpath(casePage.caseMonitoringDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
			}
		}		
	}

	@And("^I verify details on Notification of Bereavement Details Section$")
	public void i_verify_details_on_notification_of_bereavement_details_section(DataTable datatable) throws Throwable {

		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(new Date()); 

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				if(entry.getKey().equalsIgnoreCase("Reported Date Of Death"))
				{
					String dateTime = commonUtils.dynamicXpath(casePage.caseTenencyAndPersonChangesSectionsDynamic, entry.getKey()).getText();
					String[] arrOfStr = dateTime.split(" ");
					String datePart1 = arrOfStr[0];
					assertThat(datePart1).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(date);
				}
				else 
				{		
					//assertThat(casePage.newOccupantUKCitizenIndicatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
					assertThat(casePage.customerPropertySecuringIndicatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
					assertThat(casePage.policeAttendedIndictatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
					assertThat(casePage.customerNextOfKinInformIndicatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
					assertThat(casePage.policeContactedIndicatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
					assertThat(casePage.customerKeyCollectionReqIndictatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
				}
			}
		}		
	}


	@And("^I verify details on Tenancy and Person Changes Details Section$")
	public void i_verify_details_on_tenancy_and_person_changes_details_section(DataTable datatable) throws Throwable {

		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(new Date()); 

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				if(entry.getKey().equalsIgnoreCase("Date Person Moved In") || entry.getKey().equalsIgnoreCase("DOB Of Person Moving In"))
				{
					String dateTime = commonUtils.dynamicXpath(casePage.caseTenencyAndPersonChangesSectionsDynamic, entry.getKey()).getText();
					String[] arrOfStr = dateTime.split(" ");
					String datePart1 = arrOfStr[0];

					assertThat(datePart1).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(date);
				}
				else if(entry.getKey().equalsIgnoreCase("UK Citizen"))
				{		
					assertThat(casePage.newOccupantUKCitizenIndicatorCheckbox.isSelected()).as(entry.getKey() + " Should Match").isEqualTo(true);
				}
				else
				{
					assertThat(commonUtils.dynamicXpath(casePage.caseTenencyAndPersonChangesSectionsDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
				}
			}
		}		
	}



	@And("^I verify details on Reported Incident Details Section$")
	public void i_verify_details_on_reported_incident_details_section(DataTable datatable) throws Throwable {

		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = formatter.format(new Date()); 

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				if(entry.getKey().equalsIgnoreCase("Date & Time Concern Reported"))
				{
					String dateTime = commonUtils.dynamicXpath(casePage.caseResolutionSectionsDynamic, entry.getKey()).getText();
					String[] arrOfStr = dateTime.split(" ");
					String datePart1 = arrOfStr[0];

					assertThat(datePart1).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(date);
				}
				else if(entry.getKey().equalsIgnoreCase("Date of Reported Incident"))
				{
					assertThat(commonUtils.dynamicXpath(casePage.caseResolutionSectionsDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(date);
				}
				else
				{
					assertThat(commonUtils.dynamicXpath(casePage.caseUpdateSectionsDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
				}
			}
		}		
	}

	@And("^I verify the details on the Case Resolution Screen$")
	public void i_verify_the_details_on_the_case_resolution_screen(DataTable datatable) throws Throwable {

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				assertThat(commonUtils.dynamicXpath(casePage.caseResolutionSectionsDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
			}
		}		
	}

	@And("^I verify details on Alleged Person Causing Harm Section$")
	public void i_verify_details_on_alleged_person_causing_harm_section(DataTable datatable) throws Throwable {

		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);  
		for (Map<String, String> map : data) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
				assertThat(commonUtils.dynamicXpath(casePage.casePerpetratorTypeDynamic, entry.getKey()).getText()).as(entry.getKey() + " Should Match").isEqualToIgnoringCase(entry.getValue());
			}
		}		
	}


	@And("^I verify the details on the Notice Given Section$")
	public void i_verify_the_details_on_the_notice_given_section(DataTable datatable) throws Throwable {

		//Write the code to handle Data Table
		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);
		String address = data.get(0).get("Address");
		String phoneNumber = data.get(0).get("PhoneNumber");
		String noticePeriod = data.get(0).get("NoticePeriod");
		String reasonForLeaving = data.get(0).get("ReasonForLeaving");
		String subReasonForLeaving = data.get(0).get("SubReasonForLeaving");

		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "New Address").getText()).as("Address Should Match").isEqualToIgnoringCase(address);
		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "New Contact Number").getText()).as("Contact Number Should Match").isEqualToIgnoringCase(phoneNumber);
		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "Notice Period Given").getText()).as("Notice Period Should Match").isEqualToIgnoringCase(noticePeriod);
		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "Date Notice is From").getText()).as("Date Notice is From Should Match").isEqualToIgnoringCase(new SimpleDateFormat("dd/MM/YYYY").format(commonUtils.nextMonday()));
		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "Reason For Leaving").getText()).as("Reason For Leaving Should Match").isEqualToIgnoringCase(reasonForLeaving);
		assertThat(commonUtils.dynamicXpath(casePage.caseDetailsSectionsDynamic, "Sub Reason For Leaving").getText()).as("Reason For Leaving Should Match").isEqualToIgnoringCase(subReasonForLeaving);
	}

	@And("^I select Related Tab from the Case Screen to display all related elements to the case$")
	public void i_select_related_tab_from_the_case_screen_to_display_all_related_elements_to_the_case() throws Throwable {

		commonUtils.waitTime(4);   
		commonUtils.clickJS(casePage.relatedListsTabCaseTable);
		commonUtils.spinnerWait();

		/*if(casePage.btnClose.size() > 0)
        {
        	for(int i=0; i<casePage.btnClose.size(); i++ )
        	commonUtils.clickJS(casePage.btnClose.get(i));
        	 commonUtils.waitTime(1);
        }*/
	}

    @Then("^No Tasks should be created for the closed case$")
    public void no_tasks_should_be_created_for_the_closed_case() throws Throwable {
    	
    	assertThat(casePage.openActivityCounter.getAttribute("title")).as("Task Count Should be Zero For Closed Case").isEqualTo("(0)");
    
    }
	
	@And("^I open the Activity Type under Open Activities in the Related Tab$")
	public void i_open_the_activity_type_under_open_activities_in_the_related_tab() throws Throwable {

		commonUtils.spinnerWait();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		//executor.executeScript("arguments[0].click();", we);
		executor.executeScript("window.scrollBy(0,500)");
		commonUtils.clickJS(casePage.openActivityType);  
		//commonUtils.clickJS(casePage.activityType);
		commonUtils.waitTime(2); 
		Actions builder = new Actions(driver);
		builder
		.moveToElement(casePage.activityType)
		.click().build().perform();

	}

	@And("^I assign Ownership to \"([^\"]*)\" for completing the task$")
	public void i_assign_ownership_to_something_for_completing_the_task(String owner) throws Throwable {

		commonUtils.waitTime(2); 
		if(owner.equalsIgnoreCase("Dynamic Logged in User"))
			owner= dynamicUserName;

		commonUtils.clickJS(casePage.changeOwner);  
		commonUtils.spinnerWait();
		casePage.searchPeople.type(owner);
		commonUtils.waitTime(2);	
		commonUtils.spinnerWait();

		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithTitle, owner));
		//commonUtils.selectByOption(owner);
		commonUtils.clickJS(casePage.submit);

		Boolean userPermissionErroIsPresent = casePage.errorMsg.size() > 0 ;
		if(userPermissionErroIsPresent)
		{
			commonUtils.clickJS(casePage.cancel);
		}
		//assertThat(userPermissionErroIsPresent).as("Already owns this record Error Coming....").isFalse();
		//String errorMsg = owner + "already owns this record.";
		commonUtils.spinnerWait();
	}


	@And("^I mark Activity Type as Complete$")
	public void i_mark_activity_type_as_complete() throws Throwable {

		casePage.markComplete.waitUntilVisible();
		commonUtils.clickJS(casePage.markComplete);
		Boolean userPermissionErroIsPresent = casePage.userPermissionError.size() > 0 ;

		assertThat(userPermissionErroIsPresent).as("User Permission Error Coming....").isFalse();

		int closeTabsCount = casePage.closeOpenActivitiesTab.size();

		if(closeTabsCount > 2)
		{
			for( int j=closeTabsCount; j > 2 ; j-- )
			{
				commonUtils.clickJS(casePage.closeOpenActivitiesTab.get(j-1));
				commonUtils.waitTime(1);
			}
		}
		commonUtils.spinnerWait();
	}

	@And("^I Complete This Step$")
	public void i_complete_this_step() throws Throwable {

		commonUtils.clickJS(casePage.completeAction);
		casePage.recordSuccessMsg.waitUntilNotVisible();
		commonUtils.spinnerWait();
		commonUtils.waitTime(4.5);
	}

	@And("^I select \"([^\"]*)\" as Case Sub-Type$")
	public void i_select_something_as_case_subtype(String option) throws Throwable {

		try
		{
			commonUtils.spinnerWait();
			commonUtils.waitTime(3);
			commonUtils.spinnerWait();
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			//executor.executeScript("arguments[0].click();", we);
			executor.executeScript("window.scrollBy(0,500)");
			commonUtils.waitTime(3);
			commonUtils.clickJS(casePage.caseTypePicklist);
			commonUtils.waitTime(2);
			commonUtils.clickJS(casePage.caseSubTypePicklist);
			commonUtils.waitTime(2);
			commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, option));
		}
		catch(Exception e)
		{
			commonUtils.dynamicXpath(casePage.caseTypeddlValue, option).waitUntilEnabled();
			commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, option));
		}
	}


	@And("^I select CreateCaseReferral on the Rent or Arrears Query Call guide$")
	public void i_select_createcasereferral_on_the_rent_or_arrears_query_call_guide() throws Throwable {

		commonUtils.clickJS(casePage.createCaseReferral);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}


	@And("^I save the case application progress$")
	public void i_save_the_case_application_progress() throws Throwable {

		commonUtils.clickJS(casePage.save);
		casePage.recordSuccessMsg.waitUntilNotVisible();
		commonUtils.clickJS(casePage.completeAction);
		commonUtils.spinnerWait();
		commonUtils.waitTime(3);

	}

	@And("^I fill in the case comments$")
	public void i_fill_in_the_casecomments() throws Throwable {

		String date = formatter.format(new Date());
		casePage.caseComment.typeAndTab("From SK Automation Notes " + date);

	}

	@And("^I select \"([^\"]*)\" as Complaint Reason$")
	public void i_select_something_as_complaint_reason(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.caseComplaintReasonPicklist);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, "Customer disagrees with decision/policy"));

	}

	@And("^I select \"([^\"]*)\" as Complaint Type$")
	public void i_select_something_as_complaint_type(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.caseComplaintTypePicklist);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, "Lack of support"));

	}

	@And("^I select \"([^\"]*)\" as Outcome Stage 1$")
	public void i_select_something_as_outcome_stage_1(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.outcomeStage1Picklist);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, strArg1));

	}

	@And("^I fill in Outcome Stage 1 Comment$")
	public void i_fill_in_outcome_stage_1_comment() throws Throwable {

		commonUtils.waitTime(2);
		String date = formatter.format(new Date());
		casePage.outcomeStage1Text.typeAndTab("From SK Automation Notes outcomeStage1Text " + date);
	}

	@And("^I put Todays date as Customer Deadline Date$")
	public void i_put_todays_date_as_customer_deadline_date() throws Throwable {

		Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String date = formatter.format(new Date());
		casePage.customerDeadlineAgreedDate.typeAndTab(date);
	}

	@And("^I select \"([^\"]*)\" as Lessons Learnt Type$")
	public void i_select_something_as_lessons_learnt_type(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.lessonsLearntTypePicklist);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, "Training individual"));

	}

	@And("^I select \"([^\"]*)\" as Customer Deadline Outcome$")
	public void i_select_something_as_customer_deadline_outcome(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.customerDeadlineOutcomePicklist);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, "Agreed deadline met"));

	}

	@And("^I select \"([^\"]*)\" as Case Close Reason$")
	public void i_select_something_as_case_close_reason(String strArg1) throws Throwable {

		commonUtils.clickJS(casePage.caseCloseReason);
		commonUtils.waitTime(2);
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.caseTypeddlValue, "Closed - resolved"));

	}

	@And("^I fill in the Case Closed Comments$")
	public void i_fill_in_the_case_closed_comments() throws Throwable {

		//Format formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String date = formatter.format(new Date());
		casePage.caseCloseCommentsAnother.typeAndTab("From SK Automation Notes caseCloseComments " + date);
	}

	@And("^I select \"([^\"]*)\" from the Case Screen to display the recently created case$")
	public void i_select_something_from_the_case_screen_to_display_the_recently_created_case(String casesDropDownlist) throws Throwable {

		commonUtils.clickJS(casePage.headerButton);
		commonUtils.clickJS(casePage.caseDropDown);
		casePage.casesRecentlyViewedText.shouldBeVisible();	
		commonUtils.clickJS(casePage.casesRecentlyViewed);
		commonUtils.waitTime(3);	
		commonUtils.spinnerWait();

		int flag = 0;
		for(WebElement option : casePage.caseListView){
			//LOGGER.info(option.getText()); 
			if(option.getText().equals(casesDropDownlist)) {
				LOGGER.info("Trying to select: " + casesDropDownlist);
				option.click();
				flag=1;
				break;
			}
		}

		assertThat(flag == 0).as("casesDropDownlist does not contain" + casesDropDownlist).isFalse();

		commonUtils.spinnerWait();
		commonUtils.waitTime(3.5);
		// To Verify if we are working on same Reference Number as Previous
		assertThat(casePage.tableColumnAnother.get(0).getText().trim()).as("Kindly Check the Queue....refNumber should be ", refNumber).isEqualToIgnoringCase(caseNumberOnUI);

		commonUtils.clickJS(casePage.tableColumnAnother.get(0));      
		commonUtils.spinnerWait();
		commonUtils.waitTime(1);

	}


	@And("^I select Close Case Tab from the Case Actions Tab of the recently opened case$")
	public void i_select_close_case_tab_from_the_case_actions_tab_of_the_recently_opened_case() throws Throwable {
		commonUtils.clickJS(casePage.caseAction);
		commonUtils.spinnerWait();

		commonUtils.clickJS(casePage.caseCloseTab);
		commonUtils.spinnerWait();
	}

	@When("^I Resolve the Case with \"([^\"]*)\" along with Case Closed \"([^\"]*)\"$")
	public void i_resolve_the_case_with_something_along_with_case_closed_something(String reason, String comments) throws Throwable {

		commonUtils.waitTime(2.5);
		casePage.caseCloseReasonSelector.waitUntilClickable();
		commonUtils.selectByOption(reason);	
		/*builder
		.moveToElement(casePage.caseInput)
		.click().sendKeys("Closed from SK Automation")
		.sendKeys(Keys.TAB)
		//.sendKeys(Keys.ENTER)
		.perform();
		 */
		casePage.caseInput.sendKeys(comments);
		casePage.caseInput.typeAndTab(comments);
		commonUtils.waitTime(2.5);
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.waitTime(5);
		commonUtils.spinnerWait();    	
		commonUtils.closeTabs(casePage.btnClose);

	}


	@And("^I fill in \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" on Property Safety & Compliance Refer Case Screen$")
	public void i_fill_in_something_something_something_on_property_safety_compliance_refer_case_screen(String subject, String details, String accessDetails) throws Throwable {

		casePage.subjectText.typeAndTab(subject);
		casePage.caseDetailsText.get(0).typeAndTab(details);
		casePage.caseDetailsText.get(1).typeAndTab(accessDetails);

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();    	

	}


	@Then("^I should see the Case as Closed from Case Screen$")
	public void i_should_see_the_case_as_closed_from_case_screen() throws Throwable {

		commonUtils.clickJS(casePage.headerButton);    	
		commonUtils.clickJS(casePage.caseDropDown);
		casePage.table.waitUntilVisible();
		commonUtils.waitTime(3);
		commonUtils.clickJS(casePage.tableColumnAnother.get(0));
		commonUtils.spinnerWait();
		assertThat(casePage.status.get(0).getValue().trim()).as("Case should be Closed").isEqualToIgnoringCase("Closed");

	}

	@And("^I click on \"([^\"]*)\" Tab for the case$")
	public void i_click_on_something_tab_for_the_case(String tab) throws Throwable {

		try {
			commonUtils.waitTime(1.5);
			if(tab.equalsIgnoreCase("Repairs"))
			{
				commonUtils.dynamicXpath(casePage.selectDynamicByDataLabel, tab).isDisplayed();	
				commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicByDataLabel, tab));
			}
			else
			{
			commonUtils.dynamicXpath(casePage.selectDynamicByText, tab).isDisplayed();	
			commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicByText, tab));
			}
		}
		catch (NoSuchElementException e) {
			commonUtils.clickJS(casePage.moreTab);
			commonUtils.waitTime(1.5);
			commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicByText, tab));
		}

		commonUtils.spinnerWait();
		commonUtils.waitTime(2.5);

	}

	@And("^I select \"([^\"]*)\" on the Income Maximisation - Money Advice Referral guide$")
	public void i_select_something_on_the_income_maximisation_money_advice_referral_guide(String strArg1) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, strArg1).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, strArg1));
		commonUtils.clickJS(casePage.nextButton);

	}

	@And("^I click on OpenNotes$")
	public void i_click_on_opennotes() throws Throwable {

		try {
			commonUtils.waitTime(1.5);
			casePage.openNotesTab.isDisplayed();
			commonUtils.clickJS(casePage.openNotesTab);
		}
		catch (NoSuchElementException e) {

			commonUtils.clickJS(casePage.moreTab);
			commonUtils.waitTime(1.5);
			commonUtils.clickJS(casePage.openNotes);
		}

		commonUtils.spinnerWait();
		commonUtils.waitTime(2.5);
	}

	@And("^I verify that Validate Note has been created for Rent Arrears Not struggling$")
	public void i_verify_that_validate_note_has_been_created_for_rent_arrears_not_struggling() throws Throwable {

		commonUtils.spinnerWait();
		Format Newformatter = new SimpleDateFormat("YYYY-MM-dd");
		String date = Newformatter.format(new Date());

		Actions builder = new Actions(driver);
		builder.moveToElement(casePage.entryDate.get(0)).perform();
		casePage.arrearsType.shouldBeVisible();

		assertThat(casePage.entryDate.get(0).getValue()).as("Comment Date Should be Todays Date").isEqualTo(date);
		assertThat(casePage.description.get(0).getValue()).as("Comments Should Match").containsIgnoringCase("Commented From SK Automation");

		commonUtils.spinnerWait();
	}

	@Then("^I verify the Task Information under Opened Activities Type$")
	public void i_verify_the_task_information_under_opened_activities_type(DataTable datatable) throws Throwable {

		//Write the code to handle Data Table
		List<Map<String,String>> data = datatable.asMaps(String.class,String.class);
		String activityType = data.get(0).get("Activity Type");
		String taskInstructions = data.get(0).get("Task Instructions");
		String assignedTo = data.get(0).get("Assigned To");

		if(assignedTo.equalsIgnoreCase("Dynamic Logged in User"))
			assignedTo= dynamicUserName;

		commonUtils.waitTime(1.5);
		assertThat(casePage.taskActivityType.getText().trim()).as("Task ActivityType should match").isEqualToIgnoringCase(activityType);
		assertThat(casePage.taskAssignedTo.getText().trim()).as("Task AssignedTo should match").isEqualToIgnoringCase(assignedTo);
		assertThat(casePage.taskInstructions.getText().trim()).as("Task Instructions should match").isEqualToIgnoringCase(taskInstructions);

	}


	@And("^I verify that Proactive Flags are coming on the Proactive Tab for the case$")
	public void i_verify_that_proactive_flags_are_coming_on_the_proactive_tab_for_the_case() throws Throwable {

		commonUtils.waitTime(1.5);
		casePage.gasSafetyFlag.waitUntilVisible();
		casePage.gasSafetyFlag.shouldBeVisible();
		casePage.myAccountFlag.waitUntilVisible();
		assertThat(casePage.myAccountFlag.isVisible()).isTrue();

	}

	@And("^I search and open the (.+) in the Repair Table$")
	public void i_search_and_open_the_in_the_repair_table(String repairnumber) throws Throwable {

		// Check https://stackoverflow.com/questions/15942101/dynamic-table-data-retrieve-selenium-webdriver
		commonUtils.spinnerWait();
		commonUtils.waitTime(5.5);
		casePage.repairTable.waitUntilVisible();
		int numOfRow = casePage.repairTable.findElements(By.tagName("tr")).size(); 

		String first_part = "//table[@id=\"repairsTable\"]//tbody/tr[";
		String second_part = "]/td[1]";


		for (int i=1; i<=numOfRow; i++){
			{
				String final_xpath = first_part+i+second_part;
				WebElement tableRepairNumber = driver.findElement(By.xpath(final_xpath));
				if(tableRepairNumber.getText().equals(repairnumber))
				{
					commonUtils.clickJS(tableRepairNumber);
					break;
				}
			}
			commonUtils.waitTime(1.5);
		}  
	}


	@And("^I verify \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" for opened RepairNumber$")
	public void i_verify_something_something_something_something_something_something_for_opened_repairnumber(String jobdescription, String contractorname, String trade, String specialinstructions, String jobstage, String targetdate) throws Throwable {

		assertThat(jobdescription).as("Job Description Should Match").isEqualToIgnoringCase(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Job Description").getText());
		assertThat(contractorname).as("Contractor Name Should Match").isEqualToIgnoringCase(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Contractor Name").getText());
		assertThat(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Trade").getText()).as("Trade Should Match").isEqualToIgnoringCase(trade);
		assertThat(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Special Instructions").getText()).as("Special Instructions Should Match").isEqualToIgnoringCase(specialinstructions);
		assertThat(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Job Stage").getText()).as("Job Stage Should Match").isEqualToIgnoringCase(jobstage);
		assertThat(commonUtils.dynamicXpath(casePage.repairDetailsDynamic, "Target Date").getText()).as("Target Date Should Match").isEqualToIgnoringCase(targetdate);

	}

	@And("^I Add \"([^\"]*)\" for \"([^\"]*)\" if Notes need to be added$")
	public void i_add_something_for_something_if_notes_need_to_be_added(String notes, String type) throws Throwable {

		if(!(type.isEmpty()))
		{
			commonUtils.clickJS(casePage.addNoteAction);
			commonUtils.waitTime(1.5);
			Select select = new Select(casePage.selectNoteType);
			select.selectByValue(type);
			casePage.noteTextAreaId.typeAndEnter(notes);
			commonUtils.clickJS(casePage.saveButton);
			casePage.noteSuccessMsg.waitUntilNotVisible();
		}
	}

	@And("^I verify that the note added is now present on the Repair screen with Notes details \"([^\"]*)\" \"([^\"]*)\" and Todays date$")
	public void i_verify_that_the_note_added_is_now_present_on_the_repair_screen_with_notes_details_something_something_and_todays_date(String type, String notes) throws Throwable {

		//It will ONLY CHECK IN First Row of Notes Table as Notes is recently added
		if(!(type.isEmpty()))
		{
			Format formatter = new SimpleDateFormat("dd/MM/yyyy");
			String date = formatter.format(new Date()); 
			List<WebElement> tableRepairRows = casePage.repairTableNotes.findElements(By.tagName("tr"));
			List<WebElement> d = tableRepairRows.get(0).findElements(By.tagName("td"));
			String notesTableType =d.get(0).getText();
			String notesTableNotes =d.get(1).getText();
			String notescurrentDate =d.get(2).getText();

			assertThat(notesTableType).as("Type Should Match").isEqualToIgnoringCase(type);
			assertThat(notescurrentDate).as("Todays Date Should Match").isEqualToIgnoringCase(date);
			assertThat(notesTableNotes).as("Notes Comment Should Match").containsIgnoringCase(notes);
		}
	}

	@And("^I click on Logout$")
	public void i_click_on_logout() throws Throwable {

		commonUtils.spinnerWait();
		commonUtils.clickJS(casePage.profileUser);
		commonUtils.clickJS(casePage.logOut);
	}

	@And("^I select \"([^\"]*)\" ABS in Anti-Social Behaviour and FraudExisting Case Screen$")
	public void i_select_something_abs_in_antisocial_behaviour_and_fraudexisting_case_screen(String absCaseType) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, absCaseType).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, absCaseType));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	@And("^I click Next on Anti Social Behaviour and Fraud Existing Case in ASB Database Updated Screen$")
	public void i_click_next_on_anti_social_behaviour_and_fraud_existing_case_in_asb_database_updated_screen() throws Throwable {

		casePage.existingCaseText.shouldBeVisible();
		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	@And("^I click \"([^\"]*)\" on Anonymous Report Screen$")
	public void i_click_something_on_anonymous_report_screen(String reportOption) throws Throwable {

		commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, reportOption).waitUntilVisible();
		commonUtils.clickJS(commonUtils.dynamicXpath(casePage.selectDynamicWithContainsAndForAnnotation, reportOption));

		commonUtils.clickJS(casePage.nextButton);
		commonUtils.spinnerWait();
	}

	@And("^I verify that \"([^\"]*)\" is coming on Related Contacts Section$")
	public void i_verify_that_something_is_coming_on_related_contacts_section(String customername) throws Throwable {

		commonUtils.dynamicXpath(casePage.checkDynamicWithTitle, customername.toUpperCase()).shouldBePresent();
	}

	@And("^I verify the \"([^\"]*)\" and \"([^\"]*)\" on case close screen$")
	public void i_verify_the_something_and_something(String caseComments, String caseCloseComments) throws Throwable {

		commonUtils.clickJS(casePage.caseAction);
		commonUtils.spinnerWait();
		String str = casePage.caseCommentCommentBody.getValue();
		String[] spltd = str.split("\\r?\\n");

		assertThat(spltd[0]).as("caseComments Should Match").isEqualToIgnoringCase("Case Comments: " +caseComments);
		assertThat(spltd[1]).as("caseCloseComments Should Match").isEqualToIgnoringCase("Case Close Comments: " +caseCloseComments);

	}

}

