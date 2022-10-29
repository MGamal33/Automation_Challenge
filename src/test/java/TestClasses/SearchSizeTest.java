package TestClasses;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test class used to test if the count of page 2 and 3
 * in google search results are equal in results
 */
@Test
public class SearchSizeTest {
    /**
     * holds the browser with some functionality
     */
    public static WebDriverActions webDriver;
    /**
     * used to store count of second page
     */
    public int firstCount;
    /**
     * used to store count of third page
     */
    public int secondCount;

    /**
     * initialize the browser settings at the start
     */
    @BeforeTest
    public void start(){
        webDriver = new WebDriverActions();
    }

    /**
     * run some functionality implemented in webdriver class
     * and give a feedback regarding the result
     * after comparing the results count of
     * page 1 and 2 in google search result page
     */
    @Test
    public void firstTest(){
        webDriver.openGoogleAndSearchKeyword();
        webDriver.scrollDown();
        webDriver.goNextPageGoogleSearch();
        firstCount = webDriver.getGoogleSearchResultCount();
        webDriver.scrollDown();
        webDriver.goNextPageGoogleSearch();
        secondCount = webDriver.getGoogleSearchResultCount();
        System.out.println("First Count : " + firstCount + " , Second Count : " + secondCount);
        System.out.println("First test status : " + (firstCount == secondCount ? "Succeeded":"Failed"));
        Assert.assertEquals(firstCount,secondCount);
//        Assert.fail();
    }

    /**
     * terminate the browser after finishing testing
     */
    @AfterTest
    public void terminate(){
        webDriver.terminate();
    }

}
