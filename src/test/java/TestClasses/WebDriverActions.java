package TestClasses;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Represents a browser driver with its actions
 */
public class WebDriverActions {
    /**
     * The browser driver
     */
    public WebDriver webDriver;
    /**
     * used to execute javascript commands if needed
     */
    public JavascriptExecutor js;
    /**
     * holds the configuration of browsers to be dynamic
     */
    public Configuration configuration;
    /**
     * holds the data needed to avoid hard codding variables values
     */
    public CSVFile csvFile;

    /**
     * Creates new webdriver , loads Configuration and data file
     */
    public WebDriverActions(){
        csvFile = new CSVFile("test",",");
        configuration = new Configuration();
        setWebDriver();

//        setRemoteDriver();
    }

    /**
     * Get the browser driver and set it if not set
     * @return this browser driver
     */
    public WebDriver getWebDriver(){
        if(webDriver == null){
//            setRemoteDriver();
            setWebDriver();
        }
        return webDriver;
    }

    /**
     * Creates new webdriver , loads Configuration and data file
     */
    private void setWebDriver() {
        String driverProperty = configuration.getDriverProperty(),
         driverPath = configuration.getDriverPath(),
         browser = configuration.getBrowser();
        System.setProperty(driverProperty,driverPath);
        if(browser.equals("firefox")){
            webDriver = new FirefoxDriver();
        }else if (browser.equals("edge")){
            webDriver = new EdgeDriver();
        }else{
            webDriver = new ChromeDriver();
        }
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        js = (JavascriptExecutor) webDriver;
    }

    public void setRemoteDriver() {
        String url = "http://localhost:4444";
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("firefox");
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        desiredCapabilities.setVersion("100");
        try {
            js = (JavascriptExecutor) webDriver;
            webDriver = new RemoteWebDriver((new URL(url)),desiredCapabilities);
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Fetch google next button and click it
     */
    public void goNextPageGoogleSearch(){
        try {
            WebElement nextButton = webDriver.findElement(By.id("pnnext"));
            nextButton.click();
            Reporter.log("clicked next button successfully<br>");
        }catch (Exception e){
            Reporter.log("error getting and clicking next button<br>");
            e.printStackTrace();
        }
    }

    /**
     * scroll down current opened site to the end using js command
     */
    public void scrollDown(){
        try {
            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Reporter.log("scrolled down to the end of the page successfully<br>");
        }catch (Exception e){
            Reporter.log("error scrolling to the end of the page<br>");
            e.printStackTrace();
        }
    }

    /**
     * Count the number of search result links in google
     * search page
     * @return the size of total elements counted
     */
    public int getGoogleSearchResultCount(){
        try {
            List<WebElement> findElements = webDriver.findElements(By.xpath("//*[@id='rso']//a/h3"));
            Reporter.log("got results shown of the page<br>");
            return findElements.size();
        }catch (Exception e){
            Reporter.log("error counting google search results<br>");
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Fetch url from csv file and open it which is google
     * then fetch the search bar and type search keyword
     * fetched from the csv file then submit and wait till
     * the new page is loaded
     */
    public void openGoogleAndSearchKeyword(){
        try {
            String url = csvFile.getResults().get("url");
            String search = csvFile.getResults().get("search");
            webDriver.get(url);
            WebElement searchBar = webDriver.findElement(By.name("q"));
            searchBar.sendKeys(search);
            WebDriverWait w = new WebDriverWait(webDriver, 5);
            w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
            searchBar.submit();
            Reporter.log("got google search bar and searched for : " + search+"<br>");
        }catch (Exception e){
            Reporter.log("couldn't search in google<br>");
            e.printStackTrace();
        }
    }

    /**
     * terminates the browser opened
     */
    public void terminate(){
        webDriver.close();
        Reporter.log("terminated the browser<br>");
    }


}
