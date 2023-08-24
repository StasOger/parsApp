package copart;

import bot.SimpleBot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

public class ParsCopartJsoup {

    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();


    public void runParsCopart(String userGetChatId) throws IOException, InterruptedException {
        Post post = new Post();

        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
        SimpleBot simpleBot = new SimpleBot();


        //  подключаемся к стронице
        Document doc = Jsoup.connect("https://www.copart.com/ru/vehicleFinderSearch?displayStr=%5B0%20TO%209999999%5D,%5B2013%20TO%202024%5D&from=%2FnoSearchResults%3FdisplayStr%3DRentals&query=&searchCriteria=%7B%22query%22:%5B%22*%22%5D,%22filter%22:%7B%22VEHT%22:%5B%22vehicle_type_code:VEHTYPE_V%22%5D,%22ODM%22:%5B%22%23OdometerReading:%5B0%20TO%209999999%5D%22%5D,%22YEAR%22:%5B%22%23LotYear:%5B2013%20TO%202024%5D%22%5D,%22NLTS%22:%5B%22expected_sale_assigned_ts_utc:%5BNOW%2FDAY-1DAY%20TO%20NOW%2FDAY%5D%22%5D%7D,%22searchName%22:%22%22,%22watchListOnly%22:false,%22freeFormSearch%22:false%7D").get();
        System.out.println("copart read");
        System.out.println(doc);

        boolean b;

        //  model
//        String[] model = new String[25];
//        for (int i = 1; i < model.length; i++) {
//            if (i != 4 && i != 12 && i != 23) {
                Elements modelEl = doc.selectXpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[3]/span[2]/div/div[1]/div");
                String modelStr = modelEl.html().replaceAll("[^\\da-zA-Zа-яёА-ЯЁ ]", "").replaceAll("   ", " ");
                System.out.println(modelEl);
//                model[i] = modelStr;
//            }
//        }
        //  link
//        String[] link = new String[25];
//        for (int i = 1; i < link.length; i++) {
//            if (i != 4 && i != 12 && i != 23) {
                Elements linkEl = doc.selectXpath("/html/body/div[3]/div[3]/div/app-root/vehicle-search-results/search-results/div/div[2]/div[2]/search-table-component/copart-table/div/p-table/div/div[1]/table/tbody/tr[1]/td[2]/span[2]/div/span/a");
                String linkStr = "https://cars.av.by" + linkEl.attr("href");
                System.out.println(linkStr);
//                link[i] = linkStr;
//            }
//        }
//  price
//        String[] price = new String[25];
//        for (int i = 1; i < price.length; i++) {
//            if (i != 4 && i != 12 && i != 23) {
                Elements priceEl = doc.selectXpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[3]/span[2]/div/div[2]/div/span[1]");
                String priceStr = priceEl.html().replaceAll("≈&nbsp;", "").replaceAll("&nbsp;", "");
                System.out.println(priceStr);
//                price[i] = priceStr;
//            }
//        }

        //  это чтобы потом проверять на свежесть обьявления на странице
        List<String> linkList = new ArrayList<>();
        List<Post> postList = postRepository.getAllPosts();

        for (Post p : postList) {
            linkList.add(p.getLink());
        }
//

//        b = linkList.contains(link);
//        if (b == false) {
//            post.setModel(model);
//            post.setDescription(description);
//            post.setLink(link);
//            post.setPrice(price);
//            postRepository.addPost(post);
//            System.out.println("New car!!!");
//
//            String message = post.getModel() + ". Описание: " + post.getDescription() + "   " + post.getLink() + "   " + post.getPrice();
//            System.out.println(message);
//
//            //gmail.send(message);
//            simpleBot.sendMessage(message, userGetChatId);
//        }

//        }
    }
}
