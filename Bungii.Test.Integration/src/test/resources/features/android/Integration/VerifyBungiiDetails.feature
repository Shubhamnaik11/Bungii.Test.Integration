    @android
    #These feature will run in kansas geofence
    Feature: VerifyBungiiDetails

    Background:
    Given I am on customer Log in page
    And I am logged in as "Testcustomertywd_appleand_B Android" customer

    @regression1
    Scenario: Verify that correct trip details are displayed on the grey bar of the Estimate screen.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "30 mins"
    Then I should see "all elements" on Bungii estimate

    @regression1
    Scenario: Verify that Estimated Cost value reads $0.00 as default initially before selection of load + unload time.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    Then I should see "zero estimated cost" on Bungii estimate

    @regression1
    Scenario: Verify that the Estimated cost on the grey bar is updated on updating load/unload time and promo code.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I add loading/unloading time of "15 mins"
    And I tap on "Promo code value" on Estimate screen
    And I add "PROMOTER TYPE PROMO" PromoCode
    And I tap "Add" on Save Money page
    Then I should able to see expected promo code in available promo code
    And I tap on "Back" icon of page
    Then I should see "estimated cost" on Bungii estimate

    @regression1
    Scenario: Verify that four masked characters are displayed before the last four characters of Payment Mode.
    When I Switch to "customer" application on "same" devices
    And I tap on "Menu" > "Payment" link
    Then I should see "masked card number" on Payment page

    @regression1
    Scenario: Verify that clicking on Details field on the Estimate screen opens a blank text box.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I tap on "Details" on Estimate screen
    Then I should see blank textbox
    When I enter "text" in Additional Notes field
    Then the "remaining characters value" should change
    When I enter "500 characters" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Details" on Estimate screen
    When I enter "1 more character" in Additional Notes field
    Then the "remaining characters value= 0" should change

    @regression1
    Scenario: Verify that Bungii can be requested when special charaters have been entered in the Details field on Estimate screen.
    When I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled"
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "special characters" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate
    And I check if the customer is on success screen
    Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999991020      |                 |

    @regression1
    Scenario: Verify that the text entered in Details is displayed after customer schedules a Bungii of an on demand bungii that has timed out.
      When I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "text" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I wait for "5" mins
      And I click "OK" button on the "Driver Not Available" screen
      And I click "Schedule Bungii" button on the "Driver Not Available" screen
      Then "Estimate" page should be opened
      And I should be able to see "Note Details" Text

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in an On Demand Bungii request.
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "text" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      When I click on notification for "on demand trip"
      Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
      When I click "YES" button on alert message
      Then I should be able to see "Customer Note" Text
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

      @regression1
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request.
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "valid pickup and dropoff locations" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate

        When I click on notification for "on demand trip"
        Then Alert message with ACCEPT BUNGII QUESTION text should be displayed
        When I click "YES" button on alert message
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |

      @regression
      Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request.
        When I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
        And I tap on "Go Online button" on Driver Home page
        And I Switch to "customer" application on "same" devices
        And I enter "valid pickup and dropoff locations" on Bungii estimate
        And I tap on "two drivers selector" on Bungii estimate
        And I tap on "Get Estimate button" on Bungii estimate
        And I select Bungii Time as "BUNGII TIME"
        And I add loading/unloading time of "15 mins"
        Then I add "1" photos to the Bungii
        When I tap on "Details" on Estimate screen
        And I enter "text" in Additional Notes field
        And I click on "ADD NOTE" button
        Then "Estimate" page should be opened
        When I tap on "Request Bungii" on Bungii estimate
        And I tap on "Yes on HeadsUp pop up" on Bungii estimate
        Then I click "Done" button on "Success" screen

        When I Switch to "driver" application on "same" devices
        And I Select "AVAILABLE TRIPS" from driver App menu
        And I Select Trip from driver available trip
        And I tap on "ACCEPT" on driver Trip details Page
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        And I Select Trip from driver scheduled trip
        Then I should be able to see "Customer Note" Text
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request, when viewed from Available Trips page.
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled"
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    Then I should be able to see "Customer Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request, when viewed from Available Trips page.
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "two drivers selector" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "next possible scheduled"
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    Then I should be able to see "Customer Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in the Bungii Details page for a Scheduled Bungii.
    When I Switch to "driver" application on "same" devices
    And I am on the LOG IN page on driver app
    And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
    And I tap on "Go Online button" on Driver Home page
    And I Switch to "customer" application on "same" devices
    And I enter "valid pickup and dropoff locations" on Bungii estimate
    And I tap on "Get Estimate button" on Bungii estimate
    And I select Bungii Time as "OLD BUNGII TIME"
    And I add loading/unloading time of "15 mins"
    Then I add "1" photos to the Bungii
    When I tap on "Details" on Estimate screen
    And I enter "text" in Additional Notes field
    And I click on "ADD NOTE" button
    Then "Estimate" page should be opened
    When I tap on "Request Bungii" on Bungii estimate
    And I tap on "Yes on HeadsUp pop up" on Bungii estimate

    When I Switch to "driver" application on "same" devices
    And I Select "AVAILABLE TRIPS" from driver App menu
    And I Select Trip from driver available trip
    And I tap on "ACCEPT" on driver Trip details Page
    And I Select "SCHEDULED BUNGIIS" from driver App menu
    And I Select Trip from driver scheduled trip
    Then I should be able to see "Customer Note" Text
    And I cancel all bungiis of customer
      | Customer Phone  | Customer2 Phone |
      | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress.
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I select Bungii Time as "OLD BUNGII TIME"
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "text" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE TRIPS" from driver App menu
      And I Select Trip from driver available trip
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then I start selected Bungii
      When I slide update button on "EN ROUTE" Screen
      And I slide update button on "ARRIVED" Screen
      And I click on "MORE" button
      And I click on "DETAILS FROM CUSTOMER" button
      And I should be able to see "Details From Customer" Text

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress.
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdrivertywd_apple_z Android_Test" driver
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I select Bungii Time as "OLD BUNGII TIME"
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate

      When I Switch to "driver" application on "same" devices
      And I Select "AVAILABLE TRIPS" from driver App menu
      And I Select Trip from driver available trip
      And I tap on "ACCEPT" on driver Trip details Page
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      Then I start selected Bungii
      When I slide update button on "EN ROUTE" Screen
      And I slide update button on "ARRIVED" Screen
      And I click on "MORE" button
      Then I should not be able to see "Details From Customer" on screen


    @regression1
    Scenario: Verify that correct date of the trip is displayed as per the timezone of the geofence.
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time  |
        | goa  | Accepted     | 15 min ahead |
      And As a driver "Testdrivertywd_applega_a Android_test" perform below action with respective "Solo Ondemand" trip
        | driver1 state |
        | Bungii Completed      |
      When I Switch to "customer" application on "same" devices
      And I tap on "Menu" > "My Bungiis" link
      And "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      Then I verify the field "timezone"

    @regression1
    Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-OnDemand
      Given that ondemand bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State |
        | goa      | Enroute      |
      And As a driver "Testdrivertywd_applega_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state     |
        | Bungii Completed  |
      When I Switch to "customer" application on "same" devices
      And I tap on "Menu" > "My Bungiis" link
      Then "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      And I open the trip for "Testdrivertywd_applega_a Android_test" driver
      Then I verify the field "driver name"
      And I verify the field "pickup address"
      And I verify the field "dropoff address"
      And I verify the field "trip cost"

      @regression1
      Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-Solo
        Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State | Bungii Time   |
          | goa      | enroute     | NEXT_POSSIBLE |
        And As a driver "Testdrivertywd_applega_a Android_test" perform below action with respective "Solo Scheduled" trip
          | driver1 state     |
          | Bungii Completed  |
        When I Switch to "customer" application on "same" devices
        And I tap on "Menu" > "My Bungiis" link
        Then "MY BUNGIIS" page should be opened
        And I click on "Past" tab
        And I open the trip for "Testdrivertywd_applega_a Android_test" driver
        Then I verify the field "driver name"
        And I verify the field "pickup address"
        And I verify the field "dropoff address"
        And I verify the field "trip cost"

      @regression1
      Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-Duo
        Given that duo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
          | geofence | Bungii State    | Bungii Time   | Customer        |
          | goa      | unloading items | NEXT_POSSIBLE | Testcustomertywd_appleand_A Android |
        And As a driver "Testdrivertywd_applega_a Android_test" and "Testdrivertywd_applega_b Android_test" perform below action with respective "Duo Scheduled" trip
          | driver1 state     |driver2 state    |
          | Bungii Completed  |Bungii Completed |
        When I Switch to "customer" application on "same" devices
        And I tap on "Menu" > "My Bungiis" link
        Then "MY BUNGIIS" page should be opened
        And I click on "Past" tab
        And I open the trip for "Testdrivertywd_applega_a Android_test" driver
        Then I verify the field "driver name"
        And I verify the field "pickup address"
        And I verify the field "dropoff address"
        And I verify the field "trip cost"

    @regression11
    Scenario: Verify that admin can assign a driver to a solo trip when it is in searching status.
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_C Android"
        | geofence | Bungii State | Bungii Time   |
        | goa  | Scheduled    | NEXT_POSSIBLE |
      When I open new "Chrome" browser for "ADMIN_PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleand_C Android" customer
      And I assign driver for the trip
      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Login as a driver and go online
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      And I Select Trip from driver scheduled trip
      ##########to be continued