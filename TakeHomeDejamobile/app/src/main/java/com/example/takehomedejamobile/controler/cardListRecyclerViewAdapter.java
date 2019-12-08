package com.example.takehomedejamobile.controler;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;

import java.util.List;

/**
 * This object adapt cards to a RecyclerView to dispolay them
 */
public class CardListRecyclerViewAdapter extends RecyclerView.Adapter<CardListRecyclerViewAdapter.CardViewHolder> {

    private List<Card> lstCards;
    private Context context;
    private Integer user_id;

    /**
     * Constructor of the adater
     * @param user_id
     *      id of the user for all this cards
     * @param context
     *      context of the activity
     */
    public CardListRecyclerViewAdapter(Integer user_id,Context context) {
        this.context = context;
        this.user_id = user_id;
    }

    /**
     *  this function create a view holder to hold every instances of cards
     * @param parent
     * @param viewType
     * @return A CardViewHolder to holde the view
     */
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listcards,parent,false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    /**
     * This function if used to fill every holder
     * @param holder
     *      holder we are filling
     * @param position
     *      position of the holder we are filling (index)
     */
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.cardName.setText(lstCards.get(position).getName());
        holder.cardNumber.setText(lstCards.get(position).getNumber());
        String expdate = "exp : "+ lstCards.get(position).getExpMonth()+" / "+ lstCards.get(position).getExpYear();
        holder.expDate.setText(expdate);
        holder.card_id = lstCards.get(position).getId();
    }

    /**
     * This function get the number of line we need in the Recyclerview
     * @return the number of elements to display
     */
    @Override
    public int getItemCount() {
        if (lstCards==null){
            return 0;
        }
        return lstCards.size();
    }

    /**
     * This fonction is used to update the data in the recyclerView
     * @param cards
     *      List of cards to display
     */
    public void setLstCards(List<Card> cards){
        lstCards = cards;
        notifyDataSetChanged();
    }

    /**
     * This object is the ViewHolder for every object in the RecyclerView
     */
    public class CardViewHolder extends RecyclerView.ViewHolder{

        Integer card_id = -1;

        TextView cardName;
        TextView cardNumber;
        TextView expDate;
        ImageButton editButton;
        ConstraintLayout parentLayout;

        /**
         * Constructor initialize every object in the view
         * @param itemView
         */
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardNameTextView_ListCards);
            cardNumber = itemView.findViewById(R.id.cardNumberTextView_ListCards);
            expDate = itemView.findViewById(R.id.expDateTextView_ListCards);

            editButton = itemView.findViewById(R.id.editImageButton_ListCards);
            parentLayout = itemView.findViewById(R.id.listCardsConstraintLayout);

            editButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    openCardEdit();
                }
            });

        }

        /**
         * this function open the edition activity and give it the user_id and the card_id for it's card
         */
        private void openCardEdit(){
            Intent intent = new Intent(context,CardEditActivity.class);
            intent.putExtra("CARD_ID", card_id);
            intent.putExtra("USER_ID", user_id);
            context.startActivity(intent);
        }
    }

}
