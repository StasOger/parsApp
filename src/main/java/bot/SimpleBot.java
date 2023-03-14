package bot;

import Threads.MyFirstThread;
import Threads.MySecondThread;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
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

    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";

    static final String ERROR_TEXT = "Error occurred: ";

    @Override
    public void onUpdateReceived(Update update) {
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
//    вспомогательный список чтобы обновить базу юзеров после удаления уже существующего
        List<TgUser> tgUser1List = new ArrayList<>();

        ParsAvBy parsAvBy = new ParsAvBy();

        TgUser tgUser = new TgUser();
//        SendMessage responce = new SendMessage();
//        responce.setText("Шаг 1: Откройте сайт https://av.by/ \n" +
//                "Шаг 2: настройте фильтр поиска нужных вам авто с нужными параметрами \n" +
//                "Шаг 3: отправьте эту ссылку в этот чат");
//        //отправка смс в тг
//        responce.setChatId(update.getMessage().getChatId().toString());
////  отправка сообщения
//        try {
//            execute(responce);
//        } catch (TelegramApiException E){
//            E.printStackTrace();
//        }
//        SendMessage responce1 = new SendMessage();
//        responce1.setText(update.getMessage().getText());
////   достаем чатId пользователя
//        tgUser.setChatId(update.getMessage().getChatId().toString());
////   достаем имя пользователя
//        tgUser.setUsername(update.getMessage().getFrom().getFirstName());
////   получаем ссылку (сообщение) от пользователя
//        String linkMessage = update.getMessage().getText(), link = "https://cars.av.by/";
//
////   удалить старую инфу (ссылку от пользователя) если пользователь уже существует
//        for (TgUser tgUser1: tgUserList){
//            long t = Long.parseLong(tgUser.getChatId());
//            long t1 = Long.parseLong(tgUser1.getChatId());
//            if (t != t1){
//                tgUser1List.add(tgUser1);
//            }
//        }
//        System.out.println("пробую удалить нахуй существующих");
//        chatIdRepository.deleteTgUser(tgUser1List);
//
//
//
////   проверка на правильность ссылки
//        if (linkMessage.indexOf(link) != -1) {
//            //если ссылка верна то добавляем и юзера и ссылку
//            tgUser.setLinkFiltr(update.getMessage().getText());
//            try {
//                chatIdRepository.addTgUser(tgUser);
//            } catch (IOException  e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("неверный формат ссылки");
//        }

//     ПАРСЕР
//        for (int i = 0; i <= 20; i++) {
//            for (TgUser tgUser1: tgUserList){
//                try {
//                    parsAvBy.run(tgUser1.getLinkFiltr(), tgUser1.getChatId());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Это команда старт!");
                    System.out.println(message.getText());
                    break;
                case "START":
                    sendMsg(message, "START");
                    System.out.println(message.getText());
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
                    System.out.println("пробую удалить существующих");
                    chatIdRepository.deleteTgUser(tgUser1List);

                    ////   проверка на правильность ссылки
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

                    //     ПАРСЕР
                    for (int i = 0; i <= 5; i++) {
                        for (TgUser tgUser1: tgUserList){
                            try {
                                parsAvBy.run(tgUser1.getLinkFiltr(), tgUser1.getChatId());
                                System.out.println(tgUser1.getChatId() + " chatID " + tgUser1.getUsername());
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    break;
                case "STOP":
                    sendMsg(message, "STOP");
                    System.out.println(message.getText());
                    parsAvBy.close();
                    break;
                default:
                    sendMsg(message, "Это дефолт! Брейк!");
                    System.out.println(message.getText());
                    break;
            }
        }
    }

    public void sendMsg (Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        // Создаем клавиатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new
                ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add("START");
        keyboardFirstRow.add("STOP");

//        // Вторая строчка клавиатуры
//        KeyboardRow keyboardSecondRow = new KeyboardRow();
//        // Добавляем кнопки во вторую строчку клавиатуры
//        keyboardSecondRow.add("Команда 3");
//        keyboardSecondRow.add("Команда 4");

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
//        keyboard.add(keyboardSecondRow);
        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
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
