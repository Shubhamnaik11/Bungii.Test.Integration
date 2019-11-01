@web
Feature: Admin_Trips
  
Background: 
  Given I am logged in as TestAdmin
  When I click on "Trips > Trips" Menu
  Then I should be directed to "Trips Page"

@test
@regression
Scenario:  Admin_Search_TripList
  When I search by client name "Vishal"
  Then All the clients named "Vishal" should be displayed on the trip list grid

@test
@regression
Scenario: Admin_Filter_TripList
    When I click on "Filter" icon on "Trips" Page
    Then All statuses except "Price Estimated" are selected
    And All types and categories are selected
    When I select "Statuses" as "Payment Unsuccessful"
    And I click on "Apply" button on "Trips" page
