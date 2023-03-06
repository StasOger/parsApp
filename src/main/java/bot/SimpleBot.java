package bot;

import Threads.MyFirstThread;
import Threads.MySecondThread;
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
import java.io.IOException;
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
//    вспомогательный список чтобы обновить базу юзеров после удаления уже существующего
        List<TgUser> tgUser1List = new ArrayList<>();

        ParsAvBy parsAvBy = new ParsAvBy();

        TgUser tgUser = new TgUser();
        SendMessage responce = new SendMessage();
        responce.setText("Шаг 1: Откройте сайт https://av.by/ \n" +
                "Шаг 2: настройте фильтр поиска нужных вам авто с нужными параметрами \n" +
                "Шаг 3: отправьте эту ссылку в этот чат");
        //отправка смс в тг
        responce.setChatId(update.getMessage().getChatId().toString());
//  отправка сообщения
        try {
            execute(responce);
        } catch (TelegramApiException E){
            E.printStackTrace();
        }
        SendMessage responce1 = new SendMessage();
        responce1.setText(update.getMessage().getText());
//   достаем чатId пользователя
        tgUser.setChatId(update.getMessage().getChatId().toString());
//   достаем имя пользователя
        tgUser.setUsername(update.getMessage().getFrom().getFirstName());
//   получаем ссылку (сообщение) от пользователя
        String linkMessage = update.getMessage().getText(), link = "https://cars.av.by/";

//   удалить старую инфу (ссылку от пользователя) если пользователь уже существует
        for (TgUser tgUser1: tgUserList){
            long t = Long.parseLong(tgUser.getChatId());
            long t1 = Long.parseLong(tgUser1.getChatId());
            if (t != t1){
                tgUser1List.add(tgUser1);
            }
        }
        System.out.println("пробую удалить нахуй существующих");
        chatIdRepository.deleteTgUser(tgUser1List);
//


//   проверка на правильность ссылки
        if (linkMessage.indexOf(link) != -1) {
            //если ссылка верна то добавляем и юзера и ссылку
            tgUser.setLinkFiltr(update.getMessage().getText());
            try {
                chatIdRepository.addTgUser(tgUser);
            } catch (IOException  e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("неверный формат ссылки");
        }
        for (int i = 0; i <= 20; i++) {
            for (TgUser tgUser1: tgUserList){
                try {
                    parsAvBy.run(tgUser1.getLinkFiltr(), tgUser1.getChatId());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void sendMessage (String messageText, String userGetChatId) {
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
        for (TgUser tgUser:tgUserList){
            System.out.println("пытаюсь отправить сообщение ТГ");

            SendMessage responce = new SendMessage();
            responce.setChatId(tgUser.getChatId());
            responce.setText(messageText);
            try {
                if (tgUser.getChatId().equals(userGetChatId)){
                    execute(responce);
                }
            } catch (TelegramApiException E){
                E.printStackTrace();
            }
        }
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
