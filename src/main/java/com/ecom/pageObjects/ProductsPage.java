package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ProductsPage extends CommonMethods {
    WebDriver driver;
    public ProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private By allProductsList = By.cssSelector(".mb-3");
    private By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
    private By productAdddedToast = By.id("toast-container");
    @FindBy(css = ".ng-trigger-fadeIn")
    private WebElement loader ;

    @FindBy(xpath = "//button[contains(@routerlink,'/dashboard/cart')]")
    private WebElement cartLink;

    @FindBy(xpath = "//button[contains(@routerlink,'/dashboard/myorders')]")
    private WebElement orderLink;

    public List<WebElement> getAllProductsList() {
        waitForVisibility(allProductsList);
        return driver.findElements(allProductsList);
    }

    public WebElement getProductByName(String name) {
        return getAllProductsList().stream().filter(prod->
                        prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String name) {
        getProductByName(name).findElement(addToCartBtn).click();
        waitForVisibility(productAdddedToast);
        waitForInvisibility(loader);
    }

    public void clickOnCart() {
        cartLink.click();
    }

    public OrdersPage clickOnOrders() {
        orderLink.click();
        return new OrdersPage(driver);
    }

    public boolean isProductPageDisplayed() {
        waitForVisibility(allProductsList);
        return !driver.findElements(allProductsList).isEmpty();
    }

}
