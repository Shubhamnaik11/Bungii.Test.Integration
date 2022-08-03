@web
Feature: Admin_Schedule_Delivery_Edit

  Background:
    Given I am logged in as Admin

  @regression
      #failed in sprint 49
    Scenario: Verify editing drop off address for the Solo scheduled delivery
      When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
        | Bungii Time   | Customer Phone | Customer Name |
        | NEXT_POSSIBLE | 9999999200     | Testcustomertywd_appleNewM Customer  |
      And I view the all Scheduled Deliveries list on the admin portal
      And I wait for "2" mins
      Then I should be able to see the respective bungii with the below status
        |  Status |
        | Assigning Driver(s) |
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
      Then the updated drop off address should be displayed on delivery details page
      And I confirm Pickup note is "Updated"
      And Delivery price is recalculated based on updated value of drop off address

  @regression
      #stable
  Scenario: Verify editing drop off address for the duo scheduled trip
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999202     | Testcustomertywd_appleNewO Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    Then I change the drop off address to "4400 Massachusetts Avenue Northwest"
    And I remove the customer note
    And I click on "Verify" button on Edit Scheduled bungii popup
      #Then Tick mark should be displayed beside driver and scheduled date
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    Then the updated drop off address should be displayed on delivery details page
    And I confirm Pickup note is "Deleted"
    And Delivery price is recalculated based on updated value of drop off address
  
  @regression
    #stable
  Scenario: Verify editing drop off address outside of scope for the Solo scheduled trip.
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                      |
      | NEXT_POSSIBLE | 9999999203     | Testcustomertywd_appleNewP Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue, Copiague"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed
  
  @regression
    #stable
  Scenario: Verify editing drop off address outside of scope for the Duo scheduled trip.
    When I request "Duo" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name |
      | NEXT_POSSIBLE | 9999999201 | Testcustomertywd_appleNewN Customer|
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    And I edit the drop off address
    And I change the drop off address to "8500 Scudder Avenue, Copiague"
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then "Oops! It looks like this trip is a little outside our scope." message should be displayed
  
  @regression
    #stable
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
    Then the updated drop off address should be displayed on delivery details page
    And Delivery price is recalculated based on updated value of drop off address

    
  @regression
    #stable
     Scenario: Verify editing drop off address for the Partner Portal Solo Scheduled delivery.
       When I request Partner Portal "SOLO" Trip for "MRFM" partner
         |Geofence| Bungii Time   | Customer Phone | Customer Name |
         |Kansas| NEXT_POSSIBLE | 9999999205 | Testcustomertywd_appleNewR Customer|
       And I view the all Scheduled Deliveries list on the admin portal
       Then I should be able to see the respective bungii with the below status
         |  Status |
         | Assigning Driver(s) |
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
       Then the updated drop off address should be displayed on delivery details page
       And Delivery price is recalculated based on updated value of drop off address


  @ready
  Scenario: Verify that additional notes textbox is not displayed for Partner portal on Schedule deliveries edit page on admin portal
    When I navigate to "Partner" portal configured for "normal" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should see "Estimated Cost"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter following details on "Delivery Details" for "normal" on partner screen
      |Items_To_Deliver|Customer_Name        |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
      |Furniture       |Testpartner A      |9998881111     |Test Pickup        |9999999359          |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD3|12/29  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I am logged in as Admin
    And I wait for "2" mins
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii with the below status
      |  Status |
      |  Assigning Driver(s)  |
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    Then I should see "Additional Instructions" field empty


  @ready
  Scenario: To verify admin is able to add notes for customer trips in admin portal when customer has not added any additional notes
    When I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Name                         | Customer Note|
      | NEXT_POSSIBLE | 9999999200     | Testcustomertywd_appleNewM Customer  |      Blank    |
    And I view the all Scheduled Deliveries list on the admin portal
    And I wait for "2" mins
    Then I should be able to see the respective bungii with the below status
      |  Status |
      | Assigning Driver(s) |
    Then I check the price for delivery
    When I click on "Edit" link beside scheduled bungii
    And I click on "Edit Trip Details" radiobutton
    Then I should see "Additional Notes" field empty
    And I change the customer note to "New Note Added by Admin"
    And I click on "Verify" button on Edit Scheduled bungii popup
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed
    And I wait for "2" mins
    When I view the delivery details in admin portal
    And I confirm Pickup note is "Added"
