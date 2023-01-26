//package service;
//
//import java.awt.print.Book;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.List;
//import java.util.Scanner;
//
//public class PostService {
//    private void addBook() throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//
//        System.out.println("Enter name of new book: ");
//        Scanner in1 = new Scanner(System.in);
//        String bookNewName = in1.nextLine();
//
//        System.out.println("Enter author of new book: ");
//        String bookNewAuthor = in1.nextLine();
//
//        System.out.println("Enter year of new book");
//        int yearOfNewBook = in1.nextInt();
//
//        List<Book> bookList = bookRepository;
//        List<User> userList = userRepository.getAllUsers();
//
//        bookRepository.addBook(new Book(bookList.size()+1, bookNewName, bookNewAuthor, yearOfNewBook));
//
//        System.out.println("Post added!");
//    }
//}
