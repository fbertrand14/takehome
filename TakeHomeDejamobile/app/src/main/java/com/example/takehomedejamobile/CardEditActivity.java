package com.example.takehomedejamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CardEditActivity extends AppCompatActivity {

    private Integer user_id;

    private Integer card_id;
    private boolean edit_mode;

    DatabaseUserHelper databaseUser;

    private Button saveCardButton;
    private EditText cardNameTextField;
    private EditText cardNumberTextField;
    private EditText expDateTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        databaseUser = new DatabaseUserHelper(this);

        saveCardButton = (Button) findViewById(R.id.saveButton_EditCardActivity);
        cardNameTextField = (EditText) findViewById(R.id.cardNameTextField_EditCardActivity);
        cardNumberTextField = (EditText) findViewById(R.id.cardNumberTextField_EditCardActivity);
        expDateTextField = (EditText) findViewById(R.id.expDateTextField_EditCardActivity);

        saveCardButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                savecard();
            }
        });


        card_id = getIntent().getIntExtra("CARD_ID",-1);
        user_id = getIntent().getIntExtra("USER_ID",-1);

        if (card_id==-1){
            edit_mode = false;
        }
        else{
            edit_mode = true;
        }

    }

    private void savecard(){
        String name = String.valueOf(cardNameTextField.getText());
        String number = String.valueOf(cardNumberTextField.getText());
        String expDate = String.valueOf(expDateTextField.getText());

        if (edit_mode){
            //TODO Create a modification of line
        }
        else{
            databaseUser.addCard(user_id, name, number, expDate);
        }
    }
}
