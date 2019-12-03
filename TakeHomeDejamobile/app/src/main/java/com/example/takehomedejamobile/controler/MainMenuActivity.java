package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.takehomedejamobile.R;

public class MainMenuActivity extends AppCompatActivity {

    private Button paybutton;
    private Button cardsButton;

    private Integer user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        user_id = getIntent().getIntExtra("USER_ID",-1);

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
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    private void openPay(){
        Intent intent = new Intent(this,PayActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }


}
