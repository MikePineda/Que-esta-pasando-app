package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.pro.tacoteam.keztapazando.MainWall;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import static com.pro.tacoteam.keztapazando.Login.getLoggedUser;

public class WriteMessage extends AppCompatActivity {
 EditText mensaje;

    private ProgressDialog pDialog;
    private String URL = "http://10.76.13.147:5000/";
    private String TAG = WriteMessage.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
        mensaje = (EditText) findViewById(R.id.mensaje);
    }

    public void publicarMensaje(View view){
        //Mensajes dummy, aqui debemos mandarlo a la api para que lo guarde en la bd! y ya en el wall activity traer los datillos
      //  MainWall.addMessage(Login.getLoggedUser().getUsername(),mensaje.getText().toString());
        new postMessage(this).execute();
     //   this.finish();
    }

    ////////////////////////////
    /**
     * Async task class to get json by making HTTP call
     */
    private class postMessage extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        public postMessage(Context context) {

           mContext = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            // http://192.168.15.15:5000/getPostsByUser?username=rada
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String urlPro = URL + "addPost";
            sh.addPost(urlPro,mensaje.getText().toString(),getLoggedUser().getUsername());

            Log.e(TAG, "Exito ");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            Toast.makeText(getApplicationContext(),"Mensaje publicado", Toast.LENGTH_SHORT).show();
            ((WriteMessage)mContext).finish();

        }

    }



    ////////////////////////

}
