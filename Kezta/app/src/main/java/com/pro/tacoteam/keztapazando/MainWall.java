package com.pro.tacoteam.keztapazando;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        CustomList adapter = new CustomList(MainWall.this,listilla,imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wall);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

//////////////////////

        // Get ListView object from xml
       // listView = (ListView) findViewById(R.id.list);
        // Defined Array values to show in ListView
        listilla = new ArrayList<Post>();
        listilla.add(new Post("dummy","dummy", new Date()));
        listilla.add(new Post("Rada","mensaje 1", new Date()));
        listilla.add(new Post("Rada","mensaje 2", new Date()));
        listilla.add(new Post("Rada","mensaje 3", new Date()));
        listilla.add(new Post("Rada","mensaje 4", new Date()));
        listilla.add(new Post("Rada","mensaje 5", new Date()));
        listilla.add(new Post("Rada","mensaje 6", new Date()));



  /*

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, listilla) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                text1.setText(listilla.get(position).getNombre());
                text2.setText(listilla.get(position).getMensaje());
                return view;
            }
        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                Post  itemValue    = (Post) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue.getMensaje() , Toast.LENGTH_LONG)
                        .show();

                Intent myIntent = new Intent(getApplicationContext(), DescripcionMensajeActivity.class);
                //To pass:
                //
                //intent.putExtra("MyClass", obj);
                // To retrieve object in second Activity
                //   getIntent().getSerializableExtra("MyClass");
                myIntent.putExtra("user",itemValue.getNombre());
                myIntent.putExtra("mensaje", itemValue.getMensaje());
                myIntent.putExtra("cosaExtra1", Login.getLoggedUser() + "@gmail.com");
                myIntent.putExtra("cosaExtra2", "cosa extra 2");
                startActivity(myIntent);



            }

        });
*/
//////////////CUSTOM LIST TUTO CON IMG
        //array dummy
      //  String [] stringArray = new String[listilla.size()+50];
        CustomList adapter = new CustomList(MainWall.this,listilla,imageId);
        list=(ListView)findViewById(R.id.list);
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
            intent.putExtra("user",Login.getLoggedUser());
            intent.putExtra("cosaExtra1", Login.getLoggedUser() + "@gmail.com");
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
}
