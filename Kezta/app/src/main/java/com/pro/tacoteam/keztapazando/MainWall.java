package com.pro.tacoteam.keztapazando;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.pro.tacoteam.keztapazando.Login.getLoggedUser;

public class MainWall extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//Lista de mensajes
  //  ListView listView ;
    public static List<Post> listilla;
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
    private String TAG = MainWall.class.getSimpleName();
    DateFormat formatter = new SimpleDateFormat("E, W MMM y hh:mm:ss zzz", Locale.ENGLISH);
    CustomList adapter;

    public static void addMessage(String user, String message){
        listilla.add(new Post(user,message, new Date()));

    }

    /**
     * Para actualizar la lista de mensajes cada que publicas uno!!
     */
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        listilla.clear();
        new GetPosts().execute();
        //new GetPosts().onPostExecute();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list=(ListView)findViewById(R.id.list);
        listilla = new ArrayList<Post>();


       final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent();
                intent.setClass(fab.getContext(), WriteMessage.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        adapter = new CustomList(MainWall.this,listilla,imageId);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainWall.this, "You Clicked at " +listilla.get(position).getMensaje(), Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getApplicationContext(), DescripcionMensajeActivity.class);
                //To pass:
                //
                //intent.putExtra("MyClass", obj);
                // To retrieve object in second Activity
                //   getIntent().getSerializableExtra("MyClass");
                myIntent.putExtra("user",listilla.get(position).getNombre());
                myIntent.putExtra("mensaje", listilla.get(position).getMensaje());
                myIntent.putExtra("cosaExtra1", listilla.get(position).getNombre() + "@gmail.com");
                myIntent.putExtra("fecha", listilla.get(position).getDate().toString());
                startActivity(myIntent);
            }
        });



        ////////////////

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_wall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            this.finish();
            return true;
        }
        if(id == R.id.recargarLista){
            onResume();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent();
            intent.setClass(this, Profile.class);
            intent.putExtra("user", getLoggedUser().getUsername());
            intent.putExtra("cosaExtra1", getLoggedUser().getCorreo());
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent();
            intent.setClass(this, FriendListActivity.class);
            startActivity(intent);
        }
        /*(else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetPosts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            // http://192.168.15.15:5000/getPostsByUser?username=rada
            pDialog = new ProgressDialog(MainWall.this);
            pDialog.setMessage("Obteniendo publicaciones de tus amigos...");
            pDialog.setCancelable(true);
            pDialog.show();
            listilla = new ArrayList<Post>();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            /////////////////PRUEBA ENCONTRAR AMIGOS
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
                        amigos.add(amigo);
                    }
                    amigos.add(getLoggedUser().getUsername());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                Log.e(TAG,"Es amigo de: " + amigos.toString());

            /////////////////////
            // Making a request to url and getting response
            for(int count=0;count< amigos.size();count++) {
                String urlPro = URL + "getPostsByUser?username=" + amigos.get(count);
                String jsonStr = sh.makeServiceCall(urlPro);
                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONArray jsonarray = new JSONArray(jsonStr);
                        //Parseando a traves del JSON
                        for (int i = 0; i < jsonarray.length(); i++) {
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            String username = jsonobject.getString("username");
                            String date = jsonobject.getString("date");
                            String message = jsonobject.getString("message");
                            listilla.add(new Post(username, message, formatter.parse(date)));
                        }

                    } catch (JSONException e) {
                        Log.e(TAG, "json exception");
                    } catch (ParseException e) {
                        Log.e(TAG, "parse exception");
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
            }//fin del for
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()){
                pDialog.dismiss();

            }

            adapter = new CustomList(MainWall.this,listilla,imageId);
            list.setAdapter(adapter);
        }

    }



    ////////////////////////


}
