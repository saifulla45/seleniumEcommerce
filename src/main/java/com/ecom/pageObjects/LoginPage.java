package com.ecom.pageObjects;

import com.ecom.resuableMethods.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    private WebElement emailTxt;

    @FindBy(id = "userPassword")
    private WebElement passwordTxt;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(className = "toast-message")
    private WebElement errorMessage;

    public ProductsPage login(String uname , String pass) {
        emailTxt.sendKeys(uname);
        passwordTxt.sendKeys(pass);
        loginBtn.click();
        return new ProductsPage(driver);
    }

    public String getErrorText() {
        waitForVisibility(errorMessage);
        return errorMessage.getText();
    }
}