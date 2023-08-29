package copart;

import bot.SimpleBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import model.Post;
import model.TgUser;
import parserAvBy.repository.ChatIdRepository;
import parserAvBy.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsCopartSelenium {

    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();


    public void runParsCopart(String userGetChatId) throws IOException, InterruptedException {
        Post post = new Post();

        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
        SimpleBot simpleBot = new SimpleBot();


        WebDriver webDriver = new ChromeDriver();

        webDriver.get("https://www.copart.com/ru/vehicle-search-featured/rentals?displayStr=Rentals&from=%2FvehicleFinder&searchCriteria=%7B%22query%22:%5B%22*%22%5D,%22filter%22:%7B%22FETI%22:%5B%22lot_features_code:rental%22%5D,%22NLTS%22:%5B%22expected_sale_assigned_ts_utc:%5BNOW%2FDAY-1DAY%20TO%20NOW%2FDAY%5D%22%5D%7D,%22searchName%22:%22%22,%22watchListOnly%22:false,%22freeFormSearch%22:false%7D");
        Thread.sleep(5000);

        boolean b;

        String model = webDriver.findElement(By.xpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[2]/span[2]/div/span/a/span")).getText();
        System.out.println(model + " model copart ");
        String link = webDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div/app-root/vehicle-search-results/search-results/div/div[2]/div[2]/search-table-component/copart-table/div/p-table/div/div[1]/table/tbody/tr[1]/td[1]/span[2]/span/a")).getAttribute("href");
        System.out.println(link + " link copart ");
        String price = webDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div/app-root/vehicle-search-results/search-results/div/div[2]/div[2]/search-table-component/copart-table/div/p-table/div/div[1]/table/tbody/tr[1]/td[6]/span[2]/div/div/div/div[1]/span/span[1]")).getText();
        System.out.println(price + " price copart ");
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
            post.setLink(link);
            post.setPrice(price);
            postRepository.addPost(post);
            System.out.println("New car!!!");

            String message = post.getModel() + "   " + post.getLink() + "  текущая ставка  " + post.getPrice();
            System.out.println(message);

            //gmail.send(message);
            simpleBot.sendMessage(message, userGetChatId);
        }

//        }
    }
}