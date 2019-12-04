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
import com.example.takehomedejamobile.modele.DatabaseUserHelper;

import java.util.ArrayList;

public class CardsListActivity extends AppCompatActivity {

    private Integer user_id;

    private Button addcardbutton;
    private RecyclerView cardsRecyclerView;

    DatabaseUserHelper databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_list);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        databaseUser = new DatabaseUserHelper(this);

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

    @Override
    protected void onResume() {
        super.onResume();

        initRecyclerView();
    }

    private void openCardEdit(){
        Intent intent = new Intent(this,CardEditActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    private void openCardEdit(Integer cardId){
        Intent intent = new Intent(this,CardEditActivity.class);
        intent.putExtra("CARD_ID", cardId);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    private void initRecyclerView(){

        ArrayList<Card> listCards = databaseUser.getUserCard(user_id);

        CardListRecyclerViewAdapter adapter = new CardListRecyclerViewAdapter(listCards,this);

        cardsRecyclerView.setAdapter(adapter);
        cardsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
