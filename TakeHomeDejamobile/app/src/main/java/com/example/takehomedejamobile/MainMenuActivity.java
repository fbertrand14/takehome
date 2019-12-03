package com.example.takehomedejamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    private Button paybutton;
    private Button cardsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        paybutton = (Button) findViewById(R.id.paybutton_MainMenuActivity);
        cardsButton = (Button) findViewById(R.id.cardsbutton_MainMenuActivity);

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
    }

    private void openCardsList(){
        Intent intent = new Intent(this,CardsListActivity.class);
        startActivity(intent);
    }

    private void openPay(){
        Intent intent = new Intent(this,PayActivity.class);
        startActivity(intent);
    }


}
