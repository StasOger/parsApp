package Threads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class MySecondThread extends Thread {

    @Override
    public void run() {

    }

    public String userParser2(String linkFiltr) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(linkFiltr);
        return linkFiltr;
    }
}