package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.pro.tacoteam.keztapazando.Login.getLoggedUser;

public class Register extends AppCompatActivity {

    private EditText nombre;
    private EditText correo;
    private EditText password;

    private ProgressDialog pDialog;
    private String URL = "http://192.168.15.15:5000/";
    private String TAG = Register.class.getSimpleName();
    private boolean validation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registro");
        nombre = (EditText) findViewById(R.id.registerUsername);
        correo = (EditText) findViewById(R.id.registerMail);
        password = (EditText) findViewById(R.id.registerPassword);

    }



    public void registrarUsuario(View view){
         new RegisterUser(this).execute();
    }


    ////////////////////////////
    /**
     * Async task class to get json by making HTTP call
     */
    private class RegisterUser extends AsyncTask<Void, Void, Void> {
        private Context mContext;

        public RegisterUser(Context context) {

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
            String urlPro = URL + "addUser";
            if(nombre.getText().toString().trim().length()>0 &&
                    correo.getText().toString().trim().length()>0 &&
                    password.getText().toString().trim().length()>0){
                validation =true;
            }
            if(validation){
                sh.addUser(urlPro,nombre.getText().toString(),correo.getText().toString(),password.getText().toString());
            }
            Log.e(TAG, "Exito ");

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if(validation){
                Toast.makeText(getApplicationContext(),"Te has registrado exitosamente", Toast.LENGTH_SHORT).show();
                ((Register)mContext).finish();

            }else{
                Toast.makeText(getApplicationContext(),"Error, los campos no pueden estar vacios", Toast.LENGTH_SHORT).show();

            }

        }

    }



    ////////////////////////
}
