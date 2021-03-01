package fr.uavignon.ceri.tp2.data;

import java.util.List;
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
    //Todo: query qqch
    Book getBook(long id);
    @Delete
    void deleteBook(long id);
    @Query("Select * from Book")
    List<Book> getAllBooks();
}
