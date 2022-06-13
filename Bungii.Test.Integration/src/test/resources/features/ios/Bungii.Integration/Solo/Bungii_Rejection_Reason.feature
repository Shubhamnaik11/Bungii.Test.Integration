@ios

Feature: Rejection Popup on Driver App

    @ready

    Scenario: Verify that rejection popup,cancel functionality and all reasons are displayed for scheduled deliveries on available trips page
        When I Switch to "driver" application on "same" devices
        And I am on the "LOG IN" page on driverApp
        And I enter phoneNumber :9049840208 and  Password :Cci12345
        And I click "Log In" button on "Log In" screen on driverApp
        And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

        Given I Switch to "customer" application on "same" devices
        Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
            | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
            | NEXT_POSSIBLE | 8877661020     | Cci12345          |Testcustomertywd_appleMarkU LutherU |

        Then I wait for "2" mins
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        And I Select Trip from available trip
        And I click on the back button and verify the rejection popup
        And I check if all reasons are displayed on rejection popup
        And I click on "CANCEL" button on rejection popup
        Then I should be navigated to "BUNGII DETAILS" screen
        And I click on the back button and verify the rejection popup
        And I click on "SUBMIT" button on rejection popup
        Then I check if the reason is saved in db

  @ready
#  works
#    driver issue
#  @testsweta
  Scenario: Verify that Rejection reason pop-up is not displayed to driver when the toggle is disabled on Admin Portal

      When I Switch to "driver" application on "same" devices
      And I am on the "LOG IN" page on driverApp
      And I enter phoneNumber :9049840018 and  Password :Cci12345
      And I click "Log In" button on "Log In" screen on driverApp
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      Given I Switch to "customer" application on "same" devices
      Given I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
        | NEXT_POSSIBLE | 8877661021     | Cci12345          | Testcustomertywd_appleMarkV LutherV |

      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE BUNGIIS" from driver App menu
      And I Select Trip from available trip
      Then I click on the back button and verify that rejection popup is absent


#  Core-3008: To verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
    @ready
#      driver issue
#        works
#   @testsweta
    Scenario: Verify that partner portal trip with "no driver found" status is displayed under Available Deliveries of driver app
      When I Switch to "driver" application on "same" devices
      And I am on the "LOG IN" page on driverApp
      And I enter phoneNumber :9049840210 and  Password :Cci12345
      And I click "Log In" button on "Log In" screen on driverApp
      And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist

      When I request Partner Portal "SOLO" Trip for "MRFM" partner
            |Geofence| Bungii Time   | Customer Phone | Customer Name |
            |Kansas| NEXT_POSSIBLE | 9999999142 | Testcustomertywd_appleNewRQ Customer|

        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN PORTAL"
        And I wait for "4" mins
        And I navigate to admin portal
        And I wait for "4" mins
        And I log in to admin portal
        And I wait for "4" mins
        And I Select "Scheduled Trip" from admin sidebar
        And I wait for "4" mins
        And I open the trip for "Testcustomertywd_appleNewRQ Customer" the customer for delivery details
        And I wait for "4" mins
       Then I check if delivery status is "No Driver(s) Found"

        When I switch to "ORIGINAL" instance
        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE BUNGIIS" from driver App menu
        Then I Select Partner portal Trip from available trip

  @ready
  Scenario: Verify rejection reason pop-up for on-demand trips
    When I Switch to "driver" application on "same" devices
    And I login as "valid nashville" driver on "same" device and make driver status as "Online"

    And I Switch to "customer" application on "same" devices
    Given I am on the "LOG IN" page
    When I logged in Customer application using  "valid nashville" user
    And I request for  bungii for given pickup and drop location
      | Driver | Pickup Location                 | Drop Location                      | geofence  |
      | Solo   | Nashville International Airport | Graylynn Drive Nashville Tennessee | nashville |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time | PickUpImage |
      | 15       |           |              | Now  | Default     |
    Then I should be navigated to "SEARCHING" screen

    Then I view and reject virtual notification for "Driver" for "on demand trip"
