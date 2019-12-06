package com.example.takehomedejamobile.modele;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CardViewModele extends AndroidViewModel {

    private CardDao cardDao;
    private TakehomeDataBase dataBaseRoom;

    private LiveData<List<Card>> allcard;
    private LiveData<List<Card>> allUserscard;


    public CardViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        cardDao = dataBaseRoom.CardDao();

        allcard = cardDao.getAllcards();
    }

    public void insertCard(Card card){
        new InsertAsyncTask(cardDao).execute(card);
    }

    public LiveData<List<Card>> getAllcards(){
        return allcard;
    }

    public LiveData<List<Card>> getAllUserCards(Integer user_id){
        allUserscard = cardDao.getAllUsercards(user_id);
        return allUserscard;
    }

    private class InsertAsyncTask extends AsyncTask<Card, Void, Void> {

        private CardDao cardDao;

        public InsertAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.insert(cards[0]);
            return null;
        }
    }
}
