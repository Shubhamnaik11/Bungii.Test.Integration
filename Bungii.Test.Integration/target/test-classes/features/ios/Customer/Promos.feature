@Promos
@DONE
Feature: Promos
  I want to use this template for my feature file

  Background: 
#Given I am on Customer logged in Home page

  @PROMO_11x
  Scenario Outline: First Time Only
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
      | Invalid | FRESH1 | FIRST TIME ONLY PROMO |


  @PROMO_11X
  Scenario Outline: INVALID Promo code
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Senario | Promo   | Expected Message |
      | Invalid | AAAAAAA | Invalid Promo    |

  @PROMO_112
  Scenario: REERRAL Promo code FOR NEW USER only
 #   When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
    And I log in to admin portal

    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal

    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "referral"
    When I switch to "ORIGINAL" instance
    
    When I logged in Customer application using  "new" user
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "Referral" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "REERRAL FOR NEW USER"
    When I Select "LOGOUT" from Customer App menu
    
    
  @PROMO_11
  Scenario Outline: USED ONE OFF
    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "<Promo>"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    And I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"

    Examples: 
      | Senario | Promo        | Expected Message |
      | Invalid | USED ONE OFF | Invalid Promo    |


    
  @PROMO_6
  Scenario Outline: VALID PROMO
  
  
    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "<Promo>"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
#    And I Enter "<Promo>" value in "Promo Code" field in "Promo" Page
		When I add "VALID" PromoCode
    When I click "ADD" button on "PROMOS" screen
    Then I should able to see expected promo code in available promo code
    
    When I add Same Promo Code again
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "<Expected Message>"


    Examples: 
      | Senario | Promo        | Expected Message |
      | Invalid | VALID | Already Existing Code    |


  @PROMO_2
  Scenario: Expired Promo code
    When I open new "Chrome" browser for "ADMIN" instance
    When I naviagate to admin portal
    And I log in to admin portal
    When I Select "Promo Code" from admin sidebar
    Then I get promo code for "expired"
    When I switch to "ORIGINAL" instance
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    #    When I add "{EXPIRED}" PromoCode
    And I Enter "expired" value in "Promo Code" field in "Promo" Page
    When I click "ADD" button on "PROMOS" screen
    Then user is alerted for "EXPIRED PROMO"


  @PROMO_TWITTER
  Scenario Outline: Share on twitter
    When I Select "PROMOS" from Customer App menu
    Then I should be navigated to "PROMOS" screen
    When I click "GET MORE MONEY" button on "PROMOS" screen
       Then I should be navigated to "Invite" screen
    Then I get Invite Code
    
    When I click "SHARE" button on "INVITE" screen
    When I click "SHARE ON TWITTER" button on "INVITE" screen
    Then user is alerted for "No twitter installed"

    Examples: 
      | Senario | Promo   | Expected Message |
      | Invalid | AAAAAAA | Invalid Promo    |

