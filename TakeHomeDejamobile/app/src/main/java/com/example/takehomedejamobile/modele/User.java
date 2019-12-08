package com.example.takehomedejamobile.modele;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * object representing a user
 */
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

    /**
     * Constructor
     * @param id
     *      User id
     * @param email
     *      user Email
     * @param name
     *      user name
     * @param password
     *      user password
     */
    public User(Integer id, @NonNull String email, @NonNull String name, @NonNull String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;

;    }

    /**
     * Getter for the user ID
     * @return user ID
     */
    @NonNull
    public Integer getId() {
        return id;
    }

    /**
     * Getter for the user email
     * @return user email
     */
    @NonNull
    public String getEmail() {
        return email;
    }

    /**
     * Getter for the user name
     * @return  user name
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Getter for the user password
     * @return  user password
     */
    @NonNull
    public String getPassword() {
        return password;
    }
}
