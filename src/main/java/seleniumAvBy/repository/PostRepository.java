package seleniumAvBy.repository;

import seleniumAvBy.model.Post;

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
        List<Post> posts = readPostsFromCSV(ADDRESS_FILE);
        return posts;
    }

    private static List<Post> readPostsFromCSV(String fileName) {
        List<Post> posts = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // read the first line from the text file
            String line = br.readLine();
            // loop until all lines are read
            while (line != null) {
                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split("%");
                Post post = createPost(attributes);
//                   adding book into ArrayList
                posts.add(post);
                // read next line before looping
                // if end of file reached, line would be null
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
        String description = metadata[1];
        String link = metadata[2];
        String price = metadata[3];
        return new Post(model, description, link, price);
    }

    public void addPost (Post post) throws FileNotFoundException {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, true))
        {
            writer.append("\n");
            writer.append(String.valueOf(post.getModel()));
            writer.append("%");
            writer.append(String.valueOf(post.getDescription()));
            writer.append("%");
            writer.append(String.valueOf(post.getLink()));
            writer.append("%");
            writer.append(String.valueOf(post.getPrice()));

            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
