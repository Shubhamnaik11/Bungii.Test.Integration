@web
  Feature: Partner_Payment

    Background:
    Given I am logged in as Partner User on Partner Portal

      Scenario: Verify Customer Payment method with Valid card details for Bungii trip
        When When I request for  bungii for given pickup and drop location
          | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
          | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
        And I click "Get Estimate" button on "Get Estimate" screen
        Then I should see "Estimated Cost" on "Get Estimate" screen
        When I click on "Continue" button on "Get Estimate" screen
        Then I should be navigated to "Delivery Details" screen
        When I enter following details on "Delivery Details" screen
          |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
          |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
        And I Select Customer Card as Payment Method
        And I enter Card No:<CardNo> and Expiry :<Expiry> on Card Details
        And I enter postal code :<Postal Code> and Cvv: <Cvv> on Card Details
        And I click on "Schedule Bungii" button
        Then I should see bungii trip success message on "Done" screen
        When I click on Track Deliveries button
        Then I should see the Bungii trip in the Delivery List

          | CardNo        | Expiry | Postal Code       | Cvv       |
          | VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |

    Scenario: Verify Customer Payment method with Invalid Card Number for Bungii trip
      When When I request for  bungii for given pickup and drop location
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "Estimated Cost" on "Get Estimate" screen
      And I click on "Continue" button on "Get Estimate" screen
      Then I should be navigated to "Delivery Details" screen
      When I enter following details on "Delivery Details" screen
        |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select Customer Card as Payment Method
      And I enter Card No:<CardNo> on Card Details
      Then I should see "This card number is not valid." message

        | CardNo                | Expiry | Postal Code       | Cvv       |
        | InValid VISA CARD     | 12/22  | VALID POSTAL CODE | VALID CVV |

    Scenario: Verify Customer Payment method with Invalid Card Expiry Date for Bungii trip
      When When I request for  bungii for given pickup and drop location
        | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
        | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
      And I click "Get Estimate" button on "Get Estimate" screen
      Then I should see "Estimated Cost" on "Get Estimate" screen
      And I click on "Continue" button on "Get Estimate" screen
      Then I should be navigated to "Delivery Details" screen
      When I enter following details on "Delivery Details" screen
        |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
        |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
      And I Select Customer Card as Payment Method
      And I enter Card No:<CardNo> on Card Details
      And I enter Expiry Date:<Expiry> on Card Details
      Then I should see "This expiration date is not valid." message

        | CardNo              | Expiry | Postal Code       | Cvv       |
        | Valid VISA CARD     | 12/19  | VALID POSTAL CODE | VALID CVV |


    Scenario: Verify Partner Payment method for Bungii Trip
          When When I request for  bungii for given pickup and drop location
            | Driver | Pickup Address  | Delivery Address             |Load Unload Time|
            | Solo   | Margoa Railway  | Patto Centre,Panjim          |15 minutes      |
          And I click "Get Estimate" button on "Get Estimate" screen
          Then I should see "Estimated Cost" on "Get Estimate" screen
          And I click on "Continue" button on "Get Estimate" screen
          Then I should be navigated to "Delivery Details" screen
          When I enter following details on "Delivery Details" screen
            |Items To Deliver|Customer Name|Customer Mobile|Pickup Contact Name|Pickup Contact Phone|
            |Furniture       |Test Gopal   |9899999359     |Test Pickup        |9999999359          |
          And I Select Partner Payment Method
          And I click on "Schedule Bungii" button
          Then I should see bungii trip success message on "Done" screen
          When I click on Track Deliveries button
          Then I should see the Bungii trip in the Delivery List
