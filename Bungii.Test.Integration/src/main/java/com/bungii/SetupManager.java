package com.bungii;

import com.bungii.common.manager.CucumberContextManager;
import com.bungii.common.manager.DriverManager;
import com.bungii.common.utilities.FileUtility;
import com.bungii.common.utilities.LogUtility;
import com.bungii.common.utilities.ParseUtility;
import com.bungii.common.utilities.PropertyUtility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class SetupManager extends EventFiringWebDriver {
    private static LogUtility logger = new LogUtility(SetupManager.class);
    private static JSONObject jsonParsed,jsonCaps;
    private static WebDriver driver =null;
    private static String APPIUM_SERVER_IP;
    private static SetupManager setupManager;
    private static String TARGET_PLATFORM;
    private static AppiumDriverLocalService service = null;
    private static final Thread CLOSE_THREAD = new Thread() {

        @Override
        public void run() {
            driver.quit();
        }
    };
    static {
        TARGET_PLATFORM= PropertyUtility.getProp("target.platform");
        logger.detail("TARGET_PLATFORM"+TARGET_PLATFORM);
        APPIUM_SERVER_IP = PropertyUtility.getProp("server");
        if(TARGET_PLATFORM.equalsIgnoreCase("IOS")|| TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
            String deviceID = System.getProperty("DEVICE");
            String APPIUM_SERVER_PORT = String.valueOf(returnPortNumber(deviceID));
            if(TARGET_PLATFORM.equalsIgnoreCase("IOS")){
                driver = (IOSDriver<MobileElement>) startAppiumDriver(getCapabilities(deviceID),APPIUM_SERVER_PORT);
                if(getCapabilities(deviceID).getCapability("app").toString().contains("customer"))
                    CucumberContextManager.getObject().setFeatureContextContext("CURRENT_APPLICATION","CUSTOMER");
                else
                    CucumberContextManager.getObject().setFeatureContextContext("CURRENT_APPLICATION","DRIVER");

            }
            else if(TARGET_PLATFORM.equalsIgnoreCase("ANDROID")) {
                System.out.println("PORT"+APPIUM_SERVER_PORT+"");
                driver = (AndroidDriver<MobileElement>) startAppiumDriver(getCapabilities(deviceID), APPIUM_SERVER_PORT);
            }
        }
        else if(TARGET_PLATFORM.equalsIgnoreCase("WEB"))
            driver = createWebDriverInstance(PropertyUtility.getProp("default.browser"));
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);


        DriverManager.getObject().setPrimaryInstanceKey("ORIGINAL");
        DriverManager.getObject().storeDriverInstance("ORIGINAL",driver);
        DriverManager.getObject().setDriver(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    /**
     * Make class singleton, one instance of driver is shared with all the class
     */
    private SetupManager() {
        super(driver);
    }

    public static SetupManager getObject(){
        /*This logic will ensure that no more than
         * one object can be created at a time */
        if(setupManager==null){
            setupManager= new SetupManager();
        }
        return setupManager;
    }
    /**
     * Create appium server url
     * @return Appium server url
     */
    public static String getAppiumServerURL(String portNumber){
        if (APPIUM_SERVER_IP.equalsIgnoreCase("localhost")||APPIUM_SERVER_IP.equals("")||APPIUM_SERVER_IP.equals("0.0.0.0"))
            APPIUM_SERVER_IP="127.0.0.1";
        if(checkIfServerIsRunnning(portNumber)) {
            stopAppiumServer();
        }
            //Start server only if its localhost
            if( APPIUM_SERVER_IP=="127.0.0.1")
                startAppiumServer(APPIUM_SERVER_IP, portNumber);


        return "http://"+APPIUM_SERVER_IP+":"+portNumber+"/wd/hub";
    }
    public static void startAppiumServer(String APPIUM_SERVER_IP, String portNumber){

        //Build the Appium service
        AppiumServiceBuilder  builder = new AppiumServiceBuilder();
        builder.withIPAddress(APPIUM_SERVER_IP);
        builder.usingPort(Integer.parseInt(portNumber));
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();

    }
    public static void stopAppiumServer() {
        if (service!= null)
        service.stop();
    }

    public static boolean checkIfServerIsRunnning(String port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Integer.parseInt(port));
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
    /**
     * Return IOS driver instance
     * @return ios driver instance
     */
    public static WebDriver getDriver() {
       // return APPIUM_DRIVER;
        return  DriverManager.getObject().getDriver();
    }

    public static void setDriver(WebDriver newDriver) {
        //PageBase.driver=APPIUM_DRIVER;
        DriverManager.getObject().setDriver(newDriver);
        driver= driver;
    }

    public void  useDriverInstance(String instanceKey){
        DriverManager.getObject().useDriverInstance(instanceKey);
    }

    public static WebDriver createWebDriverInstance(String browser){
        WebDriver driver=null;
        switch(browser.toUpperCase()){
            case "CHROME":
                DesiredCapabilities capabilities = getChromeDesiredCapabilities();
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(FileUtility.getSuiteResource("","src/main/resources/BrowserExecutables/chromedriver.exe")))
                        .usingAnyFreePort()
                        .build();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.merge(capabilities);
                driver = new ChromeDriver(service, options);
                break;
            default:
                logger.error("webdriver method for "+browser+"is not implemented ");
        }

        return driver;
    }

    public AppiumDriver<MobileElement> createAdditionAppiumDriver(String serverPort, DesiredCapabilities capabilities) throws MalformedURLException{
        String appiumServerUrl=getAppiumServerURL(serverPort);
        return new AppiumDriver<>(new URL(appiumServerUrl), capabilities);
    }

    /**
     * Create new appium driver instance as per setting in config file and assign it to variable
     */
    public void createNewAppiumInstance(String key,String deviceId) throws MalformedURLException{

        String appiumPortNumber =String.valueOf(returnPortNumber(deviceId));
        DesiredCapabilities capabilities= getCapabilities(deviceId);
        AppiumDriver<MobileElement> newDriverInstance = null;

        if(TARGET_PLATFORM.equalsIgnoreCase("IOS")){
            IOSDriver<MobileElement> iosDriverInstance=(IOSDriver<MobileElement>)startAppiumDriver(capabilities,appiumPortNumber);
            iosDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            DriverManager.getObject().storeDriverInstance(key, iosDriverInstance);
        }else{
            newDriverInstance = createAdditionAppiumDriver( appiumPortNumber,capabilities);
            newDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);
            DriverManager.getObject().storeDriverInstance(key, newDriverInstance);
        }
    }



    /**
     * Create new appium driver instance as per setting in config file and assign it to variable
     */
    public void createNewAndroidInstance(String key,String deviceId) throws MalformedURLException{

        String appiumPortNumber =String.valueOf(returnPortNumber(deviceId));
        DesiredCapabilities capabilities= getCapabilities(deviceId);
        AndroidDriver<MobileElement> newDriverInstance = null;

        String appiumServerUrl=getAppiumServerURL(appiumPortNumber);
        newDriverInstance =  new AndroidDriver<MobileElement>(new URL(appiumServerUrl), capabilities);
        newDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);

        DriverManager.getObject().storeDriverInstance(key, newDriverInstance);
    }

    private static DesiredCapabilities getChromeDesiredCapabilities() {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-web-security");
        chromeOptions.addArguments("--test-type");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-infobars");
        capabilities.setCapability("chrome.verbose", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return capabilities;
    }

    /**
     * Create new webdriver instance
     * @param key instance name that is to be saved
     * @param browser type of browser
     */
    public void createNewWebdriverInstance(String key, String browser) {
        WebDriver newWebDriverInstance = createWebDriverInstance(browser);
        newWebDriverInstance.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyUtility.getProp("implicit.wait")), TimeUnit.SECONDS);

        DriverManager.getObject().storeDriverInstance(key, newWebDriverInstance);
    }
    /**
     * Start and return appium driver instance
     * @return appium driver instance
     */
    private static WebDriver startAppiumDriver(DesiredCapabilities capabilities,String portNumber) {
        try {
            String appiumServerUrl=getAppiumServerURL(portNumber);
            if(TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
                driver = new AndroidDriver<MobileElement>(new URL(appiumServerUrl), capabilities);
            else
                driver = new IOSDriver<MobileElement>(new URL(appiumServerUrl), capabilities);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
    /**
     * @param deviceId Device key whose capabilities need to be fetch from JSON
     * @return
     */
    public static DesiredCapabilities getCapabilities(String deviceId){
        String deviceInfoFileKey="";
        if(TARGET_PLATFORM.equalsIgnoreCase("IOS"))
            deviceInfoFileKey="ios.capabilities.file";
        else if(TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
            deviceInfoFileKey="android.capabilities.file";

        DesiredCapabilities capabilities= new DesiredCapabilities();
        String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"),PropertyUtility.getFileLocations(deviceInfoFileKey));

        ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
        jsonParsed = jsonParser.getObjectFromJSON();
        jsonCaps=jsonParsed.getJSONObject(deviceId);
        Iterator<String> keys = jsonCaps.keys();

        while(keys.hasNext()) {
            String key = keys.next();
            //TODO check key type , then verify and add
            capabilities.setCapability(key, jsonCaps.get(key));
        }
        logger.detail("return DesiredCapabilities for device "+deviceId +"as "+capabilities.toString());
        return capabilities;
    }

    /**
     * @param deviceId Device key whose port Number on which Appium server is running is to fetches
     * @return
     */
    public static int returnPortNumber(String deviceId){
        String deviceInfoFileKey="";
        if(TARGET_PLATFORM.equalsIgnoreCase("IOS"))
            deviceInfoFileKey="ios.capabilities.file";
        else if(TARGET_PLATFORM.equalsIgnoreCase("ANDROID"))
            deviceInfoFileKey="android.capabilities.file";

        logger.detail("deviceInfoFileKey="+deviceInfoFileKey);
        String capabilitiesFilePath = FileUtility.getSuiteResource(PropertyUtility.getFileLocations("capabilities.folder"),PropertyUtility.getFileLocations(deviceInfoFileKey));

        ParseUtility jsonParser = new ParseUtility(capabilitiesFilePath);
        jsonParsed = jsonParser.getObjectFromJSON();
        jsonCaps=jsonParsed.getJSONObject("connection");
        //try to fetch json value for devices , if none is present  it will throw JSONException  exception and return 0
        try{
            return (int) jsonCaps.get(deviceId);
        }
        catch(Exception e){

            logger.error("NOT able to fetch port number from JSON file . Please  verify key "+deviceId +" in JSON file");
            return 0;
        }

    }

    /*
     * @Override method of closing driver instance
     * (non-Javadoc)
     * @see org.openqa.selenium.support.events.EventFiringWebDriver#close()
     */
    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException(
                    "You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

}
