package tests;


import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppHomePAge;

import pages.StoreAppShoppingCartPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.DataUtils;
import utilities.TestBase;

import javax.xml.crypto.Data;

public class StoreAddCartTests extends TestBase {

    @Test(groups = {"regression", "smoke"})
    public void  addCartFunctionalityTest(){
        StoreAppHomePAge storeAppHomePAge=new StoreAppHomePAge();
        StoreAppShoppingCartPage storeAppShoppingCartPage = new StoreAppShoppingCartPage();
        driver.get(ConfigReader.getProperty("StoreAppURL"));
        String itemPrice = storeAppHomePAge.prices.get(1).getText();



       BrowserUtils.hoverOver(storeAppHomePAge.item1);
        storeAppHomePAge.addToCartItem1.click();
        String addCartSuccessMessage = BrowserUtils.waitElementToBeVisible(storeAppHomePAge.addCartSuccessMessage).getText();
        String expectedSuccessMessage= "Product successfully added to your shopping cart";

        SoftAssert softAssert=new SoftAssert();
      softAssert.assertEquals(addCartSuccessMessage, expectedSuccessMessage);


        String quantity = storeAppHomePAge.quantity.getText();
        String total =storeAppHomePAge.totalPrice.getText();
         //itemPrice, quantity, total
        //itemPrice -> $16.51
        //quantity -> 2
        //total -> $33.02


        double itemPriceDouble = DataUtils.remove$AndConvertToDouble(itemPrice); //converting string into double
        double quantityDouble =Double.parseDouble(quantity); // 1-> 1.0
        double totalDouble = DataUtils.remove$AndConvertToDouble(total);
        double expectedPrice = itemPriceDouble*quantityDouble;
        double  actualPrice = totalDouble;
        softAssert.assertEquals(actualPrice,expectedPrice);

        storeAppHomePAge.proceedToCheckout.click();


        String totalProduct = storeAppShoppingCartPage.totalProduct.getText();
        double totalProductDouble = DataUtils.remove$AndConvertToDouble(totalProduct);
        softAssert.assertEquals(actualPrice, totalProductDouble);

        String shippingAmount = storeAppShoppingCartPage.totalShipping.getText();
        double shippingAmountDouble = DataUtils.remove$AndConvertToDouble(shippingAmount);

        String taxAmount = storeAppShoppingCartPage.tax.getText();
        double taxAmountDouble =DataUtils.remove$AndConvertToDouble(taxAmount);

        String actualTotal = storeAppShoppingCartPage.totalPrice.getText();
        double actualTotalDouble =DataUtils.remove$AndConvertToDouble(actualTotal);

        double expectedTotal = totalProductDouble+shippingAmountDouble+taxAmountDouble;


        softAssert.assertEquals(actualTotalDouble, expectedTotal);
        softAssert.assertAll();



    }
}
