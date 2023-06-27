package Threads;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import seleniumAvBy.repository.ChatIdRepository;
import seleniumAvBy.repository.PostRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MySecondThread extends Thread {

    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();
    private List<String> chatIdList = new ArrayList<>();


    @Override
    public void run() {
        System.out.println("я новый поток");
        for (int i = 1; i < 50; i++) {

            System.out.println("света");
        }
//        Post post = new Post();
//        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
//
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver112.exe");
//        WebDriver webDriver = new ChromeDriver();
//        GmailController gmail = new GmailController();
//        SimpleBot simpleBot = new SimpleBot();
//
//        for (int i = 0; i <= 10000; i++) {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("...");
//
//                webDriver.get("https://cars.av.by/filter?brands[0][brand]=8&brands[1][brand]=1238&brands[2][brand]=589&brands[3][brand]=330&brands[3][model]=359&brands[4][brand]=6&year[min]=2016&sort=4");
//
//                String model = webDriver.findElement(By.className("listing-item__link")).getText();
//                String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
//                String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
//                String price = webDriver.findElement(By.className("listing-item__price")).getText();
//
//                List<String> linkList = new ArrayList<>();
//                List<Post> postList = postRepository.getAllPosts();
//
//                for (Post p : postList) {
//                    linkList.add(p.getLink());
//                }
//
//                boolean b = linkList.contains(link);
//
//                if (b == false) {
//                    post.setModel(model);
//                    post.setDescription(description);
//                    post.setLink(link);
//                    post.setPrice(price);
//                    try {
//                        postRepository.addPost(post);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("New car!!!");
//
//                    String message = post.getModel() + ". Описание: " + post.getDescription() + "   " + post.getLink() + "   " + post.getPrice();
//
//                    gmail.send(message);
//                    simpleBot.sendMessage(message, "481347065");
//                }
//
//        }
    }

}