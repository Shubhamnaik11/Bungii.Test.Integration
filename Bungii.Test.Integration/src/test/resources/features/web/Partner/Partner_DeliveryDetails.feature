@web
  Feature: Partner_DeliveryDetails

    Background:
      Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @ready
    Scenario: Verify all the fields on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, District of Columbia, United States, 20005  | 234 13th Street Northeast, Washington, District of Columbia, United States, 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      Then I confirm details show in summary
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      And I should logout from Partner Portal

      @ready
    Scenario: Verify that "Items To Deliver" is mandatory field on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |                |TestPP Customer |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Items To Deliver field" on Delivery Details screen
      And I should logout from Partner Portal

    @ready
    Scenario: Verify that "Customer Name" is mandatory field on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |             |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Customer Name field" on Delivery Details screen
      And I should logout from Partner Portal

    @ready
    Scenario: Verify that "Customer Mobile" is mandatory field on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name     |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer   |               |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Customer Mobile field" on Delivery Details screen
      And I should logout from Partner Portal

    @ready
    Scenario: Verify that "Pickup Contact Name" is mandatory field on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name    |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer  |9998881111     |                   |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Pickup Contact Name field" on Delivery Details screen
      And I should logout from Partner Portal

    @ready
    Scenario: Verify that "Pickup Contact Phone" is mandatory field on Delivery Details Screen
      When I request "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | Solo   | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name    |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |TestPP Customer  |9998881111     |Test Pickup        |                    |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Pickup Contact Phone field" on Delivery Details screen
      And I should logout from Partner Portal