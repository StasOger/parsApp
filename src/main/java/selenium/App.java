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

    public static void main(String[] args) throws IOException{

        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://cars.av.by/filter?year[min]=2019&price_usd[min]=12000&sort=4");

        String model = webDriver.findElement(By.className("link-text")).getText();
        String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
        String description = webDriver.findElement(By.className("listing-item__params")).getText();
        String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();

        List<Post> postList = postRepository.getAllPosts();

        postRepository.addPost(new Post(model, description, dateOfCreate, link));


        System.out.println(model+". Описание: "+description+".   Опубликовано "+dateOfCreate+"   "+link);
//
//        GmailController gmail = new GmailController();
//        gmail.send(model+". Описание: "+description+".   Опубликовано "+dateOfCreate+"   "+link);

    }
}
