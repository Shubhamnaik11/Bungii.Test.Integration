@web
Feature: Admin_Live_Delivery_Edit

  Background:
    Given I am logged in as Admin

  @ready
  Scenario: Verify editing drop off address for the Solo scheduled live delivery.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999207     | Testcustomertywd_appleNewT Customer|
    And As a driver "Testdrivertywd_appledc_a_webdd Testdriverdd" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I open the live delivery details in admin portal
    Then I confirm the change drop off address on delivery details page
    And I confirm that delivery price is change

  @ready
    Scenario: Verify editing pickup address for the Solo live delivery.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999209     | Testcustomertywd_appleNewV Customer|
    And As a driver "Testdrivertywd_appledc_a_webff Testdriverff" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute       |
      | Arrived       |
      | Loading Item   |
    And I view the Live Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status       |
      | Loading Items |
    When I click on "Edit" link beside live delivery
    And I click on "Edit Trip Details" radiobutton
    And I edit the pickup address
    Then I change the pickup address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I open the live delivery details in admin portal
    Then I confirm the change pickup address on delivery details page
    And I confirm that delivery price is change

    @ready
     Scenario: Verify editing drop off address for the Partner Portal Solo live delivery.
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
        | Enroute       |
        | Arrived       |
        | Loading Item   |
      And I view the Live Deliveries list on the admin portal
      Then I should be able to see the respective bungii with the below status
        |  Status       |
        | Loading Items |
      When I click on "Edit" link beside live delivery
      And I click on "Edit Trip Details" radiobutton
      And I edit the drop off address
      Then I change the drop off address to "400 Speedway Boulevard"
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      When I open the live delivery details in admin portal
      Then I confirm the change drop off address on delivery details page
      And I confirm that delivery price is change
