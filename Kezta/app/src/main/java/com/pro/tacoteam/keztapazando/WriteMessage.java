package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.pro.tacoteam.keztapazando.MainWall;

public class WriteMessage extends AppCompatActivity {
 EditText mensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        mensaje = (EditText) findViewById(R.id.mensaje);
    }

    public void publicarMensaje(View view){
        //Mensajes dummy, aqui debemos mandarlo a la api para que lo guarde en la bd! y ya en el wall activity traer los datillos
        MainWall.addMessage(Login.getLoggedUser(),mensaje.getText().toString());
        Toast.makeText(getApplicationContext(),"Mensaje publicado", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
