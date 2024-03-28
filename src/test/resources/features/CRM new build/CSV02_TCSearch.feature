@CSV02 @Full
Feature: TCSearch

  Background: User is Logged In
    Given I have launched the login page - Login Page
    #When user login into application with credentials
    #| saurabh.kakkar | RHVibGluQDk2 |
    Then Home page is reached
    And I verify that testing will commence in expected Enviromnent

  Scenario Outline: TCSearch
    Given I search for Contact with <TenancyReferenceNumber>
    And I click on the Account Name coming in the Accounts result table
    #And I verify the "<TenancyReferenceNumber>" of the case
    And I select the Related Tab
    And I verify that "<CustomerName>" is coming on Related Contacts Section
    And I close Open Tabs

    Examples: 
      | TenancyReferenceNumber    | CustomerName |
      |          7111035905001111 | ACNK         |
      |          9888010374005111 | ACNK11       |
      |          933311090400111  | ACNK111      |
