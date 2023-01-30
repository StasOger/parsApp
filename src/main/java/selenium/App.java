package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import repository.PostRepository;
import selenium.controller.GmailController;
import selenium.model.Post;

import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static PostRepository postRepository = new PostRepository();

    public static void main(String[] args) throws IOException, InterruptedException {
        Post post = new Post();

//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
//        WebDriver webDriver = new ChromeDriver();

//        for (int i=0; i<=20; i++) {
//            Thread.sleep(10000);
//            webDriver.get("https://cars.av.by/filter?year[min]=2019&price_usd[min]=12000&sort=4");
//            post.setModel(webDriver.findElement(By.className("listing-item__link")).getText());
//            post.setLink(webDriver.findElement(By.className("listing-item__link")).getAttribute("href"));
//            post.setDescription(webDriver.findElement(By.className("listing-item__params")).getText());
//            post.setDateOfCreate(webDriver.findElement(By.className("listing-item__date")).getText());
//            postRepository.addPost(post);

//        }
    }
}
