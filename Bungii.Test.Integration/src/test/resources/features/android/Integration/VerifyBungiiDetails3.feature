    @android
    @bungii
    #These feature will run in Goa geofence
    Feature: Verify Bungii Estimations and Requests
  
      #Customer : Testcustomertywd_appleand_E Android and Testcustomertywd_appleand_B Android
      #Driver    Testdriver_goa_e Android_test" driver Testdriver_goa_B Android_test" driver
      #Geofence : Goa
  
      @regression
        #Stable
      Scenario: Verify that correct trip details are displayed on the grey bar of the Estimate screen.
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        #Estimated Cost value reads $0.00 as default
        Then I should see "zero estimated cost" on Bungii estimate
        And I add loading/unloading time of "30 mins"
        Then I should see "all elements" on Bungii estimate

@ready
    Scenario: Verify that the Estimated cost on the grey bar is updated on updating load/unload time and promo code.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
  
      And I add loading/unloading time of "15 mins"
      And I tap on "Promo code value" on Estimate screen
      And I add "PROMOTER TYPE PROMO" PromoCode
      And I tap "Add" on Save Money page
      Then I should able to see expected promo code in available promo code
  
      And I tap on "Back" icon of page
      Then I should see "estimated cost" on Bungii estimate

@regression
  #stable
    Scenario: Verify that four masked characters are displayed before the last four characters of Payment Mode.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer

      When I Switch to "customer" application on "same" devices
      And I tap on "Menu" > "Payment" link
      Then I should see "masked card number" on Payment page

@ready
    Scenario: Verify that clicking on Details field on the Estimate screen opens a placeholder in text box
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

@ready
    Scenario: Verify that Bungii can be requested when special charaters have been entered in the Details field on Estimate screen.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I select Bungii Time as "next possible scheduled"
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "special characters" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9889889888      |                 |


 @ready
    Scenario: Verify that the text entered in Details is displayed after customer schedules a Bungii of an on demand bungii that has timed out.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_E Android" customer
      When I Switch to "customer" application on "same" devices
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
      And I wait for "8" mins
      And I click "OK" button on the "Driver Not Available" screen
      And I click "Schedule Bungii" button on the "Driver Not Available" screen
      Then "Estimate" page should be opened
      And I should be able to see "Note Details" Text

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9889889888      |                 |

    @regression
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in an On Demand Bungii request.
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
  
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request.
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
        And I select Bungii Time as "BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
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
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request, when viewed from Available Trips page.
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "next possible scheduled"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request, when viewed from Available Trips page.
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
        And I select Bungii Time as "next possible scheduled"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in the Bungii Details page for a Scheduled Bungii.
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "OLD BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
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
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress.
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
  
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "OLD BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
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
        Then I should be able to see "Details From Customer" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress.
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        #And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
        And I am logged in as "Testdriver_goa_e Android_test" driver
  
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "OLD BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from driver available trip
        And I tap on "ACCEPT" on driver Trip details Page
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from scheduled trip
        Then I start selected Bungii
        When I slide update button on "EN ROUTE" Screen
        And I slide update button on "ARRIVED" Screen
        And I click on "MORE" button
        Then I should not be able to see "Details From Customer" on screen
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
  
  
      @ready
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request.
        When I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
    
        When I Switch to "customer" application on "same" devices
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
        And I enter "Goa pickup and dropoff location" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "NEW BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
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
          | 9889889888      |                 |
  
  
  
      @ready
    #web scenario
      Scenario: Verify that Admin is not allowed to add multiple driver for solo bungii and more than 2 drivers for DUO
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_E Android | Cci12345          |
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_E Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver for the "Solo" trip
        Then I am not allowed to assign more drivers
        And I click on "Close" button
        When I open the trip for "Testcustomertywd_appleand_C Android" the customer
        And I Select "Edit Trip Details" option
        And I assign driver for the "Duo" trip
        Then I am not allowed to assign more drivers
		And I cancel all bungiis of customer
		  | Customer Phone  | Customer2 Phone |
		  | 9889889888      | 9999992222      |
  
      @ready
    #web scenario
      Scenario: Verify that if non control driver started the trip and control driver is removed by Admin and assigned a new driver, the non controller driver becomes new control driver
      Verify that a message/ alert is not displayed to ADMIN when no field on Edit schedule Trip is edited and Admin clicks Verify button
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9889889888     | Testcustomertywd_appleand_E Android | Cci12345          |
        And As a driver "Driver_goa_e Android_test" and "Driver_goa_f Android_test" perform below action with respective "DUO SCHEDULED" trip
          | driver1 state | driver2 state |
          | Accepted      | Accepted      |
          |               | Enroute       |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_E Android" the customer
        And I remove "control" driver and researches Bungii
        And I Select "Edit Trip Details" option
        And I check if a validation message "<string>" is shown
        And I assign driver for the "control" trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        And I verify that noncontrol driver becomes control driver
		And I cancel all bungiis of customer
		  | Customer Phone  | Customer2 Phone |
		  | 9889889888      |                 |
  
      @ready
      #web
      Scenario: Verify that the date and time displayed in edit Schedule bungii page against a drivers schedule list is proper timezone and not in UTC
        Given I am on the LOG IN page on driver app
        And I am logged in as "testdriver_goa_e Android_test" driver
        And I tap on "Go Online button" on Driver Home page
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_E Android"
          | geofence | Bungii State | Bungii Time   |
          | goa      | Accepted     | NEXT_POSSIBLE |
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN_PORTAL"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_E Android" the customer
        Then I check that time is not displayed in UTC
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9889889888      |                 |
      @ready
    #web
      Scenario: Verify that Cancel button goes off once the solo scheduled Trip is cancelled
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_E Android"
          | geofence | Bungii State | Bungii Time   |
          | goa      | Accepted     | NEXT_POSSIBLE |
        When I Switch to "customer" application on "same" devices
        And I am logged in as "Testcustomertywd_appleand_E Android" customer
    
        And I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "testdriver_goa_e android_test" driver
    
        And I wait for "2" mins
        And I open Admin portal and navigate to "Scheduled Deliveries" page
        And I Cancel Bungii with following details
          | Charge | Comments | Reason                         |
          | 0      | TEST     | Outside of delivery scope      |
        Then "Bungii Cancel" message should be displayed on "Scheduled Trips" page
        And "Cancel button" should not be displayed

