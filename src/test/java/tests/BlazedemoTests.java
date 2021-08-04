package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BlazedemoFligthPage;
import pages.BlazedemoHomePage;
import utilities.ConfigReader;
import utilities.TestBase;

import java.nio.channels.SelectableChannel;
import java.util.List;

public class BlazedemoTests extends TestBase {

    /*
    1. @BeforeMethod
    2. @Test
    3. @AfterMethod
     */


    @Test(groups = {"regression"})
    public void test1(){
        BlazedemoHomePage blazedemoHomePage = new BlazedemoHomePage();
        BlazedemoFligthPage blazedemoFligthPage = new BlazedemoFligthPage();
        driver.get(ConfigReader.getProperty("BlazedemoURL"));
        blazedemoHomePage.findFlightsButton.click();
        //all flights are below 1000$
        List<WebElement> prices = blazedemoFligthPage.prices;
        for (WebElement element : prices){
            String priceStr = element.getText(); //472.56$ -> convert string to int  == Integer.parseInt(String) -> int
            //$472.56 -> 472.56
           priceStr =  priceStr.substring(1);
            double priceDouble = Double.parseDouble(priceStr);

            Assert.assertTrue(priceDouble<1000);
        }
    }

    @Test(groups  = {"regression"})

    public void test2(){
        BlazedemoHomePage blazedemoHomePage = new BlazedemoHomePage();
        BlazedemoFligthPage blazedemoFligthPage = new BlazedemoFligthPage();
        driver.get(ConfigReader.getProperty("BlazedemoURL"));

        Select select = new Select(blazedemoHomePage.fromCityDropdown);
        select.selectByValue("Boston");
        select = new Select(blazedemoHomePage.toCityDropdown);
        select.selectByVisibleText("London");
        blazedemoHomePage.findFlightsButton.click();
        String actualText = blazedemoFligthPage.headerText.getText();
        String expectedText = "Flights from Boston to London:";
        Assert.assertEquals(actualText, expectedText);

    }
}
