package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_TermsPage extends PageBase {

    //Terms & Conditions - Text
    public WebElement Text_Terms() { return findElement(".//*[@id='divStep5']/div[3]/div/div/div/table/tbody/tr[9]/td[2]", LocatorType.XPath); }

    //Terms & Conditions - Error - Agree unchecked
    public WebElement Err_Terms () { return findElement("summary5", LocatorType.Id); }

    //Terms & Conditions - H5
    public WebElement Terms_H5 () { return findElement("//div[@id='divStep5']/div[2]/div/h5", LocatorType.XPath); }

    //Terms & Conditions - Agree checkbox
    public WebElement CheckBox_Agree_Click() { return findElement("//input[@id='TermsnConditions.HasAgreedToTerms']/following-sibling::label", LocatorType.XPath); }

    //Terms & Conditions - Agree checkbox
    public WebElement CheckBox_Agree() { return findElement("//input[@id='TermsnConditions.HasAgreedToTerms']", LocatorType.XPath); }

    //Terms & Conditions - Next Button
    public WebElement Button_TermsNext () { return findElement("btnTerms", LocatorType.Id); }
}
