package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.takehomedejamobile.R;

public class PayActivity extends AppCompatActivity {

    private Integer user_id;

    private Button payButton;
    private Spinner cardSpinner;
    private EditText amountEditText;

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
    }

    private void pay(){
        //TODO create pay fonction in SQL
    }
}
