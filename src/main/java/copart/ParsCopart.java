package copart;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.copart.de/en/search/bmw/?displayStr=BMW&from=%2FvehicleFinder");
//    <span data-uname="lotsearchLotmake">BMW</span>
        String model = webDriver.findElement(By.xpath("//*[@id=\"serverSideDataTable\"]/tbody/tr[1]/td[5]/span")).getText();
        System.out.println(model);
//        String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
//        String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
//        String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
//        String price = webDriver.findElement(By.className("listing-item__price")).getText();
    }
}
