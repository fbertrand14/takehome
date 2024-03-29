package com.example.takehomedejamobile.controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.Operation;

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

    /**
     * This fonction is used to update the data in the recyclerView
     * @param lstoperations
     *      List of all operations to display
     * @param lcards
     *      List of all cards used in those operations
     */
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
        Operation operation = lstoperations.get(position);
        Card card = new Card(null, null, null, null,null,null);
        card = card.findCardWithID(lcards, operation.getCard_id());
        if (card==null){
            holder.cardName.setText("Unknown card");
        }
        else{
            holder.cardName.setText(card.getName());
        }
        holder.operationAmount.setText(String.valueOf(operation.getAmount())+" $ ");
        String operationDate = operation.getOperationDate();
        holder.operationDate.setText(operationDate);
        String tagID = operation.getTagID_used();
        if (tagID==null){
            holder.tagID_used.setText("tag : Unknown");
        }else{
            holder.tagID_used.setText("tag : "+operation.getTagID_used());
        }
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
        TextView operationDate;
        TextView tagID_used;
        ConstraintLayout parentLayout;

        public OperationViewHolder(@NonNull View itemView) {
            super(itemView);

            cardName = itemView.findViewById(R.id.cardNameTextView_listOperations);
            operationAmount = itemView.findViewById(R.id.operationAmountTextView_listOperations);
            operationDate = itemView.findViewById(R.id.opDateTextView_listOperations);
            tagID_used = itemView.findViewById(R.id.tagIDUsedTextView_listOperations);
            parentLayout = itemView.findViewById(R.id.constraintLayout_listOperations);

        }
    }
}
