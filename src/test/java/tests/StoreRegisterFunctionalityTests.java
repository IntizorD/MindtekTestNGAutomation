package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppHomePAge;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.DataUtils;
import utilities.TestBase;

import java.io.IOException;
import java.util.Random;

public class StoreRegisterFunctionalityTests extends TestBase {

    String email;
    String password;
    //Data Driven testing

    @DataProvider(name = "signUpDataProvider")
    public static Object [][] testData(){
        Object [] [] data = new Object[][]{
                {"John", "Doe", "123345678", DataUtils.generateRandomNumber(30)+"", "1", "2021", "123 Clark St.", "Chicago", "13", "12345","21", "123456789"}, // Set 1
                {"Kim", "Yi", "abcdefg", "1", "12", "1980","2 MyRoad St.", "New York", "32", "11214", "21", "987654321"}, //Set 2
                {"Patel", "Harsh", "abc2345678", "1", "12", "1980","2 MyRoad St.", "New York", "32", "11214", "21", "987654321"}
        };
        return data;

    }

    @Test(dataProvider = "signUpDataProvider", groups = {"regression", "smoke"})
    public void test1(String firstname, String lastname, String password, String day,String month, String year, String address,
                      String city, String state, String zipCode, String country, String phoneNumber) throws IOException {
        StoreAppHomePAge storeAppHomePAge = new StoreAppHomePAge();
        StoreAppLoginPage storeAppLoginPage = new StoreAppLoginPage();
        StoreAppCreateAccountPage storeAppCreateAccountPage = new StoreAppCreateAccountPage();

        driver.get(ConfigReader.getProperty("StoreAppURL"));
        storeAppHomePAge.loginButton.click();

        email=DataUtils.generateEmail();
        storeAppLoginPage.emailBox.sendKeys(email);
        storeAppLoginPage.submitButton.click();
        storeAppCreateAccountPage.gender.click();
        storeAppCreateAccountPage.FirstNameBox.sendKeys(firstname);
        storeAppCreateAccountPage.LastNameBox.sendKeys(lastname);
        this.password=password;
        storeAppCreateAccountPage.PasswordBox.sendKeys(password);

        BrowserUtils.selectByValue(storeAppCreateAccountPage.daysBox, day);
        BrowserUtils.selectByValue(storeAppCreateAccountPage.monthsBox, month);
        BrowserUtils.selectByValue(storeAppCreateAccountPage.yearsBox, year);
        storeAppCreateAccountPage.address1Box.sendKeys(address);
        storeAppCreateAccountPage.cityBox.sendKeys(city);

        BrowserUtils.selectByValue(storeAppCreateAccountPage.stateBox, state);
        storeAppCreateAccountPage.postcodeBox.sendKeys(zipCode);
        BrowserUtils.selectByValue(storeAppCreateAccountPage.countryBox, country);
        storeAppCreateAccountPage.phonemobileBox.sendKeys(phoneNumber);
        storeAppCreateAccountPage.registerButton.click();
        BrowserUtils.takeScreenshot("SignUpValidation");
        String actualTitle = driver.getTitle();
        String expectedTitle = "My account - My Store";
        Assert.assertEquals(actualTitle, expectedTitle, "Actual title " + actualTitle + " didn't match with the expected title" + expectedTitle);


    }
    // Login functionality
    @Test(dependsOnMethods = {"test1"}, groups = {"regression", "smoke"})
    public void test2() throws IOException {
        StoreAppHomePAge storeAppHomePAge = new StoreAppHomePAge();
        StoreAppLoginPage storeAppLoginPage=new StoreAppLoginPage();
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        storeAppHomePAge.loginButton.click();
        storeAppLoginPage.loginEmailBox.sendKeys(email);
        storeAppLoginPage.loginPasswordBox.sendKeys(password);
        storeAppLoginPage.loginButton.click();
        BrowserUtils.takeScreenshot("LogInValidation");
        String actualTitle = driver.getTitle();
        String expectedTitle = "My account - My Store";
        Assert.assertEquals(actualTitle,expectedTitle, "Title for log in did not match.");


    }



}
