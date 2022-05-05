@android

Feature: Rejection Popup on Driver App

  @ready
#    check
#    works
#  @testsweta
  Scenario: Verify that rejection popup,cancel functionality and all reasons are displayed for scheduled deliveries on available trips page
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |

    Then I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I wait for "4" mins
    And I navigate to admin portal
    And I wait for "4" mins
    And I log in to admin portal
    And I wait for "4" mins
    And I Select "Scheduled Trip" from admin sidebar
    And I wait for "4" mins
    And I open the trip for "Testcustomertywd_appleRicha Test" the customer for delivery details
    Then I check if delivery status is "No Driver(s) Found"

#    Core-3008: To verify that customer trip with "no driver found" status is displayed under Available Deliveries of driver app

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I click on the back button and verify the rejection popup
    And I check that latest reason is saved when "back" button is clicked
    And I check if all reasons are displayed on rejection popup
    And I click on "CANCEL" button on rejection popup
    Then I should be navigated to "BUNGII DETAILS" screen
    And I click on the back button and verify the rejection popup
    And I click on "SUBMIT" button on rejection popup
    Then I check if the reason is saved in db

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleRicha Test" the customer for delivery details
    Then I stop searching driver

    And I wait for "2" mins
    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :8888881019 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    Then I check if "Testcustomertywd_appleRicha Test" customer trip that is rejected is displayed


  @ready

  Scenario: Verify that Rejection reason pop-up is not displayed to driver when the toggle is disabled on Admin Portal
    And I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I enter phoneNumber :9049840018 and  Password :Cci12345
    And I click "Log In" button on Log In screen on driver app
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

    Given I Switch to "customer" application on "same" devices
    Given I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 8805368840     | Cci12345          | Testcustomertywd_appleRicha Test |

    Then I wait for "2" mins
    And I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE BUNGIIS" from driver App menu
    And I Select Trip from available trip
    And I click on the back button and verify that rejection popup is absent

  @ready

  Scenario: Verify UI and behaviour of Rejection reason pop-up for stacked trips
    Given that ondemand bungii is in progress
      | geofence | Bungii State |
      | atlanta  | ARRIVED      |
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "valid atlanta" driver
        #put driver on background
    When I Switch to "customer" application on "same" devices
    When I request "Solo Ondemand" Bungii as a customer in "atlanta" geofence
      | Bungii Time | Customer Phone | Customer Name                      | Customer label | Customer Password |
      | now         | 9871450107     | Testcustomertywd_apple_AGQFCg Test | 2              | Cci12345          |

    And I Switch to "driver" application on "ORIGINAL" devices
    Then I click on notification for "STACK TRIP"
    And Bungii Driver "view stack message" request
    Then "correct stack trip details" should be displayed on Bungii request screen
    And I tap on the "REJECT" Button on Bungii Request screen
    And I verify the rejection popup is displayed
    And I click on "SUBMIT" button on rejection popup
    Then Bungii driver should see "Arrived screen"

    Then I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | CUSTOMER1_PHONE | CUSTOMER2_PHONE |


    @ready
    Scenario: Verify rejection reason pop-up for on-demand trips
      Given I am on customer Log in page
      When I am logged in as "valid boston" customer
      And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
      And I close "Tutorial" if exist

      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid boston" driver
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      And I tap on "Go Online button" on Driver Home page

      And I Switch to "customer" application on "same" devices
      And I enter "new boston pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "2" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      And I Open "driver" application on "same" devices
      And Bungii Driver "rejects On Demand Bungii" request
      And I verify the rejection popup is displayed
      And I click on "SUBMIT" button on rejection popup
      Then Bungii driver should see "Home screen"

#    Core-3008: To verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
  @ready
    Scenario: Verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
      When I request Partner Portal "SOLO" Trip for "Floor and Decor" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |washingtondc| NEXT_POSSIBLE | 9999999127 | Testcustomertywd_appleNewRB Customer|
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN PORTAL"
      And I wait for "4" mins
      And I navigate to admin portal
      And I wait for "4" mins
      And I log in to admin portal
      And I wait for "4" mins
      And I Select "Scheduled Trip" from admin sidebar
      And I wait for "3" mins
      And I open the trip for "Testcustomertywd_appleNewRB Customer" the customer for delivery details
      Then I check if delivery status is "No Driver(s) Found"

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_appledc_a_drvC WashingtonC" driver
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      Then I Select Trip from available trip
