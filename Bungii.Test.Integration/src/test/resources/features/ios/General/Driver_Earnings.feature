@ios

Feature: Driver Earnings

  @ready
  Scenario: Verify earning page on driver app without tip after successful payment
    Given I request "Solo Scheduled" Bungii as a customer in "washingtondc" geofence
      | Bungii Time   | Customer Phone | Customer Password | Customer Name                    |
      | NEXT_POSSIBLE | 9999999129    | Cci12345          | Testcustomertywd_appleNewRD Customer |
    And I wait for "2" mins

    And As a driver "Testdrivertywd_appledc_a_drvB WashingtonB" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
      | Enroute            |
      | Arrived            |
      | Loading Item       |
      | Driving To Dropoff |
      | Unloading Item     |
      | Bungii Completed     |

    And I wait for "2" mins
    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appledc_a_drvB WashingtonB" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    And I get "Trip Earnings" from driver earnings page on admin portal for "solo driver"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appledc_a_drvB WashingtonB" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page

        #    Core-2469  Verify UI of itemized earnings page on driver app
    And I verify all the elements on itemized earnings page
    Then I compare with earnings from admin portal for "solo driver"


  @ready
  Scenario: Verify earning page on driver app without tip after successful payment for duo partner portal
    When I request Partner Portal "DUO" Trip for "MRFM" partner
      |Geofence| Bungii Time   | Customer Phone | Customer Name |
      |Kansas| NEXT_POSSIBLE | 9999999131 | Testcustomertywd_appleNewRF Customer|
    And I wait for "2" mins

    And As a driver "Testdrivertywd_appleks_a_drvy Kansas_y" and "Testdrivertywd_appleks_a_drvz Kansas_z" perform below action with respective "DUO SCHEDULED" trip
      | driver1 state | driver2 state |
      | Bungii Completed   | Bungii Completed   |
    And I wait for "2" mins

    When I open new "Chrome" browser for "ADMIN PORTAL"
    And I navigate to admin portal
    And I log in to admin portal
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvy Kansas_y" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo first driver"
    And I Select "DRIVERS" from admin sidebar
    And I search for "Testdrivertywd_appleks_a_drvz Kansas_z" driver on driver details
    And I click on "Driver Earnings" icon on driver page
    And I click on "View" icon on driver page
    Then I get "Trip Earnings" from driver earnings page on admin portal for "duo second driver"

    When I switch to "ORIGINAL" instance
    When I Switch to "driver" application on "same" devices
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvy Kansas_y" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo first driver"
    And I click on "BACK" button
    When I Select "ACCOUNT > LOGOUT" from driver App menu
    And I am on the "LOG IN" page on driverApp
    And I am logged in as "Testdrivertywd_appleks_a_drvz Kansas_z" driver
    And I Select "EARNINGS" from driver App menu
    And I get "Itemized Earnings" from earnings page
    Then I compare with earnings from admin portal for "duo second driver"