package com.example.rahulrajbaranwal.signup;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Created by Rahul Raj Baranwal on 26-05-2020.
 */
@Database(entities = UserData.class,version=1,exportSchema = false)
public abstract class UserRepo extends RoomDatabase {
    public static final String DB_NAME="User_DB";
    public static UserRepo INSTANCE;
    public static synchronized UserRepo getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, UserRepo.class, DB_NAME).fallbackToDestructiveMigration().build();
        }
        return INSTANCE;

    }
    public abstract UserDao getDao();

}
