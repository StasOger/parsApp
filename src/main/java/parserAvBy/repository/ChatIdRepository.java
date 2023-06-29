package parserAvBy.repository;

import model.TgUser;

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

public class ChatIdRepository {
    private static final String ADDRESS_FILE = "src/main/resources/TgUser.csv";

    public List<TgUser> getAllTgUsers (){
        List<TgUser> tgUsers = readTgUsersFromCSV(ADDRESS_FILE);
        return tgUsers;
    }

    public void deleteTgUser(List<TgUser> tgUserList) {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, false))
        {
            for (int i = 0; i < tgUserList.size(); i++) {
                if (i!=0) {
                    writer.append("\n");
                }
                TgUser tgUser = tgUserList.get(i);
                writer.append(String.valueOf(tgUser.getChatId()));
                writer.append("%");
                writer.append(String.valueOf(tgUser.getUsername()));
                writer.append("%");
                writer.append(String.valueOf(tgUser.getLinkFiltr()));
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static List<TgUser> readTgUsersFromCSV(String fileName) {
        List<TgUser> tgUsers = new ArrayList<>();
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
                TgUser tgUser = createTgUser(attributes);
//                   adding book into ArrayList
                tgUsers.add(tgUser);
                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return tgUsers;
    }

    private static TgUser createTgUser(String[] metadata) {
        String chatId = metadata[0];
        String username = metadata[1];
        String linkFiltr = metadata[2];
        return new TgUser(chatId,username, linkFiltr);
    }

    public void addTgUser (TgUser tgUser) throws FileNotFoundException {
        try(FileWriter writer = new FileWriter(ADDRESS_FILE, true))
        {
            writer.append("\n");
            writer.append(String.valueOf(tgUser.getChatId()));
            writer.append("%");
            writer.append(String.valueOf(tgUser.getUsername()));
            writer.append("%");
            writer.append(String.valueOf(tgUser.getLinkFiltr()));
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
