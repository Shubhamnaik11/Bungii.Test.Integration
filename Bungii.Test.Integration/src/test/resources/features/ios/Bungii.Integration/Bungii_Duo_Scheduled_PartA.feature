@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part II
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

  @regression
    #stable
  Scenario: Verify Status Of Scheduled Duo Bungii Trip In Drivers Scheduled Bungiis Menu Screen When Required Number Of Drivers Have Not Accepted It
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | 15 min ahead | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
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
    #stable
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
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
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
    #stable
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
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
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
  Scenario: Verify If Customer Receives Notification Once Required Number Of Drivers Accepts Duo Scheduled Bungii
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
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
   #stable
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
    And I open Admin portal and navigate to "Scheduled Deliveries" page

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

#change login
  @failed
  @ready
  Scenario:Verify That Driver Is Not Able To Accept The DUO Request If The Trip Is Already Accepted By Required Number Of Drivers
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I enter phoneNumber :8888884321 and  Password :Cci12345
    And I click "Log In" button on "Log In" screen on driverApp
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
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
  Scenario: Verify Customer Receives Notification When Control Driver Starts Duo Scheduled Bungii
    When I request "duo" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                      | Customer Password |
      | NEXT_POSSIBLE | 8888889917     | Testcustomertywd_appleZTDafc Stark | Cci12345          |
    Given I am on the "LOG IN" page
    When I enter Username :8888889917 and  Password :{VALID}
    And I click "Log In" button on "Log In" screen
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I Select "MY BUNGIIS" from Customer App menu
    When I Switch to "driver" application on "same" devices
    And As a driver "Testdrivertywd_appledv_b_matt Stark_dvOnE" and "Testdrivertywd_appledv_b_seni Stark_dvThree" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Enroute       | Accepted      |
    And I click on notification for "Customer" for "DRIVERS ARE ENROUTE"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889917     |                 |

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



