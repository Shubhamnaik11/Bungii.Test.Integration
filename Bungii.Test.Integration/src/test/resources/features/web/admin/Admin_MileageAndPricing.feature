@web
Feature: Mileage and Pricing after Admin Edits

  Background:
    Given I am logged in as Admin

  @ready
#    @testsweta
  Scenario: Verify Mileage and Pricing on admin edit drop off address in Enroute/Arrived/loading status- Solo geofence Based
    When I request Partner Portal "SOLO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas  | NEXT_POSSIBLE | 9999999228     | Testcustomertywd_appleNewMO Customer|
    And As a driver "Testdrivertywd_appledc_a_ptner Driverone" perform below action with respective "Solo Scheduled" partner portal trip
      | driver1 state |
      | Accepted      |
      | Trip Started  |
    When I navigate to "Admin" portal configured for "QA" URL
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Enroute       |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "400 Speedway Boulevard"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    And I check if miles are updated