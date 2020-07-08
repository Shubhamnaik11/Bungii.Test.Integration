@web
  Feature: Partner_Delivery Details

    Background:
      Given I navigate to "Bungii Partner Portal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"

    Scenario: Verify all the fields on Delivery Details Screen
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
        |Furniture       |Test Gopal   |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"

      @regression
      Scenario: Verify that "Items To Deliver" is mandatory field on Delivery Details Screen
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
          |                |Test Gopal   |9998881111     |Test Pickup        |9999999359          |
        And I Select "Customer Card" as Payment Method
        And I enter following Credit Card details on Partner Portal
          |CardNo   |Expiry |Postal_Code      |Cvv      |
          |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
        And I click "Schedule Bungii" button on Partner Portal
        Then I should "see validations message for blank Items To Deliver field" on Delivery Details screen

        @regression
      Scenario: Verify that "Customer Name" is mandatory field on Delivery Details Screen
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
          |Furniture       |             |9998881111     |Test Pickup        |9999999359          |
        And I Select "Customer Card" as Payment Method
        And I enter following Credit Card details on Partner Portal
          |CardNo   |Expiry |Postal_Code      |Cvv      |
          |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
        And I click "Schedule Bungii" button on Partner Portal
        Then I should "see validations message for blank Customer Name field" on Delivery Details screen

          @regression
      Scenario: Verify that "Customer Mobile" is mandatory field on Delivery Details Screen
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
              |Items_To_Deliver|Customer_Name         |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
              |Furniture       |Test Gopal            |               |Test Pickup        |9999999359          |
            And I Select "Customer Card" as Payment Method
            And I enter following Credit Card details on Partner Portal
              |CardNo   |Expiry |Postal_Code      |Cvv      |
              |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
            And I click "Schedule Bungii" button on Partner Portal
            Then I should "see validations message for blank Customer Mobile field" on Delivery Details screen

            @regression
    Scenario: Verify that "Pickup Contact Name" is mandatory field on Delivery Details Screen
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
        |Furniture       |Test Gopal   |9998881111     |                   |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Pickup Contact Name field" on Delivery Details screen

     @gs
    Scenario: Verify that "Pickup Contact Phone" is mandatory field on Delivery Details Screen
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
        |Furniture       |Test Gopal   |9998881111     |Test Pickup        |                    |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see validations message for blank Pickup Contact Phone field" on Delivery Details screen