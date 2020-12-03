@android
@SoloScheduled
@bungii
#These feature will run in kansas geofence
Feature: SoloScheduled Part C
  
  Background:
    #####################################################
  @ready
    #stable
  Scenario: Verify That Solo Scheduled Bungii can be started 1 hour before the Scheduled start time
    When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 1 hour ahead |
  
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I click "Go Online" button on Home screen on driver app
    And I wait for "2" mins
    
    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer
    And I wait for "2" mins
    
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I wait for "2" mins
    And I Select Trip from driver available trip
    And I wait for "2" mins
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"
    
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
  
  
  @ready
  Scenario: Verify That a Solo scheduled Bungii can be started 30 mins before the scheduled Trip start time
    When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 0.5 hour ahead |
    
    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer
    
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
  
    Then I click "Go Online" button on Home screen on driver app
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then Bungii driver should see "Enroute screen"
    When Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    And Bungii Driver "slides to the next state"
    Then Bungii Driver "completes Bungii"
    
    And I Switch to "customer" application on "same" devices
    And I tap on "OK on complete" on Bungii estimate
    And I tap on "No free money" on Bungii estimate
  
  
  @regression
    #stable
  Scenario: Verify That a scheduled Bungii cannot be started more than 1hr before the scheduled Trip start time
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 1.5 hour ahead |
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I click "Go Online" button on Home screen on driver app
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open first Trip from driver scheduled trip
    #And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then User should see message "60 MINS BEFORE SCHEDULE TRIP TIME" text on the screen
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
  
  @ready
  Scenario: Verify error message on android When Customer has two Bungiis scheduled, and the 1 hour prior start time of second Bungii overlaps with the TELET of the first Bungii
    When that solo schedule bungii is in progress
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Accepted     | 0.75 hour ahead |
    
    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer
    
    And I tap on "Menu" > "Home" link
    And I enter "kansas pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add "1" photos to the Bungii
    And I add loading/unloading time of "30 mins"
    And I select Bungii Time as "next possible scheduled"
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    And I wait for "2" mins
    
    When I open new "Chrome" browser for "ADMIN_PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleand_A Android" customer
    And I Select "Edit Trip Details" option
    And I assign driver for the "Solo" trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed
    
    When I switch to "ORIGINAL" instance
    When that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
      | geofence | Bungii State | Bungii Time  |
      | Kansas   | Scheduled    | 0.5 hour ahead |
    
    And I Open "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer
    
    When I Switch to "driver" application on "same" devices
    When I Open "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    Then I click "Go Online" button on Home screen on driver app
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
    Then I cancel all bungiis of customer
      | Customer Phone | Customer2 Phone |
      | 8888889916     |                 |
    
  @ready
    #stable
  Scenario: Verify driver is able to view pickup note entered in Details when a Solo scheduled bungii is in progress
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid" driver
    And I tap on "Go Online button" on Driver Home page
    
    And I Switch to "customer" application on "same" devices
    When I am on customer Log in page
    When I am logged in as "Testcustomertywd_appleand_A Android" customer
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    And I select Bungii Time as "OLD BUNGII TIME"
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    
    Then I start selected Bungii
    When I slide update button on "EN ROUTE" Screen
    And I slide update button on "ARRIVED" Screen
    And I click on "MORE" button
    And I click on "DETAILS FROM CUSTOMER" button
    And I should be able to see "Details From Customer" Text
    Then Bungii Driver "cancels Bungii"

     #keep this scenario at last
#CMA 1513: delete card once trip is cancel
  #@regression
  @knownissue
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
