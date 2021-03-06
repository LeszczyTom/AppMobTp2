package fr.uavignon.ceri.tp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.BookRepository;

public class DetailViewModel extends AndroidViewModel {
    private MutableLiveData<Book> book;
    private BookRepository repo;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        repo = new BookRepository(application);
        book = repo.getSelectedBook();
    }

    MutableLiveData<Book> getBook() {
        return book;
    }

    public void insertOrUpdate(Book book) {
        System.out.println(book.getId());
        if (book.getId() != 0){
            System.out.println("update");
            repo.updateBook(book);
        }
        else {
            System.out.println("insert");
            repo.insertBook(book);
        }
    }

    public void getBookById(long id) {repo.getBook(id);}
}
