@ios
@S30READY
Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.

  Background:
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid" driver
    And I change driver status to "Online"
    And I Switch to "customer" application on "ORIGINAL" devices

    And I am on Customer logged in Home page
  #  When I clear all notification
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | froggyland Goa   | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |Save Trip Info |
      | 15       |           |             | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    When I click on notification for "Driver" for "on demand trip"

  @regression
  Scenario: On demand Bungii Driver should able to Reject On demand Bungii Request after viewing trip details.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "REJECT" button on "Bungii Request" screen
    Then I should be navigated to "HOME" screen
    When I Switch to "customer" application on "same" devices
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen

  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on En Route Update Page.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
    And Notification for "Customer" for "DRIVER CANCELLED" should be displayed

  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on Arived Update Page.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen

    And Notification for "Customer" for "DRIVER ENROUTE" should be displayed
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    When I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
    And Notification for "Customer" for "DRIVER CANCELLED" should be displayed
    
    