package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import repository.PostRepository;
import selenium.controller.GmailController;
import selenium.model.Post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppSimple {
    private static PostRepository postRepository = new PostRepository();

    public static void main(String[] args) throws IOException, InterruptedException {
        Post post = new Post();


        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        GmailController gmail = new GmailController();

        for (int i=0; i<=10000; i++) {
            Thread.sleep(100);
            System.out.println("...");
//      all new car
            webDriver.get("https://cars.av.by/filter?year[min]=2019&price_usd[min]=12000&sort=4");

//      luxury car
//            webDriver.get("https://cars.av.by/filter?brands[0][brand]=6&brands[1][brand]=8&brands[2][brand]=40&brands[3][brand]=1&brands[4][brand]=45&brands[5][brand]=330&brands[6][brand]=383&brands[7][brand]=1343&brands[8][brand]=589&brands[9][brand]=683&year[min]=2018&price_usd[min]=12000");

            String model = webDriver.findElement(By.className("listing-item__link")).getText();
            List<WebElement> models = webDriver.findElements(By.className("listing-item__link"));

        System.out.println(model + " model ");
        System.out.println(" models " + models);

            String description = webDriver.findElement(By.className("listing-item__params")).getText().replaceAll("\n", " ");
            String dateOfCreate = webDriver.findElement(By.className("listing-item__date")).getText();
            String link = webDriver.findElement(By.className("listing-item__link")).getAttribute("href");
            String price = webDriver.findElement(By.className("listing-item__price")).getText();

            List<String> linkList = new ArrayList<>();
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
                post.setPrice(price);

                postRepository.addPost(post);
                System.out.println("New car!!!");
                gmail.send(post.getModel()+". Описание: "+post.getDescription()+".   Опубликовано "+post.getDateOfCreate()+"   "+post.getLink()+"   "+post.getPrice());
            }
        }
    }
}
