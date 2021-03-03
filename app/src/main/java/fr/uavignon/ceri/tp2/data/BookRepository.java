package fr.uavignon.ceri.tp2.data;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static fr.uavignon.ceri.tp2.data.BookRoomDatabase.databaseWriteExecutor;

public class BookRepository{

    private BookDao bookDao;
    private LiveData<List<Book>> allBooks;
    private MutableLiveData<Book> selectedBook = new MutableLiveData<>();

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

    public void insertBook(Book book) {
        databaseWriteExecutor.execute(() -> {
            bookDao.insertBook(book);
        });
    }

    public void getBook(long id) {
        Future<Book> gBook = databaseWriteExecutor.submit(() -> bookDao.getBook(id));

        try {
            selectedBook.setValue(gBook.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(long id) {
        databaseWriteExecutor.execute(() -> {
            bookDao.deleteBook(id);
        });
    }

    public void deleteAllBooks() {
        databaseWriteExecutor.execute(() -> {
            bookDao.deleteAllBooks();
        });
    }

    public LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }

    public MutableLiveData<Book> getSelectedBook() {
        return selectedBook;
    }
}
