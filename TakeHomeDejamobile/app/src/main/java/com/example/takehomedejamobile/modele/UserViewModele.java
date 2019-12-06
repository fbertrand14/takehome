package com.example.takehomedejamobile.modele;

import android.app.Application;
import android.app.ListActivity;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

public class UserViewModele extends AndroidViewModel {
    private UserDao userDao;
    private TakehomeDataBase dataBaseRoom;

    private LiveData<List<User>> allUsers;

    public UserViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        userDao = dataBaseRoom.UserDao();

        allUsers = userDao.getAllUsers();
    }

    public void insertUser(User user){
        new InsertAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }


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
