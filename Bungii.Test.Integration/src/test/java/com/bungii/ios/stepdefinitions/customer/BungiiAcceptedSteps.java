package com.bungii.ios.stepdefinitions.customer;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.customer.BungiiAcceptedPage;

public class BungiiAcceptedSteps extends DriverBase {
    BungiiAcceptedPage bungiiAcceptedPage;
    ActionManager action = new ActionManager();

    public  BungiiAcceptedSteps(BungiiAcceptedPage bungiiAcceptedPage){
        this.bungiiAcceptedPage=bungiiAcceptedPage;
    }



}


