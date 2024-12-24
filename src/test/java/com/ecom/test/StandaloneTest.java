package com.ecom.test;

import com.ecom.pageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        String productName = "ZARA COAT 3";



        driver.findElement(By.id("userEmail")).sendKeys("chintu.tester@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

        WebElement product = products.stream().filter(prod->
                prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3"))
                .findFirst().orElse(null);
        assert product != null;
        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        WebElement loder = driver.findElement(By.cssSelector(".ng-trigger-fadeIn"));
        wait.until(ExpectedConditions.invisibilityOf(loder));
        driver.findElement(By.xpath("//button[contains(@routerlink,'/dashboard/cart')]")).click();

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

        boolean isProdcutCorrect = cartProducts.stream().anyMatch(cartProd -> cartProd.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(isProdcutCorrect,"added product is wrong");
        driver.findElement(By.cssSelector(".totalRow button[type='button']")).click();

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("India");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-result')]/button/span")));

        List<WebElement> coutriesList = driver.findElements(By.xpath("//section[contains(@class,'ta-result')]/button/span"));
        for(WebElement country:coutriesList) {
            if(country.getText().equalsIgnoreCase("India"))
                country.click();
        }
        WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",placeOrder);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",placeOrder);
        String confirmMsg = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
        Assert.assertEquals(confirmMsg.toLowerCase(),"thankyou for the order.");
        driver.quit();
    }
}
