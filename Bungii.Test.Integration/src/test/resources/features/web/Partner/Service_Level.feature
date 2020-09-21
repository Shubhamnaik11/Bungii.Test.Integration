@web
Feature: Service Level

  Background:
    Given I navigate to "Partner" portal configured for "service level" URL

  @ready
  Scenario: Verify that service level options display on configured Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |
    Then I should "see Service Level"
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |TestPP Customer |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    And I should logout from Partner Portal

    @ready
  Scenario: Verify that service level options display on configured Partner portal site only after selecting the pickup  and delivery address.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see No service selected"
      When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |
    Then I should "see Service Level"

    @ready
  Scenario Outline: Verify changing the service level options display on configured Partner portal <DeliveryAddress>
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "<Type>" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address            |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | <DeliveryAddress>           |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "<ServiceName>"
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I check correct price is shown for selected service

      Examples:
      |Type|DeliveryAddress                                                                  |ServiceName   |
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |
      |Solo|234 13th Street Northeast, Washington, District of Columbia 20002                |White Glove   |
      |Duo |234 13th Street Northeast, Washington, District of Columbia 20002                |White Glove   |
      |Solo|11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |White Glove   |
      |Duo |11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |White Glove   |
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|
      |Solo|234 13th Street Northeast, Washington, District of Columbia 20002                |Room of Choice|
      |Duo |234 13th Street Northeast, Washington, District of Columbia 20002                |Room of Choice|
      |Solo|11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |Room of Choice|
      |Duo |11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |Room of Choice|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |
      |Solo|234 13th Street Northeast, Washington, District of Columbia 20002                |Threshold     |
      |Duo |234 13th Street Northeast, Washington, District of Columbia 20002                |Threshold     |
      |Solo|11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |Threshold     |
      |Duo |11151 Veirs Mill Road, Wheaton-Glenmont, United States, Maryland, 20902          |Threshold     |

  @ready
  Scenario: Verify that service level partner portal trip is shown in Admin portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                   |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Biglots" Alias
    And I change the service level to "Threshold"
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
      |Furniture       |Handle with care   |TestPP Customer |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
    And I Select "Customer Card" as Payment Method
    And I enter following Credit Card details on Partner Portal
      |CardNo   |Expiry |Postal_Code      |Cvv      |
      |VISA CARD|12/23  |VALID POSTAL CODE|VALID CVV|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I select the Scheduled Bungii from Delivery List
    Then I should "see the service name"
    Then I close the Trip Delivery Details page
    When I navigate to "Admin" portal configured for "QA" URL
    #When I navigate to "Bungii Admin Portal in new tab" URL
    And I view the Deliveries list on the admin portal
    When I view the trip details
    Then the Bungii details is displayed successfully
    And I should logout from Partner Portal



