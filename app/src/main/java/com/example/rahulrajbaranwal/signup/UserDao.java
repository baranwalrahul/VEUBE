package com.example.rahulrajbaranwal.signup;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Rahul Raj Baranwal on 26-05-2020.
 */
@Dao
public interface UserDao {


    @Insert
    public void insert(UserData userData);

    @Query("SELECT * FROM UserData")
    public List<UserData> display();
}
