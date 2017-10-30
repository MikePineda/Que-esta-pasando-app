package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DescripcionMensajeActivity extends AppCompatActivity {

    private EditText author;
    private EditText mail;
    private TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion_mensaje);
        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        String mensaje = intent.getStringExtra("mensaje");
        String cosaExtra1 = intent.getStringExtra("cosaExtra1");
        String cosaExtra2 = intent.getStringExtra("cosaExtra2");

        author = (EditText) findViewById(R.id.author);
        author.setKeyListener(null);

        mail = (EditText) findViewById(R.id.authorMail);
        mail.setKeyListener(null);

        message = (TextView) findViewById(R.id.messagedescr);

        author.setText(user);
        mail.setText(cosaExtra1);
        message.setText(mensaje);
    }


    public void goBack(View view){
        this.finish();
    }
}
