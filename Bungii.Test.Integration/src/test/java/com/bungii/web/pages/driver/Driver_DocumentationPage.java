package com.bungii.web.pages.driver;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;

public class Driver_DocumentationPage extends PageBase {
    //Pickup Information - Blank field validation
    public WebElement Err_Documentation_AllBlank () { return findElement("summary3", LocatorType.Id); }

    //Documentation - driver’s License Image Upload
    public WebElement DropZone3_LicenseImage () { return findElement("//div[@id='dropzone3']", LocatorType.XPath); }

    //Documentation - driver’s License Image Upload - Remove file
    public WebElement Link_LicenseRemoveFile () { return findElement("//div[@id='dropzone3']/div/a[contains(text(),'Remove')]", LocatorType.XPath); }

    //Documentation - License number
    public WebElement TextBox_LicenseNumber () { return findElement("DriverDocument_DocumentNo", LocatorType.Id); }

    //Documentation - License number - Error
    public WebElement Err_LicenseNumber () { return findElement("DriverDocument_DocumentNo-error", LocatorType.Id); }

    //Documentation - License expiration
    public WebElement TextBox_LicenseExpiry () { return findElement("DriverDocument_LicenseExpiry", LocatorType.Id); }

    //Documentation - License expiration - Error
    public WebElement Err_LicenseExpiry () { return findElement("DriverDocument_LicenseExpiry-error", LocatorType.Id); }

    //Documentation - Insurance Image Upload
    public WebElement DropZone4_InsuranceImage () { return findElement("//div[@id='dropzone4']", LocatorType.XPath); }

    //Documentation - Insurance Image Upload - Remove file
    public WebElement Link_InsuranceRemoveFile () { return findElement("//div[@id='dropzone4']/div/a[contains(text(),'Remove')]", LocatorType.XPath); }

    //Documentation - Insurance expiration
    public WebElement TextBox_InsuranceExpiry () { return findElement("DriverDocument_InsuranceExpiry", LocatorType.Id); }

    //Documentation - Insurance expiration - Error
    public WebElement Err_InsuranceExpiry () { return findElement("DriverDocument_InsuranceExpiry-error", LocatorType.Id); }

    //Documentation - Next Button
    public WebElement Button_DocNext () { return findElement("btnDoc", LocatorType.Id); }

    public WebElement DropZoneHiddenFileTag_LicenseImage () { return findElement("//input[@class = 'dz-hidden-input'][3]", LocatorType.XPath); }

    //public WebElement DropZoneHiddenFileTag_InsuranceImagRe () { return findElement("//input[@class = 'dz-hidden-input'][last()]", LocatorType.XPath); }
    public WebElement DropZoneHiddenFileTag_InsuranceImage () { return findElement("//input[@class = 'dz-hidden-input'][3]", LocatorType.XPath); }

}
