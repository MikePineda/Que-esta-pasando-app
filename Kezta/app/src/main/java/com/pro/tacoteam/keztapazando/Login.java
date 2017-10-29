package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
private EditText username;
    private  EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

    }

    public void dummyLogin(View view){
        String a = username.getText().toString();
        if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Intent intent = new Intent(this, MainWall.class);
            TextView editText = (TextView) findViewById(R.id.btn_login);
            String message = editText.getText().toString();
            //intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
            Toast errorToast = Toast.makeText(this,"Login exitoso!", Toast.LENGTH_SHORT);
            errorToast.show();
        }else{
            Toast errorToast = Toast.makeText(this, "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
            errorToast.show();
        }

    }
}
