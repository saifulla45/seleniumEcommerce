package com.ecom.testComponents;

import com.ecom.constants.FrameworkConstants;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {
    public WebDriver driver;

    public WebDriver initializeDriver() {
        String browserName="";
        try {
            File file = new File(System.getProperty("user.dir")+"/src/test/resources/config.properties");
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            browserName = System.getProperty("browser")!= null?System.getProperty("browser"):properties.getProperty("browser");
            //browserName = properties.getProperty("browser");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(browserName.contains("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--allow-running-insecure-content");
            if(browserName.contains("headless"))
                options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browserName.contains("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("-private");
            firefoxOptions.addArguments("--start-maximized");
            firefoxOptions.addArguments("--ignore-certificate-errors");
            if(browserName.contains("headless"))
                firefoxOptions.addArguments("--headless");
            driver = new FirefoxDriver(firefoxOptions);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @BeforeMethod
    public void launchApplication() {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/client");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public String takeScreenshot(String testcaseName,WebDriver driver) {

        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destination = new File(FrameworkConstants.SCREENSHOTPATH+"/"+testcaseName+".png");
            FileUtils.copyFile(source,destination);
            return FrameworkConstants.SCREENSHOTPATH+"/"+testcaseName+".png";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
