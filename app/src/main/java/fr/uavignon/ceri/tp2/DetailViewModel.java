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

    public void modifyBook(Book book) {
        repo.updateBook(book);
    }

    public void getBookById(long id) {repo.getBook(id);}
}
