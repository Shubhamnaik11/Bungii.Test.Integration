@web
Feature: Admin_Schedule_Delivery_Edit

  Background:
    Given I am logged in as Admin

    @ready
    Scenario: Verify editing drop off address for the Solo scheduled delivery.
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9999999200     | Testcustomertywd_appleNewM Customer  |
      And I view the all Scheduled Deliveries list on the admin portal
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Searching Drivers |
      Then I check the price for delivery
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I edit the drop off address
      Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
      #4400 Massachusetts Avenue Northwest
      And I change the customer note to "PickupNote edited successfully."
      And I click on "Verify" button on Edit Scheduled bungii popup
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      And I wait for "2" mins
      When I view the delivery details in admin portal
      Then I confirm the change drop off address on delivery details page
      And I confirm Pickup note is updated
      And I confirm that delivery price is change

  @ready
  Scenario: Verify editing drop off address for the duo scheduled trip.
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999202     | Testcustomertywd_appleNewO Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers |
    Then I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
      #Then Tick mark should be displayed beside driver and scheduled date
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I confirm the change drop off address on delivery details page
    And I confirm that delivery price is change

  @ready
  Scenario: Verify editing drop off address outside of scope for the Solo scheduled trip.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999203     | Testcustomertywd_appleNewP Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed

  @ready
  Scenario: Verify editing drop off address outside of scope for the Duo scheduled trip.
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999201 | Testcustomertywd_appleNewN Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Searching Drivers |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed

  @ready
  Scenario: Verify editing drop off address for the Solo scheduled delivery when driver is assigned.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999204     | Testcustomertywd_appleNewQ Customer|
    And As a driver "Testdrivertywd_appledc_a_webaa Testdriveraa" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Scheduled |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then I confirm the change drop off address on delivery details page
    And I confirm that delivery price is change

      @ready
     Scenario: Verify editing drop off address for the Partner Portal Solo Scheduled delivery.
       When I request Partner Portal "SOLO" Trip for "MRFM" partner
         |Geofence| Bungii Time   | Customer Phone | Customer Name |
         |Kansas| NEXT_POSSIBLE | 9999999205 | Testcustomertywd_appleNewR Customer|
       And I view the all Scheduled Deliveries list on the admin portal
       Then I should be able to see the respective bungii with the below status
         |  Status |
         | Searching Drivers |
       And I check the price for delivery
       When I click on "Edit" link beside scheduled bungii
       And I click on "Edit Trip Details" radiobutton
       And I edit the drop off address
       Then I change the drop off address to "400 Speedway Boulevard"
       And I click on "Verify" button on Edit Scheduled bungii popup
       When I click on "Save" button on Edit Scheduled bungii popup
       Then "Bungii Saved!" message should be displayed
       And I wait for "2" mins
       When I view the delivery details in admin portal
       Then I confirm the change drop off address on delivery details page
       And I confirm that delivery price is change