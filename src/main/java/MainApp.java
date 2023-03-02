import Threads.MyFirstThread;
import Threads.MySecondThread;
import bot.SimpleBot;
import com.google.auto.common.BasicAnnotationProcessor;
import copart.ParsCopart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import seleniumAvBy.ParsAvBy;

import java.io.IOException;

public class MainApp {


    public static void main(String[] args) throws IOException, InterruptedException {

        ParsAvBy parsAvBy = new ParsAvBy();
        parsAvBy.run("https://cars.av.by/filter?year[min]=2018&sort=4");



//        try {
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new SimpleBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//        ParsCopart parsCopart = new ParsCopart();
//        parsCopart.runParsCopart();
    }
}
