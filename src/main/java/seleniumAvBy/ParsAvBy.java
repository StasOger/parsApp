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

    public void run(String linkFiltr) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        GmailController gmail = new GmailController();
        SimpleBot simpleBot = new SimpleBot();

//  это чтобы потом проверять на свежесть обьявления на странице
        List<String> linkList = new ArrayList<>();
        List<Post> postList = postRepository.getAllPosts();

        for (Post p : postList) {
            linkList.add(p.getLink());
        }
//
            int t = 1;
            for (int i = 0; i <= 20; i++) {
                Thread.sleep(500);
                System.out.println("...");

                webDriver.get(linkFiltr);

                boolean b = true;

                while (b != false) {
                    if (t != 4){
                        String model = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[2]/h3/a/span")).getText();
                        //*[@id="__next"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[5]/div/div[2]/h3/a/span
                        System.out.println(model);

                        String description = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[3]")).getText().replaceAll("\n", " ");
                        System.out.println(description);
                        Thread.sleep(1000);
                        String dateOfCreate = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[1]/div/div[6]/div[2]")).getText();
                        System.out.println(dateOfCreate);

                        String link = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[1]/div/div[2]/h3/a")).getAttribute("href");
                        System.out.println(link);

                        String price = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[4]/div[1]")).getText();
                        System.out.println(price);

                        b = linkList.contains(link);
                        if (b == false) {
                            post.setModel(model);
                            post.setDescription(description);
                            post.setDateOfCreate(dateOfCreate);
                            post.setLink(link);
                            post.setPrice(price);
                            postRepository.addPost(post);
                            System.out.println("New car!!!");

                            String message = post.getModel() + ". Описание: " + post.getDescription() + ".   Опубликовано " + post.getDateOfCreate() + "   " + post.getLink() + "   " + post.getPrice();
                            System.out.println(message);

//                        gmail.send(message);
//                    simpleBot.sendMessage(message, userGetChatId);
                        }
                    }
                    t++;
                    System.out.println("t=" + t);
                }
            }
    }
}
