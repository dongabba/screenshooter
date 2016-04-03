package com.gabba;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        AppLogic appLogic = new AppLogic();
        appLogic.startBrowser();
        Page page = new Page(appLogic.getDriver());
        page.userLogin("azhaleiko", "123456");
        page.getAllLinks();
        appLogic.closeBrowser();
    }
}