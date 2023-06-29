package Threads;

import model.Post;
import model.TgUser;
import parserAvBy.repository.ChatIdRepository;
import parserAvBy.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyFirstThread extends Thread {

    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();
    private List<String> chatIdList = new ArrayList<>();

    @Override
    public void run() {
        System.out.println("я новый поток");
//        Post post = new Post();
//        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
//
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
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
//            for (TgUser tguser : tgUserList) {
//                webDriver.get(tguser.getLinkFiltr());
//
//                String model = webDriver.findElement(By.className("listing-item__link")).getText();
//                String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
//                String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
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
//                    post.setDateOfCreate(dateOfCreate);
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
//                    String message = post.getModel() + ". Описание: " + post.getDescription() + ".   Опубликовано " + post.getDateOfCreate() + "   " + post.getLink() + "   " + post.getPrice();
//
//                    gmail.send(message);
//                    simpleBot.sendMessage(message);
//                }
//            }
//        }
    }



    public String userParser(String linkFiltr) throws IOException, InterruptedException {
        Post post = new Post();
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
//        WebDriver webDriver = new ChromeDriver();
//        GmailController gmail = new GmailController();
//        SimpleBot simpleBot = new SimpleBot();

        for (int i = 0; i <= 10000; i++) {
            try {
                Thread.sleep(5000);
                    System.out.println(linkFiltr + " " + linkFiltr);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("...");
//            for (TgUser tguser : tgUserList) {
//                webDriver.get(tguser.getLinkFiltr());
//
//                String model = webDriver.findElement(By.className("listing-item__link")).getText();
//                String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
//                String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
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
//                    post.setDateOfCreate(dateOfCreate);
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
//                    String message = post.getModel() + ". Описание: " + post.getDescription() + ".   Опубликовано " + post.getDateOfCreate() + "   " + post.getLink() + "   " + post.getPrice();
//
//                    gmail.send(message);
//                    simpleBot.sendMessage(message);
//                }
//            }
        }

        System.out.println("я новый метод юзерпарсер");
        return linkFiltr;
    }
}