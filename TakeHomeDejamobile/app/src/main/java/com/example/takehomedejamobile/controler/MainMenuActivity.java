package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.DatabaseTakehomeHelper;
import com.example.takehomedejamobile.modele.Operation;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    private Button paybutton;
    private Button cardsButton;
    private RecyclerView operationRecyclerView;

    DatabaseTakehomeHelper database;

    private Integer user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        database = new DatabaseTakehomeHelper(this);

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

        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        initRecyclerView();
    }

    private void openCardsList(){
        Intent intent = new Intent(this,CardsListActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }

    private void openPay(){

        if (database.userHaveCard(user_id)){
            Intent intent = new Intent(this,PayActivity.class);
            intent.putExtra("USER_ID", user_id);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"You must have at least one card to pay",Toast.LENGTH_SHORT).show();
        }

    }

    private void initRecyclerView(){

        ArrayList<Operation> loperations = database.getUserOperations(user_id);

        OperationListRecyclerViewAdapter adapter = new OperationListRecyclerViewAdapter(loperations,this);

        operationRecyclerView.setAdapter(adapter);
        operationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
