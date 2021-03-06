package fr.uavignon.ceri.tp2.data;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Book.class}, version = 2)
public abstract class BookRoomDatabase extends RoomDatabase {

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                BookDao dao = instance.bookDao();
                dao.deleteAllBooks();

                for(Book newBook : Book.books) {
                    dao.insertBook(newBook);
                }

            });
        }
    };

    public abstract BookDao bookDao();

    private static BookRoomDatabase instance;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BookRoomDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (BookRoomDatabase.class) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            BookRoomDatabase.class, "db2")
                            .addCallback(sRoomDatabaseCallback).build() ;
                }
            }
        }
        return instance;
    }
}
