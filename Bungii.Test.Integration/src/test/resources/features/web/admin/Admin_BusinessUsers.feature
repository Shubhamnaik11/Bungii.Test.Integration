@web
Feature: Admin_BusinessUsers

  Background:
    Given I am logged in as TestAdmin
    When I click on "Business Users  > Business Users" Menu
    Then I should be directed to "Business Users Page"

  @sanity
  @regression
    @demo
  Scenario: Verify Add Edit New Business User
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    When I click on the "Save" Button on "Business Users" popup
    Then the business user gets saved successfully and it is displayed in the "Business users" grid
    #When I search by Name "Testcustomertywd_apple<<UniqueNo>>" in "Business Users" page
    Then the user "Testcustomertywd_appleBiz<<UniqueNo>>" is displayed in the Business users grid
    When I edit the "Phone Number" and "Email"
    And I click on the "Save" Button on "Business Users" popup
    Then the business user gets updated successfully and it is displayed in the Business users grid
    #BOC
    #update existing business user
    When I search by Name "Testcustomertywd_apple" in "Business users" page
    And I Update the "Phone Number" and "Email"
    And I click on the "Save" Button on "Business Users" popup
    Then the business user gets updated successfully and it is displayed in the Business users grid

  @regression
    @failed
  Scenario: Verify Adding Duplicate Business User Phone Number
    #Unique phone number
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | 9999839229         | test@creativecapsule.com       |
    And I enter above same Phone number in Phone Number fields
    And I click on the "Save" Button on "Business Users" popup
    Then the "Phone number already exists." message is displayed
    When I click on the "Cancel" Button on "Business Users" popup
    #To check that a new business user cannot be added having same phone number as an existing customer and vice versa.
    And I click on the "New Business User" Button
    And I enter the following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I change the status to "Inactive"
    Then I click on the "Save" Button on "Business Users" popup
    And the business user does not get saved successfully
    #EOC

  @sanity
  @regression
  Scenario: Verify Adding Payment To Business User
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
    
  @regression
  Scenario: Verify Adding Payment To Business User - Fraud Card
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
      |4000111111111511 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Business User Cards" screen
    Then "There is an issue whilst processing your payment request. Please try after some time." message is displayed
    
    
  @sanity
  @regression
  Scenario: Verify Business User Is Not Available In Bulk Trips Until Payment Is Set
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I click on the "Save" Button on "Business Users" popup
    Then the business user gets saved successfully and it is displayed in the "Business users" grid
    When I click on "Business Users  > Bulk Trips" Menu
    Then the business user is not displayed in Bulk Trips since payment is not set

  @regression
  Scenario: Verify Add Business User - Cancellation
    When I click on the "New Business User" Button
    And I click on the "Cancel" Button on "Business Users" popup
    Then the "Business User" popup gets removed from UI
    #BOC search to check pagination
    When I search by the Code "Testcustomertywd_apple"
    And I check if pages exists
    And I check that "Previous" and "Next" button exists
    Then I verify that pagination exists
    #search for invalid data
    When I search by the Code "@#$@@"
    Then the "No Business users found." message is displayed
    #EOC

  @regression
  Scenario: Verify Add New Business User - Field validations
    When I click on the "New Business User" Button
    And I click on the "Save" Button on "Business Users" popup
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    When I enter invalid phone number and email field
    Then the "Oops! The phone number is invalid." message is displayed for the "Phone Number" field
    And the "Oops! The email address is invalid." message is displayed for the "Email" field

  @sanity
  @regression
  Scenario: Verify Add New Business User And Add Payment Method - Field validations In Uplaoded CSV For Bulk Trips
    When I click on the "New Business User" Button
    And I enter following values in "Business Users" fields
      | Name                                              | Phone                   | Email  |
      | Testcustomertywd_apple<<UniqueNo>>	  | <<UniquePhone>>         | test@creativecapsule.com       |
    And I click on the "Save" Button on "Business Users" popup
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
    And I select the file with invalid data for "Pickup address"
    And I click on "Upload" button on "Bulk Trips" page
    And I click on the error link and download the file with error
    Then the error "Max pickup Dropoff Distance exceeded" is displayed in the csv file
    When I click on "Cancel" button on "Bulk Trips" page
    And  I select user "Testcustomertywd_apple<<UniqueNo>>"
    And I select the file with invalid data for "Pickup Date"
    And I click on "Upload" button on "Bulk Trips" page
    And I click on the error link and download the file with error
    Then the error "Please enter a valid date time" is displayed in the csv file
    When I click on "Cancel" button on "Bulk Trips" page
    And I select user "Testcustomertywd_apple<<UniqueNo>>"
    And I select the file with invalid data for "Loading/Unloading time"
    And I click on "Upload" button on "Bulk Trips" page
    And I click on the error link and download the file with error
    Then the error "Loading/Unloading time should be a multiple of 15 minutes ranging from 15 to 90" is displayed in the csv file
    When I click on "Cancel" button on "Bulk Trips" page
    And I select user "Testcustomertywd_apple<<UniqueNo>>"
    And I select the file with invalid data for "No of Drivers"
    And I click on "Upload" button on "Bulk Trips" page
    And I click on the error link and download the file with error
    Then the error "Invalid no. of drivers" is displayed in the csv file
    When I click on "Cancel" button on "Bulk Trips" page
    And I select user "Testcustomertywd_apple<<UniqueNo>>"
    And I select the file with invalid data for "Blank CSV"
    And I click on "Upload" button on "Bulk Trips" page
    Then the error "Please check the CSV for errors." is displayed