package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.Card;
import com.example.takehomedejamobile.modele.CardViewModele;
import com.example.takehomedejamobile.modele.TakehomeDataBase;
import com.example.takehomedejamobile.modele.UserViewModele;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

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

    private Spinner monthSpinner;
    private Spinner yearSpinner;

    private LiveData<Card> card;

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
        monthSpinner = (Spinner) findViewById(R.id.monthspinner_EditCardActivity);
        yearSpinner = (Spinner) findViewById(R.id.yearspinner_EditCardActivity);

        initSpinner();

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
            card = cardModele.getCardByID(card_id);
            card.observe(this, new Observer<Card>() {
                @Override
                public void onChanged(Card card) {
                    if (card!=null) {
                        cardNameTextField.setText(card.getName());
                        cardNumberTextField.setText(card.getNumber());
                        monthSpinner.setSelection(card.getExpMonth());
                    }
                }
            });
        }


    }

    private  void initSpinner(){
        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] monthNames = symbols.getMonths();

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,monthNames);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        ArrayList<String> years = new ArrayList<>();

        Calendar instance = Calendar.getInstance();
        Integer currentYear = instance.get(Calendar.YEAR);
        for (int i=0;i<10;i++){
            years.add(String.valueOf(currentYear+i));
        }

        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

    }


    /**
     * This fonction save all modification to a card or create it if it's not a edition
     */
    private void savecard(){

        String name = String.valueOf(cardNameTextField.getText());
        String number = String.valueOf(cardNumberTextField.getText());

        Integer month = monthSpinner.getSelectedItemPosition()+1;
        Integer year = Integer.valueOf(yearSpinner.getSelectedItem().toString());

        if (name.isEmpty()){
            Toast.makeText(this,"Please enter a name for this card",Toast.LENGTH_SHORT).show();
            return;
        }

        if (number.isEmpty()){
            Toast.makeText(this,"Please enter a number for this card",Toast.LENGTH_SHORT).show();
            return;
        }

        if (edit_mode){
            Card card = new Card(card_id, user_id, name, number,month,year);
            cardModele.updateCard(card);
            finish();
        }
        else{
            Card card = new Card(null, user_id, name, number,month,year);
            cardModele.insertCard(card);
            finish();
        }
    }

    /**
     * This function is use to delete the card we are editing
     */
    private void deleteCard(){
        Card card = new Card(card_id, user_id, null, null,null,null);
        cardModele.deleteCard(card);
        finish();
    }
}
