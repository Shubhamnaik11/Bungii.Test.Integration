Feature: To Test Duo - Scheduling Bungii
  I want to use request Scheduling Bungii with Duo type
  Assume customer is logged in

  @TESTTT
  Scenario: Create Duo Bungii

#    When I Select "ACCOUNT" from Customer App menu
#    Then I get customer account details
#    When I Select "Home" from Customer App menu
    When I request for  bungii for given pickup and drop location
      | Driver | Pickup Location | Drop Location                |
      | Duo    | Margoa Railway  | Old Goa Road, Velha Goa, Goa |
    And I click "Get Estimate" button on "Home" screen
    Then I should be navigated to "Estimate" screen
    When I confirm trip with following details
      | LoadTime | PromoCode | Payment Card | Time          | PickUpImage | Save Trip Info |
      | 30       |           |             | NEXT_POSSIBLE | 1 images    | No             |
    Then I should be navigated to "Success" screen
    And I click "Done" button on "Success" screen
