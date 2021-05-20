@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part D
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices


  @regression
    #Stable
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

    #its scheduled time not initial request time
  @regression
    #stable
  Scenario: Verify TELET Is Calculated Correctly (Initial Request Time +  (Estimated Duration(1.5)) + 30 Minutes) For Solo Scheduled Trip

    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Scheduled    | NEXT_POSSIBLE |
    And I get TELET time of of the current trip
    Then Telet time of current trip should be correctly calculated
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  @failures
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
    @failures
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

  @failures
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

  @failures
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
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I remove current driver and researches Bungii
    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    Then Telet time of research trip should be not be same as previous trips

  @regression
  #stable
  Scenario: Verify If Incoming Scheduled Request Start Time (Trip 3) Overlaps With TELET Of Accepted Stacked request (Trip 2) Then Driver Doesnt Receive Scheduled Notification Or Offline SMS
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | denver   | Enroute      |
    #When I clear all notification
    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    And I wait for "2" mins
    When I Switch to "customer" application on "same" devices

    When I request "Solo Ondemand" Bungii as a customer in "denver" geofence
      | Bungii Time | Customer Phone | Customer Password | Customer Name                      | Customer label |
      | now         | 8888889917     | Cci12345          | Testcustomertywd_appleZTDafc Stark | 2              |
    
    And I view and accept virtual notification for "Driver" for "stack trip"

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
  @failures
  Scenario: Driver Notification : 30 Mins Before Scheduled Delivery
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | denver   | Accepted    | 0.75 hour ahead |

    And I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "valid denver" driver
    #And I Select "AVAILABLE BUNGIIS" from driver App menu
    #And I Select Trip from available trip
    #When I accept selected Bungii
    When I wait for Minimum duration for Bungii Start Time
    And I click on notification for "driver" for "TAP NOTIFICATION TO ACTIVATE BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
  
  @ready
  @failures
  Scenario:CUSTOMER: Notification - 2 hours before scheduled trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time    |
      | denver   | Accepted     | 2 hour ahead |
    And I am on the "LOG IN" page
    And I logged in Customer application using  "valid denver" user
    #And I wait for Minimum duration for current Bungii to be T-2 hours
    And I click on notification for "customer" for "T-2 BEFORE SCHEDULED TRIP"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |
    

