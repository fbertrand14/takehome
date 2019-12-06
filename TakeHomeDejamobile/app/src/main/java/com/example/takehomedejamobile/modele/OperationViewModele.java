package com.example.takehomedejamobile.modele;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class OperationViewModele extends AndroidViewModel {
    private OperationDao opDao;
    private TakehomeDataBase dataBaseRoom;

    public OperationViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        opDao = dataBaseRoom.OperationDao();
    }

    public void insertOperation(Operation operation){
        new InsertAsyncTask(opDao).execute(operation);
    }

    public LiveData<List<Operation>> getAllUserOperation(Integer user_id){
        return opDao.getOperationByUser(user_id);
    }

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
