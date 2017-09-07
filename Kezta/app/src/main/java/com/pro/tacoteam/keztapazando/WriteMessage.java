package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WriteMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
    }

    public void publicarMensaje(View view){
        Toast.makeText(getApplicationContext(),"Mensaje publicado", Toast.LENGTH_SHORT).show();
    }
}
