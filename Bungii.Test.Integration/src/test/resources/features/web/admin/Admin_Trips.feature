@web
Feature: Admin_Trips
  
Background: 
  Given I am logged in as TestAdmin
  When I click on "Trips > Trips" Menu
  Then I should be directed to "Trips Page"

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
    When I select filter "Statuses" as "Payment Unsuccessful"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Payment Unsuccessful Status"
    When I select filter "Statuses" as "Payment Successful"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Payment Successful Status"
    When I select filter "Statuses" as "Customer Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Customer Cancelled Status"
    When I select filter "Statuses" as "Driver Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Cancelled Status"
    When I select filter "Statuses" as "Admin Cancelled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Admin Cancelled Status"
    When I select filter "Statuses" as "Pickup with Error"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Pickup with Error Status"
    When I select filter "Statuses" as "Price Estimated"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Price Estimated Status"
    When I select filter "Statuses" as "Driver(s) Not Found"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver(s) Not Found Status"
    When I select filter "Statuses" as "Driver Not Arrived"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Not Arrived Status"
    When I select filter "Statuses" as "Driver Removed"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Driver Removed Status"
    When I select filter "Statuses" as "Promoter Payment Pending"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Promoter Payment Pending Status"
    When I select filter "Type" as "Solo"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Solo Type"
    When I select filter "Type" as "Duo"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Duo Type"
    When I select filter "Category" as "On-Demand"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "On-Demand Category"
    When I select filter "Category" as "Scheduled"
    And I click on "Apply" button on "Trips" page
    Then the triplist grid shows the results by type "Scheduled Category"
