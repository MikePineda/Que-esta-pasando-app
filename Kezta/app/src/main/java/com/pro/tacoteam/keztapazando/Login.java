package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity {
    private String TAG = Login.class.getSimpleName();
    private EditText username;
    private  EditText password;
    private  static Perfil loggedUser;
    private String URL = "http://192.168.15.15:5000/";
    private ProgressDialog pDialog;

    private String usernameIngresado;
    private String passwordIngresado;
    private boolean tienePermisos =false;
    //private Perfil loggedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.password);

    }

    public void login(View view){

        usernameIngresado = username.getText().toString();
        passwordIngresado = password.getText().toString();
        new GetInfo().execute();
    }

    public void dummyLogin(View view){
        String a = username.getText().toString();
        if(username.getText().toString().equals(password.getText().toString()) ){
            Intent intent = new Intent(this, MainWall.class);
            TextView editText = (TextView) findViewById(R.id.btn_login);
            String message = editText.getText().toString();
            //intent.putExtra(EXTRA_MESSAGE, message);
            setLoggedUser(new Perfil(username.getText().toString(),username.getText().toString(),username.getText().toString()));
            startActivity(intent);
            Toast errorToast = Toast.makeText(this,"Login exitoso!", Toast.LENGTH_SHORT);
            errorToast.show();
        }else{
            Toast errorToast = Toast.makeText(this, "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
            errorToast.show();
        }

    }

    public void register(View view){
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivity(intent);
    }

    public static Perfil getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Perfil loggedUser) {
        this.loggedUser = loggedUser;
    }



    ////////////////////////////
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetInfo extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String urlPro = URL + "getAllUsers";
            String jsonStr = sh.makeServiceCall(urlPro);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray jsonarray = new JSONArray(jsonStr);
                    //Parseando a traves del JSON
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        String name = jsonobject.getString("username");
                        String password = jsonobject.getString("password");
                        String mail = jsonobject.getString("email");
                        Log.e(TAG,name);
                        Log.e(TAG,password);
                        if(name.equals(usernameIngresado) && password.equals(passwordIngresado)) {
                            tienePermisos=true;
                            loggedUser = new Perfil(name,password,mail);
                            break;
                        }else{
                            tienePermisos =false;
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            if(tienePermisos){
                Intent intent = new Intent(getApplicationContext(), MainWall.class);
                //intent.putExtra(EXTRA_MESSAGE, message);
                setLoggedUser(loggedUser);
                startActivity(intent);
                Toast errorToast = Toast.makeText(getApplicationContext(),"Login exitoso!", Toast.LENGTH_SHORT);
                errorToast.show();
            }else{
                Toast errorToast = Toast.makeText(getApplicationContext(), "Error, usuario o contraseña incorrectos", Toast.LENGTH_SHORT);
                errorToast.show();
            }


        }

    }



    ////////////////////////





}
