package copart;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import seleniumAvBy.controller.GmailController;
import seleniumAvBy.model.Post;
import seleniumAvBy.model.TgUser;
import seleniumAvBy.repository.ChatIdRepository;
import seleniumAvBy.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsCopart {

    public void runParsCopart() throws IOException, InterruptedException {
        Post post = new Post();

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);

        webDriver.get("https://www.copart.com/ru/vehicle-search-featured/rentals?displayStr=Rentals&from=%2FvehicleFinder");
        Thread.sleep(5000);
        String model = webDriver.findElement(By.xpath("//*[@id=\"pr_id_52-table\"]/tbody/tr[1]/td[2]/span[2]/div/span/a/span")).getText();

        String assesedValue = webDriver.findElement(By.xpath("//*[@id=\"pr_id_52-table\"]/tbody/tr[1]/td[3]/span[2]/div/div[2]/div/span[1]")).getText();
        String value = webDriver.findElement(By.xpath("//*[@id=\"pr_id_64-table\"]/tbody/tr[1]/td[6]/span[2]/div/div/div/div[1]/span/span[1]")).getText();

        System.out.println(model + assesedValue + value);

//        }
    }
}
