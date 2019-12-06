package com.example.takehomedejamobile.modele;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @NonNull
    private String email;
    @NonNull
    private String name;
    @NonNull
    private String password;

    public User(Integer id, @NonNull String email, @NonNull String name, @NonNull String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }


    @NonNull
    public Integer getId() {
        return id;
    }
    @NonNull
    public String getEmail() {
        return email;
    }
    @NonNull
    public String getName() {
        return name;
    }
    @NonNull
    public String getPassword() {
        return password;
    }
}
