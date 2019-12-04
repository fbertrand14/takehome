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

public class OperationListRecyclerViewAdapter extends RecyclerView.Adapter<OperationListRecyclerViewAdapter.OperationViewHolder> {

    private ArrayList<Operation> lstoperations;
    private Context context;

    public OperationListRecyclerViewAdapter(ArrayList<Operation> lstoperations, Context context) {
        this.lstoperations = lstoperations;
        this.context = context;
    }

    @NonNull
    @Override
    public OperationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listoperations,parent,false);
        OperationViewHolder holder = new OperationViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OperationViewHolder holder, int position) {
        holder.cardName.setText(lstoperations.get(position).getCard_used().getName());
        holder.operationAmount.setText(String.valueOf(lstoperations.get(position).getAmount())+" $ ");
    }

    @Override
    public int getItemCount() {
        return lstoperations.size();
    }

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
