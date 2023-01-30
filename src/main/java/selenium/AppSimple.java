package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import repository.PostRepository;
import selenium.controller.GmailController;
import selenium.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppSimple {
    private static PostRepository postRepository = new PostRepository();

    public static void main(String[] args) throws IOException, InterruptedException {
        Post post = new Post();
        List<String> linkList = new ArrayList<>();

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        GmailController gmail = new GmailController();

        for (int i=0; i<=100; i++) {
            Thread.sleep(10000);
            webDriver.get("https://cars.av.by/filter?year[min]=2019&price_usd[min]=12000&sort=4");

            String model = webDriver.findElement(By.className("listing-item__link")).getText();
            String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
            String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
            String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");

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
                postRepository.addPost(post);
                System.out.println("Post added");
                gmail.send(post.getModel()+". Описание: "+post.getDescription()+".   Опубликовано "+post.getDateOfCreate()+"   "+post.getLink());
            }
        }
    }
}
