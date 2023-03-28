import bot.SimpleBot;
import com.google.auto.common.BasicAnnotationProcessor;
import copart.ParsCopart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import seleniumAvBy.ParsAvBy;
import seleniumAvBy.controller.GmailController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainApp {



//    public static void main(String[] args) throws IOException, InterruptedException {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new SimpleBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

//        ParsCopart parsCopart = new ParsCopart();
//        parsCopart.runParsCopart();
//
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");
//        WebDriver webDriver = new ChromeDriver(options);
//
//        webDriver.get("https://yandex.by/maps/?ll=29.859188%2C53.018765&mode=whatshere&whatshere%5Bpoint%5D=29.853281%2C53.141473&whatshere%5Bzoom%5D=7.9&z=7.9");
//        String element = webDriver.findElement(By.xpath("/html/body/div[1]/div[1]/ymaps/ymaps[2]/ymaps/ymaps[6]/ymaps[2]/div/div/div/div[2]/div")).getText();
//        System.out.println(element);

//    }
//
//
    public static void main(String[] args) throws InterruptedException {

            MySecondThread thread1 = new MySecondThread();
            MyFirstThread thread = new MyFirstThread();
            thread.start();
            thread1.start();
    }


    public static class MyFirstThread extends Thread {
        @Override
        public void run() {
            System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver webDriver = new ChromeDriver(options);

            for (int i = 0; i < 3; i++) {
                webDriver.get("https://baltacom.com/");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String element = webDriver.findElement(By.xpath("//*[@id=\"main\"]/div/div/div[2]/div/div/div[3]")).getText();
                System.out.println(element + " 1 ");
            }
        }
    }

    public static class MySecondThread extends Thread {
        @Override
        public void run() {
            System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            WebDriver webDriver = new ChromeDriver(options);

            for (int i = 0; i < 3; i++) {
                webDriver.get("https://baltacom.com/catalog/");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String element = webDriver.findElement(By.xpath("//*[@id=\"comp_e0dad8dd0731ebb9ed61d96e72895f9d\"]/div/div/div[2]/div/div/div[5]/span")).getText();
                System.out.println(element + " 2 ");
            }
        }
    }

}

