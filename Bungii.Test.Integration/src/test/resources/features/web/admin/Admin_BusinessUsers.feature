@web
Feature: Admin_BusinessUsers

  Background:
    Given I am logged in as TestAdmin
    When I click on "Business Users  > Business Users" Menu
    Then I should be directed to "Business Users Page"

  @testReport
  @sanity
  @regression
  Scenario: Admin_AddEditNewBusinessUser
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Business Users" popup
    Then the business user gets saved successfully and it is displayed in the "Business users" grid
    When I search by Name "Testcustomertywd_apple<<UniqueNo>>" in "Business Users" page
    Then the user "Testcustomertywd_appleBiz<<UniqueNo>>" is displayed in the Business users grid
    When I edit the "Phone Number" and "Email"
    When I click on the "Save" Button on "Business Users" popup
    Then the business user gets updated successfully and it is displayed in the Business users grid

  @testReport
  @sanity
  @regression
  Scenario: Admin_AddPaymentToBusinessUser
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Business Users" popup
    Then the business user gets saved successfully and it is displayed in the "Business users" grid
    When I click on "Business Users  > Business Users Payment" Menu
    And I select "Testcustomertywd_apple<<UniqueNo>>" from the "Select Business User" dropdown
    And I click on "Add Payment Method" button on "Business Users Payment" page
    And I enter following card details
      |Card Number | Expiration Date | CVV | Postal Code|
      |4242424242424242 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Business User Cards" screen
    Then the card is added to the user "Testcustomertywd_apple<<UniqueNo>>"
    When I click on "Business Users  > Bulk Trips" Menu
    Then the business user is displayed in Bulk Trips since payment is set
    When I select user "Testcustomertywd_apple<<UniqueNo>>"
    And I upload image to be associated with the trip
    And I click on "Upload" button on "Bulk Trips" page
    Then the pickup from the csv are listed down
    When I click on "Confirm" button on "Bulk Trips" page
    Then the "Trips have been requested successfully." message is displayed

  @testReport
  @sanity
  @regression
  Scenario: Admin_BusinessUserIsNotAvailableInBulkTripsUntilPaymentIsSet
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Business Users" popup
    Then the business user gets saved successfully and it is displayed in the "Business users" grid
    When I click on "Business Users  > Bulk Trips" Menu
    Then the business user is not displayed in Bulk Trips since payment is not set

  @testReport
  @regression
  Scenario: Admin_AddBusinessUser_Cancel
    When I click on the "New Business User" Button
    And I click on the "Cancel" Button on "Business Users" popup
    Then the "Business User" popup gets removed from UI

  @testReport
  @regression
  Scenario: Admin_AddNewPromocode_Fieldvalidations
    When I click on the "New Business User" Button
    And I click on the "Save" Button on "Business Users" popup
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    When I enter invalid phone number and email field
    Then the "Oops! The phone number is invalid." message is displayed for the "Phone Number" field
    And the "Oops! The email address is invalid." message is displayed for the "Email" field



