package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends CommonMethods {
    WebDriver driver;
    public PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    private WebElement countryNameTxt;

    @FindBy(css = ".action__submit")
    private WebElement placeOrderBtn;

    private By countryNameList = By.xpath("//section[contains(@class,'ta-result')]/button/span");

    public void selectCountryFromDropdown(String countryName) {
        countryNameTxt.sendKeys(countryName);
        waitForVisibility(countryNameList);
        List<WebElement> coutriesList = driver.findElements(countryNameList);
        for(WebElement country:coutriesList) {
            if(country.getText().equalsIgnoreCase("India"))
                country.click();
        }
    }

    public void clickOnPlaceOrderBtn() {
        scrollInViewAndClick(placeOrderBtn);
    }
}
