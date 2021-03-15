@web
Feature: Service Level

  Background:
    Given I navigate to "Partner" portal configured for "BestBuy service level" URL

    @regression
  Scenario: Verify that NA is shown for Best buy service level on configured Partner portal site.
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"
    When I request "Solo" Bungii trip in partner portal configured for "BestBuy service level" in "Kansas" geofence
      | Pickup_Address                                 | Delivery_Address                                             |
      | 1735 Noriega St, San Francisco, CA, US, 94122  | 6800 Zoo Drive, Kansas City, United States, Missouri, 64132  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    Then I should "see Delivery Cost: N/A"
    And I click "GET ESTIMATE" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "BestBuy service level" on partner screen
      |Items_To_Deliver|Special_Instruction|Customer_Name |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Order_Number|SoldBuy     |
      |5067400         |Handle with care   |Testpartner U |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |ON1         |Test_SoldBuy|
    Then I should "see Delivery Cost: N/A on Delivery Details screen"
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I click "Track Deliveries" button on Partner Portal
    Then I should "see the trip in the Delivery List"
    And I click on Filter and select check/unchecked all checkbox
    And I click on Apply button on Filter
    Then I should not able to see Filter screen
    And I select the Scheduled Bungii from Delivery List
    Then I should "see Delivery Cost: N/A on Delivery Details screen"


