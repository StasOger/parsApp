package seleniumAvBy;

import bot.SimpleBot;
import controller.GmailController;
import model.Post;
import model.TgUser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import seleniumAvBy.repository.ChatIdRepository;
import seleniumAvBy.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsAvByJsoup {
    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();

    public void run(String linkFiltr, String userGetChatId) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        GmailController gmail = new GmailController();
        SimpleBot simpleBot = new SimpleBot();

        System.out.println("...");


        boolean b;

        for (int i = 0; i < 1000; i++) {
            int t = 1;
            for (int j = 0; j < 28; j++) {
                if (t != 4 && t != 12 && t != 23) {
                    Document doc = Jsoup.connect(linkFiltr).get();
                    Elements model = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[2]/h3/a/span");
                    String modelStr = model.html().replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "").replaceAll("   ", " ");
                    System.out.println(modelStr);

                    Elements link = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[2]/h3/a");
                    String linkStr = "https://cars.av.by" + link.attr("href");
                    System.out.println(linkStr);

                    Elements price = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + t + "]/div/div[4]/div[2]");
                    String priceStr = price.html().replaceAll("≈&nbsp;", "").replaceAll("&nbsp;", "");
                    System.out.println(priceStr);

                    //  это чтобы потом проверять на свежесть обьявления на странице
                    List<String> linkList = new ArrayList<>();
                    List<Post> postList = postRepository.getAllPosts();

                    for (Post p : postList) {
                        linkList.add(p.getLink());
                    }
                    //
                    System.out.println("hay");
                    b = linkList.contains(linkStr);
                    if (b == false) {
                        post.setModel(modelStr);
                        post.setLink(linkStr);
                        post.setPrice(priceStr);
                        postRepository.addPost(post);
                        System.out.println("New car!!!");

                        String message = post.getModel() + "   " + post.getLink() + "   " + post.getPrice();
                        System.out.println(message);

                        //gmail.send(message);
                        simpleBot.sendMessage(message, userGetChatId);
                    }
                }
                System.out.println("t=" + t);
                t++;
            }
        }
    }
}