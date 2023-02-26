package Threads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MySecondThread extends Thread {

    @Override
    public void run() {
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        for (int i = 0; i <= 10000; i++) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            webDriver.get("https://cars.av.by/filter?year[max]=2016&sort=4");
        }
    }
}