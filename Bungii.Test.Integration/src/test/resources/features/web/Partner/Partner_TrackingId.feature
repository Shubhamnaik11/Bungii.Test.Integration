@web
Feature: Delivery Tracking ID

  Background:
    Given I navigate to "Partner" portal configured for "normal" URL

  @ready
  Scenario: Display and search Tracking ID and Order ID or Receipt Number on partner portals delivery list
    Given I'm logged into "Partner" portal and  created a new  delivery
      |PickupAddress                                                                    |DropdownAddress                                                  |ItemToDelivery|CustomerName|CustomerMobile|PickupContactName|PickupContactMobile|ReceiptNumber|
      |601 13th Street Northwest, Washington, United States, District of Columbia, 20005|234 13th Street Northeast, Washington, District of Columbia 20002|Furniture     |TestCustomer|9998887777    |TestPickup       |9999999359         |RN           |

    Then I should see the trackingid displayed on the delivery confirmation page
    When I click the "Track Deliveries" button on Partner Portal
    And I search the delivery using a correct "tracking id"
    Then I should see the trip Details
    When I search the trip using invalid tracking id "12345A"
    Then I should see the message "Sorry, no records found"

    And  I navigate to the "Admin" portal configured for "QA" URL
    When As a driver "Testdrivertywd_appledc_a_drve Driver" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Accepted  |
    And I wait for 2 minutes
     Then I should be able to see the respective bungii trip with the below status
      | Status    |
      | Scheduled |
    When I click on the "Scheduled Deliveries" button and enter the "Tracking Id" in the search bar
    Then I should be able to see the respective delivery
    When I click on the "Delivery Details" button from the dropdown
    Then I should see the "Tracking Id" displayed on the delivery details
    When I click on the "Scheduled Deliveries" link and click on the "Edit" button from the dropdown
    Then I should see the "Edit Scheduled Bungii" details form
    When I click on the "Edit Delivery Details" button and click  the "Edit pickup location" button
    And Change the "Dropoff" location
    Then I should see the location changed
    When I navigate back to "Partner" portal and click on "Track Deliveries" button
    Then I should see the delivery address changed and get navigated to the "Admin" portal
    When As a driver "Testdrivertywd_appledc_a_drve Driver" perform below action with respective "Edited Solo Scheduled" Delivery
      | driver1 state|
      | Enroute |
    And I wait for 2 minutes
    Then I should be able to see the  bungii trip status to be changed to the below status
      | Status |
      | Trip Started |
    When  I click on the "Live Deliveries" button and enter the "Tracking Id" in the search bar
    Then I should see the delivery details displayed
    When As a driver "Testdrivertywd_appledc_a_drve Driver" perform below action with respective "Solo Scheduled" Delivery
      | driver1 state|
      | Arrived |
      | Loading Item |
      | Driving To Dropoff |
      | Unloading Item |
      | Bungii Completed |
    And I wait for 2 minutes
    Then The "All Deliveries" page should display the delivery in "Payment Successful" state
    When  I click on the "All Deliveries" button and enter the "Tracking Id" in the search bar
    Then I should see delivery details displayed


      #CORE-4081 changes
  @regression
Scenario: Display and search delivery by Order ID or Receipt Number on partner portals delivery list
  Given I'm logged into "Partner" portal and  created a new  delivery
    |PickupAddress                                                                    |DropdownAddress                                                  |ItemToDelivery|CustomerName |CustomerMobile|PickupContactName|PickupContactMobile|ReceiptNumber|
    |601 13th Street Northwest, Washington, United States, District of Columbia, 20005|234 13th Street Northeast, Washington, District of Columbia 20002|Furniture     |TestCustomer1|9998887778    |TestPickup1      |9999999360         |RN           |
  When I click the "Track Deliveries" button on Partner Portal
  And I search the delivery using a incorrect "receipt number"
  Then I should see the message "Sorry, no records found"
  And I search the delivery using a correct "receipt number"
  Then I should see the delivery Details with "receipt number"
  And I open the search delivery
  And I click "Cancel Delivery link" button on Partner Portal
  Then I should "see the cancel delivery warning message"
  And I click "Cancel Delivery" button on Partner Portal
  Then I should "Your delivery has been canceled message"
  And I click "OK" button on Partner Portal
  And I close the Trip Delivery Details page
  And I select below delivery status in filter
        | Partner_Status |
        | Canceled    |
  And I search the delivery using a correct "receipt number"
  Then I should see the below trip status for search trip on partner portal
    | Partner_Status |
    | Canceled    |
