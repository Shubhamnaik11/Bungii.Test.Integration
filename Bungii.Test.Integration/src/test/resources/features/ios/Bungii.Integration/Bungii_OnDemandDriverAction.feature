@ios
  # this will run in miami
Feature: OnDemandBungii_DriverActions
Scenarios where customer requests a Bungii and driver accepts/rejects and cancels the Bungii.

  @FAILED2702

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
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
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
  @FAILED
  @regression
  Scenario: Promo code should be deallocated after driver cancels Bungii (first two states)-Enroute State

    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid miami" user
    And I am on Customer logged in Home page

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    And I change driver status to "Online"

    And I Switch to "customer" application on "same" devices
    And I am on Customer logged in Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I add "PROMO PERCENT OFF" PromoCode
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    And I request for bungii using Request Bungii Button

    And I click on notification for "Driver" for "on demand trip"
    And Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "EN ROUTE" screen

    And I Switch to "customer" application on "same" devices
    And I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message

    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code
  @FAILED
  @regression
  Scenario: Promo code should be deallocated after driver cancels Bungii (first two states)-Arrived State

    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid miami" user
    And I am on Customer logged in Home page

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    And I change driver status to "Online"

    And I Switch to "customer" application on "same" devices
    And I am on Customer logged in Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 30       |           |              | Now  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I add "PROMO DOLLAR OFF" PromoCode
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    And I request for bungii using Request Bungii Button

    And I click on notification for "Driver" for "on demand trip"
    And Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "ACCEPT" button on "Bungii Request" screen
    And I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" screen

    And I Switch to "customer" application on "same" devices
    And I click "Ok" button on "BUNGII ACCEPTED" screen

    And I Switch to "driver" application on "same" devices
    When I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message

    And I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code
  @FAILED2702

  @regression
  Scenario: Promo code should be deallocated after admin cancels Bungii( Before starting bungii)
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    And I Select "Promo Code" from admin sidebar
    Then I get promo code for "one off"

    When I switch to "ORIGINAL" instance
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid miami" user
    And I am on Customer logged in Home page

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid miami" driver
    And I change driver status to "Online"

    And I Switch to "customer" application on "same" devices
    And I am on Customer logged in Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location          | Drop Location           | Geofence |
      | Solo   | 7346 coldstream drive miami| 2400 S Bayshore Dr Miami | miami    |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I enter following details on "Estimate" screen
      | LoadTime | PromoCode | Payment Card | Time           | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE  | Default     |
    And I click "PROMO CODE LINE" button on "Estimate" screen
    And I Enter "VALID" value in "Promo Code" field in "Promo" Page
    And I click "ADD" button on "PROMOS" screen
    When I tap "Back" on Promos screen
    And I should be navigated to "Estimate" screen
    Then I save bungii trip time details
    And I request for bungii using Request Bungii Button
    When I click "Done" button on "Success" screen

    And I click on notification for "Driver" for "SCHEDULED PICKUP AVAILABLE"
    And Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And "correct scheduled trip details" should be displayed on Bungii request screen
    When I accept selected Bungii
    Then I should be navigated to "SCHEDULED BUNGII" screen
    Then I wait for "1" mins

    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List

    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "PROMOS" from Customer App menu
    Then I should able to see expected promo code in available promo code






