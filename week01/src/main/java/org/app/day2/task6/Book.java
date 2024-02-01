package org.app.day2.task6;

public class Book {
    String title;
    String author;
    int publicationYear;
    double rating;
    int pages;

    public Book(String title, String author, int publicationYear, double rating, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", rating=" + rating +
                ", pages=" + pages +
                '}';
    }
}
