@ios

  Feature: Partner Portal Cases for mobile friendly portals
  #  Core-4584

    @ready
      @testsweta
    Scenario: Verify UI and functionality for fixed pricing partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Safari" browser for "MOBILE DEVICE"

      And I open "fixed pricing" partner portal
      And I verify the ui elements on "get estimate" page for "fixed pricing" partner
      When I enter all details on "get estimate page" for "fixed pricing"
        | Pickup_Address                                                                     | Delivery_Address                                                    |
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 14531 Montevideo Road, Poolesville, United States, Maryland, 20837  |
      And I select "Threshold" service level
      And I click on "Continue" button
#      When I enter all details on "delivery details" for "weight based portal"
#        |Product_Description|Dimensions|Weight|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Delivery_Purpose|Rb_Sb_Number|ScheduledBy|
#        |20 boxes           |20X20X20  | 1570 |Handle with care   |Testartner T    |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |For decoration  |007         |UserFND    |
#      And I click on "Schedule Bungii" button
#      Then I verify the ui elements on "success" page for "weight based" partner


