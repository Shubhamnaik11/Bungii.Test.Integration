@ios
  # this will run in miami
Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.


  @POSTDUO
  @regression
  Scenario: On demand Bungii Driver should able to Reject On demand Bungii Request after viewing trip details.
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    And I change driver status to "Online"
    And I Switch to "customer" application on "ORIGINAL" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid miami" user
    And I am on Customer logged in Home page
 #   And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    And Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage | Save Trip Info |
      | 15       |           |              | Now  | Default     | No             |
    Then I should be navigated to "SEARCHING" screen
    When I click on notification for "Driver" for "on demand trip"
    Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "REJECT" button on "Bungii Request" screen
    Then I should be navigated to "HOME" screen
    When I Switch to "customer" application on "same" devices
    And I click "Cancel" button on "SEARCHING" screen
    Then user is alerted for "CANCEL BUNGII"
    And I should be navigated to "Home" screen

  @FAILED
  @sanity
  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on En Route Update Page.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | miami    | Enroute      |

    When I am on the "LOG IN" page
   # And I am on Customer logged in Home page
    When I logged in Customer application using  "valid miami" user
    Then Customer should be navigated to "EN ROUTE" trip status screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    Then I should be navigated to "EN ROUTE" trip status screen


    And I Switch to "driver" application on "same" devices
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen
 #   And Notification for "Customer" for "DRIVER CANCELLED" should be displayed

  @sanity
  @regression
  Scenario: On demand Bungii Driver should able to Cancel Bungii Request on Arived Update Page.
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | miami    | ARRIVED      |
    When I am on the "LOG IN" page
  #  And I am on Customer logged in Home page
    When I logged in Customer application using  "valid miami" user
    Then Customer should be navigated to "ARRIVED" trip status screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    Then I should be navigated to "ARRIVED" trip status screen

    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I should be navigated to "Home" screen
#    And Notification for "Customer" for "DRIVER CANCELLED" should be displayed
    
    