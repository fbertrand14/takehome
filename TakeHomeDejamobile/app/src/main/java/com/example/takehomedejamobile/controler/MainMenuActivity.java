package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.CardViewModele;
import com.example.takehomedejamobile.modele.Operation;
import com.example.takehomedejamobile.modele.OperationViewModele;

import java.util.ArrayList;
import java.util.List;

/**
 * Controler for the main menu activity
 */
public class MainMenuActivity extends AppCompatActivity {

    private Button paybutton;
    private Button cardsButton;
    private RecyclerView operationRecyclerView;

    private OperationViewModele operationModele;
    private CardViewModele cardModele;


    private Integer user_id;

    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        paybutton = (Button) findViewById(R.id.paybutton_MainMenuActivity);
        cardsButton = (Button) findViewById(R.id.cardsbutton_MainMenuActivity);
        operationRecyclerView = (RecyclerView) findViewById(R.id.operationRecyclerView_MainMenuActivity);

        cardsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCardsList();
            }
        });
        paybutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openPay();
            }
        });

        operationModele = ViewModelProviders.of(this).get(OperationViewModele.class);
        cardModele = ViewModelProviders.of(this).get(CardViewModele.class);

        final OperationListRecyclerViewAdapter adapter = new OperationListRecyclerViewAdapter();

        operationRecyclerView.setAdapter(adapter);
        operationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        operationModele.getAllUserOperation(user_id).observe(this, new Observer<List<Operation>>() {
            @Override
            public void onChanged(List<Operation> operations) {
                //
                retriveUserCards(operations,adapter);
            }
        });

    }

    public void retriveUserCards(final List<Operation> operations, final OperationListRecyclerViewAdapter adapter){
        cardModele.getAllUserCards(user_id).observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                List<Card> userCards;
                userCards = cards;
                adapter.setLstoperations(operations,userCards);
            }
        });
    }


    /**
     * this function open the CardListActivity for the connected user
     * @see CardsListActivity
     *
     */
    private void openCardsList(){
        Intent intent = new Intent(this,CardsListActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    /**
     * this function open the PayActivity for the connected user
     * @see PayActivity
     *
     */
    private void openPay(){

        if (true){
            Intent intent = new Intent(this,PayActivity.class);
            intent.putExtra("USER_ID", user_id);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"You must have at least one card to pay",Toast.LENGTH_SHORT).show();
        }

    }


}
