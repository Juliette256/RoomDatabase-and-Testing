package com.example.juliet2;

import android.content.Context;
import android.provider.SyncStateContract;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Book.class, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract DAO getDAO();
    public static DataBase db;

    public static DataBase getInstance(Context context){
        if(null==db){
            db=buildDatabaseInstance(context);
        }
        return db;
    }

    private static DataBase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, DataBase.class, SyncStateContract.Constants.ACCOUNT_NAME).allowMainThreadQueries().build();
    }
    public void cleanUp(){
        db=null;
    }
}
