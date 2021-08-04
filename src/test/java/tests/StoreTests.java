package tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.StoreAppContactUsPage;
import pages.StoreAppHomePAge;
import utilities.ConfigReader;
import utilities.TestBase;

public class StoreTests extends TestBase {

    //Positive Scenario



    @Test(groups = {"regression", "smoke"})
    public void test1() {
        StoreAppHomePAge storeAppHomePAge = new StoreAppHomePAge();
        StoreAppContactUsPage storeAppContactUsPage = new StoreAppContactUsPage();
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        storeAppHomePAge.contactUsButton.click();

        Select select = new Select(storeAppContactUsPage.subjectHeadingDropdown);
        select.selectByValue("2");
        storeAppContactUsPage.email.sendKeys("123@gmail.com");

        String projectPath = System.getProperty("user.dir");
        System.out.println("Path for project is: " + projectPath);
        storeAppContactUsPage.fileUpload.sendKeys(projectPath + "/src/test/resources/testdata/MyImg.jpeg");
        storeAppContactUsPage.message.sendKeys("Hello World");
        storeAppContactUsPage.submitButton.click();
        String actualSuccessMessage = storeAppContactUsPage.successMessage.getText();
        String expectedSuccessMessage = "Your message has been successfully sent to our team.";
        Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);


    }

    // Negative Scenario
    @Test(groups = {"regression", "smoke"})

    public void test2() {
        StoreAppHomePAge storeAppHomePAge = new StoreAppHomePAge();
        StoreAppContactUsPage storeAppContactUsPage = new StoreAppContactUsPage();
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        storeAppHomePAge.contactUsButton.click();

        Select select = new Select(storeAppContactUsPage.subjectHeadingDropdown);
        select.selectByValue("2");
        storeAppContactUsPage.email.sendKeys("123@gmail.com");

        String projectPath = System.getProperty("user.dir");
        System.out.println("Path for project is: " + projectPath);
        storeAppContactUsPage.fileUpload.sendKeys(projectPath + "/src/test/resources/testdata/MyImg.jpeg");
        storeAppContactUsPage.submitButton.click();
        String actualErrorMessage = storeAppContactUsPage.errorMessage.getText();
        String expectedErrorMessage ="The message cannot be blank.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


    }


}