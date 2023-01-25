//package jsoup;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import selenium.Post;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class App {
//    public static void main(String[] args) throws IOException{
//        List<Post> posts = new ArrayList<>();
//        System.out.println("Подключение к главной странице");
//        Document doc = Jsoup.connect("https://4pda.to/").get();
//        Elements postTitleElements = doc.getElementsByAttributeValue("itemprop", "url");
//        for (Element postTitleElement : postTitleElements) {
//            String detailsLink = postTitleElement.attr("href");
//            Post post = new Post();
//            post.setDetailsLink(detailsLink);
//            post.setTitle(postTitleElement.attr("href"));
//            System.out.println("Подключение к деталям о посте: " + detailsLink);
//            Document postDetailsDoc = Jsoup.connect(detailsLink).get();
//            try{
//                Element authorElement = postDetailsDoc.getElementsByClass("name").first().child(0);
//                post.setAuthor(postDetailsDoc.getElementsByClass("name").first().child(0).text());
//                post.setAuthorDetailsLink(authorElement.attr("href"));
//            } catch (NullPointerException e) {
//                post.setAuthor("Автор не определился");
//                post.setAuthorDetailsLink("Ссылка не определилась");
//            }
//            post.setDateOfCreate(postDetailsDoc.getElementsByClass("date").first().text());
//            posts.add(post);
//        }
//        posts.forEach(System.out::println);
//    }
//}
