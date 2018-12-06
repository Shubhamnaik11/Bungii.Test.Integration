$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("target/test-classes/features/ios/Customer/test.feature");
formatter.feature({
  "name": "Promos",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "TESTNE WFRAMEWORK2",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@TEST_FRAMEWORK_NEW_5"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "This is test statement",
  "keyword": "And "
});
formatter.match({
  "location": "MyStepdefs.thisIsTestStatement()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});