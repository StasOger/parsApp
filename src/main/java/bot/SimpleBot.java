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

        TgUser tgUser = new TgUser();
        SendMessage responce = new SendMessage();

        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());


        String command = update.getMessage().getText();
        if (command.equals("/run")){
            responce.setText("Шаг 1: Откройте сайт https://av.by/ \n" +
                    "Шаг 2: настройте фильтр поиска нужных вам авто с нужными параметрами \n" +
                    "Шаг 3: отправьте эту ссылку в этот чат");
            System.out.println("работает метод onUpdateReceived");
            String linkFromUser = update.getMessage().getText();
            System.out.println(linkFromUser);
            long userChanId = update.getMessage().getChatId();
            System.out.println(userChanId);

            System.out.println("идем дальше");
            responce.setChatId(update.getMessage().getChatId().toString());
            System.out.println("идем дальше1");
            tgUser.setChatId(responce.getChatId());
            System.out.println("идем дальше2");
            tgUser.setUsername(update.getMessage().getFrom().getFirstName());
            System.out.println("идем дальше3");
            tgUser.setLinkFiltr(update.getMessage().getText());

            System.out.println(update.getMessage().getFrom().getFirstName());

            System.out.println(update.getMessage().getText());

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
