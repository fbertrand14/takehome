package com.example.takehomedejamobile.modele;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This Class is used to interact with User objects in the app
 */
public class UserViewModele extends AndroidViewModel {
    private UserDao userDao;
    private TakehomeDataBase dataBaseRoom;

    private LiveData<List<User>> allUsers;

    /**
     * Constructor
     * @param application
     *      Instance of the application for the ViewModele
     */
    public UserViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        userDao = dataBaseRoom.UserDao();

        allUsers = userDao.getAllUsers();
    }

    /**
     * This function is used to insert a user in the room database
     * @param user
     *      user to insert
     */
    public void insertUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    /**
     * This function is used to retrieve all user in the room database
     * @return
     *      all user in the database
     */
    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    /**
     * This function is used to retrieve a user by his email in the room database
     * @param email
     *      user email
     * @return
     *      LiveData List of all cards
     */
    public LiveData<User> getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }


    /**
     * this AsyncTask is used to insert data in the room database
     */
    private class InsertAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao userDao;

        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }
}
