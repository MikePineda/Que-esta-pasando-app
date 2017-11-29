package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddFriendActivity extends AppCompatActivity {

    private EditText nombreCompa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        setTitle("Añadir compadre");
        nombreCompa = (EditText) findViewById(R.id.addFriendName);

    }

    public void addAndClose(View view){
        //logica para agregar compa aqui:

        if(true){
            Toast.makeText(getApplicationContext(),"Contacto añadido correctamente: "+ nombreCompa.getText().toString(), Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(),"Error al añadir el contacto, probablemente el contacto no existe: "+ nombreCompa.getText().toString(), Toast.LENGTH_SHORT).show();

        }
        this.finish();

    }
}



