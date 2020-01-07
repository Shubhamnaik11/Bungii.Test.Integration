package com.bungii.web.utilityfunctions;

import com.bungii.SetupManager;
import com.bungii.common.utilities.EmailUtility;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.common.utilities.RandomGeneratorUtility;
import com.bungii.web.manager.ActionManager;
import com.bungii.web.pages.admin.Admin_LoginPage;
import com.bungii.web.pages.driver.Driver_DashboardPage;
import com.bungii.web.pages.driver.Driver_LoginPage;
import com.bungii.web.pages.driver.Driver_RegistrationPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.bungii.common.manager.CucumberContextManager;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneralUtility {
    Driver_LoginPage Page_Driver_Login = new Driver_LoginPage();
    Driver_RegistrationPage Page_Driver_Reg = new Driver_RegistrationPage();
    DbUtility dbUtility = new DbUtility();
    ActionManager action = new ActionManager();
    Driver_DashboardPage driver_dashboardPage = new Driver_DashboardPage();
    Admin_LoginPage Page_AdminLogin = new Admin_LoginPage();
    EmailUtility emailUtility = new EmailUtility();

    private String GetDriverUrl()
{
    String driverURL = null;
    String environment = PropertyUtility.getProp("environment");
    if (environment.equalsIgnoreCase("DEV"))
        driverURL = PropertyUtility.getDataProperties("dev.driver.url");
    if (environment.equalsIgnoreCase("QA") ||environment.equalsIgnoreCase("QA_AUTO") )
        driverURL = PropertyUtility.getDataProperties("qa.driver.url");
    if (environment.equalsIgnoreCase("STAGE"))
        driverURL = PropertyUtility.getDataProperties("stage.driver.url");
    return driverURL;
}
    private String GetAdminUrl()
    {
        String adminURL = null;
        String environment = PropertyUtility.getProp("environment");
        if (environment.equalsIgnoreCase("DEV"))
            adminURL = PropertyUtility.getDataProperties("dev.admin.url");
        if (environment.equalsIgnoreCase("QA")||environment.equalsIgnoreCase("QA_AUTO"))
            adminURL = PropertyUtility.getDataProperties("qa.admin.url");
        if (environment.equalsIgnoreCase("STAGE"))
            adminURL = PropertyUtility.getDataProperties("stage.admin.url");
        return adminURL;
    }
    public void DriverLogin(String Phone, String Password) {
        String driverURL = GetDriverUrl();

        action.navigateTo(driverURL);
        action.click(Page_Driver_Login.Tab_LogIn());
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Phone(), Phone);
        action.clearSendKeys(Page_Driver_Login.TextBox_DriverLogin_Password(), Password);
        action.click(Page_Driver_Login.Button_DriverLogin());
    }
    public void NavigateToDriverLogin() {
        String driverURL = GetDriverUrl();
        action.deleteAllCookies();
        action.navigateTo(driverURL);
    }

    public void AdminLogin() {
        String adminURL = GetAdminUrl();

        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.user"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.password"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }
    public void TestAdminLogin() {
        String adminURL = GetAdminUrl();

        action.navigateTo(adminURL);
        action.sendKeys(Page_AdminLogin.TextBox_Phone(), PropertyUtility.getDataProperties("admin.testuser"));
        action.sendKeys(Page_AdminLogin.TextBox_Password(), PropertyUtility.getDataProperties("admin.testpassword"));
        action.click(Page_AdminLogin.Button_AdminLogin());
    }
    public void DriverLogout() {
        action.click(driver_dashboardPage.Link_Logout());
    }


    public String generateMobileNumber() {

        String phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");
        while (!DbUtility.isPhoneNumberUnique(phoneNumber)) {
            phoneNumber = RandomGeneratorUtility.getData("{RANDOM_PHONE_NUM}");

        }
        return phoneNumber;
    }


    public void addImageInDropZone(WebElement dropZoneWebelement, String filePath) {

        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, filePath);

    }
    public void addCSV(WebElement csvWebelement, String filePath) {

        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", csvWebelement);
        action.sendKeys(csvWebelement, filePath);

    }
    public void addImageInDropZone(WebElement dropZoneWebelement, String[] filePath) {
        String inputFilesString = "";
        for (String file : filePath) {
            inputFilesString = inputFilesString + " \n " + file;
        }
        ((JavascriptExecutor) SetupManager.getDriver()).executeScript(" arguments[0].style.visibility = 'visible';", dropZoneWebelement);
        action.sendKeys(dropZoneWebelement, inputFilesString.trim());

    }
    public String GetUniqueLastName()
    {
        String Lastname= RandomGeneratorUtility.getData("{RANDOM_STRING}", 4);
        return Lastname;

    }

    public static String GetFormattedString(String string, String word)
    {
        // Check if the word is present in string
        // If found, remove it using removeAll()
        if (string.contains(word)) {
            string = string.replaceAll(word, "");
        }

        // Return the resultant string
        return string;
    }

    public String GetUniqueFutureDate()
    {
        String newDate=null;
        String DATE_FORMAT = "MM/dd/yyyy";
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

        // Get current date
        Date currentDate = new Date();
        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 1 to 12 for months and 1 to 30 for days
        int months = rand.nextInt(12);
        int days = rand.nextInt(30);

        // convert date to localdatetime
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        // plus one
        localDateTime = localDateTime.plusYears(1).plusMonths(months).plusDays(days);
        localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);

        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        newDate=dateFormat.format(currentDatePlusOneDay);
        return newDate;
    }

    public String GetDateInFormat(String DateToFormat, String FromFormat, String ToFormat)
    {
        //date format you will get is e.g. Nov 21, 2020
        String newDateFormat=null;
        try{
            String start_dt = DateToFormat;
            DateFormat formatter = new SimpleDateFormat(FromFormat);
            Date date = (Date)formatter.parse(start_dt);
            SimpleDateFormat newFormat = new SimpleDateFormat(ToFormat);
            newDateFormat = newFormat.format(date);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newDateFormat;
    }

    public String GenerateSpecialCharString(){
        int RANDOM_STRING_LENGTH = 5;
        String CHAR_LIST ="$&#@!%?+";
        StringBuffer randStr = new StringBuffer();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = GetRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        System.out.println("Sting of special characters: "+randStr);
        return randStr.toString();
    }

    private int GetRandomNumber() {
         String CHAR_LIST ="$&#@!%?+";
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
    public String GetSpecificPlainTextEmailIfReceived(String expectedFromAddress,String expectedToAddress, String expectedSubject) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress,  expectedSubject, 1);
            System.out.println("No of Total recent Messages : " +recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");
            boolean emailFound = false;
            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("*****************************************************************************");
                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i-1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                System.out.println("Plain text: " + emailUtility.getTextFromMessage(msg));
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.equals(expectedSubject)) &&(msg.getAllRecipients()[0].toString().contains(expectedToAddress)) ) {
                   // String EmailContent = msg.getContent().toString();
                    //testStepAssert.isEquals(expectedEmailContent, EmailContent,"Email Content should match", "Email content matches", "Email content doesn't match");
                  //  Document emailContent=Jsoup.parse(EmailContent);
                    //emailFound = true;
                  //  break;
                    return emailUtility.readPlainContent((javax.mail.internet.MimeMessage) msg);
                }
            }
            return null;
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
            return null;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
    public void GetSpedificMultipartTextEmailIfReceived(String expectedFromAddress,String expectedToAddress, String expectedSubject, String expectedEmailContent) {

        try {
            Message[] recentMessages = emailUtility.getEmailObject(expectedFromAddress, expectedToAddress, expectedSubject, 1);
            System.out.println("No of Total recent Messages : " + recentMessages.length);
            String fromAddress = PropertyUtility.getEmailProperties("email.from.address");

            for (int i = recentMessages.length; i > 0; i--) {

                System.out.println("*****************************************************************************");
                System.out.println("MESSAGE " + (i) + ":");
                Message msg = recentMessages[i - 1];
                System.out.println(msg.getMessageNumber());
                String subject = msg.getSubject();//important value

                System.out.println("Subject: " + subject);
                System.out.println("From: " + msg.getFrom()[0]);
                System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                System.out.println("Date: " + msg.getReceivedDate());
                //  System.out.println("Message with Multipart: " + getText(msg));

                //  readLineByLineJava8("D:\\Bungii-QA-Automation\\Bungii.Test.Integration\\src\\main\\resources\\EmailTemplate\\BungiiReceipt.txt", getText(msg));
                //System.out.println("Size: "+msg.getSize());
                //System.out.println(msg.getFlags());
                if ((msg.getFrom()[0].toString().contains(fromAddress)) && (subject.equals(expectedSubject)) && (msg.getAllRecipients()[0].toString().contains(expectedToAddress))) {
                    String EmailContent = msg.getContent().toString();
                    // System.out.println("Email Found!!!\nEmail Content: \n" + EmailContent);//need to get extract link value from here
                    //Invoke jSoupHTMLToString object
                    Document emailContent = Jsoup.parse(EmailContent);

                }


            }

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
