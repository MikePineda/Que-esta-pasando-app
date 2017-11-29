package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.pro.tacoteam.keztapazando.Login.getLoggedUser;

public class FriendListActivity extends AppCompatActivity {
    public  List<Perfil> compadres;
    ListView list;

    Integer[] imageId = {
            R.drawable.fots,
            R.drawable.fots,
            R.drawable.fots,
            R.drawable.fots,
            R.drawable.fots,
            R.drawable.fots,
            R.drawable.fots

    };

    private ProgressDialog pDialog;
    private String URL = "http://192.168.15.15:5000/";
    private String TAG = FriendListActivity.class.getSimpleName();
    CustomListFriends adapter;

    /**
     * Para actualizar la lista de mensajes cada que publicas uno!!
     */
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        compadres.clear();
        new GetFriends().execute();
        //new GetPosts().onPostExecute();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        setTitle("Compadres");

        compadres = new ArrayList<Perfil>();
        /*
        compadres.add(new Perfil("Rada", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radas", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radass", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radasss", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radassss", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radasssss", "123", "rada@gmail.com"));
*/
        adapter = new CustomListFriends(FriendListActivity.this,compadres,imageId);
        list=(ListView)findViewById(R.id.friendsList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(FriendListActivity.this, "You Clicked at " +compadres.get(position).getUsername(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), DescripcionMensajeActivity.class);
            }
        });


    }

    public void addContact(View view){
        Intent myIntent = new Intent(getApplicationContext(), AddFriendActivity.class);
        startActivity(myIntent);
    }


    ////////////////////////////////////////////OBTENER AMIGOS
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFriends extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            // http://192.168.15.15:5000/getPostsByUser?username=rada
            pDialog = new ProgressDialog(FriendListActivity.this);
            pDialog.setMessage("Obteniendo  amigos...");
            pDialog.setCancelable(true);
            pDialog.show();
            compadres = new ArrayList<Perfil>();

        }
    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        ///////////////// ENCONTRAR AMIGOS
        List<String> amigos = new ArrayList<>();
        String urlFriends = URL + "getFriendsOfUser?username=" + getLoggedUser().getUsername() ;
        String friendsJson = sh.makeServiceCall(urlFriends);
        Log.e(TAG, "Response from url: " + friendsJson);
        if (friendsJson != null) {
            try {
                JSONArray jsonarrayAmigos = new JSONArray(friendsJson);
                //Parseando a traves del JSON
                for (int i = 0; i < jsonarrayAmigos.length(); i++) {
                    JSONObject jsonobject = jsonarrayAmigos.getJSONObject(i);
                    String amigo = jsonobject.getString("username2");
                    compadres.add(new Perfil(amigo,amigo,amigo+ "@gmail.com"));
                }
                amigos.add(getLoggedUser().getUsername());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG,"Es amigo de: " + amigos.toString());

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        if (pDialog.isShowing()){
            pDialog.dismiss();

        }

        adapter = new CustomListFriends(FriendListActivity.this,compadres,imageId);
        list.setAdapter(adapter);
    }

}



////////////////////////


}
