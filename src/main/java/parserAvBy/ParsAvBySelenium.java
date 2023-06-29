package parserAvBy;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import model.TgUser;
import parserAvBy.repository.ChatIdRepository;
import parserAvBy.repository.PostRepository;
import controller.GmailController;
import model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsAvBySelenium {
    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();

    public void close(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.close();
    }

    public void run(String linkFiltr, String userGetChatId) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver112.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver webDriver = new ChromeDriver(options);

        webDriver = new ChromeDriver(options);

        GmailController gmail = new GmailController();
        SimpleBot simpleBot = new SimpleBot();

                System.out.println("...");

                webDriver.get(linkFiltr);

                boolean b;
                int t = 1;
//                while (b != false && t<23) {
                for (int j=0; j<40; j++){
                    if (t != 4 && t != 12 && t != 23 && t != 28 ){
                        String model = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[2]/h3/a/span")).getText();

                        String description = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[3]")).getText().replaceAll("\n", " ");

                        String link = webDriver.findElement(By.xpath("/html/body/div[1]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div["+t+"]/div/div[2]/h3/a")).getAttribute("href");

                        String price = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[4]/div[1]")).getText();

                        //  это чтобы потом проверять на свежесть обьявления на странице
                        List<String> linkList = new ArrayList<>();
                        List<Post> postList = postRepository.getAllPosts();

                        for (Post p : postList) {
                            linkList.add(p.getLink());
                        }
//

                        b = linkList.contains(link);
                        if (b == false) {
                            post.setModel(model);
                            post.setDescription(description);
                            post.setLink(link);
                            post.setPrice(price);
                            postRepository.addPost(post);
                            System.out.println("New car!!!");

                            String message = post.getModel() + ". Описание: " + post.getDescription() + "   " + post.getLink() + "   " + post.getPrice();
                            System.out.println(message);

                            //gmail.send(message);
                            simpleBot.sendMessage(message, userGetChatId);
                        }
                    }
                    System.out.println("t=" + t);
                    t++;
                    webDriver.close();
                }
    }
}
