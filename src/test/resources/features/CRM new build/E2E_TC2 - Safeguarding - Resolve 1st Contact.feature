@E2E2 @Full
Feature: E2E_TC2 - Safeguarding - Resolve 1st Contact

  Background: User is Logged In
    Given I have launched the login page - Login Page
    #When user login into application with credentials
    #| saurabh.kakkar@   |  |
    Then Home page is reached
    And I verify that testing will commence in expected Enviromnent

  Scenario Outline: E2E_TC2 - Safeguarding - Resolve 1st Contact
    Given I search for Contact with <CustomerName>
    And I click and verify on <CustomerName> in the contacts result table
    And Instigate Call flow by clicking Next
    And I Complete ID&V Call Flow Screen with "Pass" Option
    And I Accept Proactive flag present statement on Call Flow Screen
    And I create a new query on Call Flow Screen with New Option Selection
    And I Populate Customer Query on Call Flow Screen with Customer Query Type "Customer Safeguarding" Customer Query Type "Adult" and Case Origin Type "Phone"
    And I select "No" on Customer Safeguarding Referral Type
    And I select "NoOtherReferral" on Customer Safeguarding Referral Type
    And I select "No" on Customer Safeguarding - Adult Mental Capacity and Consent
    And I enter Incident details along with todays date with Incident setting as "Own home" and Previous Incidents with comments
    And I select "Other family member" as Perpetrator Type "XAVIER BRAINS" as Perpetrator Contact ID and "Perpetrator Details by SK Automation" on Customer Safeguarding - Adult or Child Alleged Person Causing Harm
    And I select "No" on Anonymous Report Screen
    #And I select "Yes" on Turn on and Test Supplier Arranged Screen
    #And I click Next on Turn on and Test Arrange Turn On And Test
    And I select no further action on the Call guide
    And I select Finish on the On Finalise Call End Screen
    And I verify details for "Customer Safeguarding" from Case Table Screen
      | CaseRecordType        | OwnerName         | Status | ContactName |
      | Customer Safeguarding | Safeguarding Team | New    | STORM BERRY |
    And I open the case from Case Table
    And I verify the details on the Case Details Section
      | Type  |
      | Adult |
    And I verify the details on the Case Update Screen
      | Subject          | Comments                         | Case Reason      |
      | Adult : Referral | Comments Done From SK Automation | Report a problem |
    And I verify details on Alleged Person Causing Harm Section
      | Perpetrator Type    |
      | Other family member |
    And I verify details on Reported Incident Details Section
      | Incident details                         | Incident Setting | Previous incident details                  | Date & Time Concern Reported | Date of Reported Incident |
      | Incident details Done From SK Automation | Own home         | Previous Incidents Done From SK Automation | Todays Date                  | Todays Date               |
    And I verify details on Case Monitoring Details Section
      | Type of Abuse  |
      | Domestic Abuse |
    And I select Related Tab from the Case Screen to display all related elements to the case
    And I assign Ownership to "Dynamic Logged in User" for completing the task
    And I open the Activity Type under Open Activities in the Related Tab
    And I mark Activity Type as Complete
    And I close Open Tabs
    And I select "Recently Viewed" from the Case Screen to display the recently created case
    And I select Close Case Tab from the Case Actions Tab of the recently opened case
    When I Resolve the Case with "Closed - resolved" along with Case Closed "Closed from SKAutomation"
    Then I should see the Case as Closed from Case Screen
    And I close Open Tabs

    Examples: 
      | CustomerName |
      | XYZ  |
