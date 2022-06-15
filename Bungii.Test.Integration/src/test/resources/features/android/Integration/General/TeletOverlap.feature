@android

Feature: Overlapping TELET

  @ready
   @testsweta
  Scenario: Verify that driver is allowed to start only the first scheduled solo customer trip when there is another overlapping (TELET) Solo customer trip
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE_FIRST_SLOT | 8877661005     | Cci12345          | Testcustomertywd_appleMarkF LutherF |
    And I wait for "1" mins
    And As a driver "Testdrivertywd_appleks_a_drvu Kansas_u" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 9999999129     | Cci12345          | Testcustomertywd_appleNewRD Customer |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleNewRD Customer" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvu Kansas_u" for the trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed

    When I switch to "ORIGINAL" instance
    And I Switch to "driver" application on "same" devices
    And I am logged in as "Testdrivertywd_appleks_a_drvu Kansas_u" driver
    And I accept "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I open second Trip from driver scheduled trip
    And Bungii Driver "Start Schedule Bungii" request
