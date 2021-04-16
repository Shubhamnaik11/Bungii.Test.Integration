@web
Feature: Admin_Schedule_Trip_Edit

  Background:
    Given I am logged in as Admin

    @ready
    Scenario: Verify editing drop off address for the Solo scheduled trip.
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
      And I view the all Scheduled Deliveries list on the admin portal
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Searching Drivers |
      When I click on "Edit" link beside scheduled bungii
      And I click on "Edit Trip Details" radiobutton
      And I edit the drop off address
      Then I change the drop off address to "4400 Massachusetts Ave NW, Washington, DC 20016, United States"
      And I click on "Verify" button on Edit Scheduled bungii popup
      #Then Tick mark should be displayed beside driver and scheduled date
      When I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved" message should be displayed
      When I view the trip details in admin portal
      Then I confirm the change drop off address on delivery details page

      @gs
     Scenario: Verify editing drop off address for the Partner Poratl Solo Scheduled trip.
       When I request Partner Portal "SOLO" Trip for "MRFM" partner
         |Geofence| Bungii Time   | Customer Phone | Customer Name |
         |Kansas| NEXT_POSSIBLE | 9284000002 | Testcustomertywd_appleweb CustB|
       And I view the all Scheduled Deliveries list on the admin portal
       Then I should be able to see the respective bungii with the below status
         |  Status |
         | Searching Drivers |
       When I click on "Edit" link beside scheduled bungii
       And I click on "Edit Trip Details" radiobutton
       And I edit the drop off address
       Then I change the drop off address to "4400 Massachusetts Ave NW, Washington, DC 20016, United States"
       And I click on "Verify" button on Edit Scheduled bungii popup
      #Then Tick mark should be displayed beside driver and scheduled date
       When I click on "Save" button on Edit Scheduled bungii popup
       Then "Bungii Saved" message should be displayed
       When I view the trip details in admin portal
       Then I confirm the change drop off address on delivery details page