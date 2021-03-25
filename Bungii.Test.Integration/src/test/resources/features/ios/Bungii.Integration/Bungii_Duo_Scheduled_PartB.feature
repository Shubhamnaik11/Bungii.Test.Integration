@ios
@scheduled
@bungii
    # this will run in denver
Feature: DUO Scheduled Bungii Part B
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

  @failed
  @ready
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting Duo Scheduled Bungii
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
    @failures
  Scenario:Verify Driver Cannot Cancel Scheduled Bungii From App When Bungii Is Not Started And He Should Send SMS To Cancel Duo Scheduled Bungii
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | Accepted     | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I try to cancel selected Bungii
    Then user is alerted for "FOR EMERGENCY CONTACT SUPPORT LINE"
    And correct details should be displayed to driver for "SMS FOR CANCEL INCASE OF EMERGENCEY"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
    @failures
  Scenario: Verify Control Driver Can Cancel Scheduled Duo Bungii From The App In The Enroute State - iOS Case
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | enroute      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "EN ROUTE" screen

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I should be navigated to "EN ROUTE" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
    @failures
  Scenario: Verify Control Driver Can Cancel Scheduled Duo Bungii From The App In The Arrived State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | arrived      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "ARRIVED" screen

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I should be navigated to "ARRIVED" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
  Scenario: Verify Non Control Driver Can Cancel Scheduled Duo Bungii From The App In The Enroute State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | enroute      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "EN ROUTE" screen

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #non control driver
    And I am logged in as "valid denver driver 2" driver
    Then I should be navigated to "EN ROUTE" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
  @failures
  Scenario: Verify Non Control Driver Can Cancel Scheduled Duo Bungii From The App In The Arrived State
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | arrived      | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    Then I should be navigated to "ARRIVED" screen

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    #non control driver
    And I am logged in as "valid denver driver 2" driver
    Then I should be navigated to "ARRIVED" screen
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on alert message
    Then I should be navigated to "Driver Home" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @ready
  @failures
  Scenario: Verify Customer Can Cancel Duo Scheduled Bungii Through SMS To Admin If Required Number Of Drivers Have Accepted
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
      | denver   | Accepted     | 0.75 hour ahead | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    #Then I wait for "1" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app

    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @ready
  @failures
  Scenario: Verify Customer Can Request Cancel Scheduled Duo Bungii Through SMS To Admin If No Driver Accepts But Processing Is Over
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app

    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @ready
  @failures
  Scenario: Verify Customer Can Request Cancel Scheduled Duo Bungii Through SMS To Admin If One Driver Accepts And Processing Is Over
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Duo Scheduled" trip
      | driver1 state |
      | Accepted      |
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "4" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app

    And I open Admin portal and navigate to "Scheduled Deliveries" page

    And I Cancel Bungii with following details
      | Charge | Comments |
      | 0      | TEST     |
    Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
    And Bungii must be removed from the List
    When I switch to "ORIGINAL" instance
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
    @failures
  Scenario: Verify If Customer Is Allowed To Rate Driver For Scheduled Duo Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Bungii Completed | Bungii Completed |
    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct rating detail for duo" on Bungii completed page
    When I select "3" Ratting star for duo Driver 1
    Then "3" stars should be highlighted for Driver1
    When I select "4" Ratting star for duo Driver 2
    Then "4" stars should be highlighted for Driver2
    When I click "DONE" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

#its scheduled time not initial request time
  @regression
@failures
  Scenario: Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Duo Scheduled Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |



