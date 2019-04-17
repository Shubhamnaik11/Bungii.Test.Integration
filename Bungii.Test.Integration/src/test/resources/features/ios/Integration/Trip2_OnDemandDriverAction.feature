@ios

Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.

  Background:
    When I Switch to "driver" application on "same" devices
    Given I am logged in as "valid" driver
    Then I change driver status to "Online"
    When I Switch to "customer" application on "ORIGINAL" devices

    Given I am on Customer logged in Home page
 #   When I clear all notification
    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Solo   | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    Then Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |Save Trip Info |
      | 15       |           |             | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    Then I click on notification for "Driver" for "on demand trip"

  @regression
  Scenario: On demand Bungii Driver should able to Reject On demand Bungii Request after viewing trip details.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    And I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And I click "REJECT" button on "Bungii Request" screen
    Then I should be navigated to "HOME" screen
    When I Switch to "customer" application on "same" devices
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    Then I should be navigated to "Home" screen
  @RERUN2
  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on En Route Update Page.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    And I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    And I click "Ok" button on "BUNGII ACCEPTED" screen

    When I Switch to "driver" application on "same" devices
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    And I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    And I click "OK" on alert message
    Then I should be navigated to "Home" screen
    Then Notification for "Customer" for "DRIVER CANCELLED" should be displayed
  @RERUN1
  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on Arived Update Page.
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    And I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" trip status screen

    Then Notification for "Customer" for "DRIVER ENROUTE" should be displayed
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "BUNGII ACCEPTED" screen
    And I click "Ok" button on "BUNGII ACCEPTED" screen

    When I Switch to "driver" application on "same" devices
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen

    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    And I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    And I click "OK" on alert message
    Then I should be navigated to "Home" screen
    Then Notification for "Customer" for "DRIVER CANCELLED" should be displayed
    
    