package bot;

import copart.ParsCopartJsoup;
import copart.ParsCopartSelenium;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import model.TgUser;
import parserAvBy.ParsAvByJsoup;
import parserAvBy.repository.ChatIdRepository;
import parserAvBy.repository.PostRepository;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        ParsAvByJsoup parsAvByJsoup = new ParsAvByJsoup();
        ParsCopartJsoup parsCopartJsoup = new ParsCopartJsoup();
        ParsCopartSelenium parsCopartSelenium = new ParsCopartSelenium();

        TgUser tgUser = new TgUser();

//   достаем чатId пользователя
        tgUser.setChatId(update.getMessage().getChatId().toString());
        System.out.println(update.getMessage().getChatId().toString());
//   достаем имя пользователя
        tgUser.setUsername(update.getMessage().getFrom().getFirstName());
        System.out.println(update.getMessage().getFrom().getFirstName());

        Message message = update.getMessage();

//        for (int i=0; i<1000; i++){
            if (message != null && message.hasText()) {
                if (message.getText().equals("FIND ON AV.BY")){
                    try {
                        sendMsg(message, "loading...");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ////     ПАРСЕР
                    for (TgUser tgUser1: tgUserList){
                        if (tgUser1.getChatId().equals(tgUser.getChatId())) {
                            try {
//                            parsAvBy.run(tgUser1.getLinkFiltr(), tgUser1.getChatId());
                                for (int tim=0; tim<5; tim++){
                                    parsAvByJsoup.run(tgUser1.getLinkFiltr(), tgUser1.getChatId());
                                    System.out.println("22222222222222222222");
                                    Thread.sleep(5000);
                                    if (message.getText().equals("STOP")){
                                        System.out.println("GAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAYGAY");
                                        break;
                                    }
                                }
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        parsAvByJsoup.interrupt();
                    }

                    message.setText(null);
                } else if (message.getText().indexOf(link) != -1){
// удалить старую инфу (ссылку от пользователя) если пользователь уже существует
                    for (TgUser tgUser1: tgUserList){
                        long t = Long.parseLong(tgUser.getChatId());
                        long t1 = Long.parseLong(tgUser1.getChatId());
                        if (t != t1){
                            tgUser1List.add(tgUser1);
                        }
                    }
                    System.out.println("пробую удалить нахуй существующий фильтр на AV.BY");
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
                        message.setText(null);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (message.getText().equals("FIND ON COPART")){
                    try {
                        sendMsg(message, "FIND ON COPART");
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ////     ПАРСЕР
                    for (TgUser tgUser1: tgUserList){
                        if (tgUser1.getChatId().equals(tgUser.getChatId())) {
                            try {
                                parsCopartSelenium.runParsCopart(tgUser1.getChatId());
                                System.out.println(tgUser1.getChatId() + " chatID " + tgUser1.getUsername());
                            } catch (IOException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//        }
    }

    public void sendMsg (Message message, String text) throws IOException, InterruptedException {
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
//                sendMessage.setText(text);
//                execute(sendMessage);
            } else if (text.equals("HELP")){
                sendMessage.setText("Шаг 1: Откройте сайт https://av.by/ \n" +
                        "Шаг 2: настройте фильтр поиска нужных вам авто с нужными параметрами \n" +
                        "Шаг 3: отправьте эту ссылку в этот чат\n"  +
                        "Шаг 4: чтобы начать поиск авто нажмите кнопку FIND\n"+ "\n" +
                        "после нажатия на FIND потребуется некоторое время для поиска обьявлений");
                execute(sendMessage);
            } else if (text.equals("FIND ON COPART")){
//                sendMessage.setText(text);
//                execute(sendMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//
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
