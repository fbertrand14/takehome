package com.example.takehomedejamobile.modele;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * This Class is used to interact with Card objects in the app
 */
public class OperationViewModele extends AndroidViewModel {
    private OperationDao opDao;
    private TakehomeDataBase dataBaseRoom;

    /**
     * Constructor
     * @param application
     *      Instance of the application for the ViewModele
     */
    public OperationViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        opDao = dataBaseRoom.OperationDao();
    }

    /**
     * This function is used to insert an operation in the room database
     * @param operation
     *      operation to insert
     */
    public void insertOperation(Operation operation){
        new InsertAsyncTask(opDao).execute(operation);
    }

    /**
     * This function is used to retrieve all operation for one user in the room database
     * @param user_id
     *      id of the user
     */
    public LiveData<List<Operation>> getAllUserOperation(Integer user_id){
        return opDao.getOperationByUser(user_id);
    }

    /**
     * this AsyncTask is used to insert data in the room database
     */
    private class InsertAsyncTask extends AsyncTask<Operation, Void, Void> {

        private OperationDao opDao;

        public InsertAsyncTask(OperationDao opDao) {
            this.opDao = opDao;
        }

        @Override
        protected Void doInBackground(Operation... operations) {
            opDao.insert(operations[0]);
            return null;
        }
    }
}
