//package Threads;
//
//import model.Post;
//import model.TgUser;
//import parserAvBy.ParsAvByJsoup;
//import parserAvBy.repository.ChatIdRepository;
//import parserAvBy.repository.PostRepository;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MyFirstThread extends Thread {
//
//
//    @Override
//    public void run() {
//        System.out.println("я новый поток");
//        ParsAvByJsoup parsAvByJsoup = new ParsAvByJsoup();
//        String linkFiltr = "asd";
//        String userGetChatId = " asd";
//        parsAvByJsoup.run(String linkFiltr, String userGetChatId);
//    }
//}