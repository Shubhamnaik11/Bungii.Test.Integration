package com.bungii.ios.stepdefinitions;

import com.bungii.common.core.DriverBase;
import cucumber.api.java.en.And;

import static com.bungii.common.manager.ResultManager.pass;
//THIS IS TEST LINE
public class MyStepdefs extends DriverBase {
    @And("This is test statement")
    public void thisIsTestStatement() {
        pass("THIS IS TO TEST","PASS","TEST PASS",true);

    }
}
