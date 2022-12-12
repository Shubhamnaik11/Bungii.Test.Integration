@web
Feature: Floor and Decore Service Level

  Background:
    Given I navigate to "Partner" portal configured for "FloorDecor service level" URL

  @regression
  Scenario: Verify that 1pallet and 2pallets are shown instead of solo and duo for floor and decor partner portal
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    #CORE-3849 changes incorporated
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
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
      | Assigning Driver(s)|
#   Core-2641 Verify alias is displayed for partner portal trips on scheduled delivery page
    And I verify alias is displayed correctly on "scheduled delivery page"
    When I view the delivery details
    And I verify alias is displayed correctly on "delivery details page"
#    Core-3294: Verify Stop search button is displayed for partner portal weight based schedule trips
    And I check if "Stop Searching" button is displayed
    Then I stop searching driver
    And I wait for "2" mins
    When I click on the "Delivery Details" button from the dropdown
    Then I check if the status has been changed to "No Driver(s) Found"
    When I navigate back to Scheduled Deliveries
#   Core-2641 Verify alias is displayed for partner portal trips on all delivery page
    And I click on "Edit" link beside scheduled bungii
    And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
    And I enter cancellation fee and Comments
    And I click on "Submit" button
    Then The "Pick up has been successfully canceled." message should be displayed
    When I click on "Close" button
    And I wait for "2" mins
    When I view All Deliveries list on the admin portal
    And  I search the delivery using "Pickup Reference"
    Then I verify alias is displayed correctly on "all delivery page"
#    Core-3294: Verify Stop search is not displayed for ongoing bungii and All deliveries screen
    When I click on the "Delivery Details" button from the dropdown
    Then I check if "Stop Searching" button is not present

  @ready
    # CORE-4079: Upper left logo navigate portals to "home"
  Scenario: Verify that Partner Portal Logo redirects to homepage
    When I enter "valid" password on Partner Portal
    And I click "SIGN IN" button on Partner Portal
    Then I should "see 1 pallet and 2 pallets"
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
    And I click "Service Level List" button on Partner Portal
    Then I should "see all the Service Level" for "Floor & Decor #240" Alias
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    And I click on Partner Portal Logo in header
    Then I should get Confirmation Alert popup
    And I click on Continue button on popup
    Then I should see header as "Get Quote"

    @ready
    #CORE-4009: Date filter in Live Deliveries page of AP
  Scenario: To verify the Date filter in Live Deliveries page of Admin Portal
    When I view the Live Deliveries list on the admin portal
    Then The "Date Filter" is set to "All" by default
    When I click on "Date Filter" button on the "Live deliveries" page
    Then  I should see All Filter Options in dropdown
    #Today scenario
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |NEXT_POSSIBLE        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |10 boxes           |10X10X10  | 1000 |Handle with care   |Testartner Today    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I wait for 2 minutes
    And As a driver "Testdrivertywd_appleks_a_drvbq Kansas_bq" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state |
      | Accepted      |
      | Enroute      |
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Trip Started |
    #Tomarrow Scenario
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time            |
      |1_DAY_LATER        |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |15 boxes           |15X15X15  | 1500 |Handle with care   |Testartner Tomorrow    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |
    #5Days later scenario
    When I request "Solo" Bungii trip in partner portal configured for "FloorDecor service level" in "washingtondc" geofence
      | Pickup_Address                                                                     | Delivery_Address                                                    |Address_Enter|
      | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |CopyPaste    |
    And I click "Service Level List" button on Partner Portal
    And I change the service level to "First Threshold" in "Partner" portal
    And I select Next Possible Pickup Date and Pickup Time
      |Trip_Time         |
      |5_DAY_LATER       |
    And I click "Continue" button on Partner Portal
    Then I should "see Delivery Details screen"
    When I enter all details on "Delivery Details" for "FloorDecor service level" on partner screen
      |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
      |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner 5Days Later    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
    And I click "Schedule Bungii" button on Partner Portal
    Then I should "see Done screen"
    And I wait for 2 minutes
    When I view the Live Deliveries list on  admin portal
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Today" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    And I click on "Date Filter" button on the "Live deliveries" page
    When I change filter to "Tomorrow" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should see the message "No deliveries found." displayed
    When I change filter to "All" on Live deliveries
    And  I search the delivery using "Pickup Reference" in "Live Deliveries" Page
    Then I should be able to see the respective bungii with the status
      | Status       |
      | Assigning Driver(s) |