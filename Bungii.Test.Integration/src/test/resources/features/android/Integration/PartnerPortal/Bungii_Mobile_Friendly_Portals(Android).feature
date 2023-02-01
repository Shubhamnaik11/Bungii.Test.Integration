  @android

  Feature: Partner Portal Cases for mobile friendly portals
  #  Core-4584

    @ready
#    @testsweta
    Scenario: Verify UI and functionality for weight based partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Chrome" browser for "MOBILE DEVICE"
      And I open "weight based" partner portal

    @ready
#    @testsweta
    Scenario: Verify UI and functionality for geofence based partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Chrome" browser for "MOBILE DEVICE"
      And I open "geofence based" partner portal
      And I wait for 2 minutes

    @ready
#    @testsweta
    Scenario: Verify UI and functionality for fixed pricing based partner portal
      When I switch to "ORIGINAL" instance
      And I terminate "customer" app on "same" devices
      When I open new "Chrome" browser for "MOBILE DEVICE"
      And I open "fixed pricing" partner portal