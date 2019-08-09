package com.bungii.android.pages.customer;

import com.bungii.common.core.PageBase;
import org.openqa.selenium.WebElement;

public class InvitePage extends PageBase {

    //------Invite Page Elements-----------------------------------------------------------------
    public WebElement Header_Invite() { return findElement("//android.widget.TextView[@text='INVITE']", LocatorType.XPath); }

    public WebElement Invite_Logo() { return findElement("com.bungii.customer:id/invite_referral_imageview", LocatorType.Id); }

    public WebElement Invite_Title() { return findElement("com.bungii.customer:id/invite_referral_title", LocatorType.Id); }

    public WebElement Invite_Text() { return findElement("com.bungii.customer:id/invite_referral_description", LocatorType.Id); }

    public WebElement Invite_Code() { return findElement("com.bungii.customer:id/invite_referral_code_textview", LocatorType.Id); }

    public WebElement Button_Share() { return findElement("com.bungii.customer:id/invite_referral_share_button", LocatorType.Id); }

    //------Share to---------------------------------------------------------------------------
    public WebElement Share_Facebook() { return findElement("//android.widget.TextView[@text='Share on Facebook']", LocatorType.XPath); }

    public WebElement Share_Twitter() { return findElement("//android.widget.TextView[@text='Share on Twitter']", LocatorType.XPath); }

    public WebElement Share_Email() { return findElement("//android.widget.TextView[@text='Share by Email']", LocatorType.XPath); }

    public WebElement Share_TextMessage() { return findElement("//android.widget.TextView[@text='Share by Text Message']", LocatorType.XPath); }

    //------Share - Facebook App---------------------------------------------------------------
    public WebElement FBApp_Title() { return findElement("com.facebook.katana:id/title_view", LocatorType.Id); }

   // public WebElement FBApp_StatusText() { return findElement("com.facebook.katana:id/status_text", LocatorType.Id); }
   public WebElement FBApp_StatusText() { return findElement("//android.widget.EditText[@resource-id='com.facebook.katana:id/(name removed)']", LocatorType.XPath); }

    public WebElement FBApp_PreviewText() { return findElement("com.facebook.katana:id/link_attachment_context_text", LocatorType.Id); }

  //  public WebElement FBApp_PostLink() { return findElement("com.facebook.katana:id/button_share", LocatorType.Id); }

    public WebElement FBApp_PostLink(boolean ...ignoreException) { return findElement("//android.widget.Button[@content-desc=\"POST\"]", LocatorType.XPath,ignoreException); }
  //  public WebElement FBApp_PostLink(boolean ...ignoreException) { return findElement("//android.widget.ImageView[@content-desc=\"Set Album\"]/following-sibling::android.widget.TextView", LocatorType.XPath,ignoreException); }


    //------Share - Samsung Msg App-------------------------------------------------------------
    public WebElement Samsung_TextMsg_TextField() { return findElement("com.android.mms:id/edit_text_bottom", LocatorType.Id); }

    public WebElement TextMsg_TextField() { return findElement("com.android.mms:id/embedded_text_editor", LocatorType.Id); }

    //------Share - Gmail App-------------------------------------------------------------------
    public WebElement Gmail_Referral_Subject() { return findElement("com.google.android.gm:id/subject", LocatorType.Id); }

    public WebElement Gmail_Referral_Body() { return findElement("//*[@resource-id='com.google.android.gm:id/body_wrapper']/descendant::android.view.View", LocatorType.XPath); }

    //------Share - Twitter App-----------------------------------------------------------------
    public WebElement Twitter_Referral_Body(boolean ...ignoreException) { return findElement("//*[@resource-id='status']", LocatorType.XPath,ignoreException); }
    public WebElement Twitter_Referral_BodyChrome() { return findElement("android.widget.EditText", LocatorType.ClassName); }




    public WebElement Button_Back(){return findElement("//android.widget.ImageButton[@content-desc=\"Navigate up\"]",LocatorType.XPath);}

}
