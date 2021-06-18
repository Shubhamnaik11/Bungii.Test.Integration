    @android
    
    #These feature will run in Goa geofence
    Feature: Verify Bungii Estimations and Requests
  
      #Customer : Testcustomertywd_appleand_E Android   9393939393
      #Driver    Testdriver_goa_e Android_test" driver
      #Geofence : Goa
  ##############################################################BLOCK SHARES USER WITH FAILED ONDEMAND FEATURE
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request, when viewed from Available Trips page
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
    
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "next possible scheduled"
        And I select Bungii Time as "2 HOUR DELAY"
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in the Bungii Details page for a Scheduled Bungii
        When I Switch to "customer" application on "same" devices
    
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "OLD BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        And I tap on "ACCEPT" on driver Trip details Page
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
    
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "OLD BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
    
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
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
        Then I should be able to see "Details From Customer" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
  
  
  
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request, when viewed from Available Trips page
        When I Switch to "customer" application on "same" devices
    
        Given I am on customer Log in page
        And I am logged in as "valid goa customer" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "next possible scheduled"
        And I select Bungii Time as "2 HOUR DELAY"
    
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
      @ready
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii push notification request
        When I Switch to "driver" application on "same" devices
        When I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
    
        When I Switch to "customer" application on "same" devices
        And I am logged in as "valid goa customer" customer
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        #And I select Bungii Time as "NEW BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
    
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
    
        And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
        Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
        When I click "View" on alert message
        Then I should be able to see "Customer Note" Text
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 8888882028      |                 |
  
  
  ##############################################################
      @regression
        #Stable
      Scenario: Verify that correct trip details are displayed on the grey bar of the Estimate screen
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        #Estimated Cost value reads $0.00 as default
        Then I should see "zero estimated cost" on Bungii estimate
        And I add loading/unloading time of "30 mins"
        Then I should see "all elements" on Bungii estimate

@regression
  #stable
    Scenario: Verify that four masked characters are displayed before the last four characters of Payment Mode.
  When I Switch to "customer" application on "same" devices
  Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer

      And I tap on "Menu" > "Payment" link
      Then I should see "masked card number" on Payment page

@regression
    Scenario: Verify that clicking on Details field on the Estimate screen opens a placeholder in text box
  When I Switch to "customer" application on "same" devices
  Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer
  
      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I tap on "Details" on Estimate screen
      Then I should see placeholder textbox
      When I enter "text" in Additional Notes field
      Then the "remaining characters value" should change
      When I enter "500 characters" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Details" on Estimate screen
      When I enter "1 more character" in Additional Notes field
      Then the "remaining characters value= 0" should change

@regression
#Stable
  @nonstable
    Scenario: Verify that Bungii can be requested when special charaters have been entered in the Details field on Estimate screen
  When I Switch to "customer" application on "same" devices
  Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer

      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "special characters" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      And I select Bungii Time as "2 HOUR DELAY"
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9889889888      |                 |

 @regression
 #stable
    Scenario: Verify that the text entered in Details is displayed after customer schedules a Bungii of an on demand bungii that has timed out
   When I Switch to "customer" application on "same" devices
   Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer
      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "text" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I wait for "3" mins
      And I click "Schedule Bungii" button on the "Driver Not Available" screen
      #add code to check bungii  is scheduled with pickupnote
   
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9889889888      |                 |

   
  
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request
        When I Switch to "customer" application on "same" devices
  
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        #And I select Bungii Time as "BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
  
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        Then I click "Done" button on "Success" screen
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        And I tap on "ACCEPT" on driver Trip details Page
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
      @regression
      @nonstable
      Scenario: Verify that driver is not able to view  the text entered in Details field while solo bungii is in progress if text is not entered
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        Then "Estimate" page should be opened
        #And I select Bungii Time as "OLD BUNGII TIME"
        And I select Bungii Time as "2 HOUR DELAY"
    
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I click "Done" button on "Success" screen
        And I get the pickupref
        And As a driver "Testdriver_goa_e Android_test" perform below action with respective "SOLO SCHEDULED" trip
          | driver1 state |
          | Accepted      |
          | enroute       |
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        When I slide update button on "EN ROUTE" Screen
        And I slide update button on "ARRIVED" Screen
        And I click on "MORE" button
        Then I should not be able to see "Details From Customer" on screen
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
      @regression
      @nonstable
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in an On Demand Bungii request.
        When I Switch to "customer" application on "same" devices
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        When I click on notification for "on demand trip"
        Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
        When I click "YES" button on alert message
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
        
     