package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.DatabaseTakehomeHelper;

import java.util.ArrayList;
/**
 * Controler for the activity used to display all card of a user
 */
public class CardsListActivity extends AppCompatActivity {

    private Integer user_id;

    private Button addcardbutton;
    private RecyclerView cardsRecyclerView;

    DatabaseTakehomeHelper database;

    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_list);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        database = new DatabaseTakehomeHelper(this);

        addcardbutton = (Button) findViewById(R.id.addcard_CardsListActivity);
        cardsRecyclerView = (RecyclerView) findViewById(R.id.listcardrecyclerview_CardsListActivity);

        addcardbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCardEdit();
            }
        });

        initRecyclerView();
    }

    /**
     * on resume refresh the content of the RecyclerView to keep it up to date
     */
    @Override
    protected void onResume() {
        super.onResume();

        initRecyclerView();
    }

    /**
     * This function open the cardEdit activity with only a user_id. it will create a new card.
     */
    private void openCardEdit(){
        Intent intent = new Intent(this,CardEditActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    /**
     * This function load all card of a user in the RecyclerView using the CardListRecyclerViewAdapter
     *
     * @see CardListRecyclerViewAdapter
     */
    private void initRecyclerView(){

        ArrayList<Card> listCards = database.getUserCard(user_id);

        CardListRecyclerViewAdapter adapter = new CardListRecyclerViewAdapter(listCards,user_id,this);

        cardsRecyclerView.setAdapter(adapter);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
