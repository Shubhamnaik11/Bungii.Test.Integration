package com.bungii.web.pages.admin;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class Admin_ReferralSourcePage extends PageBase {

    public WebElement AptComplex_AccCreated () { return findElement("//td[text()='Apt Complex']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement AptComplex_PercentAccCreated () { return findElement("//td[text()='Apt Complex']/following-sibling::td[2", LocatorType.XPath); }
    //------------------
    public WebElement Blimp_AccCreated () { return findElement("//td[text()='Blimp']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Blimp_PercentAccCreated () { return findElement("//td[text()='Blimp']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Craigslist_AccCreated () { return findElement("//td[text()='Craigslist']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Craigslist_PercentAccCreated () { return findElement("//td[text()='Craigslist']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement EstateSale_AccCreated () { return findElement("//td[text()='Estate Sale']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement EstateSale_PercentAccCreated () { return findElement("//td[text()='Estate Sale']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Event_AccCreated () { return findElement("//td[text()='Event']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Event_PercentAccCreated () { return findElement("//td[text()='Event']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Facebook_AccCreated () { return findElement("//td[text()='Facebook']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Facebook_PercentAccCreated () { return findElement("//td[text()='Facebook']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Google_AccCreated () { return findElement("//td[text()='Google']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Google_PercentAccCreated () { return findElement("//td[text()='Google']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement NewsStory_AccCreated () { return findElement("//td[text()='News Story']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement NewsStory_PercentAccCreated () { return findElement("//td[text()='News Story']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Other_AccCreated () { return findElement("//td[text()='Other']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Other_PercentAccCreated () { return findElement("//td[text()='Other']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement Store_AccCreated () { return findElement("//td[text()='Store']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement Store_PercentAccCreated () { return findElement("//td[text()='Store']/following-sibling::td[2]", LocatorType.XPath); }
    //------------------
    public WebElement WordOfMouth_AccCreated () { return findElement("//td[text()='Word Of Mouth']/following-sibling::td[1]", LocatorType.XPath); }

    public WebElement WordOfMouth_PercentAccCreated () { return findElement("//td[text()='Word Of Mouth']/following-sibling::td[2]", LocatorType.XPath); }
}
