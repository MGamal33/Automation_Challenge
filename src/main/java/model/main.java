package model;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) {
        CSVFile csvFile = new CSVFile("test",",");
        HashMap<String,String> results = csvFile.getResults();
        System.out.println(results.get("url"));

        System.setProperty("webdriver.chrome.driver", "browsersDrivers\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.get("https://www.google.com");
        WebElement p=webDriver.findElement(By.name("q"));
        p.sendKeys("Vodafone");
        WebDriverWait w = new WebDriverWait(webDriver,5);
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));
        p.submit();
        List<WebElement> count = webDriver.findElements(By.tagName("a"));
        System.out.println(count.size());
//        for(WebElement a:count){
//            System.out.println(a.getText());
//        }
        w.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul")));

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        WebElement page2 = webDriver.findElement(By.id("pnnext"));
        page2.click();
        List<WebElement> findElements = webDriver.findElements(By.xpath("//*[@id='rso']//a/h3"));
        System.out.println(findElements.size());
        for(WebElement a: findElements){
            System.out.println(a.getText());
        }
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement page3 = webDriver.findElement(By.id("pnnext"));
        page3.click();
        List<WebElement> findElements2 = webDriver.findElements(By.xpath("//*[@id='rso']//a/h3"));
        System.out.println(findElements2.size());
        for(WebElement b: findElements2){
            System.out.println(b.getText());
        }
//        for(WebElement a: pages )
//        webDriver.findElement(By.linkText("التالية")).click();
//        webDriver.close();
    }
}
