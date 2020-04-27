    @android
    #These feature will run in Goa geofence
    Feature: VerifyBungiiDetails

    @regression
    Scenario: Verify that correct trip details are displayed on the grey bar of the Estimate screen.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "30 mins"
      Then I should see "all elements" on Bungii estimate

    @regression
    Scenario: Verify that Estimated Cost value reads $0.00 as default initially before selection of load + unload time.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "valid pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      Then I should see "zero estimated cost" on Bungii estimate

    @regression
    Scenario: Verify that the Estimated cost on the grey bar is updated on updating load/unload time and promo code.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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

    @regression
    Scenario: Verify that four masked characters are displayed before the last four characters of Payment Mode.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

      When I Switch to "customer" application on "same" devices
      And I tap on "Menu" > "Payment" link
      Then I should see "masked card number" on Payment page

    @regression
    Scenario: Verify that clicking on Details field on the Estimate screen opens a blank text box.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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

    @regression
    Scenario: Verify that Bungii can be requested when special charaters have been entered in the Details field on Estimate screen.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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

    @regression
    Scenario: Verify that the text entered in Details is displayed after customer schedules a Bungii of an on demand bungii that has timed out.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer
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
      And I wait for "8" mins
      And I click "OK" button on the "Driver Not Available" screen
      And I click "Schedule Bungii" button on the "Driver Not Available" screen
      Then "Estimate" page should be opened
      And I should be able to see "Note Details" Text

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in an On Demand Bungii request.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_f Android_test" driver
      And I tap on "Go Online button" on Driver Home page
      And I Switch to "customer" application on "same" devices
      And I enter "Goa pickup and dropoff location" on Bungii estimate
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

    @regression
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Solo Bungii request.
      When I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_f Android_test" driver
      And I tap on "Go Online button" on Driver Home page

      When I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_B Android" customer
      And I enter "Goa pickup and dropoff location" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I select Bungii Time as "NEW BUNGII TIME"
      And I add loading/unloading time of "15 mins"
      Then I add "1" photos to the Bungii
      When I tap on "Details" on Estimate screen
      And I enter "text" in Additional Notes field
      And I click on "ADD NOTE" button
      Then "Estimate" page should be opened
      When I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I click "Done" button on "Success" screen

      And I click on notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
      Then Alert message with ACCEPT SCHEDULED BUNGII QUESTION text should be displayed
      When I click "View" on alert message
      Then I should be able to see "Customer Note" Text
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field in a Scheduled Duo Bungii request.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_b Android_test" driver
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
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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
      Then I should be able to see "Details From Customer" Text
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression1
    Scenario: Verify that driver is able to correctly view all the text entered in Details field while a solo bungii is in progress.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_B Android" customer

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

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression1
    Scenario: Verify that correct date of the trip is displayed as per the timezone of the geofence.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time  |
        | goa  | Accepted     | 15 min ahead |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Ondemand" trip
        | driver1 state |
        | Bungii Completed      |

      When I Switch to "customer" application on "same" devices
      And I tap on "Menu" > "My Bungiis" link
      And "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      Then I verify the field "timezone"

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression1
    Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-OnDemand
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      Given that ondemand bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State |
        | goa      | Enroute      |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state     |
        | Bungii Completed  |
      When I Switch to "customer" application on "same" devices
      And I select "4" Ratting star for duo "Driver 1"
      And I give tip to Bungii Driver with following tip and Press "OK" Button
        | Tip |
        | 5   |
      Then I Switch to "driver" application on "same" devices
      And Bungii driver should see "correct details" on Bungii completed page
      And I click "On To The Next One" button on "Bungii Completed" screen
      When I Switch to "customer" application on "same" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
      And I tap on "Menu" > "My Bungiis" link
      Then "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      And I open the trip for "Testdriver_goa_a Android_test" driver
      Then I verify the field "driver name"
      And I verify the field "pickup address"
      And I verify the field "dropoff address"
      And I verify the field "trip cost"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression1
    Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-Solo
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time   |
        | goa      | enroute     | NEXT_POSSIBLE |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state     |
        | Bungii Completed  |
      When I Switch to "customer" application on "same" devices
      And I select "4" Ratting star for duo "Driver 1"
      And I give tip to Bungii Driver with following tip and Press "OK" Button
        | Tip |
        | 5   |
      Then I Switch to "driver" application on "same" devices
      And Bungii driver should see "correct details" on Bungii completed page
      And I click "On To The Next One" button on "Bungii Completed" screen
      When I Switch to "customer" application on "same" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
      And I tap on "Menu" > "My Bungiis" link
      Then "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      And I open the trip for "Testdriver_goa_a Android_test" driver
      Then I verify the field "driver name"
      And I verify the field "pickup address"
      And I verify the field "dropoff address"
      And I verify the field "trip cost"

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression1
    Scenario: Verify that that Past Trips page correctly displays completed Scheduled (solo/duo) as well as On Demand Bungiis. SCENARIO-Duo
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer

      Given that duo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State    | Bungii Time   | Customer        |
        | goa      | unloading items | NEXT_POSSIBLE | Testcustomertywd_appleand_A Android |
      And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled" trip
        | driver1 state     |driver2 state    |
        | Bungii Completed  |Bungii Completed |
      When I Switch to "customer" application on "same" devices
      And I select "4" Ratting star for duo "Driver 1"
      And I give tip to Bungii Driver with following tip and Press "OK" Button
        | Tip |
        | 5   |
      Then I Switch to "driver" application on "same" devices
      And Bungii driver should see "correct details" on Bungii completed page
      And I click "On To The Next One" button on "Bungii Completed" screen
      When I Switch to "customer" application on "same" devices
      And I tap on "OK on complete" on Bungii estimate
      And I tap on "No free money" on Bungii estimate
      And I tap on "Menu" > "My Bungiis" link
      Then "MY BUNGIIS" page should be opened
      And I click on "Past" tab
      And I open the trip for "Testdriver_goa_a Android_test" driver
      Then I verify the field "driver name"
      And I verify the field "pickup address"
      And I verify the field "dropoff address"
      And I verify the field "trip cost"

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression1
    Scenario: Verify that admin can assign a driver to a solo trip when it is in searching status.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_A Android"
        | geofence | Bungii State | Bungii Time   |
        | goa  | Scheduled    | NEXT_POSSIBLE |
      When I open new "Chrome" browser for "ADMIN_PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleand_A Android" customer
      And I Select "Edit Trip Details" option
      And I assign driver for the "Solo" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "solo driver names"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression1
    Scenario: Verify that admin can assign one or both drivers to a duo trip when it is in searching status.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_C Android" customer

      Given that duo schedule bungii is in progress for customer "Testcustomertywd_appleand_C Android"
        | geofence | Bungii State | Bungii Time   |
        | goa      | Scheduled    | NEXT_POSSIBLE |
      When I open new "Chrome" browser for "ADMIN_PORTAL"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I open the trip for "Testcustomertywd_appleand_C Android" customer
      And I Select "Edit Trip Details" option
      And I assign driver for the "Duo" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_C Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "duo driver names"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario:Verify that admin can assign a driver to a solo trip when it has been re-searched.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_D Android"
        | geofence | Bungii State | Bungii Time   |
        | goa   | Accepted     | NEXT_POSSIBLE |
      When I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "valid" driver
      When I Switch to "customer" application on "same" devices
      Then I wait for "2" mins
      And I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I remove current driver and researches Bungii
      And I Select "Edit Trip Details" option
      And I assign driver for the "Solo" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_D Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "solo driver names"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario: Verify admin can assign one (controlled) driver on duo trip when it has been re-searched.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_e Android_test" and "Testdriver_goa_f Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
      And I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I remove "control" driver and researches Bungii
      And I Select "Edit Trip Details" option
      And I assign driver for the "control" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_D Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "control driver name"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario: Verify if admin can assign one (non controlled) driver on duo trip when it has been re-searched.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_e Android_test" and "Testdriver_goa_f Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
      And I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I remove "noncontrol" driver and researches Bungii
      And I Select "Edit Trip Details" option
      And I assign driver for the "noncontrol" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_D Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "noncontrol driver name"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario: Verify if admin can assign both drivers on duo trip when it has been re-searched
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_e Android_test" and "Testdriver_goa_f Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
      And I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I remove current driver and researches Bungii
      And I Select "Edit Trip Details" option
      And the " Adding a driver through this feature overrides driver assigning restrictions." message is displayed
      And I assign driver for the "Duo" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_D Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "duo driver names"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario: Verify if re-search automatically happens if admin does not add a new driver after removal.
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
        | geofence | Bungii State | Bungii Time  |
        | goa      | Accepted     | 15 min ahead |
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      Then I remove current driver

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
    Scenario: Verify if admin can update date/time for a solo trip for which no driver has accepted.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer
      When I enter "goa location in pickup and dropoff fields long distance" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I select Bungii Time as "NEW BUNGII TIME"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip" button
      And I Select "Edit Trip Details" option
      And I change the "trip time" to future time
      And I click on "VERIFY" button
      Then the "Your changes are good to be saved." message is displayed
      And I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I Select "Research Driver" option
      Then I verify that time change is saved
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression
    Scenario: Verify if admin can update date/time for a solo trip for which a driver has accepted and Customer has no conflicting trips at the new time.
      Given that solo schedule bungii is in progress
        | geofence | Bungii State | Bungii Time   |
        | goa   | Accepted     | NEXT_POSSIBLE |
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip" button
      And I Select "Edit Trip Details" option
      And I change the "trip time" to future time
      And I click on "VERIFY" button
      Then the "Your changes are good to be saved." message is displayed
      And I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I Select "Research Driver" option
      Then I verify that time change is saved
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | CUSTOMER1_PHONE |                 |


    @regression
    Scenario: Verify that changing date/time for a scheduled bungii for which the customer has a conflicting bungii during the newly selected time.
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999999999     | Testcustomertywd_appleand_F Android | Cci12345          |
      And I save the Bungii Time
      And that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
        | geofence | Bungii State | Bungii Time    |
        | goa   | Scheduled     | 0.5 hour ahead |
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I change the "particular trip time" to future time
      And I click on "VERIFY" button
      Then the "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time" message is displayed
      And I cancel all bungiis of customer
        | Customer Phone | Customer2 Phone |
        | 9999999999     |                 |

    @regression
    Scenario: Verify that changing date/time for a scheduled bungii for which the assigned driver has a conflicting bungii during the newly selected time.
      Given that solo schedule bungii is in progress for customer "Testcustomertywd_appleand_F Android"
      | geofence | Bungii State | Bungii Time     |
      | goa      | Accepted     | 1.5 hour ahead  |
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I change the "particular trip time" to future time
      And I click on "VERIFY" button
      Then the "It looks like customer already has a Bungii scheduled at this time. Customer can have only one Bungii at a time" message is displayed
      And I cancel all bungiis of customer
        | Customer Phone | Customer2 Phone |
        | 9999999999     |                 |

    @regression
    Scenario: Verify that if admin can assign (controlled)driver on duo trip when non controlled driver has started the trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
        |               | Enroute       |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I remove "control" driver and researches Bungii
      And I Select "Edit Trip Details" option
      And I assign driver for the "control driver" trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I switch to "ORIGINAL" instance
      And I Switch to "customer" application on "same" devices
      And I am logged in as "Testcustomertywd_appleand_D Android" customer
      And I tap on "Menu" > "MY BUNGIIS" link
      And I select already scheduled bungii
      Then I verify the "solo driver names"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074 |                 |

    @regression
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at same time
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9393939393      |

    @regression
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at overlapping time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9393939393      |

    @regression
    Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned
              to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9393939393      |

    @regression
    Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9393939393      |

    @regression
    Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9393939393      |

      @regression
      Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned
      to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9393939393      |

      @regression
      Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9393939393      |

      @regression
      Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I assign driver "Testdriver_goa_b Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9393939393      |

      @regression
      Scenario: Verify that TELET is impacted of solo scheduled when trip is not started and driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9393939393      |


      @regression
      Scenario: Verify that TELET is impacted of duo scheduled when trip is started and driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_a Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled Researched" trip
          | driver1 state | driver2 state |
          | Enroute       | Enroute       |
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_a Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I switch to "ORIGINAL" instance
        And I Switch to "driver" application on "same" devices
        And I Select "SCHEDULED BUNGIIS" from driver App menu
        Then I should able to see "two" scheduled trip
        And I cancel all bungiis of customer
          | Customer Phone  | Customer2 Phone |
          | 9999990074      | 9393939393      |

    @regression
    Scenario: Verify that the driver can be assigned to a duo trip irrespective of drive time to pickup
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "30 mins"
      And I select Bungii Time as "next possible scheduled"
      And I add "1" photos to the Bungii
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression
    Scenario: Verify that the driver can be assigned to a duo trip irrespective of driver home address
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer

      When I Switch to "customer" application on "same" devices
      And I enter "far off Goa pickup and dropoff locations" on Bungii estimate
      And I tap on "two drivers selector" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add loading/unloading time of "30 mins"
      And I add "1" photos to the Bungii
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      And I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate

      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_a Android_test" for the trip
      And I assign driver "Testdriver_goa_b Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression
    Scenario: Verify updating time to past time and date
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_A Android" customer

      When I enter "goa location in pickup and dropoff fields long distance" on Bungii estimate
      And I tap on "Get Estimate button" on Bungii estimate
      And I add "1" photos to the Bungii
      And I add loading/unloading time of "30 mins"
      And I select Bungii Time as "NEW BUNGII TIME"
      And I tap on "Request Bungii" on Bungii estimate
      And I tap on "Yes on HeadsUp pop up" on Bungii estimate
      And I check if the customer is on success screen
      Then I tap on "Done after requesting a Scheduled Bungii" on Bungii estimate
      And I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip" button
      And I Select "Edit Trip Details" option
      And I change the "trip time to past" to future time
      And I click on "VERIFY" button
      Then the "Please check the date/time selected. You cannot select a past date/time." message is displayed

      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999991020      |                 |

    @regression
    Scenario: Verify that  if driver have two TELET overlapping bungiis, and admin researches any one of the Bungii,
              then concerned driver does not recieve push notification
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_a Android_test" driver
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state      |
        | Accepted           |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP | 9999990074     | Testcustomertywd_appleand_E Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state      |
        | Accepted           |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      Then I remove "control" driver and researches Bungii
      When I switch to "ORIGINAL" instance
      Then I should not get notification for "driver" for "SCHEDULED PICKUP AVAILABLE"
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      |                 |

    @regression
      Scenario: Verify the DUO trip started by non controller driver and controller driver is removed and new driver is added to the same trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled" trip
        | driver1 state  |  driver2 state  |
        | Accepted       |  Accepted       |
        |                |  Enroute        |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      Then I remove "control" driver and researches Bungii
      When I Select "Edit Trip Details" option
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9393939393      |                 |

    @regression
    Scenario: Verify that the list displays all completed Bungiis in descending order of date.
      #Given I am logged in as "Testcustomertywd_appleand_A Android" customer
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_d Android_test" perform below action with respective "Duo Scheduled Researched" trip
        | driver1 state      | driver2 state      |
        | Accepted           | Accepted           |
        | Arrived            | Arrived            |
        | Loading Item       | Loading Item       |
        | Driving To Dropoff | Driving To Dropoff |
        | Unloading Item     | Unloading Item     |
        | Bungii Completed   | Bungii Completed   |
#      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
#        | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
#        | NEXT_POSSIBLE  | 9393939393     | Testcustomertywd_appleand_A Android | Cci12345          |
#      And As a driver "Testdriver_goa_b Android_test" perform below action with respective "Solo Scheduled" trip
#        | driver1 state      |
#        | Accepted           |
#        | Arrived            |
#        | Loading Item  |
#        | Driving To Dropoff |
#        | Unloading Item |
#        | Bungii Completed |
#      When I request "Solo Ondemand" Bungii as a customer in "goa" geofence
#        | Bungii Time   | Customer Phone | Customer Name                       |
#        | NEXT_POSSIBLE | 9393939393     | Testcustomertywd_appleand_A Android |
#      And As a driver "Testdriver_goa_c Android_test" perform below action with respective "Solo Ondemand" trip
#        | driver1 state|
#        | Accepted |
#        | Arrived |
#        | Loading Item |
#        | Driving To Dropoff |
#        | Unloading Item |
#        | Bungii Completed |
#      Then I Switch to "customer" application on "same" devices
#      When I tap on "Menu" > "MY BUNGIIS" link
#      And I click on "Past" tab
#      Then I verify that completed bungiis are displayed in "Descending order of date"

