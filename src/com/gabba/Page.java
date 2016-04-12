package com.gabba;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
    DateFormat dateFormat = new SimpleDateFormat("ddMMYYYY");
    DateFormat timeFormat = new SimpleDateFormat("HHmmss");


    private void type(By element, String text) {
        driver.findElement(element).click();
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public Page userLogin(String login, String password) throws IOException, InterruptedException {
        type(loginField, login);
        type(passwordField, password);
        click(loginButton);
        return new Page(driver);
    }

    public String getUserId(){
        String url = driver.getCurrentUrl();
        String userId = url.substring(url.indexOf(":",47), url.indexOf(":", 50));
        System.out.println("UserId: " + userId);
        return userId;
    }

    public String getPageCode(){
        String url = driver.getCurrentUrl();
        String codePage = url.substring(url.indexOf(":", 41), url.indexOf(":", 43));
        codePage = codePage.replaceAll(":","");
        System.out.println("PageCode: " + codePage);
        return codePage;
    }

    private String getTitle(){
        return driver.getTitle();
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

    public void takeScreenShot() throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pageName = getTitle();
        String pageCode = getPageCode();
        pageName = pageName.replaceAll(" ", "");
        pageName = pageName.replaceAll("\"", "");
        FileUtils.copyFile(scrFile, new File("screenshots/" + pageName +"_"+pageCode+"_"+dateFormat.format(currentDate)+ "_"+timeFormat.format(currentDate)+"_"+"DE"+".png"));
    }

    public void takeScreenShotEng() throws IOException {
        Date currentDate = new Date(System.currentTimeMillis());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pageName = getTitle();
        String pageCode = getPageCode();
        pageName = pageName.replaceAll(" ", "");
        pageName = pageName.replaceAll("\"", "");
        FileUtils.copyFile(scrFile, new File("screenshots/" + pageName +"_"+pageCode+"_"+dateFormat.format(currentDate)+ "_"+timeFormat.format(currentDate)+"_"+"ENG"+".png"));
    }


    private List<String> getListLinksFromFile(){
        BufferedReader reader = null;
        ArrayList<String> allLinks = new ArrayList<String>();
        String line;
        try {
        reader = new BufferedReader(new FileReader(new File("allLinks_unique.txt")));

        while ((line = reader.readLine())!=null) {
                allLinks.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allLinks;
    }

    private void printList(List<String> list){
        for (int i=0; i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public void printLinks(){
        printList(getListLinksFromFile());
    }

    public void getScreenShoots(List<String> list) throws IOException, InterruptedException {
        for (int i=0; i<list.size();i++){
            driver.navigate().to(list.get(i));
            Thread.sleep(1000);
            takeScreenShot();
        }
    }

    public ArrayList<String> replaceUserId(List<String> list, String s1, String s2){
        ArrayList<String> replacedList = new ArrayList<String>();
        for (int i=0; i<list.size(); i++){
            replacedList.add(list.get(i).replace(s1, s2));
        }
        return replacedList;
    }
    public void getScreenShootsFromLinksFile() throws IOException, InterruptedException {
        String id = getUserId();
        getScreenShoots(replaceUserId(getListLinksFromFile(), ":userId", id));
    }

    public void getScreenShootsFromLinksFileEn() throws IOException, InterruptedException {
        click(By.linkText("ENG"));
        String id = getUserId();
        getScreenShoots(replaceUserId(getListLinksFromFile(), ":userId", id));
    }

    public void cleanWorkingDirectory() {
        File file = new File("screenshots");
        if (file.isDirectory()){
            System.out.println("Directory exist");
        }else{
            file.mkdirs();
            System.out.println("Directory created");
        }
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f1 = files[i];
            //System.out.println("Delete file: "+ f1.getName());
            f1.delete();
        }
    }

    public void getScreenShootsCSLinks() throws InterruptedException, IOException {
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Stadien"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Städte"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Ländern"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Bodenarten"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Spielerstatus"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Grundposition"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Unterpositionen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("erstellen"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Verzeichnise"));
        click(By.linkText("Team"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Alle Teams"));
        Thread.sleep(1000);
        takeScreenShot();
        click(By.linkText("Inaktive"));
        Thread.sleep(1000);
        takeScreenShot();

    }

    public void getScreenShootsCSLinksENG() throws InterruptedException, IOException {
        click(By.linkText("ENG"));
        click(By.linkText("Handbooks"));
        click(By.linkText("Stadiums/Venues"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Cities"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Countries"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Turf type"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Player’s status in the match"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Main positions"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Subpositions"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("create"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Handbooks"));
        click(By.linkText("Teams"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("All teams"));
        Thread.sleep(1000);
        takeScreenShotEng();
        click(By.linkText("Inactive"));
        Thread.sleep(1000);
        takeScreenShotEng();

    }


}


