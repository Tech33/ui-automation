package pages;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;


import java.util.List;

import org.openqa.selenium.WebDriver;

public class CasesPage extends PageObject {

	@Managed
	WebDriver driver;

	@FindBy(xpath = "//button[@class=\"slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container\"]")
	public List<WebElementFacade> btnClose;

	@FindBy(xpath = "//a[@class='menuItem' and @data-itemid=\"Home\"]")
	public WebElementFacade homeDropDown;

	@FindBy(xpath = "//button[@title ='Next']")
	public WebElementFacade nextButton;

	@FindBy(xpath = "//*[@title=\"Person Reference\"]/following::slot[1]")
	public WebElementFacade personReference;

	@FindBy(xpath = "//*[contains(text(),'In line')]")
	public WebElementFacade covidText;

	@FindBy(xpath = "//div[@class=\"uiOutputRichText\"]/b")
	public List<WebElementFacade> proactiveFlags;

	@FindBy(xpath = "//*[contains(@for,\"New__\")]")
	public WebElementFacade newSelect;

	@FindBy(xpath = "//select[(@class=\"slds-select\")]", timeoutInSeconds="15")
	public WebElementFacade selectorAnotherType;

	@FindBy(xpath = "//select[(@class=\"select uiInput uiInputSelect uiInput--default uiInput--select\")]")
	public WebElementFacade selector;

	@FindBy(xpath = "//select[(@class=\"slds-select\")]", timeoutInSeconds="5")
	public List<WebElementFacade> selectCustomerQueryType;

	@FindBy(xpath = "//select[(@class=\"select uiInput uiInputSelect uiInput--default uiInput--select\")]", timeoutInSeconds="5")
	public WebElementFacade selectCaseOrigin;

	@FindBy(xpath = "//*[contains(@id,\"AmendPersonDetails__\")]")
	public WebElementFacade selectNatureofEnquiry;

	@FindBy(xpath = "//*[contains(@for,\"RequestANameChange__\")]")
	public WebElementFacade selectRequestANameChange;

	public String selectCustomer = "//*[contains(text(),\"{dynamic}\")]//parent::*[@data-aura-class=\"flowruntimeRichTextWrapper\"]";

	@FindBy(xpath = "//*[contains(@id,\"ProofOfNameChange.Marriage Certificate__\")]")
	public WebElementFacade selectDocumentMC;

	//public String selectDocument = "//*[contains(text(),\"{dynamic}\")]";

	@FindBy(xpath = "//*[(@name=\"New_full_name\")]")
	public WebElementFacade fullName;

	@FindBy(xpath = "//*[(@name=\"Date_of_name_change\")]")
	public WebElementFacade dateChange;

	@FindBy(xpath = "//*[contains(@for,\"No__\")]", timeoutInSeconds="10")
	public WebElementFacade selectNo;

	//*[contains(text(),'You have successfully completed the call. Pressing finish button will restart the call guide.')] 

	@FindBy(xpath = "//*[(@title=\"Finish\")]")
	public WebElementFacade btnFinish;

	@FindBy(xpath = "//*[(@id=\"relatedListsTab__item\")]", timeoutInSeconds="5")
	public WebElementFacade relatedListsTab;

	@FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]", timeoutInSeconds="10")
	public WebElementFacade table;

	@FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr[1]/th", timeoutInSeconds="5")
	public List<WebElementFacade> tableColumn;

	@FindBy(xpath = "//a[@rel=\"noreferrer\"]", timeoutInSeconds="10")
	public List<WebElementFacade> tableColumnAnother;

	@FindBy(xpath = "//div[contains(@data-aura-class,\"forceOutputLookup\")]")
	public List<WebElementFacade> tableRow;

	@FindBy(xpath = "//*[contains(@data-proxy-id,\"aura-pos-lib-1\")]")
	public WebElementFacade tableRow1;

	@FindBy(xpath = "//*[(@ class=\"profileTrigger branding-user-profile bgimg slds-avatar slds-avatar_profile-image-small circular forceEntityIcon\")]")
	public WebElementFacade profileUser;

	@FindBy(xpath = "//*[@title=\"Case Actions\"]//preceding-sibling::li[1]")
	public WebElementFacade relatedListsTabCaseTable;

	@FindBy(xpath = "//*[@id=\"customTab__item\"]")
	public WebElementFacade caseAction;

	@FindBy(xpath = "//*[(@class=\"profile-link-label logout uiOutputURL\")]")
	public WebElementFacade logOut;

	@FindBy(xpath = "//slot[(@slot=\"primaryField\")]")
	public WebElementFacade caseID;

	//***************************************************************************

	@FindBy(xpath = "//*[@name=\"CaseSubType__c\"]", timeoutInSeconds="10")
	public WebElementFacade caseSubTypePicklist;

	@FindBy(xpath = "//*[@name=\"Type\"]", timeoutInSeconds="10")
	public WebElementFacade caseTypePicklist;
	
	@FindBy(xpath = "//*[@name=\"Comments__c\"]", timeoutInSeconds="10")
	public WebElementFacade caseComment;

	@FindBy(xpath = "//*[@name=\"ComplaintReasonPicklist__c\"]", timeoutInSeconds="10")
	public WebElementFacade caseComplaintReasonPicklist;

	@FindBy(xpath = "//*[@name=\"ComplaintTypePicklist__c\"]")
	public WebElementFacade caseComplaintTypePicklist;

	@FindBy(xpath = "//*[@name=\"OutcomeStage1Picklist__c\"]", timeoutInSeconds="10")
	public WebElementFacade outcomeStage1Picklist;

	@FindBy(xpath = "//*[@name=\"OutcomeStage1Text__c\"]")
	public WebElementFacade outcomeStage1Text;

	@FindBy(xpath = "//*[@name=\"CustomerDeadlineAgreedDate__c\"]")
	public WebElementFacade customerDeadlineAgreedDate;

	@FindBy(xpath = "//*[@name=\"LessonsLearntTypePicklist__c\"]")
	public WebElementFacade lessonsLearntTypePicklist;

	@FindBy(xpath = "//*[@name=\"CustomerDeadlineOutcomePicklist__c\"]", timeoutInSeconds="10")
	public WebElementFacade customerDeadlineOutcomePicklist;

	@FindBy(xpath = "//*[@name=\"CaseCloseReason__c\"]", timeoutInSeconds="10")
	public WebElementFacade caseCloseReason;

	@FindBy(xpath = "//*[@name=\"CaseClosedComments__c\"]")
	public WebElementFacade caseCloseCommentsAnother;

	//public String selectCaseComplaintType = "//*[@name=\"{dynamic}\"]";

	public String caseTypeddlValue = "//span[@title=\"{dynamic}\"]";

	@FindBy(xpath = "//span[@title=\"Find a Home\"]")
	public WebElementFacade caseTypeddlValue1;

	@FindBy(xpath = "//span[@title=\"Customer disagrees with decision/policy\"]")
	public WebElementFacade complaintReasonValue;

	//*****************************************************e**********************

	@FindBy(xpath = "//button[@class=\"slds-button slds-button_stateful slds-button_neutral slds-not-selected runtime_sales_activitiesTaskStatusButton\"]")
	public WebElementFacade markComplete;

	@FindBy(xpath = "//span[(@title=\"Open Activities\")]", timeoutInSeconds="5")
	public WebElementFacade openActivityType;

	//th[text()=\"Activity Type\"]//..//..//../tbody/tr/th/div/a
	@FindBy(xpath = "//span[text()=\"Activity Type\"]//ancestor::table//tbody//th", timeoutInSeconds="10")
	public WebElementFacade activityType;

	@FindBy(xpath = "//button[@name=\"save\"]")
	public WebElementFacade save;

	@FindBy(xpath = "//*[text()=\"Record updated successfully\"]", timeoutInSeconds="5")
	public WebElementFacade recordSuccessMsg;

	@FindBy(xpath = "//button[@title=\"Brand action\"]")
	public WebElementFacade completeAction;

	@FindBy(xpath = "//button[@title=\"Close Complaint Introduction Contact\"]")
	public WebElementFacade closeComplaintTab;

	@FindBy(xpath = "//button[@class=\"slds-button slds-button_icon slds-button_icon-x-small slds-button_icon-container\"]/lightning-primitive-icon")
	public List<WebElementFacade> closeOpenActivitiesTab;

	@FindBy(xpath = "//label[text()=\"Case Type\"]//../div//input")
	public WebElementFacade caseTypeddl;

	//********************************************************************************************************************************

	@FindBy(xpath = "//button[@class='slds-button slds-button_icon slds-p-horizontal__xxx-small slds-button_icon-small slds-button_icon-container']")
	public WebElementFacade headerButton;

	@FindBy(xpath = "//a[@class='menuItem' and @data-itemid=\"Case\"]")
	public WebElementFacade caseDropDown;

	@FindBy(xpath = "//*[@class=\"triggerLinkText selectedListView uiOutputText\"]")
	public WebElementFacade casesRecentlyViewed;

	@FindBy(xpath = "//*[@class=\"slds-input default input uiInput uiInputTextForAutocomplete uiInput--default uiInput--input\"]")
	public WebElementFacade casesInputText;

	@FindBy(xpath = "//*[@class=\" virtualAutocompleteOptionText\"]")
	public List<WebElementFacade> caseListView;	

	// @FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr[1]/th")
	// public List<WebElementFacade> casesDropDownValues;

	// @FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//th/span/a")
	// public List<WebElementFacade> casesDropDownValues;

	@FindBy(xpath = "//*[@data-tab-name=\"Case.Close_Case\"]")
	public WebElementFacade caseCloseTab;

	@FindBy(xpath = "//select[(@class=\"select uiInput uiInputSelect uiInput--default uiInput--select\")]", timeoutInSeconds="10")
	public WebElementFacade caseCloseReasonSelector;

	@FindBy(xpath = "//textarea[starts-with(@id,'longInput')]")
	public WebElementFacade caseInput;

	@FindBy(xpath = "//*[contains(@for,\"CallResolved__\")]")
	public WebElementFacade callResolved;

	@FindBy(xpath = "//*[contains(@for,\"CreateCaseReferral__\")]")
	public WebElementFacade createCaseReferral;

	@FindBy(xpath = "//a[(@data-label=\"Open Notes\")]")
	public WebElementFacade openNotesTab;

	@FindBy(xpath = "//*[(text()=\"Open Notes\")]")
	public WebElementFacade openNotes;

	@FindBy(xpath = "//button[(@title=\"More Tabs\")]")
	public WebElementFacade moreTab;

	public String selectDynamicByText = "//*[(text()=\"{dynamic}\")]";

	public String selectDynamicByDataLabel = "//a[@data-label=\"{dynamic}\"]";

	public String selectDynamicWithContainsAndForAnnotation = "//*[contains(@for,\"{dynamic}\")]";

	@FindBy(xpath = "//table[@id=\"repairsTable\"]//tbody")
	public WebElementFacade repairTable;

	@FindBy(xpath = "//button[@title=\"Add Note action\"]")
	public WebElementFacade addNoteAction;

	@FindBy(xpath = "//select[@name=\"notetype\"]")
	public WebElementFacade selectNoteType;

	@FindBy(xpath = "//textarea[@id=\"noteTextAreaId\"]")
	public WebElementFacade noteTextAreaId;

	@FindBy(xpath = "//button[text()=\"Save\"]")
	public WebElementFacade saveButton;

	//********************************************************************************************************************************

	@FindBy(xpath = "//*[@name=\"NewAddress1\"]")
	public WebElementFacade newAddressText;

	@FindBy(xpath = "//*[@name=\"NewContactNumber1\"]")
	public WebElementFacade newContactNumber1Text; 

	@FindBy(xpath = "//*[@name=\"DateNoticeisFromTwo\"]")
	public WebElementFacade dateNoticeisFromTwoText; 

	//********************************************************************************************************************************

	@FindBy(xpath = "//*[@name=\"MobilePhone\"]")
	public WebElementFacade mobilePhoneText;

	@FindBy(xpath = "//*[@name=\"HomePhone\"]")
	public WebElementFacade momePhoneText; 

	@FindBy(xpath = "//*[@name=\"BusinessPhone\"]")
	public WebElementFacade businessPhoneText; 

	@FindBy(xpath = "//*[@name=\"PersonalEmail\"]")
	public WebElementFacade personalEmailText; 

	@FindBy(xpath = "//*[@name=\"WorkEmail\"]")
	public WebElementFacade workEmailText; 

	@FindBy(xpath = "//*[@name=\"Update_date_of_birth\"]")
	public WebElementFacade update_date_of_birthText; 

	@FindBy(xpath = "//*[@name=\"Update_name_other_info\"]")
	public WebElementFacade update_name_other_infoText; 

	//************************************************************************************************************************************

	@FindBy(xpath = "//*[starts-with(@name,'Compliance_Enquiry_Subject')]")
	public WebElementFacade subjectText;

	@FindBy(xpath = "//*[(@aria-describedby=\"long textArea\")]")
	public List<WebElementFacade> caseDetailsText;

	@FindBy(xpath = "//*[text()=\"Edit\"]//ancestor::li//following-sibling::li//*[text()=\"Change Owner\"]")
	public WebElementFacade changeOwner;

	@FindBy(xpath = "//input[@title=\"Search People\"]")
	public WebElementFacade searchPeople;

	@FindBy(xpath = "//input[@placeholder=\"Search Contacts...\"]")
	public WebElementFacade searchContacts;
	
	@FindBy(xpath = "//button[@title=\"Submit\"]")
	public WebElementFacade submit;

	@FindBy(xpath = "//*[@class=\"toastMessage slds-text-heading--small forceActionsText\"]")
	public List <WebElementFacade> errorMsg;

	@FindBy(xpath = "//button[@title=\"Cancel\"]")
	public WebElementFacade cancel;

	public String selectDynamicWithTitle = "//div[@title=\"{dynamic}\"]";
	
	public String selectDynamicPerpetratorContactID = "//lightning-base-combobox-formatted-text[@title=\"{dynamic}\"]";

	@FindBy(xpath = "//*[@name=\"DateOfReportedIncident\"]")
	public WebElementFacade dateOfReportedIncident;

	@FindBy(xpath = "//*[@class=\"textarea uiInput uiInputTextArea uiInput--default uiInput--textarea\"]")
	public List <WebElementFacade> inputTextArea;

	@FindBy(xpath = "//*[@name=\"CaseSubject\"]")
	public WebElementFacade caseSubjectText;
	
	@FindBy(xpath = "//*[@name=\"ComplaineeDetails\"]")
	public WebElementFacade complaineeDetailsText;

	//********************************************************************************************************************************
	
	@FindBy(xpath = "//*[@name=\"Date_they_left\"]")
	public WebElementFacade date_they_left;
	
	@FindBy(xpath = "//select[starts-with(@id,'dropdownInput')]")
	public WebElementFacade caseDDl;
	
	@FindBy(xpath = "//input[@name='Full_name_of_person_moving_in']")
	public WebElementFacade nameOfPersonMovingInput;
	
	@FindBy(xpath = "//input[@name='Date_of_birth_of_person_moving_in']")
	public WebElementFacade dateOfBirthofPersonMovingInput;
	
	@FindBy(xpath = "//input[@name='What_date_did_the_person_move_in']")
	public WebElementFacade whatDateThePersonMovingInput;
	
	@FindBy(xpath = "//span[@class='slds-checkbox_faux']")
	public List<WebElementFacade> checkboxFaux;
	
	//********************************************************************************************************************************
	
	@FindBy(xpath = "//*[@name=\"PublicDateOfDeath\"]")
	public WebElementFacade publicDateOfDeathInput;
	
	@FindBy(xpath = "//*[@name=\"PublicPoliceLogNumber\"]")
	public WebElementFacade publicPoliceLogNumberInput;
	
	@FindBy(xpath = "//*[@name=\"PoliceInformedComments\"]")
	public WebElementFacade policeInformedCommentsInput;
	
	@FindBy(xpath = "//*[@name=\"PoliceAttendedCheckbox\"]")
	public WebElementFacade policeAttendedCheckbox;
	
	@FindBy(xpath = "//*[@name=\"PublicConfirmNextOfKinInformed\"]")
	public WebElementFacade publicConfirmNextOfKinInformed;
	
	@FindBy(xpath = "//*[@name=\"PublicHasThePropertySecured\"]")
	public WebElementFacade publicHasThePropertySecured;
	
	@FindBy(xpath = "//*[@name=\"PublicKeysCollecting\"]")
	public WebElementFacade publicKeysCollecting;
	
	//********************************************************************************************************************************
	//  Identifiers for Assertions

	@FindBy(xpath = "//*[contains(text(),\"In line with government advice\")]", timeoutInSeconds="10")
	public WebElementFacade covidCheck;

	//*[@title="Person Reference"]/parent::div//following-sibling::p//lightning-formatted-text
	@FindBy(xpath = "//*[(@title=\"Person Reference\")]//..//p[2]/slot/lightning-formatted-text")
	public List<WebElementFacade> personReferenceNumber;

	@FindBy(xpath = "//*[(@title=\"Tenancy Reference\")]//..//p[2]/slot/lightning-formatted-text")
	public List<WebElementFacade> tenancyReferenceNumber;

	@FindBy(xpath = "//*[contains(text(),\"There are current Proactive Flags for this Customer.\")]")
	public WebElementFacade flagCheck;

	@FindBy(xpath = "//*[contains(text(),\"Case Created\")]")
	public WebElementFacade caseCreated;

	@FindBy(xpath = "//*[(text()=\"You have successfully completed the call. Pressing finish button will restart the call guide.\")]")
	public WebElementFacade caseCompleted;

	@FindBy(xpath = "//*[(text()=\"You no longer have permission to view this task.\")]")
	public List<WebElementFacade> userPermissionError;

	@FindBy(xpath = "//*[(text()=\"Update personal data, e.g. spelling errors, incorrect DOB\")]")
	public WebElementFacade updatePersonalData;

	@FindBy(xpath = "//*[@data-aura-class=\"uiVirtualDataTable\"]//tbody//tr[1]/td")
	public List<WebElementFacade> tableColumnValues;

	@FindBy(xpath = "//*[(text()=\"Amend person details\")]", timeoutInSeconds="10")
	public WebElementFacade amendPersonDetails;

	@FindBy(xpath = "//*[(text()=\"Tenancy & Person Changes\")]", timeoutInSeconds="10")
	public WebElementFacade tenancyPersonChanges;

	@FindBy(xpath = "//*[(@title=\"Status\")]//..//p[2]/slot/lightning-formatted-text")
	public List<WebElementFacade> status;

	@FindBy(xpath = "//*[(@title=\"Date/Time Opened\")]//..//p[2]/slot/lightning-formatted-text")
	public List<WebElementFacade> date;

	@FindBy(xpath = "//*[@title=\"Entry Date\"]//lightning-formatted-date-time")
	public List<WebElementFacade> entryDate;

	@FindBy(xpath = "//*[@title=\"Description\"]//lightning-formatted-text")
	public List<WebElementFacade> description;

	@FindBy(xpath = "//span[text()=\"Type\"]//..//..//div//lightning-formatted-text")
	public WebElementFacade caseType;

	//span[text()="Task Instructions"]//..//..//following-sibling::*//span[@class="uiOutputTextArea"]

	@FindBy(xpath = "//span[text()=\"Task Instructions\"]/parent::div//following-sibling::*//span[@class=\"uiOutputTextArea\"]")
	public WebElementFacade taskInstructions;

	//    @FindBy(xpath = "//span[text()=\"Assigned To\"]/parent::div//following-sibling::*//span[@class=\"textUnderline uiOutputText forceOutputLookup\"]")
	//    public WebElementFacade taskAssignedTo;

	@FindBy(xpath = "//span[text()=\"Assigned To\"]/parent::div//following-sibling::div/span/div")
	public WebElementFacade taskAssignedTo;

	@FindBy(xpath = "//span[text()=\"Activity Type\"]/parent::div//following-sibling::*//span[@class=\"uiOutputText\"]")
	public WebElementFacade taskActivityType;
	
	@FindBy(xpath = "//span[text()=\"UK Citizen\"]//parent::div//following-sibling::div/span")
	public WebElementFacade caseTenencyAndPersonUKCitizenSection;

	@FindBy(xpath = "//span[text()=\"Case Record Type\"]/parent::div/following-sibling::div//div[@class=\"recordTypeName slds-grow slds-truncate\"]")
	public WebElementFacade caseRecordType;

	public String caseUpdateSectionsDynamic = "//span[text()=\"{dynamic}\"]//..//..//following-sibling::*/span//lightning-formatted-text";

	public String caseDetailsSectionsDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//lightning-formatted-text";

	public String caseResolutionSectionsDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//lightning-formatted-text";

	public String casePerpetratorTypeDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//lightning-formatted-text";

	public String caseMonitoringDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//lightning-formatted-text";

	public String caseTenencyAndPersonChangesSectionsDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//lightning-formatted-text";
	
	@FindBy(xpath = "//b[text()=\"GAS Safety Flag\"]")
	public WebElementFacade gasSafetyFlag;

	@FindBy(xpath = "//b[text()=\"'My ' Account\"]")
	public WebElementFacade myAccountFlag;

	public String repairDetailsDynamic = "//span[text()=\"{dynamic}\"]//..//..//following-sibling::div/div[@class=\"slds-form-element__static\"]";

	@FindBy(xpath = "//*[text()=\"Note created successfully.\"]", timeoutInSeconds="5")
	public WebElementFacade noteSuccessMsg;

	@FindBy(xpath = "//span[text()=\"Type\"]//parent::div//ancestor::table//tbody", timeoutInSeconds="5")
	public WebElementFacade repairTableNotes;

	public String contactDetailsSectionsDynamic = "//span[text()=\"{dynamic}\"]//parent::div//following-sibling::div/span//a";

	@FindBy(xpath = "//*[text()=\"ARREARS\"]", timeoutInSeconds="5")
	public WebElementFacade arrearsType;

	@FindBy(xpath = "//*[text()=\"Existing Case in ASB Database Updated\"]", timeoutInSeconds="5")
	public WebElementFacade existingCaseText;

	@FindBy(xpath = "//*[text()=\"Please select a choice\"]", timeoutInSeconds="5")
	public WebElementFacade choiceError;

	@FindBy(xpath = "//*[text()=\"Chasing Vouchers offered on Sign Up\"]", timeoutInSeconds="5")
	public WebElementFacade chasingVouchersText;

	@FindBy(xpath = "//*[text()=\"Determine Compliance Area\"]", timeoutInSeconds="5")
	public WebElementFacade determineComplianceArea;

	@FindBy(xpath = "//*[@class=\"triggerLinkText selectedListView uiOutputText\" and text()=\"Recently Viewed\"]", timeoutInSeconds="10")
	public WebElementFacade casesRecentlyViewedText;

	public String checkDynamicWithTitle = "//a[@title=\"{dynamic}\"]";
	
	@FindBy(xpath = "//*[@name=\"NewOccupantUKCitizenIndicator__c\"]", timeoutInSeconds="5")
	public WebElementFacade newOccupantUKCitizenIndicatorCheckbox;
	
	@FindBy(xpath = "//*[@name=\"CustomerPropertySecuringIndicator__c\"]", timeoutInSeconds="5")
	public WebElementFacade customerPropertySecuringIndicatorCheckbox;
	
	@FindBy(xpath = "//*[@name=\"PoliceAttendedIndictator__c\"]", timeoutInSeconds="5")
	public WebElementFacade policeAttendedIndictatorCheckbox;
	
	@FindBy(xpath = "//*[@name=\"CustomerNextOfKinInformIndicator__c\"]", timeoutInSeconds="5")
	public WebElementFacade customerNextOfKinInformIndicatorCheckbox;
	
	@FindBy(xpath = "//*[@name=\"PoliceContactedIndicator__c\"]", timeoutInSeconds="5")
	public WebElementFacade policeContactedIndicatorCheckbox;
	
	@FindBy(xpath = "//*[@name=\"CustomerKeyCollectionReqIndictator__c\"]", timeoutInSeconds="5")
	public WebElementFacade customerKeyCollectionReqIndictatorCheckbox;
	
	@FindBy(xpath = "//*[@class=\"caseCommentCommentBody\"]", timeoutInSeconds="5")
	public WebElementFacade caseCommentCommentBody;
	
	@FindBy(xpath = "//span[@title=\"Open Activities\"]//../span[2]", timeoutInSeconds="5")
	public WebElementFacade openActivityCounter;

}