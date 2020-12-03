@ios
@scheduled
@bungii
    # this will run in denver
Feature: Solo Scheduled Bungii Part II
  I want to use request Scheduling Bungii with Solo type

  Background:
    #When I clear all notification
    When I Switch to "customer" application on "same" devices

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
    And I open Admin portal and navigate to "Scheduled Deliveries" page

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
    And I open Admin portal and navigate to "Scheduled Deliveries" page

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


