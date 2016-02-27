package com.examples.owner.flashcards;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class RegisterActivity extends Activity {


    Button registerbtn;
    EditText name,email,username,password;
    String nameET,emailET, usernameET, passwordET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = (EditText) findViewById(R.id.nameET);
        email=(EditText)findViewById(R.id.emailET);
        username = (EditText) findViewById(R.id.usernameET);
        password = (EditText) findViewById(R.id.passwordET);
        registerbtn = (Button) findViewById(R.id.registerBtn);

    }

    public void userReg(View view){

        nameET=name.getText().toString();
        emailET=email.getText().toString();
        usernameET=username.getText().toString();
        passwordET=password.getText().toString();
        String method= "register";
        BackgroundTask backgroundTask= new BackgroundTask(this);
        backgroundTask.execute(method,nameET,emailET,usernameET,passwordET);
        finish();
    }
}
