package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.DatabaseUserHelper;

import java.util.ArrayList;

public class CreateUserActivity extends AppCompatActivity {

    DatabaseUserHelper databaseUser;

    private Button newaccountButton;
    private EditText emailTextField;
    private EditText passwordTextField;
    private EditText nameTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        databaseUser = new DatabaseUserHelper(this);

        emailTextField = (EditText) findViewById(R.id.emailfield_createUserActivity);
        passwordTextField = (EditText) findViewById(R.id.passwordfield_createUserActivity);
        nameTextField = (EditText) findViewById(R.id.namefield_createUserActivity);

        newaccountButton = (Button) findViewById(R.id.createButton_createUserActivity);

        newaccountButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) { addUser();
            }
        });
    }

    public void addUser(){

        String email = String.valueOf(emailTextField.getText());
        String pass = String.valueOf(passwordTextField.getText());
        String name = String.valueOf(nameTextField.getText());

        // email, password and name error handling
        if ( !(email.contains("@")) ){
            Toast.makeText(this,"invalid email address : " +email,Toast.LENGTH_SHORT).show();
            return;
        }
        //  stop insert on already existing email
        Cursor data = databaseUser.getUser(email);
        ArrayList<String> passlist = new ArrayList<String>();
        while(data.moveToNext()){
            passlist.add(data.getString(2));
        }
        if (passlist.size() > 1){
            Toast.makeText(this,"email already used",Toast.LENGTH_SHORT).show();
            return;
        }


        if (pass.isEmpty()){
            Toast.makeText(this,"invalid password : " +pass ,Toast.LENGTH_SHORT).show();
            return;
        }

        if (name.isEmpty()){
            Toast.makeText(this,"name is empty",Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("DATABASE","adding"+email+"/"+pass+"/"+name);
        boolean insertuser = databaseUser.addUser(email,pass,name);

        if (insertuser){
            Toast.makeText(this,"INSERT OK",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this,"INSERT FAIL",Toast.LENGTH_SHORT).show();
        }
    }
}
