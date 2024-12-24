package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankyouPage extends CommonMethods {
    WebDriver driver;
    public ThankyouPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    private WebElement thankyouTxt;

    public String getThankyouText() {
        return thankyouTxt.getText().trim().toLowerCase();
    }
}
