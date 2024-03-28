@CSV01 @Full
Feature: TCSearchRepair

  Background: User is Logged In
    Given I have launched the login page - Login Page
    #When user login into application with credentials
    #| saurabh.kakkar | RHVibGluQDk2 |
    Then Home page is reached
    And I verify that testing will commence in expected Enviromnent

  Scenario Outline: TCSearchRepair
    Given I search for Contact with <CustomerRefNumber>
    And I click and verify on <CustomerName> in the contacts result table
    And I click on "Repairs" Tab for the case
    And I search and open the <RepairNumber> in the Repair Table
    And I verify "<JobDescription>" "<ContractorName>" "<Trade>" "<SpecialInstructions>" "<JobStage>" "<TargetDate>" for opened RepairNumber
    And I Add "Added By SK Automation" for "<NoteType>" if Notes need to be added
    And I verify that the note added is now present on the Repair screen with Notes details "<NoteType>" "Added By SK Automation" and Todays date
    And I close Open Tabs

    Examples: 
      | CustomerRefNumber   | CustomerName | RepairNumber | JobDescription                                 | ContractorName                                 | Trade       | SpecialInstructions | JobStage   | TargetDate | NoteType |
      |         98515667811 | ABBB         |    400779406 | LAMP:RENEW SHAVER LAMP                         | MNorth Responsive Evolve Liverpool City Region | Electrician |                     | Job Logged | 11/09/2020 |          |
      |         71080126211 | ABCCC        |    400781018 | Socket not working or loose from wall (double) | MNorth Responsive Evolve Liverpool City Region | Electrician |                     | Job Logged | 21/12/2020 | MISC     |
