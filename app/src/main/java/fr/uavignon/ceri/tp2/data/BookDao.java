package fr.uavignon.ceri.tp2.data;

import java.util.List;

public interface BookDao {
    void updateBook(Book book);
    void insertBook(Book book);
    Book getBook(long id);
    void deleteBook(long id);
    List<Book> getAllBooks();
}
