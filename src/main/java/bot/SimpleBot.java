package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import seleniumAvBy.ParsAvBy;
import seleniumAvBy.model.Post;
import seleniumAvBy.model.TgUser;
import seleniumAvBy.repository.ChatIdRepository;
import seleniumAvBy.repository.PostRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SimpleBot extends TelegramLongPollingBot {

    private static ChatIdRepository chatIdRepository = new ChatIdRepository();
    private static PostRepository postRepository = new PostRepository();
    private List<String> chatIdList = new ArrayList<>();

    @Override
    public void onUpdateReceived(Update update) {
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        Post post = new Post();
        TgUser tgUser = new TgUser();
        ParsAvBy parsAvBy = new ParsAvBy();
        SendMessage responce = new SendMessage();
        String messagef = "Нажмите /run для начала";
        responce.setText(messagef);
        try {
            execute(responce);
//                execute(responce1);
        } catch (TelegramApiException E){
            E.printStackTrace();
        }
//        System.out.println(update.getMessage().getText());
//        System.out.println(update.getMessage().getFrom().getFirstName());
        String command = update.getMessage().getText();
        System.out.println("Нажмите /run для начала");
        if (command.equals("/run")){

            String message = post.getModel() + "gay";
            System.out.println("работает метод onUpdateReceived");

            responce.setChatId(update.getMessage().getChatId().toString());
            tgUser.setChatId(responce.getChatId());
            tgUser.setUsername(update.getMessage().getFrom().getFirstName());
            System.out.println(responce.getChatId());
            System.out.println(update.getMessage().getFrom().getFirstName());

            for (TgUser tgUser1 : tgUserList) {
                chatIdList.add(tgUser1.getChatId());
            }

            try {
                boolean b = chatIdList.contains(responce.getChatId());
                    if (b == false){
                        chatIdRepository.addTgUser(tgUser);
                    }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            responce.setText(message);

            try {
                execute(responce);
            } catch (TelegramApiException E){
                E.printStackTrace();
            }
        }
    }

    public void sendMessage (String messageText) {
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();

        for (TgUser tgUser:tgUserList){
            System.out.println("пытаюсь отправить сообщение ТГ");

            SendMessage responce = new SendMessage();
            responce.setChatId(tgUser.getChatId());
            responce.setText(messageText);

//        SendMessage responce1 = new SendMessage();
//        responce1.setChatId("476293411");
//        responce1.setText(messageText);
            try {
                execute(responce);
//                execute(responce1);
            } catch (TelegramApiException E){
                E.printStackTrace();
            }
        }
//        for (String chatId : chatIdList){
//
//            System.out.println("работает метод sendMessage");
//            SendMessage responce = new SendMessage();
//            responce.setChatId(chatId);
//            responce.setText(messageText);
//
//            try {
//                execute(responce);
//            } catch (TelegramApiException E){
//                E.printStackTrace();
//            }
//        }
    }



    @Override
    public String getBotUsername() {
        // TODO
        return "CarAdSenderBot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return "5474927385:AAEYbs7wWidJYiFUs84I9Epq5UjRoqhrkUs";
    }

}
