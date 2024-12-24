package com.ecom.test;

import com.ecom.pageObjects.LoginPage;
import com.ecom.testComponents.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidationTest extends TestBase {

    @Test
    public void validatingErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("saifulla.a@gmail.com","ttest");
        Assert.assertEquals(loginPage.getErrorText().trim(),"Incorrect email or password.");
    }

}
