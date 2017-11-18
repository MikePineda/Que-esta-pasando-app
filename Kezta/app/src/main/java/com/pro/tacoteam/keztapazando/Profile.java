package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String user = intent.getStringExtra("user");
        String mail = intent.getStringExtra("cosaExtra1");
//get the intent for password


        EditText profileName = (EditText) findViewById(R.id.editText2);
        EditText correo = (EditText) findViewById(R.id.editText6);
        password = (EditText) findViewById(R.id.pwUser);
        //profileName.setKeyListener(null);
        //correo.setKeyListener(null);

        profileName.setText(user);

        correo.setText(mail);

        //set password in textView

    }

    public void saveInfo(View view){
        //retrieve info from textview

        //save info in user


    }

}
