package fr.uavignon.ceri.tp2.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;

@Dao
public interface BookDao {
    @Update
    void updateBook(Book book);

    @Insert
    void insertBook(Book book);

    @Query("select * from booksDb where bookId = :id")
    Book getBook(long id);

    @Query("delete from booksDb where bookId = :id")
    void deleteBook(long id);

    @Query("Select * from booksDb")
    LiveData<List<Book>> getAllBooks();

    @Query("delete from booksDb")
    void deleteAllBooks();
}
