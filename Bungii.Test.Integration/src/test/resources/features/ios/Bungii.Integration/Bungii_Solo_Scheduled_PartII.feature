@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part II
  I want to use request Scheduling Bungii with Solo type

  Background:
    When I clear all notification
    When I Switch to "customer" application on "same" devices

  @FAILED2702
  @regression
  @sanity
  Scenario: Verify Details Of Solo Schedule Bungii

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    #When I Switch to "customer" application on "same" devices
    #Given I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
  #  And I am on Customer logged in Home page
   #When I Switch to "driver" application on "same" devices
    #And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid denver" driver

    Given I login as "valid denver" customer and on Home page
    And I login as "valid denver" driver on "same" device and make driver status as "Online"

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

  @regression
  Scenario: Verify Customer Can Create And Complete Schedule Solo Bungii
    #Given I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    #When I Switch to "driver" application on "same" devices
    #And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid denver" driver
    Given I login as "valid denver" customer and on Home page
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
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
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    #And Trip Information should be correctly displayed on BUNGII DETAILS screen
    And Driver Bungii Information should be correctly displayed on BUNGII DETAILS screen

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
  Scenario: Verify Customer Cannot Schedule Bungii At Same Time As That Of Already Scheduled Bungii
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
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

  @ready
  @shacky
  Scenario: Verify Customer Can Cancel Solo Scheduled Bungii Also Verify Trip Details In Bungii Details
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
   # And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    And I select already scheduled bungii
    Then Trip Information should be correctly displayed on BUNGII DETAILS screen
    When I Cancel selected Bungii
    And Bungii must be removed from "SCHEDULED BUNGIIS" screen

  @ready
  @failed
  Scenario: Verify When Admin Cancels Bungii Then Trip Is Removed From The Scheduled Trip In App
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
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
  Scenario: Verify Status Of Scheduled Solo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Required Number Of Drivers Have Not Accepted It
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
    And I Select "MY BUNGIIS" from Customer App menu
    Then trips status should be "Contacting Drivers"
    And I select already scheduled bungii
    Then trips status on bungii details should be "driver 1 - contacting drivers"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Required Number Of Drivers Have Not Accepted It
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
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Only One Drivers Accepts It
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
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Customers Scheduled Bungiis Menu Screen When Both Drivers Have Accepted It
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
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts Solo Scheduled Bungii
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
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts Duo Scheduled Bungii
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
  Scenario: Verify Customer Doesnt Receives Notification When Solo Scheduled Bungii Is Requested At A Time Outside Working Hours

    #When I am on the "LOG IN" page
   # And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today - after working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
    When I try to schedule bungii for "tommorow - before working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"

  @regression
  Scenario: Verify Customer Doesnt Receives Notification When Duo Scheduled Bungii Is Requested At A Time Outside Working Hours
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | duo    | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |
    And I click "Get Estimate" button on "Home" screen
    When I try to schedule bungii for "today - after working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"
    When I try to schedule bungii for "tommorow - before working hour"
    Then user is alerted for "OUTSIDE BUISSNESS HOUR"


  @regression
  @failed
  Scenario:  Verify Customer Can Schedule Solo Bungii Only 5 Days Ahead Including Current Date
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
   # And I Select "Home" from Customer App menu
    Given I login as "valid denver" customer and on Home page
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
    #Cancellation not needed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario:  Verify Customer Can Schedule Duo Bungii Only 5 Days Ahead Including Current Date
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    #And I Select "Home" from Customer App menu
    Given I login as "valid denver" customer and on Home page
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

  @FAILED2702
  @regression
  Scenario:  Verify Customer Cannot Schedule Solo Bungii That Overlaps With Another Scheduled Trip TELET Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |

    And I get TELET time of of the current trip
    #And I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
    Given I login as "valid denver" customer and on Home page

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
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @FAILED2802
  @regression
  Scenario:  Verify Customer Cannot Schedule Duo Bungii That Overlaps With Another Scheduled Trip TELET Time
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

  @failed
    #comment below tag and  add to first scenario
  @ready
  Scenario:Verify If Customer Receives Notification After Admin Researches Drivers And Both Drivers Accept It
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I Select "Home" from Customer App menu
    And I wait for Minimum duration for "current" Bungii to be in Driver not accepted state
    Then I wait for "4" mins
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
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |



  @regression
  Scenario: Verify Alert Message Is Displayed When Customer Tries To Contact Driver Who Has Bungii In Progress
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | denver   | Accepted     | 0.75 hour ahead |
    Given I login as "valid denver" customer and on Home page
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
    #And I Switch to "customer" application on "same" devices
    #When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
   # And I Switch to "driver" application on "same" devices
   # And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid denver" driver
    #Then I change driver status to "Online"

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
  @failed
  Scenario: Verify Alert Message Is Displayed When Customer Tries To Contact Driver More Than One Hour From Scheduled Time
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 1 hour ahead |

    Given I login as "valid denver" customer and on Home page
    #And I Switch to "customer" application on "same" devices
   # When I am on the "LOG IN" page
    #And I logged in Customer application using  "valid denver" user
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
  @test
  Scenario: Verify Customer Can Contact Control Driver When Non-control Driver Starts The Trip
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
  Scenario:Verify Scheduled Bungii Notification Info(Estimated Earnings Date etc.)
    When I clear all notification
    #And I Switch to "driver" application on "same" devices
    #And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid denver" driver
    #Then I change driver status to "Online"
    And I login as "valid denver" driver on "same" device and make driver status as "Online"
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
  Scenario:Verify If Driver Receives Scheduled Bungii Request While In Offline State
    When I clear all notification
    #And I Switch to "driver" application on "same" devices
    #And I am on the "LOG IN" page on driverApp
    #And I am logged in as "valid denver" driver
    #Then I change driver status to "OFFLINE"
    And I login as "valid denver" driver on "same" device and make driver status as "OFFLINE"

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
  @failed
  @ready
  Scenario:Verify That Driver Is Not Able To Accept The Request If The Trip Is Already Accepted By Required Number Of Drivers
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :8888884321 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Then I wait for "1" mins
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip

    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
    When I click "Cancel" on alert message if any
    And I click "ACCEPT" button on "Bungii Request" screen
    Then user is alerted for "PICKUP REQUEST NO LONGER AVAILABLE"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @regression
  @failed
  Scenario: Verify If Driver Receives More Than One Requests Then He Cant Accept The Bungii whos TELET time overlaps With Already accepted Solo Scheduled Bungiis
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
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |
  #change login step 1

  @regression
  Scenario: Verify If Driver Receives More Than One Requests Then He Cant Accept The Bungii whos TELET time overlaps With Already accepted Duo Scheduled Bungiis

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
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "two" available trip
    And I Select Trip from available trip
    And I click "ACCEPT" button on "Bungii Request" screen
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    Then I should able to see "zero" available trip

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |

  @regression
  Scenario: Verify Driver Receives Alert Stating That The Trip Has Already Been Accepted By Him If He Receives Request Notification After Accepting The Trip From Available Trips
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    Given I Switch to "customer" application on "same" devices

    Given I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                      |
      | NEXT_POSSIBLE | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark |

    Then I wait for "2" mins
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    Then I should be navigated to "BUNGII DETAILS" screen
    When I accept selected Bungii
    And I Switch to "customer" application on "same" devices
    And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
    Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
    When I click "View" on alert message
    Then user is alerted for "PICKUP ALREADY ACCEPTED BY YOU"
    And I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

  @ready
  @failed1
  Scenario: Verify Status Of Scheduled Bungii In The Scheduled Trip Page When Only One Driver Accepts It

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

  @ready
  @failed1
  Scenario:Verify Details In The Bungii Details Screen When Required Number Of Drivers Accepts Trip

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
  Scenario: Verify Customer Receives Notification When Control Driver Starts Duo Scheduled Bungii
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
  Scenario: Verify Customer Receives Notification When Driver Starts Solo Scheduled Bungii
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
  @FAILED2702
  @regression
  Scenario: Verify Driver Doesnt Receive Scheduled Trip Request If His Home Is Over 30 Mins Away From Pickup Location
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


  @ready
  @failed
  Scenario: Verify Re-searched Trip Request Show Urgent Notification Text If Admin Re-searches Bungii Less Than Hour From Scheduled Trip Time Or For Trip Time Between 24 Hours Prior To Current Time
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

  @ready
  @failed
  Scenario: Verify Re-searched Trip Request Doesnt Show Urgent Notification Text If Is More Than One Hour From The Scheduled Trip Time
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
  Scenario: Verify Validation Message Shown If Driver Tries To Start A Bungii More Than 60 Mins Before The Scheduled Time
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
  Scenario: Verify Driver Is Not Allowed To Start Bungii Within 60 Mins Of Scheduled Time If Required Number Of Drivers Have Not Accepted The Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And As a driver "Testdrivertywd_appledv_b_seni Stark_dvThree" and "Testdrivertywd_appledv_b_matt Stark_dvOnE" perform below action with respective "DUO SCHEDULED" trip
    #And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      |               |
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :9955112208 and  Password :Cci12345
    #And I enter phoneNumber :9999998086 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from scheduled trip
    And I start selected Bungii
    Then user is alerted for "REQUIRED DRIVER NOT ACCEPTED"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @ready
  Scenario: Verify Driver Is Not Allowed To Start Bungii If The Customer Is Currently In An Ongoing Solo Scheduled Trip
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

  @ready
  @failed
  Scenario: Verify Driver Is Not Allowed To Start Bungii If The Customer Is Currently In An Ongoing Duo Scheduled Trip
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
      | CUSTOMER1_PHONE | 9999993015      |

  @FAILED2802
  @regression
  Scenario:  Verify If Control Driver Is Allowed To Complete The Trip And Proper Summary Detail Is Shown
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
  Scenario: Verify If Non Control Driver Completes Trip Before Control Driver Then He Is Shown Waiting Screen Till The Control Driver Completes And The Correct Summary Is Shown Thereafter
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


  @ready
  @failed
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting Solo Scheduled Bungii
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
    When I click "YES" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @failed
  @ready
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting Duo Scheduled Bungii
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
    When I click "YES" on alert message
    Then I should be navigated to "SCHEDULED BUNGII" screen

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |


  @regression

  Scenario:Verify Driver Cannot Cancel Scheduled Bungii From App When Bungii Is Not Started And He Should Send SMS To Cancel Solo Scheduled Bungii
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
  Scenario: Verify Control Driver Can Cancel Scheduled Duo Bungii From The App In The Enroute State
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
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen


  @regression
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
    Then I should be navigated to "HOME" screen

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
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen


  @regression
  @failed
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
    Then I should be navigated to "HOME" screen

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then I should be navigated to "Home" screen


  @ready
  @failed
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
#CMA1513
#use customer with only one card
  @regression
    @FAILED0203_02
  Scenario Outline: Verify Customer Cannot Delete Payment Method Linked To Any On-going Or Scheduled Trips
    When I Switch to "customer" application on "same" devices
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
    When I click "ADD" button on "PAYMENT" screen
    And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details page
    And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details page
    And I click "ADD PAYMENT METHOD" button on "PAYMENT" screen
    Then I should see "new card" on Payment page

    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone  | Customer Name       | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER | VishalIHHnZkrz Test | Cci12345          |
    When I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "newly created user" user
    When I Select "PAYMENT" from Customer App menu
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then Alert message with CARD IS ASSOCIATED TO TRIP text should be displayed
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | NEW_USER_NUMBER |                 |
    When I Switch to "customer" application on "same" devices
    And I logged in Customer application using  "newly created user" user
    When I Select "PAYMENT" from Customer App menu
    When I swipe "other" card on the payment page
    And I tap on "Delete" on Payment page
    Then Alert message with Delete Warning text should be displayed
    When I accept Alert message
    Then I should see "the card has been deleted" on Payment page
    Examples:
      | Scenario | First Name | Last Name | Email ID                        | Phone Number       | Password | Referral Code | Source   | CardNo    | Expiry | Postal Code       | Cvv       |
      | VALID    | Mike       | Test      | vishal.bagi@creativecapsule.com | {RANDOM_PHONE_NUM} | Cci12345 |               | Facebook | VISA CARD | 12/22  | VALID POSTAL CODE | VALID CVV |


  @failed
  @ready
  Scenario: Verify Customer Can Request Cancel Solo Scheduled Bungii Through SMS To Admin If No Driver Accepts And Processing Gets Over
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

  @ready
  @failed
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

  @ready
  @failed
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

  @failed
  @ready
  Scenario: Verify Customer Can Request Cancel Scheduled Trip Via Admin SMS After 2 Hour (5 mins in QA Auto) Processing Is Over
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

  @ready
  @failed
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

  @FAILED2602
  @regression
  Scenario:Verify If Driver Rating Is Shown To Customer On Bungii Details Page When Driver Accepts Scheduled Bungii
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
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
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
    Then "3" stars should be highlighted for Driver 1
    When I select "4" Ratting star for duo Driver 2
    Then "4" stars should be highlighted for Driver 2
    When I click "DONE" button on "BUNGII COMPLETE" screen
    When I click "I DON'T LIKE FREE MONEY" button on "Promotion" screen


  #its scheduled time not initial request time
  @regression
  Scenario: Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Solo Scheduled Trip

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

  Scenario: Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Duo Scheduled Trip
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |


  @regression
  @failed
  Scenario: Verify Customer Is Not Allowed To Request Bungii If TELET Time Of The New Bungii Overlaps With Already Scheduled Bungii
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |

    And I get TELET time of of the current trip
    And I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
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
  Scenario: Verify If Incoming Scheduled Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted     | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    When I clear all notification
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
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "zero" available trip

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |


  @regression
  Scenario: Verify If Incoming Scheduled Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
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
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should able to see "zero" available trip
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone      |
      | CUSTOMER1_PHONE | CUSTOMER_PHONE_EXTRA |

  @FAILED2702
  @regression
  Scenario: Verify If Incoming On-demend Trip Request TELET (Trip A) Overlaps Start Time Of Previously Scheduled Trip (Trip B) Then Driver Doesnt Receive Notification Or Offline SMS
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

  @failed
  @regression
  Scenario: Verify If Incoming Ondemand Trip TELET Overlaps Scheduled Trip TELET Then Request Should Not Be Sent To Driver
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | denver   | Accepted     | 15 min ahead |
    When I clear all notification
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

  @failed
  #KNOWN ISSUE , TELET TIME IS NOT RECALCULATED
  @ready
  Scenario: Verify TELET Of Re-searched Trip Should Not Be Same As That Of Previous Trip - KNOWN ISSUE
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


  @regression
  @nonstable
  Scenario: Verify If Incoming Scheduled Request Start Time (Trip 3) Overlaps With TELET Of Accepted Stacked request (Trip 2) Then Driver Doesnt Receive Scheduled Notification Or Offline SMS

    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | denver   | Enroute      |
    When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I wait for "2" mins
    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |

    And I click on notification for "Driver" for "stack trip"
    When I click "View" on alert message
    When I click "ACCEPT" button on "Bungii Request" screen
    When I click "OK" on alert message
    And I get TELET time of currrent trip of customer 2

    And I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I enter Username :8888889907 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen

    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                    | Drop Location                    | Geofence |
      | Solo   | 2052 Welton Street Denver Colorado | 16th Street Mall Denver Colorado | denver   |

    And I click "Get Estimate" button on "Home" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time                              | PickUpImage | Save Trip Info |
      | 30       |           |              | <TIME WITHIN TELET OF CUSTOMER 2> | large image | Yes            |
    When I click "Done" button on "Success" screen
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | 8888889917      |


  @ready
  @failed
  Scenario:Verify Driver Notification Received For 30 Mins Before Scheduled Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    When I accept selected Bungii
    And I Switch to "customer" application on "same" devices
    When I wait for Minimum duration for Bungii Start Time
    And I click on notification for "driver" for "TAP NOTIFICATION TO ACTIVATE BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

