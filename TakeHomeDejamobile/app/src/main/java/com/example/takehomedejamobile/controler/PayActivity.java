package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.DatabaseTakehomeHelper;

import java.util.ArrayList;

public class PayActivity extends AppCompatActivity {

    private Integer user_id;
    private ArrayList<Card> lstCards;

    DatabaseTakehomeHelper database;

    private Button payButton;
    private Spinner cardSpinner;
    private EditText amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        database = new DatabaseTakehomeHelper(this);

        lstCards = database.getUserCard(user_id);

        payButton = (Button) findViewById(R.id.paybutton_PayActivity);
        amountEditText = (EditText) findViewById(R.id.amountTextField_PayActivity);
        cardSpinner = (Spinner) findViewById(R.id.cardspinner_PayActivity);

        payButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                pay();
            }
        });

        initSpinner();

    }

    private  void initSpinner(){
        ArrayList<String> lCardname = new ArrayList<>();
        for (  Card c :lstCards){
            lCardname.add(c.getName());
        }

        ArrayAdapter<String> cardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,lCardname);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardSpinner.setAdapter(cardAdapter);
    }

    private void pay(){
        //TODO create pay fonction in SQL

        Card selectedCard = lstCards.get(cardSpinner.getSelectedItemPosition());

        String sAmount = String.valueOf(amountEditText.getText());

        if (sAmount.isEmpty()){
            Toast.makeText(this,"Please enter an amount ",Toast.LENGTH_SHORT).show();
            return;
        }

        Float amount = Float.valueOf(sAmount);

        Log.d("OPERATION", "operation with card : "+selectedCard.getId()+" for : "+amount);

        boolean insertuser = database.addOperation(selectedCard.getId(),amount);

        if (insertuser){
            Toast.makeText(this,"INSERT OK",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this,"INSERT FAIL",Toast.LENGTH_SHORT).show();
        }
    }
}
