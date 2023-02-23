package seleniumAvBy;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import seleniumAvBy.model.TgUser;
import seleniumAvBy.repository.ChatIdRepository;
import seleniumAvBy.repository.PostRepository;
import seleniumAvBy.controller.GmailController;
import seleniumAvBy.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsAvBy {
    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();

    public void run(String linkFiltr, String command) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        GmailController gmail = new GmailController();
        SimpleBot simpleBot = new SimpleBot();

        if (command.equals("/run")) {
            for (int i = 0; i <= 10000; i++) {
//            Thread.sleep(5000);
                System.out.println("...");
//      all new car
                System.out.println(linkFiltr + " hui  ");
                webDriver.get(linkFiltr);

//      luxury car
//            webDriver.get("https://cars.av.by/filter?brands[0][brand]=6&brands[1][brand]=8&brands[2][brand]=40&brands[3][brand]=1&brands[4][brand]=45&brands[5][brand]=330&brands[6][brand]=383&brands[7][brand]=1343&brands[8][brand]=589&brands[9][brand]=683&year[min]=2018&price_usd[min]=12000");
//            String model = (new WebDriverWait(webDriver, 10))
//                    .until(ExpectedConditions.presenceOfElementLocated(By.className("listing-item__link");

                String model = webDriver.findElement(By.className("listing-item__link")).getText();
                String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
                String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
                String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
                String price = webDriver.findElement(By.className("listing-item__price")).getText();

                List<String> linkList = new ArrayList<>();
                List<Post> postList = postRepository.getAllPosts();

                for (Post p : postList) {
                    linkList.add(p.getLink());
                }

                boolean b = linkList.contains(link);

                if (b == false) {
                    post.setModel(model);
                    post.setDescription(description);
                    post.setDateOfCreate(dateOfCreate);
                    post.setLink(link);
                    post.setPrice(price);
                    postRepository.addPost(post);

                    System.out.println("New car!!!");

                    String message = post.getModel() + ". Описание: " + post.getDescription() + ".   Опубликовано " + post.getDateOfCreate() + "   " + post.getLink() + "   " + post.getPrice();

                    gmail.send(message);
                    simpleBot.sendMessage(message);
                }
            }
        } else if (command.equals("/stop")){
            webDriver.close();
        } else {
            System.out.println("я нихера не понял что ты хочешь");
        }
    }
}
