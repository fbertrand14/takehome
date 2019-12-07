package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.AESCipher;
import com.example.takehomedejamobile.modele.TakehomeDataBase;
import com.example.takehomedejamobile.modele.User;
import com.example.takehomedejamobile.modele.UserDao;
import com.example.takehomedejamobile.modele.UserViewModele;

/**
 * Controler for the activity used to create a new user with email, password and name
 */
public class CreateUserActivity extends AppCompatActivity {

    private UserViewModele userModele;

    private Button newaccountButton;
    private EditText emailTextField;
    private EditText passwordTextField;
    private EditText nameTextField;


    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        userModele = ViewModelProviders.of(this).get(UserViewModele.class);

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

    /**
     * this function check if all information given by the user are correct, if they are it create the user.
     */
    public void addUser(){

        String email = String.valueOf(emailTextField.getText());
        String pass = String.valueOf(passwordTextField.getText());
        String name = String.valueOf(nameTextField.getText());

        // email, password and name error handling
        if ( !(email.contains("@")) ){
            Toast.makeText(this,"invalid email address : " +email,Toast.LENGTH_SHORT).show();
            return;
        }

        // password is empty
        if (pass.isEmpty()){
            Toast.makeText(this,"invalid password : " +pass ,Toast.LENGTH_SHORT).show();
            return;
        }
        // no name given
        if (name.isEmpty()){
            Toast.makeText(this,"name is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        //  TODO stop insert on already existing email
        /*Cursor data = database.getUser(email);
        ArrayList<String> passlist = new ArrayList<String>();
        while(data.moveToNext()){
            passlist.add(data.getString(2));
        }
        //email allready used
        if (passlist.size() > 1){
            Toast.makeText(this,"email allready used",Toast.LENGTH_SHORT).show();
            return;
        }*/

        // encode password
        String encPass ="";
        AESCipher cipher = new AESCipher("TakeHome");
        try {
            encPass = cipher.encrypt(pass);
            Log.d("CYPHER", "encrypted password = "+encPass);
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.d("CYPHER", "Cannont encrypt password");
        }

        // Insert in database
        Log.d("DATABASE","adding"+email+"/"+encPass+"/"+name);

        User user = new User(null, email, name, encPass);
        userModele.insertUser(user);

        Toast.makeText(this,"INSERT OK",Toast.LENGTH_SHORT).show();
        finish();

    }


}
