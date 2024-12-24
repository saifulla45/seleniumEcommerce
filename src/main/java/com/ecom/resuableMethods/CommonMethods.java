package com.ecom.resuableMethods;

import com.ecom.constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CommonMethods {
    WebDriver driver;
    public CommonMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForVisibility(WebElement locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(locator));
    }
    public void waitForVisibility(By locator) {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForInvisibility(WebElement locator) {
        new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOf(locator));
    }

    public void scrollInViewAndClick(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element);
    }


 }
