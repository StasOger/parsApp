import bot.SimpleBot;
import com.google.auto.common.BasicAnnotationProcessor;
import copart.ParsCopart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import seleniumAvBy.ParsAvBy;

import java.io.IOException;

public class MainApp {


    public static void main(String[] args) throws IOException, InterruptedException {
//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new SimpleBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

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

//        ParsCopart parsCopart = new ParsCopart();
//        parsCopart.runParsCopart();

    }
}
