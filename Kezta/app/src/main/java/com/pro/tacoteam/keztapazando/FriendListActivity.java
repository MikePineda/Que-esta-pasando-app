package com.pro.tacoteam.keztapazando;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FriendListActivity extends AppCompatActivity {
    public static List<Perfil> compadres;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        setTitle("Compadres");

        compadres = new ArrayList<Perfil>();
        compadres.add(new Perfil("Rada", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radas", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radass", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radasss", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radassss", "123", "rada@gmail.com"));
        compadres.add(new Perfil("Radasssss", "123", "rada@gmail.com"));

        CustomListFriends adapter = new CustomListFriends(FriendListActivity.this,compadres,imageId);
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
}
