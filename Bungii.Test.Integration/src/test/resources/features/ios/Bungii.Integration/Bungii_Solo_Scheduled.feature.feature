@ios
@scheduled
    # this will run in denver
Feature: To Test Solo - Scheduling Bungii
  I want to use request Scheduling Bungii with Solo type

#  @DUO_SCH_DONOT_ACCEPT
  @POSTDUO
  @regression
  Scenario: I should able to Create and Complete Schedule Bungii, Verify details

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
  #  And I am on Customer logged in Home page
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver

    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time

    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "EN ROUTE" trip status screen
    And Trip Information should be correctly displayed on "EN ROUTE" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "EN ROUTE" Screen
 #   When I verify and slide update button on "EN ROUTE" Screen

    Then I should be navigated to "ARRIVED" trip status screen
    And Trip Information should be correctly displayed on "ARRIVED" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    And Trip Information should be correctly displayed on "ARRIVED" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    And Trip Information should be correctly displayed on "LOADING ITEM" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    And Trip Information should be correctly displayed on "DRIVING TO DROP OFF" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for driver
    And correct details should be displayed to driver on "SMS" app
    And correct details should be displayed to driver on "Call" app
    And correct details should be displayed to driver for "SMS FOR SUPPORT"
    And correct details should be displayed to driver for "VIEW ITEMS"

    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    And Trip Information should be correctly displayed on "UNLOADING ITEM" status screen for customer
    And correct details should be displayed to customer on "SMS" app
    And correct details should be displayed to customer on "Call" app

    When I Switch to "driver" application on "same" devices
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen

    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen

  @failed1
  @regression
  @sanity
  Scenario: I should able to Create and Complete Schedule Bungii
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Switch to "customer" application on "ORIGINAL" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
#    Then Trip Information should be correctly displayed on Estimate screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
 #   Then Bungii Posted message should be displayed
    And I click "Done" button on "Success" screen
    Then I Select "Home" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    And Trip Information should be correctly displayed on TRIP DETAILS screen
    When I accept selected Bungii
#    When I Switch to "driver" application on "same" devices
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I wait for Minimum duration for Bungii Start Time
    #TODO: verify bungii detail page
   # Then If Alerted I ""
    And I start selected Bungii
    Then I should be navigated to "EN ROUTE" trip status screen
    When I slide update button on "EN ROUTE" Screen
    Then I should be navigated to "ARRIVED" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "ARRIVED" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "ARRIVED" Screen
    Then I should be navigated to "LOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "LOADING ITEM" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "LOADING ITEM" Screen
    Then I should be navigated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "DRIVING TO DROP OFF" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "DRIVING TO DROP OFF" Screen
    Then I should be navigated to "UNLOADING ITEM" trip status screen
    When I Switch to "customer" application on "same" devices
    Then Customer should be navigated to "UNLOADING ITEM" trip status screen
    When I Switch to "driver" application on "same" devices
    When I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    When I rate Bungii Driver  with following details and Press "OK" Button
      | Ratting | Tip |
      | 5       | 5   |
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

  @regression
  Scenario: To check that Customer cannot schedule a Bungii at same time as an already scheduled bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
   # Then I should be navigated to "Estimate" screen
    And I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time              | PickUpImage | Save Trip Info |
      | 30       |           |              | <OLD BUNGII TIME> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    When I click "Cancel" button on "Estimate" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then I Cancel selected Bungii

  @regression
  Scenario: Customer cancel bungii , Verify trip details in Bungii Details
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then Trip Information should be correctly displayed on BUNGII DETAILS screen
    When I Cancel selected Bungii
    And Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii

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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: To check status of Scheduled Bungii trip in Scheduled Bungiis menu page when required drivers have Not accepted it.Scenario:SOLO
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: To check status of Scheduled Bungii trip in Scheduled Bungiis menu page when required drivers have Not accepted it.Scenario:DUO
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then trips status on bungii details should be "driver 2 - contacting drivers"
    Then message stating contact driver should be "not be displayed"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |


  @regression
  Scenario: To check  status in Scheduled Bungiis page when only one driver accepts trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver 2 - contacting drivers"
    Then message stating contact driver should be "not be displayed"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: To check status on customer in Scheduled Bungiis page when both drivers have accepted trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "estimated cost"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver1 name"
    Then trips status on bungii details should be "driver2 name"
    Then message stating contact driver should be "displayed"

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: Check to see if customer receive Notification once both/required No of  drivers have accepted scheduled trip.Scenario:Solo
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    And I click on notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then message stating contact driver should be "displayed"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: Check to see if customer receive Notification once both/required No of  drivers have accepted scheduled trip.Scenario:DUO
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I click on notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario:  To check that Customer canNot Schedule Bungii for a time that is outside working hours.Scenario:SOLO
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today - after working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
    When I try to schedule bungii for "tommorow - before working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"

  @regression
  Scenario:  To check that Customer canNot Schedule Bungii for a time that is outside working hours.Scenario:DUO
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today - after working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
    When I try to schedule bungii for "tommorow - before working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"


  @regression
  Scenario:  To check that Customer is able to Schedule Bungii only 5 days ahead including current date.Scenario:SOLO
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+1 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices

    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+2 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices

    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+3 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices

    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+4 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices

    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today+5"
    Then user is alerted for "SCHEDULED ONLY 5 DAYS"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:  To check that Customer is able to Schedule Bungii only 5 days ahead including current date.Scenario:Duo
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+1 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+2 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+3 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time            | PickUpImage |
      | 30       |           |              | Today+4 1:00 PM | Default     |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    And I Switch to "customer" application on "same" devices
    And I Select "Home" from Customer App menu
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today+5"
    Then user is alerted for "SCHEDULED ONLY 5 DAYS"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: To check that Customer canNot Schedule bungii that overlaps with aNother Scheduled trip TELET time.Scenario:Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |

    And I get TELET time of of the current trip
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    And I click "Cancel" button on "Estimate" screen
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |              | <AFTER TELET> | Default     | No             |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: To check that Customer canNot Schedule bungii that overlaps with aNother Scheduled trip TELET time.Scenario:Duo
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

    #comment below tag and  add to first scenario
  @regression
  Scenario:Check to see if customer receieve Notification after admin researches for drivers and both drivers accept.
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "Home" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "3" mins

    When I Switch to "driver" application on "same" devices
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I verify status and researches Bungii with following details
      | label                | Status of Trip      |
      | DUO_SCH_DONOT_ACCEPT | Driver(s) Not Found |

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I Switch to "driver" application on "ORIGINAL" devices
    And I click on notification for "Customer" for "SCHEDULED PICKUP ACCEPTED"

  @regression
    # negative scenario is handle in long haul message scenario . In this scenario verify trip >140 but less than 150 go through
  Scenario:Trip limit (150 miles)
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I enter pickup location
      | Driver | Pickup Location |
      | Solo   | Margoa Railway  |
    And I enter drop location
      | Driver | Drop Location       |
      | Solo   | Devghali Beach road |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen

  @regression
  Scenario:Alert message should be displayed when customer tries to contact driver who is currently has a Bungii in progress.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | denver   | Accepted     | 0.75 hour ahead |
    And I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I change driver status to "Online"
    And I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I click on notification for "Driver" for "on demand trip"
    And Alert message with ACCEPT BUNGII QUESTION text should be displayed
    When I click "YES" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    And I Switch to "customer" application on "same" devices
    And I Select "MY BUNGIIS" from Customer App menu
    And I select 1st trip from scheduled bungii
    When I wait for 1 hour for Bungii Schedule Time
    When I try to contact driver using "call driver1"
    Then user is alerted for "driver finishing current bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @regression
  Scenario:Alert message should be displayed when customer tries to contact driver more than one hour from scheduled time.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 1 hour ahead |
    And I Switch to "customer" application on "same" devices
    When I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I try to contact driver using "sms driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    Then correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "call driver1"
    Then user is alerted for "more than 1 hour from scheduled time"
    Then correct support details should be displayed to customer on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Customer should be able to contact control driver when Non-control driver has started the trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
      |               | Enroute       |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    When I try to contact driver using "call driver2"
    Then correct support details should be displayed to customer on "call" app
    When I try to contact driver using "call driver1"
    Then correct support details should be displayed to customer on "call" app
    When I try to contact driver using "sms driver1"
    Then correct support details should be displayed to customer on "SMS" app
    When I try to contact driver using "sms driver2"
    Then correct support details should be displayed to customer on "SMS" app
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario:Ensure shceduled Bungii notification info is correct (est. earnings, date)
    When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I change driver status to "Online"
    When I Switch to "customer" application on "same" devices

    And I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |

    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    And "correct scheduled trip details" should be displayed on Bungii request screen
    When I accept selected Bungii
    Then I should be navigated to "SCHEDULED BUNGII" screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario:To check if driver recieves Bungii scheduled request even while in the Offline state (assuming he does Not have Bungiis overlapping the TELET time)
    When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I change driver status to "OFFLINE"
    When I Switch to "customer" application on "same" devices
    And I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "REJECT" button on "Bungii Request" screen
    When I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |
    And I Switch to "customer" application on "same" devices
    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then I should be navigated to "BUNGII REQUEST" screen
    When I click "REJECT" button on "Bungii Request" screen
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |
  #change login
  @regression
  Scenario:To check that a driver is Not able to accept the request if the trip has already been accepted by the required number of drivers
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :8888884321 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    And I click "ACCEPT" button on "Bungii Request" screen
    Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: To check that if driver received more than one requests, he is not able to accept the Bungii if he has already accepted a Bungiis who's TELET time overlaps.Scenario:Solo
    Given I Switch to "customer" application on "same" devices

    #trip 1
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Scheduled    | 15 min ahead |
     #trip 2
    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |
  #change login step 1
  @regression
  Scenario: To check that if driver received more than one requests, he is not able to accept the Bungii if he has already accepted a Bungiis who's TELET time overlaps.Scenario:duo
    Given I Switch to "customer" application on "same" devices
    #trip 1
    Given I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time  | Customer Phone | Customer Password | Customer Name                   |
      | 15 min ahead | 9999993015     | Cci12345          | Testcustomertywd_appleerwf Test |
     #trip 2
    Given I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "AVAILABLE TRIPS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @regression
  Scenario: Driver should receive alert stating that the trip has already been accepted by him, if he receives request Notification after accepting the trip from Available trips.
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Given I Switch to "customer" application on "same" devices

    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |

    Then I wait for "2" mins
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "TRIP DETAILS" screen
    When I accept selected Bungii
    And I Switch to "customer" application on "same" devices
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then user is alerted for "PICKUP ALREADY ACCEPTED BY YOU"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |


  @regression
  Scenario:To check the status of scheduled Bungii in the scheduled trip page when only one driver has accepted

    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "contacting other driver"
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii Details screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario:To check all details in the Bungii Details page when required number of drivers have accepted

    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    Then trips status should be "estimated cost of duo trip"
    And I Select Trip from scheduled trip
    Then I should be navigated to "BUNGII DETAILS" screen
    And "correct duo scheduled trip details" should be displayed on Bungii Details screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  @regression
  Scenario: Check that customer received Notification when control driver starts bungii duo
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Enroute       | Accepted      |
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: Check that customer received Notification when driver starts bungii solo
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
      | Enroute       |
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |
#########################
  @regression
  Scenario: Check to see if a driver deosn't receive scheduled trip request if his home is over 30 mins away from PU location
    When I clear all notification
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    When I Switch to "driver" application on "same" devices
    And I am logged in as "valid denver" driver
    And I Switch to "customer" application on "same" devices
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                        | Drop Location                    | Geofence |
      | Solo   | Edmondson Trail Head  Colorado Springs | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage |
      | 30       |           |              | NEXT_POSSIBLE | Default     |
    Then I should be navigated to "Success" screen
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Driver should Not receive scheduled request if the request is sent outside of the time that is set for Trip Alert settings.
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Select "TRIP ALERT SETTINGS" from driver App menu
    And I update denvers driver todays trip alert setting to outside current time
    When I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should be navigated to "AVAILABLE TRIPS" screen
    And I should able to see "zero" available trip
    And I Select "TRIP ALERT SETTINGS" from driver App menu
    And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  Scenario: Re-searched trip request should show Urgent Notification text if admin re-searches less than one hour from scheduled trip time or for trip time between 24 hours prior to current time
    When I clear all notification
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "1" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    And Notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Check that re-searched trip request does Not show Urgent Notification text if is more than one hour from the scheduled trip time
    When I clear all notification
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 2 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "1" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    And I should not get notification for "driver" for "URGENT SCHEDULED PICKUP AVAILABLE"
    When I Switch to "customer" application on "same" devices
    And Notification for "driver" for "SCHEDULED PICKUP AVAILABLE" should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  Scenario: To check validation message shown if driver tries to start a Bungii more than 60 mins before the scheduled time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 1 hour ahead |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "60 MINS BEFORE SCHEDULE TRIP TIME"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: To check that driver is not allowed to start Bungii within 60 mins of the scheduled time if required number of Drivers have not accepted
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "REQUIRED DRIVER NOT ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:To check that driver is not allowed to start Bungii if the Customer is currently in an ongoing trip.Scenario .Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | denver   | Accepted     | 0.75 hour ahead |
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | denver   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "CUSTOMER HAS ONGOING BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:To check that driver is not allowed to start Bungii if the Customer is currently in an ongoing trip.Scenario .Duo
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     | Customer        | Driver1         | Driver2         |
      | denver   | Accepted     | 0.75 hour ahead | denver customer | denver driver 1 | denver driver 2 |
    Given that ondemand bungii is in progress
      | geofence | Bungii State | Driver label | Trip Label |
      | denver   | Enroute      | driver 2     | 2          |
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    When I wait for 1 hour for Bungii Schedule Time
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "CUSTOMER HAS ONGOING BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:  To check if control driver is allowed to complete the trip and proper summary is shown
    Given that duo schedule bungii is in progress
      | geofence | Bungii State    | Bungii Time   | Customer        | Driver1         | Driver2         |
      | denver   | unloading items | NEXT_POSSIBLE | denver customer | denver driver 1 | denver driver 2 |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details for duo trip" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen


  @regression
  Scenario:  To check that if Non control driver completes the trip first, he is shown waiting page till the control driver completes and that the correct summary is shown thereafter
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state  | driver2 state  |
      | Unloading Item | Unloading Item |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112208 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I slide update button on "UNLOADING ITEM" Screen
    Then I accept Alert message for "Reminder: both driver at drop off"
    Then non control driver should see "waiting for other driver" screen
    When I Switch to "customer" application on "same" devices
    Then I should be navigated to "UNLOADING ITEM" screen

    #control driver complete bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Duo Scheduled" trip
      | driver1 state    |
      | Bungii Completed |

    Then I should be navigated to "Bungii Complete" screen
    And Bungii customer should see "correct details for duo trip" on Bungii completed page
    When I click "CLOSE BUTTON" button on "Bungii Complete" screen
    Then I should be navigated to "Promotion" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen
    Then I should be navigated to "Home" screen

    When I Switch to "driver" application on "same" devices
    Then Bungii driver should see "correct details" on Bungii completed page
    And I click "On To The Next One" button on "Bungii Completed" screen


  @regression
  Scenario: check if re-searched driver can cancel trip after starting Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 15 min ahead |
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: check if re-searched driver can cancel trip after starting Duo
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    And I click "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "Yes" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |


  @regression
  Scenario:To check that when Bungii has Not yet started, Driver is Not able to cancel scheduled Bungii directly from app and that he is able to send SMS to cancel Bungii.Scenario.solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
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
  Scenario:To check that when Bungii has Not yet started, Driver is Not able to cancel scheduled Bungii directly from app and that he is able to send SMS to cancel Bungii.Scenario.duo
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
  Scenario: To check that Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:enroute
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
    When I click "Yes" on alert message
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
  Scenario: To check that Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:arrived
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
    When I click "Yes" on alert message
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen


  @regression
  Scenario: To check that Non-Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:enroute
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
    When I click "Yes" on alert message
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
  Scenario: To check that Non-Control Driver is able to cancel Duo Bungii directly from the app in the first two states after Bungii has been started.Scenario:arrived
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
    When I click "Yes" on alert message
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen

  @regression
  Scenario: To check that Customer can cancel through SMS to ADMIN if required no. of drivers have accepted (cancellation on admin side).scenario : duo
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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

#use customer with only one card
  @regression
  Scenario: Customer canNot delete payment method linked to any on-going/scheduled trips
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name       | Customer Password |
      | NEXT_POSSIBLE | 8877995598     | VishalIHHnZkrz Test | Cci12345          |
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8877995598 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    When I Select "PAYMENT" from Customer App menu
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then Alert message with CARD IS ASSOCIATED TO TRIP text should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8877995598     |                 |

  @regression
    #this test case is from customer signup module. but as this require bungii to be created , moved to this feature file
  Scenario Outline: Check if Trip completed count on admin portal is updated when customer completes a Bungii.

    Given I am on the "SIGN UP" page
    When I Enter "<Phone Number>" value in "Phone Number" field in "SIGN UP" Page
    And I Enter "<First Name>" value in "First Name" field in "SIGN UP" Page
    And I Enter "<Last Name>" value in "Last Name" field in "SIGN UP" Page
    And I Enter "<Email ID>" value in "Email" field in "SIGN UP" Page
    And I Enter "<Password>" value in "Password" field in "SIGN UP" Page
    And I Select Referral source as "<Source>"
    And I click "SIGN UP" button on "SIGN UP" screen
    Then I should be navigated to "VERIFICATION" screen
    When I Get SMS CODE for new "Customer"
    And I enter "valid" Verification code
    Then I should be navigated to "Home" screen
    When I Select "PAYMENT" from Customer App menu
    Then I should be navigated to "PAYMENT" screen
    When I click "Add-Button" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page

    When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
      | Bungii Time   | Customer Phone  | Customer Name |  | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER |               |  | Cci12345          |
    And As a driver "testdriver4 Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state      |
      | Accepted           |
      | Enroute            |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112203 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I slide update button on "UNLOADING ITEM" Screen
    Then I should be navigated to "Bungii Completed" screen
    And I click "On To The Next One" button on "Bungii Completed" screen
    Then I wait for "2" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "customers" from admin sidebar
    Then trips requested count should be "1"
    Examples:
      | First Name | Last Name       | Email ID                        | Phone Number       | Password | Referral Code | Source   | CardNo        | Expiry | Postal Code       | Cvv       |
      | Donaldd    | {RANDOM_STRING} | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook | DISCOVER CARD | 12/22  | VALID POSTAL CODE | VALID CVV |


  @regression
  Scenario: To check that Customer can request cancel through SMS to ADMIN if No driver accepts but processing is over (cancellation on admin side).Scenario:Solo
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |

    When I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I Select "MY BUNGIIS" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app
 #   And I click "TOP BACK" button on "Bungii Details" screen

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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: To check that Customer can request cancel through SMS to ADMIN if No driver accepts but processing is over (cancellation on admin side).Scenario:Duo
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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: To check that Customer can request cancel through SMS to ADMIN even if one driver accepts but processing is over (Trip not started) (cancellation on admin side)
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
    Then I wait for "2" mins
    And I select already scheduled bungii
    When I Cancel selected Bungii
    Then correct support details should be displayed to customer on "ADMIN-SMS" app

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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression
  Scenario: To check that Customer can request cancel scheduled trip via admin SMS after 2 hour processing is over (No. of required drivers accepted or Not)
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
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
    And I Select "MY BUNGIIS" from Customer App menu
    Then Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @regression22
  Scenario:CUSTOMER: Notification - 2 hours before scheduled trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | denver   | Accepted     | 1.5 hour ahead |
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    When I Switch to "driver" application on "same" devices
    And I wait for Minimum duration for current Bungii to be T-2 hours
    And I click on notification for "customer" for "T-2 BEFORE SCHEDULED TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:Check if driver rating is shown to customer on Bungii Details page when driver accepts scheduled Bungii
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then ratting should be correctly displayed on Bungii detail page

  @regression
  Scenario: Check if customer is allowed to rate driver for duo trip
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
    Then "3" starts should be highlighted for Driver 1
    When I select "4" Ratting star for duo Driver 2
    Then "4" starts should be highlighted for Driver 2
    When I click "DONE" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen

  #its scheduled time not initial request time
  @regression
  Scenario: check to ensure TELET is calculated correctly (Initial request time +  (Estimated Duration(1.5)) + 30 minutes).Solo

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  #its scheduled time not initial request time
  @regression
  Scenario: check to ensure TELET is calculated correctly (Initial request time +  (Estimated Duration(1.5)) + 30 minutes).duo
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Customer shouldn't be allowed to request Bungii if TELET time of the new Bungii overlaps with already scheduled Bungiis
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    And I get TELET time of of the current trip
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET> | Default     | No             |
    Then user is alerted for "already scheduled bungii"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: If incoming scheduled trip request TELET (Trip A) overlaps start time of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                                    | PickUpImage | Save Trip Info |
      | 30       |           |              | <START TIME WITHIN TELET OF CUSTOMER 1> | Default     | No             |

    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "zero" available trip

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |

  @regression
  Scenario: If incoming scheduled trip request TELET (Trip A) overlaps start time of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 15 min ahead |
    And I get TELET time of of the current trip

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following detail
      | LoadTime | PromoCode | Payment Card | Time                                               | PickUpImage | Save Trip Info |
      | 30       |           |              | <TELET TIME OVERLAP WITH START TIME OF CUSTOMER 1> | Default     | No             |

    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    Then I should able to see "zero" available trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |

  @regression
  Scenario: If incoming on-demend trip request TELET (Trip A) overlaps start time of previously scheduled trip (Trip B) = driver doesn't receive Notification or offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I change driver status to "Online"

    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |

    And I should not get notification for "driver" for "ON DEMAND TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  @regression
  Scenario: if incoming on demand trip TELET overlaps scheduled trip telet, then request should Not be sent to driver.
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | denver   | Accepted     | 0.5 hour ahead |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Then I change driver status to "Online"

    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |

    And I should not get notification for "driver" for "ON DEMAND TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |

  #KNOW ISSUE , TELET TIME IS NOT RECALCULATED
  @regression
  Scenario: check TELET of re-searched trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    When I Switch to "customer" application on "same" devices
    Then I wait for "1" mins
    And I open new "Chrome" browser for "ADMIN"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    Then Telet time of research trip should be not be same as previous trips