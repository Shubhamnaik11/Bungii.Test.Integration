@web
Feature: Admin_Partners_Business_And_EAPI

  Background:
    Given I am logged in as Admin
    When I click on "Partner Portal  > Partners" Menu
    Then I should be directed to "Partner Portal Page"
    
  @regression
  Scenario: Verify Add New Partner
    When I click on the "New Portal Parter" Button
    And I enter following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | RMart_<<Unique>>| Partner | 9284174823 | bungiiauto@gmail.com | Mukesh  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is Normal Partner| CC| Active|
    When I click on the "Save" Button on "Add Partner" popup
    Then the partner "RMart_<<Unique>>" gets saved successfully and it is displayed in the Partners grid
    When I search by partner Name "RMart_<<Unique>>"
    Then the partner "RMart_<<Unique>>" is displayed in the Partners grid
  
  @sanity
  @regression
  Scenario: Verify Add New Enterprise Partner
    When I click on the "New Portal Parter" Button
    And I enter following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | EAPIMart_<<Unique>>| Enterprise API | 9284174823 | bungiiauto@gmail.com | Mukesh  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is EAPI Partner| CC| Active|
    When I click on the "Save" Button on "Add Partner" popup
    Then the partner "EAPIMart_<<Unique>>" gets saved successfully and it is displayed in the Partners grid
    When I search by partner Name "EAPIMart_<<Unique>>"
    Then the partner "EAPIMart_<<Unique>>" is displayed in the Partners grid
    
  @regression
  Scenario: Verify Cancel on Add New Partner
    When I click on the "New Portal Parter" Button
    And I enter following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | RMart_<<Unique>>| Partner | 9284174823 | bungiiauto@gmail.com | Mukesh  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is Normal Partner| CC| Active|
    When I click on the "Cancel" Button on "Add Partner" popup
    Then I should be directed to "Partner Portal Page"
  
  @regression
  Scenario: Verify Add New Partner Field Validations
    When I click on the "New Portal Parter" Button
    When I click on the "Save" Button on "Add Partner" popup
    Then The validation message should be displayed against the "partner" fields
    | Field             | Message                   |
    |  Partner Name     | Partner name is required. |
    |  Partner Type     | Partner type is required. |
    |  Phone            | Phone is required.        |
    |  Email            | Partner email is required.|
    |  Address1         | Address 1 is required.    |
    |  City             | City is required.         |
    |  State            | State is required.        |
    |  Zipcode          | Zipcode is required.      |
    
  @regression
  Scenario: Verify Add New Partner With Mandatory Details
    When I click on the "New Portal Parter" Button
    And I enter following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | RMart_<<Unique>>| Partner | 9284174823 | bungiiauto@gmail.com |   | | 6200 Kansas Zoo |  | Kansas | Kansas | 64132 | | | Active|
    When I click on the "Save" Button on "Add Partner" popup
    Then the partner "RMart_<<Unique>>" gets saved successfully and it is displayed in the Partners grid
    
  @regression
  @testing
  Scenario: Verify Add New Partner in Inactive state
	When I click on the "New Portal Parter" Button
	And I enter following values in fields in "Add Partner" screen
	  | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
	  | RMart_<<Unique>>| Partner | 9284174823 | bungiiauto@gmail.com | Mukesh  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is Normal Partner| CC| Inactive|
	When I click on the "Save" Button on "Add Partner" popup
	Then the partner "RMart_<<Unique>>" gets saved successfully and it is displayed in the Partners grid
	When I search by partner Name "RMart_<<Unique>>"
	Then the partner "RMart_<<Unique>>" is displayed in the Partners grid
    When I view the partner "RMart_<<Unique>>"
	Then the saved data is getting displayed in the "Edit Partner" page
    And I update following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | RMart_<<Unique>>_Edited| Partner | 9766209256 | bungiiauto@gmail.com | Anil  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is Normal edited Partner| CC|Active|
    When I click on the "Save" Button on "Add Partner" popup
    Then the partner "RMart_<<Unique>>_Edited" gets saved successfully and it is displayed in the Partners grid
    
  @regression
  @testing
  Scenario: Verify Add New Enterprise Partner in Inactive state
	When I click on the "New Portal Parter" Button
	And I enter following values in fields in "Add Partner" screen
	  | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
	  | EAPIMart_<<Unique>>| Enterprise API | 9284174823 | bungiiauto@gmail.com | Mukesh  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is EAPI Partner| CC| Inactive|
	When I click on the "Save" Button on "Add Partner" popup
	Then the partner "EAPIMart_<<Unique>>" gets saved successfully and it is displayed in the Partners grid
	When I search by partner Name "EAPIMart_<<Unique>>"
	Then the partner "EAPIMart_<<Unique>>" is displayed in the Partners grid
    When I view the partner "EAPIMart_<<Unique>>"
    Then the saved data is getting displayed in the "Edit Partner" page
    And I update following values in fields in "Add Partner" screen
      | Partner Name | Partner Type | Phone  | Email  |First Name| Last Name| Address 1| Address 2| City | State | Zip | Comments | Logo| Status|
      | EAPIMart_<<Unique>>_Edited| Partner | 9766209256 | bungiiauto@gmail.com | Anil  | Bani| 6200 Kansas Zoo | Kansas | Kansas | Kansas | 64132 | This is EAPI edited Partner| CC|Active|
    When I click on the "Save" Button on "Add Partner" popup
    Then the partner "RMart_<<Unique>>_Edited" gets saved successfully and it is displayed in the Partners grid