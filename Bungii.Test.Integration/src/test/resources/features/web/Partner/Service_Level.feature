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

    @regression
  Scenario: Verify that service level options display on configured Partner portal site only after selecting the pickup  and delivery address.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see No service selected"
      When I request "Solo" Bungii trip in partner portal configured for "service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |
    Then I should "see Service Level"

    @regression
  Scenario Outline: Verify changing the service level options display on configured Partner portal <Type>-<ServiceName> for <Distance> distance
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
      |Type|DeliveryAddress                                                                  |ServiceName   |Distance |
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|White Glove   |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |White Glove   |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |White Glove   |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |White Glove   |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |White Glove   |Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Room of Choice|0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Room of Choice|10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Room of Choice|15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Room of Choice|30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Room of Choice|Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Threshold     |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Threshold     |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Threshold     |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Threshold     |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Threshold     |Above 100|
      |Solo|900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |
      |Duo |900 23rd Street Northwest, Washington, United States, District of Columbia, 20037|Curbside      |0-10     |
      |Solo|11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |
      |Duo |11151 Veirs Mill Road, Silver Spring, United States, Maryland, 20902             |Curbside      |10-15    |
      |Solo|655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |
      |Duo |655 Watkins Mill Road, Gaithersburg, United States, Maryland, 20879              |Curbside      |15-30    |
      |Solo|1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |
      |Duo |1101 Stevenson Lane, Baltimore, United States, Maryland, 21286                   |Curbside      |30-100   |
      |Solo|1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|
      |Duo |1601 Kirkwood Highway, Wilmington, United States, Delaware, 19805                |Curbside      |Above 100|

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
    #And I view the Deliveries list on the admin portal
    And I view the all Scheduled Deliveries list on the admin portal
    Then I should be able to see the respective bungii partner portal trip with the below status
      | Status           |
      | Searching Drivers|
    #Then the Bungii details is displayed successfully
    #And I should logout from Partner Portal



