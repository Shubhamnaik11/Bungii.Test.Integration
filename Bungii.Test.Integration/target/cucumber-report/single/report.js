$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("target/test-classes/features/ios/Integration/Scheduling_SOLO.feature");
formatter.feature({
  "name": "To Test Solo - Scheduling Bungii",
  "description": "  I want to use request Scheduling Bungii with Solo type\n  Assume customer is logged in",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@tag"
    }
  ]
});
formatter.scenario({
  "name": "Cancel Bungii from Admin Panel , verify trip is gone from scheduled trip in app",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@tag"
    },
    {
      "name": "@Solo_SchedulingTEST"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I Select \"ACCOUNT\" from Customer App menu",
  "keyword": "When "
});
formatter.match({
  "location": "HomeSteps.i_select_something_from_customer_app_menu(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I get customer account details",
  "keyword": "Then "
});
formatter.match({
  "location": "AccountSteps.i_get_customer_account_details()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Select \"Home\" from Customer App menu",
  "keyword": "When "
});
formatter.match({
  "location": "HomeSteps.i_select_something_from_customer_app_menu(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I request for  bungii",
  "rows": [
    {
      "cells": [
        "Driver",
        "Distance"
      ]
    },
    {
      "cells": [
        "Solo",
        "Long"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "HomeSteps.iRequestForBungii(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click \"Get Estimate\" button on \"Home\" screen",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.iClickButtonOnScreen(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be navigated to \"Estimate\" screen",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.i_should_be_naviagated_to_something_screen(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I confirm trip with following details",
  "rows": [
    {
      "cells": [
        "LoadTime",
        "PromoCode",
        "PayMentCard",
        "Time",
        "PickUpImage"
      ]
    },
    {
      "cells": [
        "30",
        "",
        "",
        "NEXT_POSSIBLE",
        "Default"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "EstimateSteps.iEnterTripInformation(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should be navigated to \"Success\" screen",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.i_should_be_naviagated_to_something_screen(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click \"Done\" button on \"Success\" screen",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.iClickButtonOnScreen(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open new \"Chrome\" browser for \"ADMIN\"",
  "keyword": "When "
});
formatter.match({
  "location": "CommonSteps.i_open_new_something_browser_for_something_instance(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I naviagate to admin portal",
  "keyword": "When "
});
formatter.match({
  "location": "LogInSteps.i_naviagate_to_admin_portal()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I log in to admin portal",
  "keyword": "And "
});
formatter.match({
  "location": "LogInSteps.i_log_in_to_admin_portal()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Select \"Scheduled Trip\" from admin sidebar",
  "keyword": "When "
});
formatter.match({
  "location": "DashBoardSteps.i_select_something_from_admin_sidebar(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Cancel Bungii with following details",
  "rows": [
    {
      "cells": [
        "Charge",
        "Comments"
      ]
    },
    {
      "cells": [
        "15",
        "TEST"
      ]
    }
  ],
  "keyword": "When "
});
formatter.match({
  "location": "ScheduledTripSteps.i_cancel_bungii_with_following_details(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"Bungii Cancel\" message should be displayed on \"Scheduled Trips\" page",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.something_message_should_be_displayed_on_something_page(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Bungii must be removed from the List",
  "keyword": "Then "
});
formatter.match({
  "location": "ScheduledTripSteps.bungii_must_be_removed_from_the_list()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I switch to \"ORIGINAL\" instance",
  "keyword": "When "
});
formatter.match({
  "location": "CommonSteps.i_switch_to_something_instance(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Switch to \"customer\" application on \"same\" devices",
  "keyword": "When "
});
formatter.match({
  "location": "CommonSteps.i_switch_to_something_application_on_something_devices(String,String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I Select \"SCHEDULED BUNGIIS\" from Customer App menu",
  "keyword": "When "
});
formatter.match({
  "location": "HomeSteps.i_select_something_from_customer_app_menu(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Bungii must be removed from \"SCHEDULED BUNGIIS\" screen",
  "keyword": "Then "
});
formatter.match({
  "location": "ScheduledBungiiSteps.bungii_must_be_removed_from_something_screen(String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});