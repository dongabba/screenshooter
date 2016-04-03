package com.gabba;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by gabba on 03.04.2016.
 */
public class Page {
    private WebDriver driver;
    private WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    By loginField = By.id("P101_USERNAME");
    By passwordField = By.id("P101_PASSWORD");
    By loginButton = By.id("P101_LOGIN");

    private void type(By element, String text) {
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public Page userLogin(String login, String password) throws IOException {
        type(loginField, login);
        type(passwordField, password);
        click(loginButton);
        return new Page(driver);
    }

    private void click(By element) {
        int i = 0;
        while (i < 5) {
            try {
                driver.findElement(element).click();
                break;
            } catch (Exception e) {
                i = i + 1;
            }
        }

    }


    public String getPageName() {
        return driver.getTitle();
    }

    public void takeScreenShot() throws IOException {
        Random random = new Random();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pageName = getPageName();
        pageName = pageName.replaceAll(" ", "");
        pageName = pageName.replaceAll("\"", "");
        FileUtils.copyFile(scrFile, new File("screenshots/" + "screenshot_" + pageName +"_"+ random.nextInt(100000)+".png"));
    }

    public void getAllLinks() throws IOException, InterruptedException {
        List<WebElement> linksToClick = driver.findElements(By.cssSelector("a"));
        List<String> linkText = new ArrayList<>();
        for (int i = 0; i < linksToClick.size(); i++) {
            linkText.add(linksToClick.get(i).getText());
        }
        System.out.println("Total links on MainPage: "+linkText.size());
        for (int i = 0; i < linkText.size(); i++)
        {
            if (linkText.get(i).equals("Abmelden")) {
                System.out.println("This is link: " + linkText.get(i) + " Not click");
            } else {
                if (linkText.get(i).equals("DE")) {
                    System.out.println("This is link: " + linkText.get(i) + " Not click");
                } else {
                    if (linkText.get(i).equals("RU")) {
                        System.out.println("This is link: " + linkText.get(i) + " Not click");
                    } else {
                        if (linkText.get(i).equals("ENG")) {
                            System.out.println("This is link: " + linkText.get(i) + " Not click");
                        } else {
                            if (linkText.get(i).equals("ES")) {
                                System.out.println("This is link: " + linkText.get(i) + " Not click");
                            } else {
                                if (linkText.get(i).equals("Startseite")) {
                                    System.out.println("This is link: " + linkText.get(i) + " Not click");
                                } else {
                                    if (linkText.get(i).equals("Neue Nachricht")) {
                                        System.out.println("This is link: " + linkText.get(i) + " Not click");
                                    } else {
                                        System.out.println(linkText.get(i));
                                        click(By.linkText(linkText.get(i)));
                                        Thread.sleep(1000);
                                        takeScreenShot();
                                        click(By.linkText("Startseite"));
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


