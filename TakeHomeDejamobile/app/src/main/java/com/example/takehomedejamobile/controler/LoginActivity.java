package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.DatabaseTakehomeHelper;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    DatabaseTakehomeHelper database;

    Integer user_id = -1;

    private EditText loginTextField;
    private EditText passwordTextField;
    private Button connectButton;
    private Button newaccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = new DatabaseTakehomeHelper(this);

        loginTextField = (EditText) findViewById(R.id.loginfield_LoginActivity);
        passwordTextField = (EditText) findViewById(R.id.passwordfield_LoginActivity);
        connectButton = (Button) findViewById(R.id.connectbutton_LoginActivity);
        newaccountButton = (Button) findViewById(R.id.newaccountbutton_LoginActivity);

        newaccountButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openCreateUserActivity();
            }
        });
        connectButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                connect();
            }
        });
    }

    private void openCreateUserActivity(){
        Intent intent = new Intent(this,CreateUserActivity.class);
        startActivity(intent);
    }

    private void connect(){
        String email = String.valueOf(loginTextField.getText());
        String pass = String.valueOf(passwordTextField.getText());

        // Retrive all password for a known email and put it in passlist
        Cursor data = database.getUser(email);
        ArrayList<String> passlist = new ArrayList<String>();
        while(data.moveToNext()){
            passlist.add(data.getString(2));
            user_id = data.getInt(0);
        }
        // No user found for this email
        if (passlist.size()==0){
            Toast.makeText(this,"Email unknown",Toast.LENGTH_SHORT).show();
            return;
        }
        // if more than 1 user have the same email (not supposed to happen)
        if (passlist.size() > 1){
            Toast.makeText(this,"Something weird happened",Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.equals(passlist.get(0))){
            //open the main menu
            Toast.makeText(this,"Connecting",Toast.LENGTH_SHORT).show();
            openMainMenu(user_id);
        }
        else{
            Toast.makeText(this,"Incorrect password",Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void openMainMenu(Integer user_id){
        Intent intent = new Intent(this,MainMenuActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }
}
