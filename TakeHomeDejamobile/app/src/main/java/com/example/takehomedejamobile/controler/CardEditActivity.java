package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.DatabaseTakehomeHelper;

public class CardEditActivity extends AppCompatActivity {

    private Integer user_id;

    private Integer card_id;
    private boolean edit_mode;

    DatabaseTakehomeHelper database;

    private Button saveCardButton;
    private Button deleteCardButton;
    private EditText cardNameTextField;
    private EditText cardNumberTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        database = new DatabaseTakehomeHelper(this);

        saveCardButton = (Button) findViewById(R.id.saveButton_EditCardActivity);
        deleteCardButton = (Button) findViewById(R.id.deleteButton_EditCardActivity);
        cardNameTextField = (EditText) findViewById(R.id.cardNameTextField_EditCardActivity);
        cardNumberTextField = (EditText) findViewById(R.id.cardNumberTextField_EditCardActivity);

        saveCardButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                savecard();
            }
        });

        deleteCardButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteCard();
            }
        });

        card_id = getIntent().getIntExtra("CARD_ID",-1);
        user_id = getIntent().getIntExtra("USER_ID",-1);

        if (card_id==-1){
            edit_mode = false;
            deleteCardButton.setVisibility(View.GONE);
        }
        else{
            edit_mode = true;
            loadCard();
        }

    }

    private void loadCard(){
        Card card = database.getCard(card_id);
        cardNameTextField.setText(card.getName());
        cardNumberTextField.setText(card.getNumber());
    }

    private void savecard(){
        boolean result;

        String name = String.valueOf(cardNameTextField.getText());
        String number = String.valueOf(cardNumberTextField.getText());

        if (name.isEmpty()){
            Toast.makeText(this,"Please enter a name for this card",Toast.LENGTH_SHORT).show();
            return;
        }

        if (number.isEmpty()){
            Toast.makeText(this,"Please enter a number for this card",Toast.LENGTH_SHORT).show();
            return;
        }



        if (edit_mode){
            result = database.updateCard(name, number,card_id);
            if (result){
                finish();
            }
            else{
                Toast.makeText(this,"Update Error",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            result = database.addCard(user_id, name, number);
            if (result){
                finish();
            }
            else{
                Toast.makeText(this,"Insert Error",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteCard(){
        boolean result;
        result = database.deleteCard(card_id);

        if (result){
            finish();
        }
        else{
            Toast.makeText(this,"Delete Error",Toast.LENGTH_SHORT).show();
        }
    }
}
