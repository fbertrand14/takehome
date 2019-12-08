package com.example.takehomedejamobile.controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.CardViewModele;
import com.example.takehomedejamobile.modele.Operation;

import java.util.ArrayList;
import java.util.List;

/**
 * This object adapt Operations to a RecyclerView to dispolay them
 */
public class OperationListRecyclerViewAdapter extends RecyclerView.Adapter<OperationListRecyclerViewAdapter.OperationViewHolder> {

    private List<Operation> lstoperations;
    private List<Card> lcards;

    /**
     * Constructor of the adater
     *
     */
    public OperationListRecyclerViewAdapter() {
    }

    public void setLstoperations(List<Operation> lstoperations,List<Card> lcards) {
        this.lstoperations = lstoperations;
        this.lcards = lcards;
        notifyDataSetChanged();
    }

    /**
     *  this function create a view holder to hold every instances of operations
     * @param parent
     * @param viewType
     * @return A OperationViewHolder to holde the view
     */
    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listoperations,parent,false);
        OperationViewHolder holder = new OperationViewHolder(view);
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
    public void onBindViewHolder(@NonNull OperationViewHolder holder, int position) {
        Card card = new Card(null, null, null, null,null,null);
        card = card.findCardWithID(lcards, lstoperations.get(position).getCard_id());
        if (card==null){
            holder.cardName.setText("Unknown card");
        }
        else{
            holder.cardName.setText(card.getName()+"  "+card.getNumber());
        }
        holder.operationAmount.setText(String.valueOf(lstoperations.get(position).getAmount())+" $ ");
    }

    /**
     * This function get the number of line we need in the Recyclerview
     * @return the number of elements to display
     */
    @Override
    public int getItemCount() {
        if (lstoperations==null){
            return 0;
        }
        return lstoperations.size();
    }

    /**
     * This object is the ViewHolder for every object in the RecyclerView
     */
    public class OperationViewHolder extends RecyclerView.ViewHolder{

        TextView cardName;
        TextView operationAmount;
        ConstraintLayout parentLayout;

        public OperationViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardNameTextView_listOperations);
            operationAmount = itemView.findViewById(R.id.operationAmountTextView_listOperations);
            parentLayout = itemView.findViewById(R.id.constraintLayout_listOperations);

        }
    }
}
