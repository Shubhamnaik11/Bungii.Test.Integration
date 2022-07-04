@web
Feature: Cort Service Level

  Background:
    Given I navigate to "Partner" portal configured for "Cort service level" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
   @Siddhi
  @ready
  Scenario: Verify partner invoice Payment method selected by default
    When I request "Solo" Bungii trip in partner portal configured for "Cort service level" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I select Pickup Date and Pickup Time on partner portal
      |PickUp_Date  |PickUp_Time          |
      |Today+1      |09:30 AM             |
    And I click "GET ESTIMATE" button on Partner Portal
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "kiosk mode" on partner screen
        |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
        |Furniture       |Handle with care   |Testpartner P |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1             |
    And I enter the value "Test ScheduledBy" in Scheduled by field
    And I check the Bodc Code dropdown options
    And I select the value in Bodc Code
    Then Partner invoice should be selected as default Payment Method
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I should logout from Partner Portal
