@web
  Feature: Partner_Payment

    Background:
      Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
    Scenario: Verify Customer Payment method with Valid card details for Bungii trip
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
        |Furniture       |TestPP Customer   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I should logout from Partner Portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Card Number for Bungii trip
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
        |Furniture       |TestPP Customer   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo           |Expiry |Postal_Code      |Cvv      |
        |INVALID CARD     |12/23  |VALID POSTAL CODE|VALID CVV|
      Then I should "see validation message for invalid card number" on partner portal
      And I should logout from Partner Portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Card Expiry Date for Bungii trip
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
        |Furniture       |TestPP Customer   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo        |Expiry |Postal_Code      |Cvv      |
        |VISA CARD     |12/19  |VALID POSTAL CODE|VALID CVV|
      Then I should "see validation message for Expired date" on partner portal
      And I should logout from Partner Portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Cvv for Bungii trip
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
        |Furniture       |TestPP Customer   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo        |Expiry |Postal_Code      |Cvv        |
        |VISA CARD     |12/24  |VALID POSTAL CODE|INVALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validation message for Cvv" on partner portal
      And I should logout from Partner Portal

    @ready
    Scenario: Verify Customer Payment method with Invalid Postal Code for Bungii trip
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
        |Furniture       |TestPP Customer  |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo        |Expiry |Postal_Code        |Cvv      |
        |VISA CARD     |12/24  |INVALID POSTAL CODE|VALID CVV|
      Then I should "see validation message for Postal Code" on partner portal
      And I should logout from Partner Portal

    @ready
    Scenario: Verify Partner Payment method for Bungii Trip
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
        |Furniture       |TestPP Customer  |9899999359     |Test Pickup        |9999999359          |
      And I Select "Partner Pay" as Payment Method
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I should logout from Partner Portal
  
    @fraud
    Scenario: Verify Customer Payment method with Fraud Card Number for Bungii trip
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
        |Furniture       |TestPP Customer   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo           |Expiry |Postal_Code      |Cvv      |
        |FRAUD CARD     |12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validation message for fraud card number" on partner portal
      And I should logout from Partner Portal