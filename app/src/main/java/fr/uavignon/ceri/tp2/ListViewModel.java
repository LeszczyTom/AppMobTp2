package fr.uavignon.ceri.tp2;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import fr.uavignon.ceri.tp2.data.Book;
import fr.uavignon.ceri.tp2.data.BookRepository;

public class ListViewModel extends AndroidViewModel {
    private BookRepository repo;
    private LiveData<List<Book>> allBooks;

    public ListViewModel(@NonNull Application application) {
        super(application);
        repo = new BookRepository(application);
        allBooks = repo.getAllBooks();
    }

    LiveData<List<Book>> getAllBooks() {
        return allBooks;
    }
}
