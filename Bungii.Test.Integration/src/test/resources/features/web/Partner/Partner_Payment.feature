@web
  Feature: Partner_Payment

    Background:
      Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    @regression
    Scenario: Verify Customer Payment method with Valid card details for Bungii trip
        When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
          | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
          | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
        And I select Pickup Date and Pickup Time
          |PickUp_Date  |PickUp_Time          |
          |Today+1      |5th quarter          |
        And I click "GET ESTIMATE" button on Partner Portal
        Then I should see "Estimated Cost"
        And I click "Continue" button on Partner Portal
        Then I should "see Delivery Details screen"
        When I enter following details on "Delivery Details" partner screen
          |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
          |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
        And I Select "Customer Card" as Payment Method
        And I enter following Credit Card details on Partner Portal
          |CardNo   |Expiry |Postal_Code      |Cvv      |
          |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
        And I click "Schedule Bungii" button on Partner Portal
        Then I should "see Done screen"
        When I click "Track Deliveries" button on Partner Portal
        Then I should "see the trip in the Delivery List"

    @regression
    Scenario: Verify Customer Payment method with Invalid Card Number for Bungii trip
        When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
          | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
          | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo           |Expiry |Postal_Code      |Cvv      |
        |INVALID CARD     |12/23  |VALID POSTAL CODE|VALID CVV|
      Then I should "see validation message for invalid card number" on partner portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Card Expiry Date for Bungii trip
          When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
            | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
            | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
          And I select Pickup Date and Pickup Time
            |PickUp_Date  |PickUp_Time          |
            |Today+1      |5th quarter          |
          And I click "GET ESTIMATE" button on Partner Portal
          Then I should see "Estimated Cost"
          And I click "Continue" button on Partner Portal
          Then I should "see Delivery Details screen"
          When I enter following details on "Delivery Details" partner screen
            |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
            |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
          And I Select "Customer Card" as Payment Method
          And I enter following Credit Card details on Partner Portal
            |CardNo        |Expiry |Postal_Code      |Cvv      |
            |VISA CARD     |12/19  |VALID POSTAL CODE|VALID CVV|
          Then I should "see validation message for Expired date" on partner portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Cvv for Bungii trip
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo        |Expiry |Postal_Code      |Cvv        |
        |VISA CARD     |12/24  |VALID POSTAL CODE|INVALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validation message for Cvv" on partner portal

    @regression
    Scenario: Verify Customer Payment method with Invalid Postal Code for Bungii trip
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo        |Expiry |Postal_Code        |Cvv      |
        |VISA CARD     |12/24  |INVALID POSTAL CODE|VALID CVV|
      Then I should "see validation message for Postal Code" on partner portal

    @regression
    Scenario: Verify Partner Payment method for Bungii Trip
      When I request for "Solo" Bungii trip in partner portal in "washingtondc" geofence
        | Driver | Pickup_Address                                 | Delivery_Address                                        |Load_Unload_Time|
        | Solo   | 1735 Noriega St, San Francisco, CA, US, 94122  | 1600 Holloway Avenue, San Francisco, California 94132   |30 minutes      |
      And I select Pickup Date and Pickup Time
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |5th quarter          |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" partner screen
        |Items_To_Deliver|Customer_Name|Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select "Partner Pay" as Payment Method
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
