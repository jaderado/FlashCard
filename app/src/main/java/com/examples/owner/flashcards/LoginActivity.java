package com.examples.owner.flashcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
        EditText username,password;
        String login_name,login_pass;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       username = (EditText) findViewById(R.id.usernametxt);
      password = (EditText) findViewById(R.id.passwordtxt);

    }

     public void userReg(View view){

        startActivity(new Intent(this,RegisterActivity.class));

    }

    public void userLogin(View view){

            login_name=username.getText().toString();
            login_pass=password.getText().toString();

        String method="login";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,login_name,login_pass);
    }
}






