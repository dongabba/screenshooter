package com.gabba;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    List<String> unusedLinks = Arrays.asList("Abmelden", "DE", "RU", "ENG", "ES", "Startseite", "Neue Nachricht");


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
    public List<String> getAllMainPageLinks(){
        List<String> allMainPageLinks = getLinksOnPage();
        return allMainPageLinks;
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

    public List<String> getLinksOnPage() {
        List<WebElement> linksToClick = driver.findElements(By.cssSelector("a"));
        List<String> pageLinksText = new ArrayList<String>();
        for (int i = 0; i < linksToClick.size(); i++) {
            pageLinksText.add(linksToClick.get(i).getText());
        }
        return pageLinksText;
    }

    public List<String> compare(List<String> first, List<String> second) {
        List<String> result = new ArrayList<String>();
        int compare = 0;
        for (int i=0; i<second.size();i++){
            for (int j=0; j<first.size(); j++){
                if (second.get(i).equals(first.get(j))){
                    compare = compare+1;
                }
            }
            if (compare == 0){
                result.add(second.get(i));
            }
            compare=0;
        }
        return result;
    }



    public void printLinks(List<String> linkList){
        for (int i=0;i<linkList.size(); i++){
            System.out.println(linkList.get(i));
        }

    }

    public List<String> getMainPageLinks(){
        List<String> mainPageLinks = compare(unusedLinks, getAllMainPageLinks());
        return mainPageLinks;
    }

    public void getScreenShots(){
        List<String> mainPageLinks = getMainPageLinks();
        for (int i=0;i<mainPageLinks.size();i++ ){
            click(By.linkText(mainPageLinks.get(i)));
            List<String> pageLinks = getLinksOnPage();
            List<String> uniquePageLinks = compare(getAllMainPageLinks(),pageLinks);
            if (uniquePageLinks.size()>0){
                goToNextPage(uniquePageLinks);
            } else {
                takeScreenShot();
                returnToPreviousPage();
            }
        }
    }

    private void goToNextPage(List<String> list) {
        for (int i=0; i<list.size();i++){
            click(By.linkText(list.get(i)));
        }
    }

    private boolean isLastPage(List<String> list){
        if (list.size()==0){
            return true;
        } else{
            return false;
        }
    }

}


