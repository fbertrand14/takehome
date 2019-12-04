package com.example.takehomedejamobile.controler;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;

import java.util.ArrayList;

public class CardListRecyclerViewAdapter extends RecyclerView.Adapter<CardListRecyclerViewAdapter.CardViewHolder> {

    private ArrayList<Card> lstCards;
    private Context context;
    private Integer user_id;

    public CardListRecyclerViewAdapter(ArrayList<Card> lstCards,Integer user_id,Context context) {
        this.lstCards = lstCards;
        this.context = context;
        this.user_id = user_id;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listcards,parent,false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        holder.cardName.setText(lstCards.get(position).getName());
        holder.cardNumber.setText(lstCards.get(position).getNumber());
        holder.card_id = lstCards.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return lstCards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        Integer card_id = -1;

        TextView cardName;
        TextView cardNumber;
        ImageButton editButton;
        ConstraintLayout parentLayout;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardNameTextView_ListCards);
            cardNumber = itemView.findViewById(R.id.cardNumberTextView_ListCards);
            editButton = itemView.findViewById(R.id.editImageButton_ListCards);
            parentLayout = itemView.findViewById(R.id.listCardsConstraintLayout);

            editButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    openCardEdit();
                }
            });

        }

        private void openCardEdit(){
            Intent intent = new Intent(context,CardEditActivity.class);
            intent.putExtra("CARD_ID", card_id);
            intent.putExtra("USER_ID", user_id);
            context.startActivity(intent);
        }
    }

}
