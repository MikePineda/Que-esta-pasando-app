package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {
private EditText username;
    private  EditText password;
    private  static String loggedUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

    }

    public void dummyLogin(View view){
        String a = username.getText().toString();
        if(username.getText().toString().equals(password.getText().toString()) ){
            Intent intent = new Intent(this, MainWall.class);
            TextView editText = (TextView) findViewById(R.id.btn_login);
            String message = editText.getText().toString();
            //intent.putExtra(EXTRA_MESSAGE, message);
            setLoggedUser(username.getText().toString());
            startActivity(intent);
            Toast errorToast = Toast.makeText(this,"Login exitoso!", Toast.LENGTH_SHORT);
            errorToast.show();
        }else{
            Toast errorToast = Toast.makeText(this, "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
            errorToast.show();
        }

    }

    public static String getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(String loggedUser) {
        this.loggedUser = loggedUser;
    }
}
