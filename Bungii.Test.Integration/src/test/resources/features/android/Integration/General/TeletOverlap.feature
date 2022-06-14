@android

Feature: Overlapping TELET

  @ready
#    @testsweta
  Scenario: Verify that driver is allowed to start only the first scheduled solo customer trip when there is another overlapping (TELET) Solo customer trip
    Given I request "Solo Scheduled" Bungii as a customer in "kansas" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 9999999129     | Cci12345          | Testcustomertywd_appleNewRD Customer |
    And I wait for "1" mins
    And I get TELET time of of the current trip
    And As a driver "Testdrivertywd_appleks_a_drvu Kansas_u" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |

    Given I login as customer "8805368840" and is on Home Page
    And I accept "TERMS & CONDITIONS" and "ALLOW NOTIFICATIONS" and "ALLOW LOCATION" permission if exist
    And I close "Tutorial" if exist
    And I enter "kansas pickup and dropoff locations less than 150 miles" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then "Estimate" page should be opened
    When I confirm trip with following details
      | Day | Trip Type | Time                |
      | 0   | SOLO      | <TIME WITHIN TELET> |
    And I add loading/unloading time of "30 mins"
    And I get Bungii details on Bungii Estimate
    And I add "1" photos to the Bungii
    And I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I click "Done" button on "Success" screen

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "Scheduled Trip" from admin sidebar
    And I open the trip for "Testcustomertywd_appleRicha Test" the customer
    And I Select "Edit Trip Details" option
    And I assign driver "Testdrivertywd_appleks_a_drvu Kansas_u" for the trip
    And I click on "VERIFY" button
    And the "Your changes are good to be saved." message is displayed
    Then I click on "SAVE CHANGES" button
    And the "Bungii Saved!" message is displayed