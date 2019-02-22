@ios

Feature: Promos
  As a Bungii customer
  I Should able to add new promo code

  Background:
    Given I am on Customer logged in Home page

  @regression
  Scenario Outline:As a existing bungii customer , I should not be allowed to use First time only Promo code
    When I logged in Customer application using  "existing" user
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"
    When I Select "LOGOUT" from Customer App menu

#added promo code in
    Examples:
      | Senario | Promo  | Expected Message      |
      | Invalid | first time only | FIRST TIME ONLY PROMO |

  @regression
  Scenario Outline: As a Bungii Customer , I should be alert while adding invalid promo code
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"

    Examples:
      | Senario | Promo   | Expected Message |
      | Invalid | AAAAAAA | Invalid Promo    |

  @regression
  Scenario: As a Bungii Customer , I should not able to add Referral promo code after creating account . I Should be alerted that Referral code are for new customer only

    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal

    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "referral"
    When I switch to "ORIGINAL" instance

    When I logged in Customer application using  "new" user
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "Referral" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "REFERRAL FOR NEW USER"
    When I Select "LOGOUT" from Customer App menu

  @regression
  Scenario: As a Bungii Customer , I should be alerted while added used one off promo code
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "USED ONE OFF"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "USED ONE OFF" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Invalid Promo"

  @regression
  Scenario: As a Bungii Customer , I should be alerted while adding already existing code
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "VALID"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I add "VALID" PromoCode
    When I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    When I add Same Promo Code again
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "Already Existing Code"

  @regression
  Scenario: When i try to enter expired promo code I should be alerted for Expired Promo code message
    When I open new "Chrome" browser for "ADMIN PORTAL"
    When I navigate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "expired"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "expired" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "EXPIRED PROMO"


    #commented scenario outcome due to change in application
  @regression
  Scenario: When i try to share my promo code , via twitter but there is no application installed then I should be alerted for No twitter Installed message
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
    Then I should be navigated to "Invite" screen
    Then I get Invite Code
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE ON TWITTER" button on "INVITE" screen
#    Then user is alerted for "No twitter installed"
    Then I should be navigated to "Invite" screen

