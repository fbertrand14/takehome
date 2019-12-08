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
public class CardViewModele extends AndroidViewModel {

    private CardDao cardDao;
    private TakehomeDataBase dataBaseRoom;

    private LiveData<List<Card>> allcard;
    private LiveData<List<Card>> allUserscard;

    /**
     * Constructor
     * @param application
     *      Instance of the application for the ViewModele
     */
    public CardViewModele(@NonNull Application application) {
        super(application);

        dataBaseRoom = TakehomeDataBase.getDatabase(application);
        cardDao = dataBaseRoom.CardDao();

        allcard = cardDao.getAllcards();
    }


    /**
     * This function is used to insert a card in the room database
     * @param card
     *      Card to insert
     */
    public void insertCard(Card card){
        new InsertAsyncTask(cardDao).execute(card);
    }

    /**
     * This function is used to update a card in the room database
     * @param card
     *      Card to update
     */
    public void updateCard(Card card) {new UpdateAsyncTask(cardDao).execute(card);}

    /**
     * This function is used to delete a card in the room database
     * @param card
     *      Card to delete
     */
    public void deleteCard(Card card){new DeleteAsyncTask(cardDao).execute(card);}

    /**
     * This function is used to retrieve all cards in the room database
     * @return
     *      LiveData List of all cards
     */
    public LiveData<List<Card>> getAllcards(){
        return allcard;
    }

    /**
     * This function is used to retrieve all cards for a user in the room database
     * @param user_id
     *      ID of the user
     * @return
     *      LiveData List of all cards for the user
     */
    public LiveData<List<Card>> getAllUserCards(Integer user_id){
        allUserscard = cardDao.getAllUsercards(user_id);
        return allUserscard;
    }

    /**
     * This function is used to retrieve a card by it's id in the room database
     * @param card_id
     *      ID of the card
     * @return
     *      LiveData of the card
     */
    public LiveData<Card> getCardByID(Integer card_id){
        return cardDao.getCardByID(card_id);
    }

    /**
     * this AsyncTask is used to insert data in the room database
     */
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

    /**
     * this AsyncTask is used to update data in the room database
     */
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

    /**
     * this AsyncTask is used to delete data in the room database
     */
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
