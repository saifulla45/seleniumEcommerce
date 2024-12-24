package com.ecom.test;

import com.ecom.pageObjects.*;
import com.ecom.testComponents.RetryListener;
import com.ecom.testComponents.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;

public class SubmitOrderTest extends TestBase {
    String productName = "ZARA COAT 3";

    @Test
    public void submitOrder() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("chintu.tester@gmail.com","Test@1234");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCart(productName);
        productsPage.clickOnCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isAddedProductCorrect(productName));
        cartPage.clickOnCheckoutBtn();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.selectCountryFromDropdown("India");
        paymentPage.clickOnPlaceOrderBtn();

        ThankyouPage thankyouPage = new ThankyouPage(driver);
        Assert.assertEquals(thankyouPage.getThankyouText(),"thankyou for the order.");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test(dependsOnMethods = {"submitOrder"},retryAnalyzer = RetryListener.class)
    public void orderHistoryTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage
                .login("chintu.tester@gmail.com","Test@1234")
                .clickOnOrders();
        OrdersPage ordersPage = new OrdersPage(driver);
                Assert.assertTrue(ordersPage.verifyProductIsDisplayed(productName));

    }


}
