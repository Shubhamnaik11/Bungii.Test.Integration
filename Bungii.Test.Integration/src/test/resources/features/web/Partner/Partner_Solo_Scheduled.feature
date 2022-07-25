@web
  Feature: Partner Solo Scheduled Trips

    Background:
#      Given I navigate to "Partner" portal configured for "normal" URL
#      And I enter "valid" password on Partner Portal
#      And I click "SIGN IN" button on Partner Portal
#      Then I should "be logged in"

    @test
    @regression
    @sanity
      #stable
      Scenario: Verify that Partner can schedule Solo bungii Trip
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Next Possible Pickup Date and Pickup Time
        |Trip_Time            |
        |NEXT_POSSIBLE        |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      #And I confirm the trip details from Get Estimate
      When I enter all details on "Delivery Details" for "normal" on partner screen
        |Items_To_Deliver|Special_Instruction|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|Drop_Off_Contact_Name|Drop_Contact_Phone|Receipt_Number|
        |Furniture       |Handle with care   |Testpartner X |9998881111     |Test Pickup        |9999999359          |Test Dropcontact     |9998881112        |RN1           |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD6|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I should logout from Partner Portal


    @regression
    Scenario: Verify the five future days shown in Pickup Date dropdown
      And I click on Pickup date
      Then I should see five future days including today
      And I should logout from Partner Portal

    @regression
      #stable
    Scenario: Verify changing the pickup date for scheduled Solo bungii Trip
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                                                                     | Delivery_Address                                                    |Load_Unload_Time|
        | 601 13th Street Northwest, Washington, United States, District of Columbia, 20005  | 234 13th Street Northeast, Washington, District of Columbia 20002   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |09:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" for "normal" on partner screen
        |Items_To_Deliver|Customer_Name     |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Testpartner B   |9998881111     |Test Pickup        |9999999359          |
      And I click "Back to Estimate" button on Partner Portal
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+2      |10:30 PM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I should logout from Partner Portal

    @regression
    @sanity
      #stable
    Scenario: Verify Cancellation of Solo Scheduled Delivery
      When I request "Solo" Bungii trip in partner portal configured for "normal" in "washingtondc" geofence
        | Pickup_Address                          | Delivery_Address                        |Load_Unload_Time|
        | 601 13th Street Northwest, Washington   | 234 13th Street Northeast, Washington   |30 minutes      |
      And I select Pickup Date and Pickup Time on partner portal
        |PickUp_Date  |PickUp_Time          |
        |Today+1      |11:30 AM             |
      And I click "GET ESTIMATE" button on Partner Portal
      Then I should see "Estimated Cost"
      And I click "Continue" button on Partner Portal
      Then I should "see Delivery Details screen"
      When I enter following details on "Delivery Details" for "normal" on partner screen
        |Items_To_Deliver|Customer_Name   |Customer_Mobile|Pickup_Contact_Name|Pickup_Contact_Phone|
        |Furniture       |Testpartner C |9998881111     |Test Pickup        |9999999359          |
      And I Select "Customer Card" as Payment Method
      And I enter following Credit Card details on Partner Portal
        |CardNo   |Expiry |Postal_Code      |Cvv      |
        |VISA CARD5|12/23  |VALID POSTAL CODE|VALID CVV|
      And I click "Schedule Bungii" button on Partner Portal
      Then I should "see Done screen"
      When I click "Track Deliveries" button on Partner Portal
      Then I should "see the trip in the Delivery List"
      And I select the Scheduled Bungii from Delivery List
      Then I should "see the trip details"
      And I click "Cancel Delivery link" button on Partner Portal
      Then I should "see the cancel delivery warning message"
      And I click "Cancel Delivery" button on Partner Portal
      Then I should "see delivery has been cancelled message"
      And I click "OK" button on Partner Portal
      Then I should "see Canceled trip message"
      Then I close the Trip Delivery Details page
      And I should logout from Partner Portal

   #CORE:3372-To verify delivery status is updated when PartnerPortal delivery is moved from one status to other on CHROME browser
    @ready
    Scenario:To verify delivery status is updated when PartnerPortal delivery is moved from one status to other on CHROME browser
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
#      And I get the time in CST
      When I navigate to "Partner" portal configured for "normal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Scheduled" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        |Enroute|
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "En Route To Pickup" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Arrived |
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Driver Arrived At Pickup" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Loading Item |
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Loading Items" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Driving To Dropoff |
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Driving To Drop Off" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Unloading Item |
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Unloading Items" on partner portal delivery details page
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Bungii Completed |
      And I refresh the page
      And I select "Completed" option from the filter
      And I click on "Apply" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Done" on partner portal delivery details page


    #CORE-3372:To verify delivery status is updated when Partner Portal delivery is canceled by partner
    @ready
    Scenario: To verify delivery status is updated when Partner Portal delivery is canceled by partner
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" Delivery
        | driver1 state |
        | Accepted      |
      Given I navigate to "Partner" portal configured for "normal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      And I click "Cancel Delivery link" button on Partner Portal
      And I click "Cancel Delivery" button on Partner Portal
      Then I click "OK" button on Partner Portal
      And I refresh the page
      And I select "Canceled" option from the filter
      And I click on "Apply" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Canceled" on partner portal delivery details page

      #CORE-3372:To verify delivery status is updated when PartnerPortal delivery is moved from one status to other for change in driver
    @testAllan
    Scenario:To verify delivery status is updated when PartnerPortal delivery is moved from one status to other for change in driver
      When I request Partner Portal "SOLO" Trip for "MRFM" partner
        |Geofence| Bungii Time   | Customer Phone | Customer Name |
        |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state|
        | Accepted |
        | Enroute |
      And its blah
      When I click the "Track Deliveries" button on Partner Portal
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "En Route To Pickup" on partner portal delivery details page
      And I save the delivery details
      When I navigate to "Admin" portal configured for "QA" URL
      And I wait for 2 minutes
      And I view the Live Deliveries list on the admin portal
      Then I should be able to see the respective bungii with the below status
        |  Status       |
        | Trip Started|
      And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state|
        | Driver Canceled |
      And its blah
      When I navigate back to "Partner" portal and click on "Track Deliveries" button
      And I refresh the page
      And I select "Canceled" option from the filter
      And I click on "Apply" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Canceled" on partner portal delivery details page
      When I navigate to "Admin" portal configured for "QA" URL
      And I wait for 2 minutes
      And I view the Deliveries list on the admin portal
      And  I search the delivery using "Pickup Reference"
      Then Revive button should be displayed beside the trip
      And I wait for 1 minutes
      When I click on "Revive" button
      Then I should see "Are you sure you want to revive the trip?" message on popup with PickupId, Pickup Origin and Partner Name
      When I click on "Confirm" button on Revival Popup
      And I wait for 2 minutes
      And I view the all Scheduled Deliveries list on the admin portal
      And  I search the delivery using "Pickup Reference"
      When I click on the "Edit" button from the dropdown
      And I click on "Edit Trip Details" radiobutton
      And I click on "Add Driver" and add "Testdrivertywd_appleks_a_drvaa Kansas_aa" driver
      And I click on "Verify" button on Edit Scheduled bungii popup
      And I click on "Save" button on Edit Scheduled bungii popup
      Then "Bungii Saved!" message should be displayed
      When I wait for 2 minutes
      And I get the new pickup reference generated
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state|
        |Enroute      |
      And its blah
      When I navigate back to "Partner" portal and click on "Track Deliveries" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "En Route To Pickup" on partner portal delivery details page
      And The driver name should be changed
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Arrived |
      And its blah
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Driver Arrived At Pickup" on partner portal delivery details page
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Loading Item |
      And its blah
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Loading Items" on partner portal delivery details page
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Driving To Dropoff |
      And its blah
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Driving To Drop Off" on partner portal delivery details page
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Unloading Item |
      And its blah
      And I refresh the page
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Unloading Items" on partner portal delivery details page
      And As a driver "Testdrivertywd_appleks_a_drvaa Kansas_aa" perform below action with respective "Solo Scheduled" partner portal trip
        | driver1 state |
        | Bungii Completed |
      And its blah
      And I refresh the page
      And I select "Completed" option from the filter
      And I click on "Apply" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Done" on partner portal delivery details page


#CORE-3372:To verify delivery status is updated when PartnerPortal delivery is marked as Delivery complete on Schedule deliveries
    @ready
  Scenario: To verify delivery status is updated when PartnerPortal delivery is marked as Delivery complete on Schedule deliveries
  When I request Partner Portal "SOLO" Trip for "MRFM" partner
   |Geofence| Bungii Time   | Customer Phone | Customer Name |
   |Kansas| NEXT_POSSIBLE | 9999999208 | Testcustomertywd_appleNewU Customer|
    And As a driver "Testdrivertywd_appledc_a_webee Testdriveree" perform below action with respective "Solo Scheduled" partner portal trip
    | driver1 state|
    | Accepted |
   When I navigate to "Admin" portal configured for "QA" URL
   And I wait for 2 minutes
   And I view the all Scheduled Deliveries list on the admin portal
     And  I search the delivery using "Pickup Reference"
      Then I should be able to see the respective bungii with the below status
        |  Status       |
        | Scheduled|
      When I click on the "Edit" button from the dropdown
      And I click on "Cancel entire Bungii and notify driver(s)" radiobutton
      And I enter cancellation fee and Comments
      And I select "Outside of delivery scope" from the "Cancellation Reason" dropdown
      And I click on "Submit" button
      Then The "Pick up has been successfully canceled." message should be displayed
      Given I navigate to "Partner" portal configured for "normal" URL
      And I enter "valid" password on Partner Portal
      And I click "SIGN IN" button on Partner Portal
      Then I should "be logged in"
      When I click "Track Deliveries" button on Partner Portal
      And I select "Check / uncheck all" option from the filter
      And I click on "Apply" button
      And I select "Check / uncheck all" option from the filter
      And I click on "Apply" button
      And I select "Canceled" option from the filter
      And I click on "Apply" button
      And I click on the delivery based on customer name
      Then I should see the delivery status highlighted and to be set as "Canceled" on partner portal delivery details page