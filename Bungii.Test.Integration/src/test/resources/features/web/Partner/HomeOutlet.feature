@web
Feature: Home Outlet

  Background:
    Given I navigate to "Partner" portal configured for "Home Outlet" URL
    And I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "be logged in"

 #CORE-3293: Verify Warning shown on admin portal when admin tries to edit time outside partners day of week operating time
  @ready
  Scenario: Verify Warning shown on admin portal when admin tries to edit time outside partners day of week operating time
    When I request "Solo" Bungii trip in partner portal configured for "Home outlet service level" in "kansas" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 8300 West 135th Street, Overland Park, United States, Kansas, 66223  | 4707 Bannister Road, Kansas City, United States, Missouri, 6413  |
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "Home outlet service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Order_Number|SoldBuy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |ON1         |Krishna|
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I wait for 2 minutes
    When I am logged in as Admin
    And I view the all Scheduled Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    When I click on the "Edit" button from the dropdown
    And I click on "Edit Trip Details" radiobutton
    And I set the the time of the delivery outside bungii working hours
    And I click on "Reason" for change time
    And I click on "Customer initiated" in the dropdown
    And I click on "Verify" button on Edit Scheduled bungii popup
    Then The "Warning" "Icon" should be displayed
    And the "Your pickup is scheduled outside partner working hours." message is displayed
    When I click on "Save" button on Edit Scheduled bungii popup
    Then "Bungii Saved!" message should be displayed

   #CORE-3293:Verify Time slot displayed on Partner portal within Bungii and Partner working hrs when partner has different working hrs on each day
  @ready
  Scenario:Verify Time slot displayed on Partner portal within Bungii and Partner working hrs when partner has different working hrs on each day
    When I click on "<string>" button
    Then I check if the day is "Monday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
    Then I check if the day is "Tuesday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
    Then I check if the day is "Wednesday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
    Then I check if the day is "Thursday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
    Then I check if the day is "Friday" first time slot is "09:00 AM" and last time slot is "06:00 PM"
    Then I check if the day is "Saturday" first time slot is "09:00 AM" and last time slot is "05:00 PM"
    Then I check if the day is "Sunday" first time slot is "Not Present" and last time slot is "Not Present"