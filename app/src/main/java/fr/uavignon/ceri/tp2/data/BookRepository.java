package fr.uavignon.ceri.tp2.data;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

import static fr.uavignon.ceri.tp2.data.BookRoomDatabase.databaseWriteExecutor;

public class BookRepository{

    private BookDao bookDao;
    private List<Book> allBooks;

    public BookRepository(Application application) {
        BookRoomDatabase db = BookRoomDatabase.getInstance(application);
        bookDao = db.bookDao();
        allBooks = bookDao.getAllBooks();
    }


    public void updateBook(Book book) {
        databaseWriteExecutor.execute(() -> {
            bookDao.updateBook(book);
        });
    }
}
