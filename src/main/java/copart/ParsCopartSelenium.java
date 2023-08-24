//package copart;
//
//import bot.SimpleBot;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import model.Post;
//import model.TgUser;
//import parserAvBy.repository.ChatIdRepository;
//import parserAvBy.repository.PostRepository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ParsCopartSelenium {
//
//    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
//    private static PostRepository postRepository = new PostRepository();
//
//
//    public void runParsCopart(String userGetChatId) throws IOException, InterruptedException {
//        Post post = new Post();
//
//        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
//        SimpleBot simpleBot = new SimpleBot();
//
//
//        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver111.exe");
////        ChromeOptions options = new ChromeOptions();
////        options.addArguments("--remote-allow-origins=*");
//        WebDriver webDriver = new ChromeDriver(options);
//
//        ChromeOptions options = new ChromeOptions();
//        webDriver = new ChromeDriver(options);
//        options.addArguments("--start-maximized");
//
//        webDriver.get("https://www.copart.com/ru/vehicle-search-featured/rentals?displayStr=Rentals&from=%2FvehicleFinder");
//        Thread.sleep(5000);
//
//        boolean b;
//
//        String model = webDriver.findElement(By.xpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[2]/span[2]/div/span/a/span")).getText();
//
//        String description = webDriver.findElement(By.xpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[4]/span[2]/div/div[1]/span")).getText();
//
//        String link = webDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div/app-root/vehicle-search-results/search-results/div/div[2]/div[2]/search-table-component/copart-table/div/p-table/div/div[1]/table/tbody/tr[1]/td[2]/span[2]/div/span/a")).getAttribute("href");
//
//        String price = webDriver.findElement(By.xpath("//*[@id=\"pr_id_1-table\"]/tbody/tr[1]/td[3]/span[2]/div/div[2]/div/span[1]")).getText();
//
//        //  это чтобы потом проверять на свежесть обьявления на странице
//        List<String> linkList = new ArrayList<>();
//        List<Post> postList = postRepository.getAllPosts();
//
//        for (Post p : postList) {
//            linkList.add(p.getLink());
//        }
////
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
//
////        }
//    }
//}