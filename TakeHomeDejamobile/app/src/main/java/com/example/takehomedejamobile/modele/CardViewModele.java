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

    public void updateCard(Card card) {new UpdateAsyncTask(cardDao).execute(card);}

    public void deleteCard(Card card){new DeleteAsyncTask(cardDao).execute(card);}

    public LiveData<List<Card>> getAllcards(){
        return allcard;
    }

    public LiveData<List<Card>> getAllUserCards(Integer user_id){
        allUserscard = cardDao.getAllUsercards(user_id);
        return allUserscard;
    }

    public LiveData<Card> getCardByID(Integer card_id){
        return cardDao.getCardByID(card_id);
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

    private class UpdateAsyncTask extends AsyncTask<Card, Void, Void> {

        private CardDao cardDao;

        public UpdateAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.update(cards[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Card, Void, Void> {

        private CardDao cardDao;

        public DeleteAsyncTask(CardDao cardDao) {
            this.cardDao = cardDao;
        }

        @Override
        protected Void doInBackground(Card... cards) {
            cardDao.delete(cards[0]);
            return null;
        }
    }
}
