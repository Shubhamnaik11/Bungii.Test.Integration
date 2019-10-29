@web
Feature: Admin_Promoter

  Background:
    Given I am logged in as Admin
    When I click on "Promotion  > Promoters" Menu
    Then I should be directed to "Promoters Page"

  @testReport
  @sanity
  @regression
  Scenario: Admin_AddNewPromoter
    When I click on the "New Promoter" Button
    And I enter following values in fields in "Add New Promoter" popup
      | Promoter Name | Code Initials | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>>| PTRDescription | Active  |
    When I click on the "Save" Button on "Add New Promoter" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I search by promoter Name "PT<<CurrentDateTime>>"
    Then the promoter "PT<<Unique>>" is displayed in the Promocodes grid

  @testReport
  @sanity
  @regression
  Scenario: Admin_AddPromotion_To_Promoter_And_Add_Promocodes
    When I click on the "New Promoter" Button
    And I enter following values in fields in "Add New Promoter" popup
      | Promoter Name | Code Initials    | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>> | PTR Description | Active  |
    When I click on the "Save" Button on "Add New Promoter" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I view Details of promoter Name "PT<<CurrentDateTime>>"
    And I click on "New Promotion" button on the "Promotions" page
    And I enter following values in fields in "Add Promotion" popup
      | Promotion Name | Promotion Start Date    | Expiration Date  |
      | PT<<Unique>>         | <<CurrentDateTime>>   | <<CurrentDateTime>>+1 |
    And I click on the "Save" Button on "Add Promotion" popup
    Then the "Do you wish to generate promo codes?" is displayed
    When I click on the "Yes" Button on "Generate Promo Code" popup
    And I enter following values in fields on "Promo Code" popup
      | Promo Code Name     | No Of Codes  |
      | DP<<Unique>> | 5        |
    When I click on the "Save" Button
    Then the "Delivery By Promocode" type 5 promocodes gets saved successfully and it is displayed in the Promocodes grid
    When I search by first code generated for above promocode
    Then the promocode is displayed in the Promocodes grid

  @testReport
  @sanity
  @regression
  Scenario: Admin_AddPaymentToPromoter
    When I click on the "New Promoter" Button
    And I enter following values in fields in "Add New Promoter" popup
      | Promoter Name | Code Initials    | Description  | Status  |
      | PT<<Unique>>         | PTR<<Unique>> | PTR Description | Active  |
    When I click on the "Save" Button on "Add New Promoter" popup
    Then the promoter "PT<<Unique>>" gets saved successfully and it is displayed in the Promoters grid
    When I click on "Promotion  > Promoter Cards" Menu
    And I select "PT<<Unique>> " from the "Select Promoter" dropdown
    And I click on "Add Payment Method" button on "Promoter Cards" page
    And I enter following card details on "Promoter Cards" screen
      |Card Number | Expiration Date | CVV | Postal Code|
      |4111111111111111 | 11/29      | 123  |      12345|
    And I click on "Save" button on "Promoter Cards" screen
    Then the card is added to the promoter "PT<<CurrentDateTime>>"

  @testReport
  @regression
  Scenario: Admin_Promotergrid_Sort
    When I click on "Name" header "Ascending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Ascending" order of "Name"
    When I click on "Name" header "Descending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Descending" order of "Name"
    When I click on "Created" header "Ascending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Ascending" order of "Created"
    When I click on "Created" header "Descending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Descending" order of "Created"
    When I click on "Code Initials" header "Ascending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Ascending" order of "Code Initials"
    When I click on "Code Initials" header "Descending" on "Promoter" grid
    Then the "Promoter" list should be sorted by "Descending" order of "Code Initials"

  @testReport
  @regression
  Scenario: Admin_AddNewPromoter_Cancel
    When I click on the "New Promoter" Button
    And I click on the "Cancel" Button on "Add New Promoter" popup
    Then the "Add New Promoter" popup gets removed from UI

  @testReport
  @regression
  Scenario: Admin_AddNewPromoter_Fieldvalidations
    When I click on the "New Promoter" Button
    When I click on the "Save" Button on "Add New Promoter" popup
    Then the "Oops! It looks like you missed something. Please fill out all fields before proceeding." message is displayed
    When I enter "Code Initials" field with below values and click Save
    |Value|Message|
    |ABC... | Please enter a valid Code containing alphanumeric and special characters like $,&,#,@,!,%,?,+ only                 |
    Then the "corresponding" message is displayed beside the "respective" field


