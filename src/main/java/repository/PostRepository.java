package repository;

import selenium.model.Post;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PostRepository {

    private static final String ADDRESS_FILE = "src/main/resources/Posts.csv";

    public List<Post>  getAllPosts (){
        return readPostsFromCSV(ADDRESS_FILE);
    }

    private static List<Post> readPostsFromCSV(String fileName) {
        List<Post> posts = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {

            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Post post = createPost(attributes);
                posts.add(post);
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return posts;
    }

    private static Post createPost(String[] metadata) {
        String model = metadata[0];
        String link = metadata[1];
        String description = metadata[2];
        String dateOfCreate = metadata[3];
        return new Post(model, link, description, dateOfCreate);
    }

    public void addPost (Post post) throws FileNotFoundException {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, true))
        {
            writer.append("\n");
            writer.append(String.valueOf(post.getModel()));
            writer.append(":");
            writer.append(String.valueOf(post.getDescription()));
            writer.append(":");
            writer.append(String.valueOf(post.getDateOfCreate()));
            writer.append(":");
            writer.append(String.valueOf(post.getLink()));

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
