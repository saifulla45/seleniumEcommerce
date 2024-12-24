package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends CommonMethods {
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> listOfProductsAddedToCart;

    @FindBy(css = ".totalRow button[type='button']")
    WebElement checkoutBtn;

    public boolean isAddedProductCorrect(String productName) {
      return listOfProductsAddedToCart.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
    }

    public void clickOnCheckoutBtn() {
        checkoutBtn.click();
    }
}
