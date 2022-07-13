package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Admin_ScheduledTripsPage extends PageBase {

    public WebElement Menu_ScheduledTrips () { return findElement("adminmenu-scheduledtrips", LocatorType.Id); }

    public WebElement Menu_AllTrips () { return findElement("adminmenu-completedtrips", LocatorType.Id); }

    //public WebElement Dropdown_Geofence () { return findElement("drpGeofence", LocatorType.Id); }

    public WebElement Button_Submit () { return findElement("CustomerCancel", LocatorType.Name); }

    public WebElement Textbox_CancellationFee () { return findElement("txtCancellationFee", LocatorType.Id); }

    public WebElement Label_Drop_Off_Location () { return findElement("//p[contains(text(),'Drop Off Location:')]",LocatorType.XPath);}

    public WebElement Label_Pickup_Location () { return findElement("//p[contains(text(),'Pickup Location:')]",LocatorType.XPath);}

    public WebElement Label_Market () { return findElement("//th[text()='Market']",LocatorType.XPath);}

    public WebElement Label_Delivery_Portal () { return findElement("//th[text()='Delivery Type']",LocatorType.XPath);}

    public WebElement Button_Edit_Drop_Off_Address () { return findElement("//img[@title='Edit drop off location']",LocatorType.XPath);}

    public WebElement Button_Edit_Pickup_Address () { return findElement("//img[@title='Edit pickup location']",LocatorType.XPath);}


    //public WebElement Admin_Dropdown_ServiceLevel(String serviceLevel) { return findElement("//li/div/div/span[@class='service-title' and @data-name='"+serviceLevel+"']",LocatorType.XPath);}
    public WebElement Admin_Dropdown_ServiceLevel() { return findElement("ddServiceLevelOption",LocatorType.Id);}

    public WebElement Link_Grid_First_Row() { return findElement("//tr[@id='row1']/td[4]/a",LocatorType.XPath);}
    public WebElement Textbox_Drop_Off_Location () { return findElement("PickupDetails_DestinationAddress",LocatorType.Id);}
    public WebElement Textbox_Pickup_Location () { return findElement("PickupDetails_PickupOriginAddress",LocatorType.Id);}

    //public WebElement FirstAddressDropdownResult () { return findElement("//div[@id='divPlacesResult']/div[1]",LocatorType.XPath);}

    public WebElement DropdownResult (String address) { return findElement(String.format("//div[@id='divPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}

    public WebElement DropdownPickupResult (String address) { return findElement(String.format("//div[@id='divPickupPlacesResult']/div[contains(.,'%s')]",address),LocatorType.XPath);}

    public WebElement DropOff_Address() { return findElement("//label[@id='lblDestinationAddress']",LocatorType.XPath);}

    public WebElement Pickup_Address() { return findElement("//label[@id='lblPickupAddress']",LocatorType.XPath);}

    public WebElement Textbox_CancellationComment () { return findElement("txtCustomerCancellationComments", LocatorType.Id); }

    public WebElement Dropdown_CancellationReason () { return findElement("txtCancellationRemark", LocatorType.Id); }

    public WebElement Dropdown_Reason () { return findElement("ddEditDeliveryRemark", LocatorType.Id); }

    //changed by Richa
    //public WebElement RadioButton_CancelBungii () { return findElement("//span[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::input", LocatorType.XPath); }
    public WebElement RadioButton_CancelBungii () { return findElement("//span[text()='Cancel entire Bungii and notify driver(s)']/preceding-sibling::span", LocatorType.XPath); }

    public WebElement RadioButton_RemoveDriver () { return findElement("//span[text()='Remove driver(s) and re-search']/preceding-sibling::span", LocatorType.XPath); }//richa

    //public WebElement Button_RemoveDrivers () { return findElement("(//input[@value='Remove Driver(s)'])[2]", LocatorType.XPath); }
    public WebElement Button_RemoveDrivers () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Remove')]", LocatorType.XPath); }//Richa

    //public WebElement Button_Research () { return findElement("(//input[@value='Re-search'])[2]", LocatorType.XPath); }
    public WebElement Button_Research () { return findElement("//div[@id='tripDriverDetails']//strong[contains(text(),'Re-search a driver')]", LocatorType.XPath); }//Richa

    //public WebElement Checkbox_driver (String driver) { return findElement("(//div/label[contains(.,'"+driver+"')])[2]/input[1]", LocatorType.XPath); }
    public WebElement Checkbox_driver () { return findElement("//div[@id='tripDriverDetails']//span[@class='checkmark'][1]", LocatorType.XPath); }//richa

    public WebElement Label_SuccessMessage () { return findElement("SuccessMessage", LocatorType.Id); }
    //changed by Richa
    public WebElement Label_CancelSuccessMessage () { return findElement("//p[@id='cancel-success-message']/i[2]", LocatorType.XPath); }

    public WebElement Label_CancelSuccessMessageLive () { return findElement("//p[@id='delivery-cancelled-success-message']/i[2]", LocatorType.XPath); }

    public WebElement Label_DeliverySuccessMessageLive()  { return findElement("//p[@id='delivery-completed-success-message']/i[2]", LocatorType.XPath); }

    public WebElement Dropdown_SearchForPeriod () { return findElement("SearchForPeriod", LocatorType.Name); }

    public WebElement Checkbox_NonControlDriver () { return findElement( "//div[@id='tripDriverDetails']/div[1]/label[1]/input", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriver () { return findElement( "//div[@id='tripDriverDetails']/div[2]/label[1]/input", LocatorType.XPath);}

    public WebElement Checkbox_ControlDriverEdit () { return findElement( "//*[@id='editTripDrivers']/tbody/tr[1]/td//input", LocatorType.XPath);}

    public WebElement Checkbox_NonControlDriverEdit () { return findElement( "//*[@id='editTripDrivers']/tbody/tr[2]/td//input", LocatorType.XPath);}

    public WebElement Button_RemoveDriversEdit () { return findElement("btnRemoveDriver", LocatorType.Id); }//Richa

    public WebElement Label_DriverRemovalSuccessMessage () { return findElement( "//p/i[text()='Driver(s) removed successfully']" , LocatorType.XPath); }

    public WebElement Button_Close () { return findElement("//button[@class='close']", LocatorType.XPath);}

    public WebElement Estimated_Distance_Cost () { return findElement("",LocatorType.XPath);}

    public WebElement Textbox_Search () { return findElement("SearchCriteria", LocatorType.Id); }
    public WebElement Button_Search(){return  findElement("btnSearch",LocatorType.Id);}

    public WebElement List_ViewDeliveries(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Delivery Details')]",LocatorType.XPath);}

    //Deivery details
    public WebElement Link_DeliveryDetails(){return  findElement("dLabel",LocatorType.Id);}

    public WebElement Text_Admin_CustomerName(){return  findElement("//tbody/tr[1]/td[7]/a",LocatorType.XPath);}

    public WebElement Text_Admin_TrackingId(){return  findElement("//div/h4[3]",LocatorType.XPath);}


    public WebElement Dropdown_LiveDelivery_Details(){return  findElement("//div/ul/li/a[text()=\"Delivery Details\"]",LocatorType.XPath);}
    public WebElement List_ViewEdit(){return  findElement("//td/div[@class='dropdown open']/ul/li/*[contains(text(),'Edit')]",LocatorType.XPath);}
    public WebElement Dropdown_Edit_DeliveryDetails(){return  findElement("//label/span[text()='Edit Delivery Details']",LocatorType.XPath);}

    public WebElement Link_Edit_dropOffLocation(){return  findElement("//div/img[@class=\"cursor editDropoffAddress\"]",LocatorType.XPath);}

    public WebElement Textbox_Edit_dropOfflocationAddress(){return  findElement("PickupDetails_DestinationAddress",LocatorType.Id);}

    public WebElement Text_SelectAdd(){return  findElement("//div[@data-name='Fort Lesley J. McNair']",LocatorType.XPath);}


    public WebElement RadioButton_EditDeliveryDetails(){return  findElement("exampleModalLongTitle",LocatorType.Id);}

    public WebElement Button_Edit_Verify(){return  findElement("//button[text() =\"VERIFY\"]",LocatorType.XPath);}

    public WebElement Button_Edit_Save(){return  findElement("//button[text() =\"SAVE\"]",LocatorType.XPath);}

    public WebElement Button_Edit_Close(){return  findElement("//div/button[@class =\"close\"]",LocatorType.XPath);}

    public WebElement Text_NewDropoffAddress(){return  findElement("lblDestinationAddress",LocatorType.Id);}


    public WebElement Text_Delivery_Scheduled(){return  findElement("//tbody/tr/td[text() ='Scheduled']",LocatorType.XPath);}

    public WebElement Text_Delivery_Successfull(){return  findElement("//tbody/tr/td[contains(text() ,\"Payment Successful\")]",LocatorType.XPath);}

    public WebElement Text_Delivery_TripStarted(){return  findElement("//tbody/tr/td[contains(text() ,\"Trip Started\")]",LocatorType.XPath);}
    public WebElement Dropdown_Notes_History(){return  findElement("showNotes",LocatorType.Id);}

    public WebElement Text_NotesEmpty_Message(){return  findElement("notes-tab",LocatorType.Id);}

    public WebElement Textbox_AddNote(){return  findElement("newNote",LocatorType.Id);}

    public WebElement Button_SaveNote(){return  findElement("saveNote",LocatorType.Id);}

    public WebElement Text_FirstSavedNote(){return  findElement("//div[1][@class =\"note\"]/div/p",LocatorType.XPath);}

    public WebElement Text_AdminName(){return  findElement("//div[2]/p[1]/strong",LocatorType.XPath);}

    public WebElement Text_AdminCreatedNote(){return  findElement("//div[1][@class =\"note\"]/h5",LocatorType.XPath);}

    public WebElement Text_Admin2Name(){return  findElement("//div[2][@class =\"note\"]/h5",LocatorType.XPath);}

    public WebElement Link_EditNote(){return  findElement("//div/a[text() =\"Edit\"]",LocatorType.XPath);}

    public WebElement Link_EditNote_NotDisplayed(boolean...ignoreException){return  findElement("//div/a[text() =\"Edit\"]",LocatorType.XPath,ignoreException); }

    public WebElement Link_DeleteNote(){return  findElement("//div/a[text() =\"Delete\"]",LocatorType.XPath);}

    public WebElement Link_ConfirmDeleteNote(){return  findElement("//div/a[text() =\"Yes\"]",LocatorType.XPath);}

    public WebElement Button_NoteClose(){return  findElement("//div[@id=\"CustomerServiceNotes\"]/div/button/span[text() =\"×\"]",LocatorType.XPath);}

    public WebElement Text_EditNote_TextArea(){return  findElement("//div[1]/div/textarea",LocatorType.XPath);}

    public WebElement Link_NoteUpdate(){return  findElement("//div/div/button[text() =\"Update\"]",LocatorType.XPath);}

    public WebElement Link_Notes(){return  findElement("//td/div/ul/li/p/span[text() =\"Notes\"]",LocatorType.XPath);}

    public List<WebElement> List_Notes(){return  findElements("//div[@class ='note']/div/p",LocatorType.XPath);}

    public List<WebElement> List_AllNotes(){return  findElements("//div[@class ='note']",LocatorType.XPath);}

    public WebElement Text_AdminCreatedNotes(int note) { return findElement(String.format("//div[%s][@class ='note']/div/p",note), LocatorType.XPath); }

    public WebElement Text_AdminNames(int admin) { return findElement(String.format("//div[%s][@class ='note']/h5",admin), LocatorType.XPath); }

    public WebElement Text_NoteTime(){return  findElement("//div[1][@class =\"note\"]/label",LocatorType.XPath);}

    public WebElement Text_DeliveryStatus(String status) { return findElement(String.format("//tbody/tr/td[contains(text() ,'%s')]",status), LocatorType.XPath); }

    public WebElement Button_View(){return  findElement("//div/button[text() =\"View\"]",LocatorType.XPath);}

    public WebElement Text_TripIndicator(boolean...ignoreException){return  findElement("//tr[@id=\"row1\"]/td[1]/label",LocatorType.XPath,ignoreException);}

    public WebElement Text_ScheduledTripDate(){return  findElement("//td[@class=\"no-word-break\"]/a",LocatorType.XPath);}

    public WebElement Link_EditScheduleTripCalenderNextMonth(){return  findElement("//div/a[2]/span[1]",LocatorType.XPath);}

    public WebElement Link_EditScheduleTripCalenderPreviousMonth(){return  findElement("//div/a[1]/span[1]",LocatorType.XPath);}

    public WebElement Link_NewScheduleDeliveryDate(String newDate){return  findElement(String.format("//tbody/tr/td/a[text()=\"%s\"]",newDate),LocatorType.XPath);}

}
