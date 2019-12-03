package com.example.takehomedejamobile.controler;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;

import java.util.ArrayList;

public class cardListRecyclerViewAdapter extends RecyclerView.Adapter<cardListRecyclerViewAdapter.CardViewHolder> {

    private ArrayList<Card> lstCards;
    private Context context;

    public cardListRecyclerViewAdapter(ArrayList<Card> lstCards, Context context) {
        this.lstCards = lstCards;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return lstCards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        TextView cardName;
        TextView cardNumber;
        RelativeLayout parentLayout;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardNameTextView_ListCards);
            cardNumber = itemView.findViewById(R.id.cardNumberTextView_ListCards);
            parentLayout = itemView.findViewById(R.id.listCardsRelativeLayout);

        }
    }
}
