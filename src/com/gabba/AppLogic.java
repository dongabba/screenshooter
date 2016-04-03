package com.gabba;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by gabba on 03.04.2016.
 */
public class AppLogic {
    private WebDriver driver = new FirefoxDriver();

    public Page startBrowser() throws IOException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://smcp-cloud.fors.ru/pls/apex/f?p=706:101");
        return new Page(driver);
    }

    public WebDriver getDriver(){
        return driver;
    }

    public void closeBrowser(){
        driver.close();
    }


}
