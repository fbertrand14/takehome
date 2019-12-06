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

import java.util.ArrayList;

/**
 * Controler for the activity used to pay (simulation)
 */
public class PayActivity extends AppCompatActivity {

    private Integer user_id;
    private ArrayList<Card> lstCards;


    private Button payButton;
    private Spinner cardSpinner;
    private EditText amountEditText;

    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        user_id = getIntent().getIntExtra("USER_ID",-1);

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

    /**
     * this function initilize the spinner used to select one card of the user.
     */
    private  void initSpinner(){
        ArrayList<String> lCardname = new ArrayList<>();
        for (  Card c :lstCards){
            lCardname.add(c.getName()+"  "+ c.getNumber());
        }

        ArrayAdapter<String> cardAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,lCardname);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardSpinner.setAdapter(cardAdapter);
    }

    /**
     * this function check if all informations given are correct. If they are create the operation.
     */
    private void pay(){

        Card selectedCard = lstCards.get(cardSpinner.getSelectedItemPosition());

        String sAmount = String.valueOf(amountEditText.getText());

        if (sAmount.isEmpty()){
            Toast.makeText(this,"Please enter an amount ",Toast.LENGTH_SHORT).show();
            return;
        }

        Float amount = Float.valueOf(sAmount);

        Log.d("OPERATION", "operation with card : "+selectedCard.getId()+" for : "+amount);

        boolean insertuser = true;

        // user feedback
        if (insertuser){
            Toast.makeText(this,"INSERT OK",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this,"INSERT FAIL",Toast.LENGTH_SHORT).show();
        }
    }
}
