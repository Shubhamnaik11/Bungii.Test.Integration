package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Driver_DetailsPage extends PageBase {

    //Header
    public WebElement DriverReg_AllPagesHeader () { return findElement("//div[@id='tab-title']/h3", LocatorType.XPath); }

    //Driver Details - Blank field validation
    public WebElement Err_DriverDetails_AllBlank () { return findElement("summary1", LocatorType.Id); }

    //Details - Address Information - Street Address
    public WebElement TextBox_StreetAddress () { return findElement("DriverDetails_Address1", LocatorType.Id); }

    //Details - Address Information - City
    public WebElement TextBox_City () { return findElement("City", LocatorType.Id); }

    //Details - Address Information - State
    public WebElement DropDown_State () { return findElement("DriverDetails_State", LocatorType.Id); }

    //Details - Address Information - Zip Code
    public WebElement TextBox_ZipCode () { return findElement("DriverDetails_ZipPostalCode", LocatorType.Id); }

    //Details - Address Information - Zip Code - Error
    public WebElement Err_ZipCode () { return findElement("DriverDetails_ZipPostalCode-error", LocatorType.Id); }

    //Details - 'Im 18 or older' checkbox
    public WebElement CheckBox_Age18 () { return findElement("//input[@id='DriverDetails.MeetsAgeLimit']/following-sibling::label", LocatorType.XPath); }

    //Details - 'I'm able to lift up 75 pounds' checkbox
    public WebElement CheckBox_Lift75 () { return findElement("//input[@id='DriverDetails.CanLiftRequiredPounds']/following-sibling::label", LocatorType.XPath); }

    //Details - 'I have had at least one year of driving experience' checkbox
    public WebElement CheckBox_DrivingExp () { return findElement("//input[@id='DriverDetails.HasSufficientDrivingExperience']/following-sibling::label", LocatorType.XPath); }

    //Details - Driver Availability - Clear all
    public WebElement Link_ClearAll () { return findElement("//input[@class='clearAll']/parent::label", LocatorType.XPath); }

    //Details - Driver Availability - Wednesday Afternoon
    public WebElement CheckBox_WedAftrn () { return findElement("//input[@id='DriverDetails_DriverAvailability_TimeSlots_2__Afternoon']", LocatorType.XPath); }

    //Details - Driver Availability - Select all
    public WebElement Link_SelectAll () { return findElement("//input[@class='selectAll']/parent::label", LocatorType.XPath); }

    //Details - Driver Availability - Sunday Evening
    public WebElement CheckBox_SunEve () { return findElement("//input[@id='DriverDetails_DriverAvailability_TimeSlots_6__Evening']", LocatorType.XPath); }

    //Details - TextArea - Other
    public WebElement TextArea_Other () { return findElement("DriverDetails_DriverAvailability_OtherAvailabilityDetails", LocatorType.Id); }

    //Details - TextArea - Other - Error
    public WebElement Err_Other () { return findElement("DriverDetails_DriverAvailability_OtherAvailabilityDetails-error", LocatorType.Id); }

    //Details - TextArea - Current / Primary Occupation
    public WebElement TextArea_Occupation () { return findElement("DriverDetails_CurrentOccupation", LocatorType.Id); }

    //Details - Driver Picture Upload
    public WebElement Link_DriverPicture () { return findElement("//div[@id='dropzone1']/div/p/a[contains(text(),'click here')]", LocatorType.XPath); }

    //Details - Driver Picture Upload - Crop Button
    public WebElement Button_Crop () { return findElement("//div[@class='modal-content']/div[@class='modal-footer']/button[text()='Crop']", LocatorType.XPath); }

    //Details - Driver Picture Upload - Remove File Link
    public WebElement Link_RemoveFile () { return findElement("//div[@id='dropzone1']/div/a[text()='Remove file']", LocatorType.XPath); }

    //Details - More Details - Social Security Number
    public WebElement TextBox_SSN () { return findElement("DriverDetails_SocialSecurityNumber", LocatorType.Id); }

    //Details - More Details - Social Security Number - Error
    public WebElement Err_SSN () { return findElement("DriverDetails_SocialSecurityNumber-error", LocatorType.Id); }

    //Details - More Details - Birthday
    public WebElement TextBox_Birthday () { return findElement("DriverDetails_DateOfBirth", LocatorType.Id); }

    //Details - More Details - Birthday - Error
    public WebElement Err_Birthday () { return findElement("DriverDetails_DateOfBirth-error", LocatorType.Id); }

    //Details - More Details - How’d you hear about us?
    public WebElement DropDown_Info () { return findElement("DriverDetails_ReferralSource", LocatorType.Id); }

    //Details - "I consent to background and driving record checks" checkbox
    public WebElement CheckBox_Consent () { return findElement("//input[@id='DriverDetails.HasBackgroundConsent']/following-sibling::label", LocatorType.XPath); }

    //Details - Next Button
    public WebElement Button_DetailsNext () { return findElement("btnAddInfo", LocatorType.Id); }
}
