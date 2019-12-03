package com.example.takehomedejamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardsListActivity extends AppCompatActivity {

    private Button addcardbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards_list);

        addcardbutton = (Button) findViewById(R.id.addcard_CardsListActivity);

        addcardbutton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCardEdit();
            }
        });
    }

    private void openCardEdit(){
        Intent intent = new Intent(this,CardEditActivity.class);
        startActivity(intent);
    }

    private void openCardEdit(Integer cardId){
        Intent intent = new Intent(this,CardEditActivity.class);
        intent.putExtra("CARD_ID", cardId);
        startActivity(intent);
    }
}
