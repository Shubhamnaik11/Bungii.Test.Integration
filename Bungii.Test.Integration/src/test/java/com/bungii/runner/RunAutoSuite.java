package com.bungii.runner;

import com.bungii.common.utilities.ManageDevices;
import com.bungii.common.utilities.PropertyUtility;
import com.bungii.hooks.CucumberHooks;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.apache.commons.lang3.ArrayUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.util.Properties;

@CucumberOptions(features = "target/test-classes/features/ios", monochrome = true, tags = "@ios and @regression1", plugin = {
        "pretty", "html:target/cucumber-report/single",
        "json:target/cucumber-report/single/cucumber.json",
        "rerun:target/cucumber-report/single/rerun.txt", "com.bungii.common.utilities.CustomFormatter"},
        glue = {"com.bungii.android.stepdefinitions","com.bungii.api", "com.bungii.hooks"}
)
public class RunAutoSuite extends AbstractTestNGCucumberTests {
    CucumberHooks hooks;
    private static final String INITIAL_FILE_NAME="login";
    String lstallDevice;
    /**
     * @param device Device variable from maven
     */
    @Parameters({"test.Device", "test.Platform", "test.Environment", "test.Category","multiple.data.file","remote.adb.ip"})
    public RunAutoSuite(@Optional("device1") String device, @Optional("web") String Platform, @Optional("QA_AUTO") String environment, @Optional("underconst") String category,@Optional("false") String multipleLoginFile,@Optional("") String remoteAdbHost) {
        //Use below statement only in test runner running which are not suppose to run with maven ,
        //In case of maven logFilepath is set in maven set in POM.xml

        System.setProperty("LogFilePath", "Results");

        String ClassName = this.getClass().getSimpleName();
        lstallDevice=device;
        System.setProperty("ALL_DEVICES",lstallDevice);
        if (Platform.equalsIgnoreCase("ios") || Platform.equalsIgnoreCase("android")) {
            //if mutiple devices are pass from maven then get class number and use that device for running that class
            if (lstallDevice.split(",").length > 1) {

                String curentThreadNumber = ClassName.substring(8, 10);curentThreadNumber="01";
                if(curentThreadNumber.equals("01")){
                    ManageDevices.write("");
                    System.setProperty("DEVICE", lstallDevice.split(",")[0]);
                }else {
                    try {Thread.sleep(2000);} catch (InterruptedException e) {e.printStackTrace();}
                    boolean searchFreeDevice=true;
                    do{
                        try {Thread.sleep((long)(Math.random() * 2000));} catch (InterruptedException e) {e.printStackTrace();}
                        device= ManageDevices.readFile();
                        System.out.println("Searching for device for, class"+ClassName+ " Found "+device);
                        if(device!=null){
                            if(!device.isEmpty()){
                                String[] deviceList = device.split(",");
                                String deviceToBeUsed=deviceList[0];
                                System.setProperty("DEVICE", deviceToBeUsed);
                                System.out.println("Found device for, class"+ClassName+ " .Using "+deviceToBeUsed);
                                deviceList = ArrayUtils.remove(deviceList, 0);
                                String strDeviceList = String.join(",", deviceList);
                                ManageDevices.write(strDeviceList);
                                System.out.println("Updated device for, class"+ClassName+ "is  "+ManageDevices.readFile());
                                searchFreeDevice=false;
                            }
                        }
                        try {Thread.sleep(20000);} catch (InterruptedException e) {e.printStackTrace();}

                    }while (searchFreeDevice);
                }
            } else {
                System.setProperty("DEVICE", device);

            }
        }
        if(multipleLoginFile.trim().equalsIgnoreCase("true")){
           // ClassName="Parallel02IT";
        //    String threadNumber = ClassName.substring(8, 10);
        //    System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_"+threadNumber);
        //    System.out.println("LOGIN FILE :"+INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_"+threadNumber);

            switch (System.getProperty("DEVICE").toLowerCase()){
                case "device1":
                    System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME);
                    break;
                case "device2":
                    System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_01");
                    break;
                case "device3":
                    System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_02");
                    break;
                case "extra1":
                    System.setProperty("LOGIN_FILE",INITIAL_FILE_NAME+"_"+environment.toLowerCase()+"_03");
                    break;
            }
        }
        System.setProperty("runner.class", ClassName);
        //this is to update values from config value
        PropertyUtility.environment=environment;
        PropertyUtility.targetPlatform=Platform;
        System.setProperty("remoteAdbHost",remoteAdbHost);
        this.hooks = new CucumberHooks();

    }

    /**
     * @param resultFolder Result Folder variable from maven
     */
    @BeforeSuite
    @Parameters({"NameWithtimestamp", "test.Platform", "test.Environment"})
    public void start(@Optional("") String resultFolder, @Optional("web") String Platform, @Optional("QA") String environment) {
        //vishal[0102]:commented this as logic to update config properties is moved while reading property file in PropertyUtility class
        //TODO: Remove commented block
/*        Properties props = new Properties();

        String propsFileName = "./src/main/resources/UserProperties/config.properties";
        try {
            //first load old one:
            FileInputStream configStream = new FileInputStream(propsFileName);
            props.load(configStream);
            configStream.close();

            //modifies existing or adds new property
            props.setProperty("target.platform", Platform);
            props.setProperty("environment", environment);

            //save modified property file
            FileOutputStream output = new FileOutputStream(propsFileName);
            props.store(output, "");
            output.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        try {this.hooks.start(resultFolder); }catch (Exception e){
            afterSuite(); }
    }

    /**
     * After suite method
     *
     * @throws IOException
     */
    @AfterSuite
    public void afterSuite() {
        try {
            this.hooks.tearDown();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (PropertyUtility.targetPlatform.equalsIgnoreCase("ios") || PropertyUtility.targetPlatform.equalsIgnoreCase("android"))
            ManageDevices.afterSuiteManageDevice();
        /*    String ClassName = this.getClass().getSimpleName();

        String curentThreadNumber = ClassName.substring(8, 10);
        System.out.println(curentThreadNumber+"XXXXXXXXXXXXXXXXXXXX"+ManageDevices.readFile());
        if(curentThreadNumber.equals("01")){
            ManageDevices.write(lstallDevice);
        }else {
                if(ManageDevices.readFile().trim().equals("")) {
                    ManageDevices.write(System.getProperty("DEVICE"));
                }else {
                    ManageDevices.write(ManageDevices.readFile()+","+System.getProperty("DEVICE"));
                }
        }
        System.out.println(curentThreadNumber+"XXXXXXXXXXXXXXXXXXXX"+ManageDevices.readFile());*/
    }

}