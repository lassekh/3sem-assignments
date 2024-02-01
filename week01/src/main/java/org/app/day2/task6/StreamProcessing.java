package org.app.day2.task6;

import java.util.*;
import java.util.stream.Collectors;

public class StreamProcessing {
    public static void main(String[] args) {
        List<Book> books = bookData();

        //Find average rating of all books
        double average = books.stream()
                .mapToDouble(Book::getRating)
                .average()
                .getAsDouble();
        //System.out.println(average);


        //Books published after year
        List<Book> publishedAfter = books.stream()
                .filter(book -> book.getPublicationYear() > 2000)
                .collect(Collectors.toList());
        //publishedAfter.forEach(System.out::println);

        //Sort books by rating
        List<Book> sortByRatingDesc = books.stream()
                .sorted(Comparator.comparing(Book::getRating).reversed())
                .collect(Collectors.toList());
        //sortByRatingDesc.forEach(System.out::println);

        //Book with highest rating
        Optional<Book> highestRating = books.stream()
                .max(Comparator.comparing(Book::getRating));
        //highestRating.ifPresent(System.out::println);

        //Group by author and calculate average rating
        books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor))
                .forEach((author, authorsBooks) -> {
                    System.out.println(author);
                    double authorAvgRating = authorsBooks.stream()
                    .mapToDouble(Book::getRating).average()
                    .getAsDouble();
                //System.out.println(authorAvgRating);
        });

        //Total number of pages in all books
        int totalPages = books.stream()
                .mapToInt(Book::getPages).sum();
        //System.out.println(totalPages);

        //Chaining
        List<Book> booksAfter2000SortByAuthor = books.stream()
                .filter(book -> book.getPublicationYear() > 2000)
                .sorted(Comparator.comparing(Book::getAuthor))
                .collect(Collectors.toList());
        booksAfter2000SortByAuthor.forEach(System.out::println);

    }
    public static List<Book> bookData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Hero","Henrik Henriksen",1992,4.5,220));
        books.add(new Book("The Anti-Hero","Susan Bondegaard",2003,3.8,234));
        books.add(new Book("Come-on!","Anders Andersen",2008,3.9,344));
        books.add(new Book("The New Man","Lars Larsen",2022,2.1,151));
        books.add(new Book("The New Woman","Lars Larsen",2023,4.2,148));
        books.add(new Book("History of Denmark","Dan Marksen",1995,4.9,516));
        books.add(new Book("Learn Programming","Johan Johansen",2015,4.5,374));
        books.add(new Book("Learn Python","Johan Johansen",2016,4.1,194));
        books.add(new Book("Learn SQL","Johan Johansen",2017,2.0,174));
        books.add(new Book("Programming 101","Jens Jensen",2018,4.0,299));
        return books;
    }

    /*
    public static List<Book> readAllBookData(File file){
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] dataSplit = data.split(",");
                String title = dataSplit[0];
                String author = dataSplit[1];
                int publicationYear = dataSplit[2];
                double rating = dataSplit[0];
                int pages;

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
     */
}
