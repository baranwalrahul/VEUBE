package com.example.rahulrajbaranwal.signup;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Rahul Raj Baranwal on 26-05-2020.
 */
@Entity
public class UserData {
    @PrimaryKey
      @NonNull



    String email;

    String name;
    String password;
    String DOB;
    String profile_picture;

    public UserData(String email, String name, String password, String DOB, String profile_picture) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.DOB = DOB;
        this.profile_picture = profile_picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }
}
