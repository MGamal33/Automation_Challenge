package TestClasses;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

/**
 * Represents a configuration from json file
 */
public class Configuration {
    /**
     * the path of file to be read
     */
    private String jsonFilePath = "src\\test\\java\\configuration.json";
    /**
     * content inside the file
     */
    private JSONObject jsonContent;
    /**
     * the browser to use on runtime
     */
    private String browser;
    /**
     * the driver path of the browser to be set
     */
    private String driverPath;
    /**
     * the driver property to set it equal to driver path
     */
    private String driverProperty;

    /**
     * constructor used to get all the data from json file
     * and set the needed variables such as
     * browser , driver path and driver property
     */
    public Configuration(){
        JSONParser parser = new JSONParser();
        try{
            jsonContent = (JSONObject) parser.parse(new FileReader(jsonFilePath));
            browser = (String) jsonContent.get("browser");
            JSONObject browserSettings = (JSONObject) jsonContent.get(browser);
            driverPath = (String) browserSettings.get("driverPath");
            driverProperty = (String) browserSettings.get("DriverProperty");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * get the browser value
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * get the driver path value
     */
    public String getDriverPath() {
        return driverPath;
    }

    /**
     * get the driver property value
     */
    public String getDriverProperty() {
        return driverProperty;
    }
}
