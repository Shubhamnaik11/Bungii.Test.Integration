@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part G

  Background:
    
    #####################################################
  
  @regression
  Scenario: Verify Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:arrived
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | arrived      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas 2" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then for a Bungii I should see "Arrived screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    Then Bungii driver should see "Arrived screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify Non-Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:enroute
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | enroute      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then for a Bungii I should see "Enroute screen"

     #non control driver
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    Then Bungii driver should see "Enroute screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

  @regression
  Scenario: Verify Non-Control Driver Can Cancel Duo Bungii From The App In The First Two States Of Started Bungii - Scenario:arrived
    Given that duo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   | Customer        | Driver1         | Driver2         |
      | kansas   | arrived      | NEXT_POSSIBLE | Kansas customer | Kansas driver 1 | Kansas driver 2 |
    When I Switch to "customer" application on "same" devices
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    Then for a Bungii I should see "Arrived screen"

    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Kansas driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    Then Bungii driver should see "Arrived screen"
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    And I Select "HOME" from driver App menu
    Then Bungii driver should see "Home screen"

    When I Switch to "customer" application on "same" devices
    Then Alert message with DRIVER CANCELLED text should be displayed
    When I click "OK" on alert message
    Then "Home" page should be opened

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
  Scenario: Verify If Customer Can Rate Driver For The Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8888888881     | Testcustomertywd_appleRicha Test | Cci12345          |
    Given I am on customer Log in page
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And As a driver "Testdrivertywd_appleks_rathree Test" and "Testdrivertywd_appleks_ra_four Kent" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state    | driver2 state    |
      | Bungii Completed | Bungii Completed |
    When I Switch to "customer" application on "same" devices
    And Bungii customer should see "correct rating detail for duo" on Bungii completed page
    When I select "3" Ratting star for duo "Driver 1"
    And I select "5" Ratting star for duo "Driver 2"
    Then I tap on "OK" on Bungii Complete
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 8888888881 |                 |

  @regression
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting The Scheduled Solo Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | kansas1  | Accepted     | 15 min ahead |
  
    And I open Admin portal and navigate to "Scheduled Deliveries" page
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appleks_rathree Test" perform below action with respective "Solo Scheduled" trip
      | driver1 state |
      | Accepted      |

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 1" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    Then Bungii driver should see "Scheduled Bungii screen"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

  @regression
 
  Scenario: Verify If Re-searched Driver Can Cancel Trip After Starting The Scheduled Duo Trip
    When I request "duo" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |

    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |
  
    And I open Admin portal and navigate to "Scheduled Deliveries" page
  
    And I remove current driver and researches Bungii
    And As a driver "Testdrivertywd_appleks_ra_four Kent" and "Testdrivertywd_appleks_rathree Test" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Accepted      | Accepted      |

    When I switch to "ORIGINAL" instance

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "kansas driver 2" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
  
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    And I click the "Cancel" button on "update" screen
    Then Alert message with DRIVER CANCEL BUNGII text should be displayed
    When I click "YES" on the alert message
    Then Bungii driver should see "Scheduled Bungii screen"

    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |

  #@regression
  @ready
  Scenario: Verify Driver Notification - 30 Mins Before Scheduled Trip
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time   |
      | kansas   | Scheduled    | NEXT_POSSIBLE |

    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    And I tap on "Available Trips link" on Driver Home page
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    When I Switch to "customer" application on "same" devices

    And I wait for Minimum duration for Bungii Start Time
    Then I click on notification for "TAP NOTIFICATION TO ACTIVATE BUNGII"
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE |                 |

     #keep this scenario at last
#CMA 1513: delete card once trip is cancel
  #@regression
  @ready
  Scenario Outline: Verify Customer Cannot Delete Payment Method Linked To Any Ongoing Or Scheduled Trip
    Given I am on Sign up page
    When I enter "unique" customer phone number on Signup Page
    And I enter "valid test" data in mandatory fields on Signup Page
    And I enter "ValidPercent" promo code on Signup Page
  #  And I enter "Referral" promo code on Signup Page
    And I tap on the "Sign Up" button on Signup Page
    And I enter "valid" Verification code
    And I tap on the "Verification Continue" Link
    Then The user should be logged in

    When I tap on "Menu" > "Payment" link
    And I get the number of cards present
    And I tap on "Add" on Payment page
    And I tap on "Credit or Debit Card" on Payment page
    And I enter "<Card Detail>" on Card Details page
    And I enter "<Card Expiry>" on Card Details page
    And I enter "<CVV>" on Card Details page
    And I enter "<Postal Code>" on Card Details page

    And I tap on "Add Card" on Payment page
    Then I should see "the card has been added" on Payment page


    When I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone  | Customer Name | Customer Password |
      | NEXT_POSSIBLE | NEW_USER_NUMBER |               | Cci12345          |

    Given I am on customer Log in page
    And I am logged in as "new test customer" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "PAYMENT" link
    Then "Payment" page should be opened
    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
  #  Then Alert message with Delete Warning text should be displayed
    And I should see "Payment Method is already associated to a trip" on Payment page
    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | NEW_USER_NUMBER |                 |
    Given I am on customer Log in page
    And I am logged in as "new test customer" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    When I tap on "Menu" > "PAYMENT" link
    When I swipe "default" card on the payment page
    And I tap on "Delete" on Payment page
    Then I should see "message when no payment methods exist" on Payment page
    Examples:
      | Scenario       | Card Detail                | Card Expiry       | CVV       | Postal Code       |
      | VALID_discover | valid discover card number | valid expiry date | valid cvv | valid postal code |


  #@regression
  @ready
  Scenario: Verify Driver Doesnt Receive Scheduled Request If The Request Is Sent Outside Of The Time That Is Set For Trip Alert Settings
    When I clear all notification
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    When I Select "ALERT SETTINGS" from driver App menu
    And I update kansas driver todays trip alert setting to outside current time
    When I Switch to "customer" application on "same" devices
    When I request "Solo Scheduled" Bungii as a customer in "denver" geofence
      | Bungii Time   | Customer Phone | Customer Name                    | Customer Password |
      | NEXT_POSSIBLE | 8805368840     | Testcustomertywd_appleRicha Test | Cci12345          |
    And I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I should be navigated to "AVAILABLE BUNGIIS" screen
    And I should able to see "zero" available trip
    And I Select "ALERT SETTINGS" from driver App menu
    And I update trip setting of "TODAY" to "12:00 AM" to "11:59 PM"
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8805368840     |                 |


  @regression
  Scenario: Verify that error message on android and iOS when driver accepts a trip1 through push notification and admin assign trip2 for another customer through portal such that trip1 TELET overlaps start time of trip2, then error message is shown to the driver when he starts either of the trips
    Given that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time     |
      | Kansas   | Accepted     | 0.75 hour ahead |

    When I Switch to "customer" application on "same" devices
    And I am logged in as "valid kansas" customer
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    ########################################################################
