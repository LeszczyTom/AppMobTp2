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
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = Book.class, version = 1)
public abstract class BookRoomDatabase extends RoomDatabase {
    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {
    }

    //ExecutorService
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //Singleton
    private static BookRoomDatabase instance;

    public abstract BookDao bookDao();

    public static BookRoomDatabase getInstance(Context context) {
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), BookRoomDatabase.class,
                    "BookDatabase").build();
        return instance;
    }

    public BookDao bookDao;
}
