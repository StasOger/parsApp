
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        List<Post> posts = new ArrayList<>();
        Document doc = Jsoup.connect("https://4pda.to/").get();
        Elements postTitleElements = doc.getElementsByAttributeValue("itemprop", "url");
        for (Element postTitleElement : postTitleElements) {
            String detailsLink = postTitleElement.attr("href");
            Post post = new Post();
            post.setDetailsLink(detailsLink);
            post.setTitle(postTitleElement.attr("href"));
            Document postDetailsDoc = Jsoup.connect(detailsLink).get();
            post.setAuthor(postDetailsDoc.getElementsByClass("name").first().child(0).text());
        }
    }
}
