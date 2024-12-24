package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrdersPage extends CommonMethods {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private By productNames = By.xpath("//tbody/tr/td[2]");

    public boolean verifyProductIsDisplayed(String productName) {
        waitForVisibility(productNames);
        return getAllProductsListInOrdersPage().stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
    }

    public List<WebElement> getAllProductsListInOrdersPage() {
        waitForVisibility(productNames);
        return driver.findElements(productNames);
    }
}
