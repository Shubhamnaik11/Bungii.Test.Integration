@web
Feature: Partner Integration with Admin and Driver

  Background:
    Given I'm logged into "Partner" portal and  created a new  delivery

  @ready
  Scenario: Display and search Tracking ID on partner portals
    Then I should see the trackingid displayed on the delivery confirmation page
    When I click the "Track Deliveries" button on Partner Portal
    And I search the trip using a correct tracking id
    Then I should see the trip Details
    When I search the trip using invalid tracking id "12345A"
    Then I should see the message "Sorry, no records found"
    And  I navigate to the "Admin" portal configured for "QA" URL
    When I click on the "Scheduled Deliveries" button and enter the "Tracking Id" in the search bar
    Then I should be able to see the respective delivery
    When I click on the "View Delivery Details" button from the dropdown
    Then I should see the "Tracking Id" displayed on the delivery details
    When I Click on the "Live Deliveries" link and enter "Tracking Id" in the search bar
    Then I should see the delivery details displayed
#    When I Click on the "All Deliveries" link and enter the "Tracking Id" into search bar
#    Then I should see delivery details displayed
    When I click on the "Scheduled Deliveries" link and click on the "Edit" button from the dropdown
    Then I should see the "Edit Scheduled Bungii" details form
    When I click on the "Edit Delivery Details" button and click  the "Edit pickup location" button
    And change the "Dropoff" location
    Then I should see the location changed
    When I navigate back to "Partner" portal and click on "Track Deliveries" button
    Then I should see the delivery address changed
