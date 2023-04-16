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
import seleniumAvBy.model.Post;
import seleniumAvBy.model.TgUser;

import java.io.IOException;
import java.util.List;

public class MainApp {


    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new SimpleBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
