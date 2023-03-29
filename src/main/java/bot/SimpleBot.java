package bot;

import Threads.MyFirstThread;
import Threads.MySecondThread;
import copart.ParsCopart;
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

    @Override
    public void onUpdateReceived(Update update) {
        String link = "https://cars.av.by/";
        List<TgUser> tgUserList = chatIdRepository.getAllTgUsers();
////    вспомогательный список чтобы обновить базу юзеров после удаления уже существующего
        List<TgUser> tgUser1List = new ArrayList<>();

        ParsAvBy parsAvBy = new ParsAvBy();
        ParsCopart parsCopart = new ParsCopart();
        TgUser tgUser = new TgUser();

//   достаем чатId пользователя
        tgUser.setChatId(update.getMessage().getChatId().toString());
//   достаем имя пользователя
        tgUser.setUsername(update.getMessage().getFrom().getFirstName());

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("FIND ON AV.BY")){
                try {
                    sendMsg(message, "FIND ON AV.BY");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ////     ПАРСЕР
                for (TgUser tgUser1: tgUserList){
                    if (tgUser1.getChatId().equals(tgUser.getChatId())) {
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
            } else if (message.getText().indexOf(link) != -1){
// удалить старую инфу (ссылку от пользователя) если пользователь уже существует
                for (TgUser tgUser1: tgUserList){
                    long t = Long.parseLong(tgUser.getChatId());
                    long t1 = Long.parseLong(tgUser1.getChatId());
                    if (t != t1){
                        tgUser1List.add(tgUser1);
                    }
                }
                System.out.println("пробую удалить нахуй существующих");
                chatIdRepository.deleteTgUser(tgUser1List);
// если ссылка верна то добавляем и юзера и ссылку
                tgUser.setLinkFiltr(update.getMessage().getText());
                try {
                    chatIdRepository.addTgUser(tgUser);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            } else if (message.getText().equals("HELP")){
                try {
                    sendMsg(message, "HELP");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else if (message.getText().equals("FIND ON COPART")){
                try {
                    parsCopart.runParsCopart();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void sendMsg (Message message, String text) throws IOException, InterruptedException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        ParsCopart parsCopart = new ParsCopart();

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
        keyboardFirstRow.add("FIND ON AV.BY");
        keyboardFirstRow.add("FIND ON COPART");
        keyboardFirstRow.add("HELP");
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

        try {
            if (text.equals("FIND ON AV.BY")){
                sendMessage.setText(text);
                execute(sendMessage);
            } else if (text.equals("HELP")){
                sendMessage.setText("Шаг 1: Откройте сайт https://av.by/ \n" +
                        "Шаг 2: настройте фильтр поиска нужных вам авто с нужными параметрами \n" +
                        "Шаг 3: отправьте эту ссылку в этот чат\n"  +
                        "Шаг 4: чтобы начать поиск авто нажмите кнопку FIND\n"+ "\n" +
                        "после нажатия на FIND потребуется некоторое время для поиска обьявлений");
                execute(sendMessage);
            } else if (text.equals("FIND ON COPART")){
                parsCopart.runParsCopart();
            }
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
