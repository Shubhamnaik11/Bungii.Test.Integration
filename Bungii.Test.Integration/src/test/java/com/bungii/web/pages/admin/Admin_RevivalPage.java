package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_RevivalPage extends PageBase {

    public WebElement Button_Confirm() { return findElement("//button[text()='Confirm']", LocatorType.XPath); }

    public WebElement Button_Cancel() { return findElement("//button[text()='Cancel']", LocatorType.XPath); }

    public WebElement Label_HeaderPopup() { return findElement("//p[text()='Are you sure you want to revive the trip?']", LocatorType.XPath); }

    public WebElement Label_PickupOrigin() { return findElement("revive-pickup-customer-type", LocatorType.Id); }

    public WebElement Label_PickupId() { return findElement("revive-pickup-id", LocatorType.Id); }

    public WebElement Label_PickupCustomer() { return findElement("revive-pickup-customer", LocatorType.Id); }

    public WebElement Label_PickupPartnerPortal() { return findElement("revive-pickup-partner-portal", LocatorType.Id); }

    public WebElement Link_ChangeDeliveryStatus(boolean...ignoreException) { return findElement("//tr/td/a/img", LocatorType.XPath,ignoreException); }


    public WebElement DropDown_DeliveryStatus() { return findElement("txtNewStatus", LocatorType.Id); }

    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//select/option[text() =\"%s\"]",status), LocatorType.XPath); }

    public WebElement DropDown_DeliveryStatusReason() { return findElement("txtNewStatusReason", LocatorType.Id);}

    public WebElement Text_DeliveryStatusReason(String statusReason) { return findElement(String.format("//div/select/option[text() =\"%s\"]",statusReason), LocatorType.XPath); }

    public WebElement Textbox_CommentForStatus() { return findElement("reason-other-comments mt1", LocatorType.Id);}

    public WebElement Button_ConfirmStatus() { return findElement("//div[@class=\"modal-footer\"]/p/following-sibling::button[2]", LocatorType.XPath);}

    public WebElement Text_SuccessMessage() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[1]", LocatorType.XPath);}

    public WebElement Button_CloseStatus() { return findElement("//div[@id=\"edit-status-success-modal\"]/div/div/div[2]/button", LocatorType.XPath);}

}
