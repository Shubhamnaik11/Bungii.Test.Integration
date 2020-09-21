@web
Feature: Kiosk Mode

  Background:
    Given I navigate to "Partner" portal configured for "kiosk mode" URL

  @ready
  Scenario: Verify login to Partner portal configured with Kiosk mode with Valid Credentials for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "valid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see Admin access" on Kiosk Partner Portal
    And I should logout from Kiosk Partner Portal

    @ready
  Scenario: Verify login to Partner portal configured with Kiosk mode with InValid Password for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "invalid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see validations message for incorrect password field" on Kiosk Partner Portal

    @ready
  Scenario: Verify login to Partner portal configured with Kiosk mode with Blank Password for Admin access
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see validations message for blank password field" on Kiosk Partner Portal

     @ready
  Scenario: Verify login to Partner portal configured with Kiosk mode can scheduled solo trip
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "valid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see Admin access" on Kiosk Partner Portal
    And I set the Admin Access mode "ON" on partner portal
    When I request "Solo" Bungii trip in partner portal configured for "kiosk mode" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |                |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    #Then I should see "Estimated Cost"
    #And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I confirm the trip details from Get Estimate
    When I enter all details on "Delivery Details" for "kiosk mode" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |TestPP Customer |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I enter the value "Gopal" in Scheduled by field
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I should logout from Partner Portal
    #And I should logout from Kiosk Partner Portal

    @ready
  Scenario: Verify login to Partner portal configured with Kiosk mode can scheduled duo trip
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    And I click on "Setting" Link on Kiosk Partner Portal
    And I enter "valid" password for Admin access
    And I click on "Continue" button on Kiosk Partner Portal
    Then I should "see Admin access" on Kiosk Partner Portal
    And I set the Admin Access mode "ON" on partner portal
    When I request "Duo" Bungii trip in partner portal configured for "kiosk mode" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |                |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    #Then I should see "Estimated Cost"
    #And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I confirm the trip details from Get Estimate
    When I enter all details on "Delivery Details" for "kiosk mode" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |TestPP Customer |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I enter the value "Test Scheduled" in Scheduled by field
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    When I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I should logout from Partner Portal
    #And I should logout from Kiosk Partner Portal

