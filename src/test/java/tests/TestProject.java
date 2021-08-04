package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestProject {


    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public  void test1(){
        driver.get(ConfigReader.getProperty("RedShelfURL"));
        driver.findElement(By.id("search-catalog-navbar")).sendKeys(" java data structure "+ Keys.ENTER);
        String result = driver.findElement(By.xpath("//div[@class='col-xs-12 col-sm-8']")).getText();
        System.out.println(result);
        String expectedResult = "Showing results for java data structure";
        Assert.assertEquals(result, expectedResult);



    }
    @Test
    public void test2(){
        driver.get(ConfigReader.getProperty("RedShelfURL"));
        driver.findElement(By.id("search-catalog-navbar")).sendKeys("Seth Godin"+ Keys.ENTER);
        driver.findElements(By.xpath("//div[@class='author']"));
       List<WebElement> list  =  driver.findElements(By.xpath("//*[@class='author']"));


        for (int i=0; i<list.size(); i++){
            String actualResult=list.get(i).getText();

            String expectedResult="Seth Godin";
            System.out.println(actualResult);
            Assert.assertEquals(actualResult, expectedResult);
        }

    }
    @Test
    public void test3(){
        driver.get(ConfigReader.getProperty("RedShelfURL"));
        driver.findElement(By.id("search-catalog-navbar")).sendKeys("Purple Cow"+ Keys.ENTER);
        driver.findElement(By.xpath("//img[@alt='Cover image for Purple Cow, New Edition']")).click();
        driver.findElement(By.xpath("//button[@title='Add Selected Option To Cart']")).click();
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
        String actualResult = driver.findElement(By.xpath("//a[@class='title']")).getText();
        String expectedResult = "Purple Cow, New Edition";
        Assert.assertEquals(actualResult, expectedResult);




    }
    @Test
    public void test4(){
        driver.get(ConfigReader.getProperty("RedShelfURL"));
        driver.findElement(By.id("search-catalog-navbar")).sendKeys("Java data structure"+ Keys.ENTER);
        String itemNumbers = driver.findElement(By.xpath("//span[contains(text(), '1-25 of 58')]")).getText();
        String expected = "1-25 of 58";
        Assert.assertEquals(itemNumbers, expected);



    }
    @Test
    public void test5(){
        driver.get(ConfigReader.getProperty("RedShelfURL"));

        String bookName="";
        for (int i=0; i<=300; i++){


             bookName+="b";
        }
        driver.findElement(By.id("search-catalog-navbar")).sendKeys(bookName+Keys.ENTER);
        String actualResult = driver.findElement(By.xpath("//h1[@class='text-center']")).getText();
        String expectedResult = "Oh No! Looks like we don't have the eBook you're searching for.";
        Assert.assertEquals(actualResult, expectedResult);

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
