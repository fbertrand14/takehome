package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.CardViewModele;

import java.util.List;

/**
 * Controler for the activity used to display all card of a user
 */
public class CardsListActivity extends AppCompatActivity {

    private Integer user_id;

    private Button addcardbutton;
    private RecyclerView cardsRecyclerView;

    private CardViewModele cardModele;

    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_list);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        addcardbutton = (Button) findViewById(R.id.addcard_CardsListActivity);
        cardsRecyclerView = (RecyclerView) findViewById(R.id.listcardrecyclerview_CardsListActivity);

        addcardbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCardEdit();
            }
        });

        cardModele = ViewModelProviders.of(this).get(CardViewModele.class);

        final CardListRecyclerViewAdapter adapter = new CardListRecyclerViewAdapter(user_id,this);

        cardsRecyclerView.setAdapter(adapter);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardModele.getAllUserCards(user_id).observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                adapter.setLstCards(cards);
            }
        });

    }


    /**
     * This function open the cardEdit activity with only a user_id. it will create a new card.
     */
    private void openCardEdit(){
        Intent intent = new Intent(this,CardEditActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

}
