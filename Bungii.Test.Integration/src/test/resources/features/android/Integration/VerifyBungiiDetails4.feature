    @android
    @bungii
    #These feature will run in Goa geofence
    Feature: Admin God Mode Feature
  # Customer  Testcustomertywd_appleand_A Android - 9393939393
  # Driver Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test
  
  
      @ready
      Scenario: Verify the DUO trip started by non controller driver and controller driver is removed and new driver is added to the same trip
    
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled" trip
          | driver1 state  |  driver2 state  |
          | Accepted       |  Accepted       |
          |                |  Enroute        |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        Then I remove "control" driver and researches Bungii
        When I Select "Edit Trip Details" option
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
  
      @ready
      Scenario: Verify that the list displays all completed Bungiis in descending order of date.
      #Given I am logged in as "Testcustomertywd_appleand_A Android" customer
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled Researched" trip
          | driver1 state      | driver2 state      |
          | Accepted           | Accepted           |
          | Arrived            | Arrived            |
          | Loading Item       | Loading Item       |
          | Driving To Dropoff | Driving To Dropoff |
          | Unloading Item     | Unloading Item     |
          | Bungii Completed   | Bungii Completed   |
        
        
#      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
#        | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
#        | NEXT_POSSIBLE  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
#      And As a driver "Testdriver_goa_b Android_test" perform below action with respective "Solo Scheduled" trip
#        | driver1 state      |
#        | Accepted           |
#        | Arrived            |
#        | Loading Item  |
#        | Driving To Dropoff |
#        | Unloading Item |
#        | Bungii Completed |
#      When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
#        | Bungii Time   | Customer Phone | Customer Name                       |
#        | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android |
#      And As a driver "Testdriver_goa_c Android_test" perform below action with respective "Solo Ondemand" trip
#        | driver1 state|
#        | Accepted |
#        | Arrived |
#        | Loading Item |
#        | Driving To Dropoff |
#        | Unloading Item |
#        | Bungii Completed |
#      Then I Switch to "customer" application on "same" devices
#      When I tap on "Menu" > "MY BUNGIIS" link
#      And I click on "Past" tab
#      Then I verify that completed bungiis are displayed in "Descending order of date"
  
  
      @ready
      Scenario: Verify that the driver can be assigned to a duo trip irrespective of drive time to pickup
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
    
        When I Switch to "customer" application on "same" devices
        And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "30 mins"
        And I select Bungii Time as "next possible scheduled"
        And I add "1" photos to the Bungii
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |
  
      @ready
      Scenario: Verify that the driver can be assigned to a duo trip irrespective of driver home address
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
    
        When I Switch to "customer" application on "same" devices
        And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add loading/unloading time of "30 mins"
        And I add "1" photos to the Bungii
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
    
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |
      @ready
      Scenario: Verify updating time to past time and date
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
    
        When I enter "goa location in pickup and dropoff fields long distance" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add "1" photos to the Bungii
        And I add loading/unloading time of "30 mins"
        And I select Bungii Time as "NEW BUNGII TIME"
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time to past" to future time
        And I click on "VERIFY" button
        Then the "Please check the date/time selected. You cannot select a past date/time." message is displayed
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |
  
  
      @ready
      Scenario: Verify if admin can update date_time for a solo trip for which no driver has accepted
        Given I am on customer Log in page
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
        When I enter "goa location in pickup and dropoff fields long distance" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I add "1" photos to the Bungii
        And I add loading/unloading time of "30 mins"
        And I select Bungii Time as "NEW BUNGII TIME"
        And I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        And I check if the customer is on success screen
        Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
        And I wait for "2" mins
        
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time" to future time
        And I click on "VERIFY" button
        Then the "Your changes are good to be saved." message is displayed
        And I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        
        And I wait for "2" mins
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        When I Select "Research Driver" option
        Then I verify that time change is saved
        Then I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |
  
  
      @ready
      Scenario: Verify if admin can update date_time for a solo trip for which a driver has accepted and Customer has no conflicting trips at the new time.
        Given that solo schedule bungii is in progress
          | geofence | Bungii State | Bungii Time   |
          | goa   | Accepted     | NEXT_POSSIBLE |
        And I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I open the trip for "Testcustomertywd_appleand_A Android" customer
        And I Select "Edit Trip Details" option
        And I change the "trip time" to future time
        And I click on "VERIFY" button
        Then the "Your changes are good to be saved." message is displayed
        And I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed
        When I Select "Research Driver" option
        Then I verify that time change is saved
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | CUSTOMER1_PHONE |                 |
  
  
  
  
      @ready
      Scenario: Verify that correct date of the trip is displayed as per the timezone of the geofence.
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time  |
          | goa  | Accepted     | 15 min ahead |
        And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Ondemand" trip
          | driver1 state |
          | Bungii Completed      |
    
        When I Switch to "customer" application on "same" devices
		Given I am on customer Log in page
		And I am logged in as "Testcustomertywd_appleand_A Android" customer
        And I tap on "Menu" > "My Bungiis" link
        And "MY BUNGIIS" page should be opened
        And I click on "Past" tab
        Then I verify the field "timezone"
    
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
  
  
  
      @ready
      Scenario: Verify that admin can assign a driver to a solo trip when it is in searching status.
     
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time   |
          | goa  | Scheduled    | NEXT_POSSIBLE |
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
        And I Switch to "customer" application on "same" devices
        And I am logged in as "Testcustomertywd_appleand_A Android" customer
        And I tap on "Menu" > "MY BUNGIIS" link
        And I select already scheduled bungii
        Then I verify the "solo driver names"
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9393939393      |                 |
        
        