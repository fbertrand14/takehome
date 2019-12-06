package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.CardViewModele;
import com.example.takehomedejamobile.modele.TakehomeDataBase;
import com.example.takehomedejamobile.modele.UserViewModele;

/**
 * Controler for the activity used to create, edit and delete cards
 */
public class CardEditActivity extends AppCompatActivity {

    private Integer user_id;

    private Integer card_id;
    private boolean edit_mode;

    private CardViewModele cardModele;

    private Button saveCardButton;
    private Button deleteCardButton;
    private EditText cardNameTextField;
    private EditText cardNumberTextField;

    /**
     * On create initialise all object of the activity
     *
     * @param savedInstanceState
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_edit);

        user_id = getIntent().getIntExtra("USER_ID",-1);

        cardModele = ViewModelProviders.of(this).get(CardViewModele.class);

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

        // retrive card_id and user_id from the last activity
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

    /**
     * this fonction load the card name and number if we are editing a card
     */
    private void loadCard(){
        Card card = new Card(1, 5, "kdfgjk", "65465");
        cardNameTextField.setText(card.getName());
        cardNumberTextField.setText(card.getNumber());
    }

    /**
     * This fonction save all modification to a card or create it if it's not a edition
     */
    private void savecard(){

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
            //TODO UPDATE LINE
            finish();
        }
        else{
            Card card = new Card(null, user_id, name, number);
            cardModele.insertCard(card);
            finish();
        }
    }

    /**
     * This function is use to delete the card we are editing
     */
    private void deleteCard(){
        boolean result;
        result = true;

        if (result){
            finish();
        }
        else{
            Toast.makeText(this,"Delete Error",Toast.LENGTH_SHORT).show();
        }
    }
}
