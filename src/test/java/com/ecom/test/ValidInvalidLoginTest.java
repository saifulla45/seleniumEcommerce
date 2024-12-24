package com.ecom.test;

import com.ecom.constants.FrameworkConstants;
import com.ecom.pageObjects.LoginPage;
import com.ecom.pageObjects.ProductsPage;
import com.ecom.testComponents.TestBase;
import com.ecom.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class ValidInvalidLoginTest extends TestBase {

    @Test(dataProvider = "validInvalidData")
    public void dataDriven(HashMap<String, String> maps) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(maps.get("email"), maps.get("password"));
        if (maps.get("isvalid").equalsIgnoreCase("valid")) {
            ProductsPage productsPage = new ProductsPage(driver);
            Assert.assertTrue(productsPage.isProductPageDisplayed());
        } else if (maps.get("isvalid").equalsIgnoreCase("invalid")) {
            Assert.assertEquals(loginPage.getErrorText().trim(), "Incorrect email or password.");
        }
    }

    @DataProvider(name = "validInvalidData")
    public Object[][] getData() {

        List<HashMap<String, String>> data = JsonDataReader.getDataFromJson(FrameworkConstants.JSON_FILEPATH);
       int limit = data.size();
       Object[][] maps= new Object[limit][1];
       for(int i=0;i<limit;i++) {
           maps[i][0] = data.get(i);
       }
       return maps;
    }
}