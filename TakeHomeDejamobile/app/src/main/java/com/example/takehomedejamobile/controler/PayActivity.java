package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
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
import com.example.takehomedejamobile.modele.CardViewModele;
import com.example.takehomedejamobile.modele.Operation;
import com.example.takehomedejamobile.modele.OperationViewModele;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Controler for the activity used to pay (simulation)
 */
public class PayActivity extends AppCompatActivity {

    private Integer user_id;
    private List<Card> lstCards;

    private Button payButton;
    private Spinner cardSpinner;
    private EditText amountEditText;

    private OperationViewModele operationModele;
    private CardViewModele cardModele;

    private NfcAdapter mNFCAdapter;

    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        user_id = getIntent().getIntExtra("USER_ID", -1);

        cardModele = ViewModelProviders.of(this).get(CardViewModele.class);
        operationModele = ViewModelProviders.of(this).get(OperationViewModele.class);

        payButton = (Button) findViewById(R.id.paybutton_PayActivity);
        amountEditText = (EditText) findViewById(R.id.amountTextField_PayActivity);
        cardSpinner = (Spinner) findViewById(R.id.cardspinner_PayActivity);

        payButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pay(null);
            }
        });


        cardModele.getAllUserCards(user_id).observe(this, new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                lstCards = cards;
                initSpinner();

            }
        });

        mNFCAdapter = NfcAdapter.getDefaultAdapter(this);

        if(mNFCAdapter == null){
            Toast.makeText(this, "This phone doesn't support NFC", Toast.LENGTH_SHORT).show();
        }
        if (!mNFCAdapter.isEnabled()){
            Toast.makeText(this, "NFC is disabled", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This fonction is used to react to NFC tags
     *
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent){

        super.onNewIntent(intent);

        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)){
            Toast.makeText(this, "NFC tag detected", Toast.LENGTH_SHORT).show();
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            if(tag != null){
                String tagID_used = String.valueOf(tag.getId());
                Toast.makeText(this, tagID_used, Toast.LENGTH_SHORT).show();
                pay(tagID_used);
            }
            else{
                Toast.makeText(this, "No tag retrived ", Toast.LENGTH_SHORT).show();
            }
        }

    }


    /**
     * This fonction enable NFC reading on resume
     */
    @Override
    protected void onResume() {

        Intent intent = new Intent(this,PayActivity.class);
        intent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        IntentFilter[] intentFilter = new IntentFilter[]{};

        mNFCAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);

        super.onResume();
    }

    /**
     * This fonction desable NFC reading on pause
     */
    @Override
    protected void onPause() {
        mNFCAdapter.disableForegroundDispatch(this);
        super.onPause();
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
    private void pay(String tagID_used){

        Card selectedCard = lstCards.get(cardSpinner.getSelectedItemPosition());

        String sAmount = String.valueOf(amountEditText.getText());

        Calendar instance = Calendar.getInstance();
        Integer currentDay = instance.get(Calendar.DAY_OF_MONTH);
        Integer currentMonth = instance.get(Calendar.MONTH)+1;
        Integer currentYear = instance.get(Calendar.YEAR);
        Integer currentHour = instance.get(Calendar.HOUR_OF_DAY);
        Integer currentMinute = instance.get(Calendar.MINUTE);



        if (sAmount.isEmpty()){
            Toast.makeText(this,"Please enter an amount ",Toast.LENGTH_SHORT).show();
            return;
        }

        Float amount = Float.valueOf(sAmount);

        Log.d("OPERATION", "operation with card : "+selectedCard.getId()+" for : "+amount);

        Operation op = new Operation(null, selectedCard.getId(), amount,currentYear,currentMonth,currentDay,currentHour,currentMinute,tagID_used);

        operationModele.insertOperation(op);
        finish();
    }
}
