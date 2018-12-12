package com.bungii.ios.stepdefinitions.admin;

import com.bungii.common.core.DriverBase;
import com.bungii.ios.manager.ActionManager;
import com.bungii.ios.pages.admin.PromoCodePage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PromoCodeSteps extends DriverBase {
    PromoCodePage promosPage;
    ActionManager action = new ActionManager();
    public PromoCodeSteps(PromoCodePage promosPage) {
        this.promosPage = promosPage;
    }

    @Then("^I get promo code for \"([^\"]*)\"$")
    public void iGetPromoCodeFor(String codeType) throws Throwable {
        switch (codeType.toLowerCase()) {
            case "referral":
                cucumberContextManager.setFeatureContextContext("REFERRAL", getPromoCode(codeType));
                break;
            case "valid":
                cucumberContextManager.setFeatureContextContext("VALID", getPromoCode(codeType));
                break;
            case "promo":
                cucumberContextManager.setFeatureContextContext("PROMO", getPromoCode(codeType));
                break;
            case "expired":
                cucumberContextManager.setFeatureContextContext("EXPIRED", getPromoCode(codeType));
                break;
            case "one off":
                cucumberContextManager.setFeatureContextContext("ONE_OFF", getPromoCode(codeType));
                break;
            case "used one off":
                cucumberContextManager.setFeatureContextContext("USED_ONE_OFF", getPromoCode(codeType));
                break;
            case "unused one off":
                cucumberContextManager.setFeatureContextContext("UNUSED_ONE_OFF", getPromoCode(codeType));
                break;

            default:
                break;
        }
    }

    /**
     * Find required promocode and return list of it
     * @param key type of promocode that is to be searched
     * @return list of promocode for input category
     */
    public List<String> getPromoCode(String key ){
        List <String> codeList =new ArrayList<String>();
        if( !promosPage.Text_ActivePageNumber().getText().equals("1"))
            promosPage.Button_Previouspage().click();
        while(codeList.size()<=5){
            List<WebElement> codes =new ArrayList<WebElement>() ;
            switch (key.toLowerCase()) {
            case "referral":
                codes =promosPage.Text_ReferralCode();
                break;
            case "one off":
                codes =promosPage.Text_OneOffCode();
                break;
            case "used one off":
                codes =promosPage.Text_UsedOneOffCode();
                break;
            case "unused one off":
                codes =promosPage.Text_UnUsedOneOffCode();
                break;
            case "valid":
            case "promo":
                codes =promosPage.Text_PromoCode();
                break;
            case "expired":
                codes =promosPage.Text_ExpiredPromoCode();
                break;
            default:
                break;
        }
            for(WebElement code : codes){
                codeList.add(code.getText());
            }
            promosPage.Button_Nextpage().click();
            promosPage.waitForPageLoad();
            //  action.invisibilityOfElementLocated(promosPage.Loadder());
        }
        return codeList;
    }

    //.getText()
    /**
     * Go to page 1 and try searching for code
     * @param elements locator for searching code
     * @return list of promocode
     */
    private List <String> getCode( List <WebElement> elements){
        List <String> codeList =new ArrayList<String>();
        if( !promosPage.Text_ActivePageNumber().getText().equals("1"))
            promosPage.Button_Previouspage().click();

        while(codeList.size()<=5){
            List<WebElement> codes = elements;
            for(WebElement code : codes){
                codeList.add(code.getText());
            }
            promosPage.Button_Nextpage().click();
            promosPage.waitForPageLoad();
          //  action.invisibilityOfElementLocated(promosPage.Loadder());
        }
        return codeList;
    }
}
