package com.example.takehomedejamobile.controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Operation;

import java.util.ArrayList;

/**
 * This object adapt Operations to a RecyclerView to dispolay them
 */
public class OperationListRecyclerViewAdapter extends RecyclerView.Adapter<OperationListRecyclerViewAdapter.OperationViewHolder> {

    private ArrayList<Operation> lstoperations;
    private Context context;

    /**
     * Constructor of the adater
     *
     * @param lstoperations
     *      List of all operation to display
     * @param context
     *      context of the activity
     */
    public OperationListRecyclerViewAdapter(ArrayList<Operation> lstoperations, Context context) {
        this.lstoperations = lstoperations;
        this.context = context;
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
        holder.cardName.setText(lstoperations.get(position).getCard_id());
        holder.operationAmount.setText(String.valueOf(lstoperations.get(position).getAmount())+" $ ");
    }

    /**
     * This function get the number of line we need in the Recyclerview
     * @return the number of elements to display
     */
    @Override
    public int getItemCount() {
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
