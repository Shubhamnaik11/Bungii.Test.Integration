    @android
    @bungii
    #These feature will run in Goa geofence
    Feature: Customer Pickup Details Feature
      #Customer D and C - Testcustomertywd_appleand_D Android 9999990074 and Testcustomertywd_appleand_C Android 9999992222
      # Driver D and C  - Testdriver_goa_d Android_test and Testdriver_goa_c Android_test
      
@ready
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


 @ready
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
   And I open Admin portal and navigate to "Scheduled Deliveries" page
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


@ready
    Scenario: Verify admin can assign one (controlled) driver on duo trip when it has been re-searched.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_d Android_test" and "Testdriver_goa_c Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
  And I open Admin portal and navigate to "Scheduled Deliveries" page
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


@ready
    Scenario: Verify if admin can assign one (non controlled) driver on duo trip when it has been re-searched.
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_d Android_test" and "Testdriver_goa_c Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
  And I open Admin portal and navigate to "Scheduled Deliveries" page
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


@ready
    Scenario: Verify if admin can assign both drivers on duo trip when it has been re-searched
      Given I am on customer Log in page
      And I am logged in as "Testcustomertywd_appleand_D Android" customer

      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_d Android_test" and "Testdriver_goa_c Android_test" perform below action with respective "DUO SCHEDULED" trip
        | driver1 state | driver2 state |
        | Accepted      | Accepted      |
  And I open Admin portal and navigate to "Scheduled Deliveries" page
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




 

   
 @ready
    Scenario: Verify that if admin can assign (controlled)driver on duo trip when non controlled driver has started the trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_d Android_test" and "Testdriver_goa_c Android_test" perform below action with respective "DUO SCHEDULED" trip
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


 @ready
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at same time
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I switch to "ORIGINAL" instance
      And I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      And I Select "SCHEDULED BUNGIIS" from driver App menu
      Then I should able to see "two" scheduled trip
      And I cancel all bungiis of customer
        | Customer Phone  | Customer2 Phone |
        | 9999990074      | 9999992222      |


 @ready
    Scenario: Verify that TELET time of solo scheduled when trip is not started and same driver is assigned to another scheduled trip at overlapping time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed
      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
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
        | 9999990074      | 9999992222      |


 @ready
    Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned
              to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
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
        | 9999990074      | 9999992222      |


 @ready
    Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
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
        | 9999990074      | 9999992222      |
@ready
    Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And I get TELET time of of the current trip
      When I request "duo" Bungii as a customer in "goa" geofence
        | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
        | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      Then I wait for "2" mins
      When I open new "Chrome" browser for "ADMIN"
      And I navigate to admin portal
      And I log in to admin portal
      And I Select "Scheduled Trip" from admin sidebar
      And I click on "Edit Trip1" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
      And I assign driver "Testdriver_goa_c Android_test" for the trip
      And I click on "VERIFY" button
      And the "Your changes are good to be saved." message is displayed
      Then I click on "SAVE CHANGES" button
      And the "Bungii Saved!" message is displayed

      When I click on "Close" button
      And I click on "Edit Trip2" button
      And I Select "Edit Trip Details" option
      And I assign driver "Testdriver_goa_d Android_test" for the trip
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
        | 9999990074      | 9999992222      |


 @ready
      Scenario: Verify that TELET time of duo scheduled when trip is not started and controlled driver is assigned
      to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
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
          | 9999990074      | 9999992222      |

@ready
      Scenario: Verify that TELET time of duo scheduled when trip is not started and non controlled driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
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
          | 9999990074      | 9999992222      |


@ready
      Scenario: Verify that TELET time of duo scheduled when trip is not started and both driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time    | Customer Phone | Customer Name                       | Customer Password |
          | TELET OVERLAP  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I assign driver "Testdriver_goa_c Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
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
          | 9999990074      | 9999992222      |


 @ready
      Scenario: Verify that TELET is impacted of solo scheduled when trip is not started and driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip1" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
        And I click on "VERIFY" button
        And the "Your changes are good to be saved." message is displayed
        Then I click on "SAVE CHANGES" button
        And the "Bungii Saved!" message is displayed

        When I click on "Close" button
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
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
          | 9999990074      | 9999992222      |

@ready
      Scenario: Verify that TELET is impacted of duo scheduled when trip is started and driver is assigned to another scheduled trip at same time
        Given I Switch to "driver" application on "same" devices
        And I am on the LOG IN page on driver app
        And I am logged in as "Testdriver_goa_d Android_test" driver
        When I request "duo" Bungii as a customer in "goa" geofence
          | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
          | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
        And I get TELET time of of the current trip
        And As a driver "Testdriver_goa_a Android_test" and "Testdriver_goa_b Android_test" perform below action with respective "Duo Scheduled Researched" trip
          | driver1 state | driver2 state |
          | Enroute       | Enroute       |
        When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
          | Bungii Time      | Customer Phone | Customer Name                       | Customer Password |
          | TELET SAME TIME  | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
        Then I wait for "2" mins
        When I open new "Chrome" browser for "ADMIN"
        And I navigate to admin portal
        And I log in to admin portal
        And I Select "Scheduled Trip" from admin sidebar
        And I click on "Edit Trip2" button
        And I Select "Edit Trip Details" option
        And I assign driver "Testdriver_goa_d Android_test" for the trip
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
          | 9999990074      | 9999992222      |


@ready
    Scenario: Verify that  if driver have two TELET overlapping bungiis, and admin researches any one of the Bungii,
              then concerned driver does not recieve push notification
      Given I Switch to "driver" application on "same" devices
      And I am on the LOG IN page on driver app
      And I am logged in as "Testdriver_goa_d Android_test" driver
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | NEXT_POSSIBLE | 9999990074     | Testcustomertywd_appleand_D Android | Cci12345          |
      And As a driver "Testdriver_goa_a Android_test" perform below action with respective "Solo Scheduled" trip
        | driver1 state      |
        | Accepted           |
      And I get TELET time of of the current trip
      When I request "Solo Scheduled" Bungii as a customer in "goa" geofence
        | Bungii Time   | Customer Phone | Customer Name                       | Customer Password |
        | TELET OVERLAP | 9999992222     | Testcustomertywd_appleand_C Android | Cci12345          |
      And As a driver "Testdriver_goa_d Android_test" perform below action with respective "Solo Scheduled" trip
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
        | 9999990074      | 9999992222   |



