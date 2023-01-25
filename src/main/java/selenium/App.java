package selenium;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.controller.GmailController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException{
        Post post = new Post();
        List<Post> posts = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://cars.av.by/filter?year[min]=2019&price_usd[min]=12000&sort=4");

        String model = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[1]/div/div[2]/h3/a")).getText();
        String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
        String description = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[1]/div/div[3]")).getText();
        String dateOfCreate = webDriver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/main/div/div/div[1]/div[4]/div[3]/div/div[3]/div/div[1]/div/div[6]/div[2]")).getText();

        post.setModel(model);
        post.setLink(link);
        post.setDescription(description);
        post.setGetDateOfCreate(dateOfCreate);

        posts.add(post);

        System.out.println(model+". Описание: "+description+".   Опубликовано "+dateOfCreate+"   "+link);

        GmailController gmail = new GmailController();
        gmail.send(model+". Описание: "+description+".   Опубликовано "+dateOfCreate+"   "+link);

    }
}
