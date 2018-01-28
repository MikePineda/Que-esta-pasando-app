package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.pro.tacoteam.keztapazando.Login.getLoggedUser;

public class AddFriendActivity extends AppCompatActivity {

    private EditText nombreCompa;
    private ProgressDialog pDialog;
    private String URL = "http://10.76.13.147:5000/";
    private String TAG = AddFriendActivity.class.getSimpleName();
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

        new postMessage(this).execute();
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
            String urlPro = URL + "addFriendToUser";
            sh.addCompadre(urlPro,getLoggedUser().getUsername(),nombreCompa.getText().toString());
            Log.e(TAG, "Exito ");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            Toast.makeText(getApplicationContext(),"Mensaje publicado", Toast.LENGTH_SHORT).show();
            ((AddFriendActivity)mContext).finish();

        }

    }



    ////////////////////////
}



