package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserUtils {
    /**
    Method that will accept dropdown WebElement and
    value of that dropdown, and it will select that value which is provided in parameter.
    Ex:
    .selectByValue(dropdownElement, "1"); -> void
     */

    public static void selectByValue(WebElement element, String value){
        Select select = new Select(element);
        select.selectByValue(value);


    }

    public static WebElement waitElementToBeVisible(WebElement element){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }


    /**
     * This method will hover over to element in browser.
     * EX:
     * .hoverOver(element);
     */

    public static  void hoverOver(WebElement element){
        Actions actions =  new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
}
