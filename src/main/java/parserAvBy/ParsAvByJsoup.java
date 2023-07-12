package parserAvBy;

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
import parserAvBy.repository.ChatIdRepository;
import parserAvBy.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsAvByJsoup extends Thread{
    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();


    public void run(String linkFiltr, String userGetChatId) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        GmailController gmail = new GmailController();
        SimpleBot simpleBot = new SimpleBot();

        System.out.println("...");


        boolean b;

//        for (int i = 0; i < 1000; i++) {
            int t = 1;
            for (int j = 0; j < 28; j++) {
                    Document doc = Jsoup.connect(linkFiltr).get();
//      model
                    String[] model = new String[25];
                    for (int i=1; i< model.length; i++){
                        if (i != 4 && i != 12 && i != 23) {
                            Elements modelEl = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + i + "]/div/div[2]/h3/a/span");
                            String modelStr = modelEl.html().replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "").replaceAll("   ", " ");
                            model[i] = modelStr;
                            System.out.println(modelStr);
                        }
                    }
//      link
                    String[] link = new String[25];
                    for (int i=1; i< link.length; i++){
                        if (i != 4 && i != 12 && i != 23) {
                            Elements linkEl = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + i + "]/div/div[2]/h3/a");
                            String linkStr = "https://cars.av.by" + linkEl.attr("href");
                            System.out.println(linkStr);
                            link[i] = linkStr;
                        }
                    }
//      price
                String[] price = new String[25];
                for (int i=1; i< price.length; i++){
                    if (i != 4 && i != 12 && i != 23) {
                        Elements priceEl = doc.selectXpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[" + i + "]/div/div[4]/div[2]");
                        String priceStr = priceEl.html().replaceAll("≈&nbsp;", "").replaceAll("&nbsp;", "");
                        System.out.println(priceStr);
                        price[i] = priceStr;
                    }
                }
                System.out.println("69");
                    //  это чтобы потом проверять на свежесть обьявления на странице
                    List<String> linkList = new ArrayList<>();
                    List<Post> postList = postRepository.getAllPosts();
                System.out.println("69");
                    for (Post p : postList) {
                        linkList.add(p.getLink());
                    }
                    //
                System.out.println("69");
                    for (int n=0; n<25; n++){
                        System.out.println("hay");
                        b = linkList.contains(link[n]);
                        if (b == false) {
                            post.setModel(model[n]);
                            post.setLink(link[n]);
                            post.setPrice(price[n]);
                            if (model[n] != null && link[n] != null && price[n] != null){
                                postRepository.addPost(post);
                                System.out.println("New car!!!");

                                String message = post.getModel() + "   " + post.getLink() + "   " + post.getPrice();
                                System.out.println(message);

                                //gmail.send(message);
                                simpleBot.sendMessage(message, userGetChatId);
                            }
                        }
                    }
                }
                System.out.println("t=" + t);
                t++;
    }
}