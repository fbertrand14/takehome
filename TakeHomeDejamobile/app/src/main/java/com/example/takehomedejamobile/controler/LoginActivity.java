package com.example.takehomedejamobile.controler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.takehomedejamobile.R;
import com.example.takehomedejamobile.modele.User;
import com.example.takehomedejamobile.modele.UserViewModele;

import java.util.List;

/**
 * Controler for the activity used to login
 */
public class LoginActivity extends AppCompatActivity {

    private Integer user_id = -1;

    private UserViewModele userModele;

    private EditText loginTextField;
    private EditText passwordTextField;
    private Button connectButton;
    private Button newaccountButton;

    private List<User> listAllUsers;
    /**
     * On create initialise all object of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userModele = ViewModelProviders.of(this).get(UserViewModele.class);

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

        userModele.getAllUsers().observe((LifecycleOwner) this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                listAllUsers = users;
            }
        });
    }

    /**
     * This function open the activity to create a new account
     */
    private void openCreateUserActivity(){
        Intent intent = new Intent(this,CreateUserActivity.class);
        startActivity(intent);
    }

    /**
     *  This function check if all information given are correct. If they are open the main menu activity
     */
    private void connect(){
        String email = String.valueOf(loginTextField.getText());
        String pass = String.valueOf(passwordTextField.getText());

        // Retrive all password for a known email and put it in passlist
        User user = listAllUsers.get(0);

        // No user found for this email
        if (user==null){
            Toast.makeText(this,"Email unknown",Toast.LENGTH_SHORT).show();
            return;
        }

        // user feedback
        if (pass.equals(user.getPassword())){
            //open the main menu
            user_id = user.getId();
            Toast.makeText(this,"Connecting",Toast.LENGTH_SHORT).show();
            openMainMenu(user_id);
        }
        else{
            Toast.makeText(this,"Incorrect password",Toast.LENGTH_SHORT).show();
            return;
        }


    }

    /**
     * This function open the main menu activity for a specific user.
     * @param user_id
     *      ID of the user connecting
     */
    private void openMainMenu(Integer user_id){
        Intent intent = new Intent(this,MainMenuActivity.class);
        intent.putExtra("USER_ID", user_id);
        startActivity(intent);
    }
}
