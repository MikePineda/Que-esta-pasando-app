package com.pro.tacoteam.keztapazando;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mikep on 28/11/2017.
 */

public class CustomListFriends extends ArrayAdapter<String> {

    List<Perfil> perfiles;
    private final Activity context;
    private final Integer[] imageId;
    //MIKEEEE
    public CustomListFriends(Activity context,
                      List<Perfil> perfiles, Integer[] imageId) {
        //noob se usa porque por alguna razon me pide un arreglo de strings para iterar, asi que hice un arreglo dummy del tama;o de mi lista,
        // pero al momento de insertar valores en las views uso Posts
        super(context, R.layout.list_single,new String[perfiles.size()] );
        this.context = context;
        this.perfiles = perfiles;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single_friends, null, true);
        //buscando los textviews para asignarles valor
        TextView name = (TextView) rowView.findViewById(R.id.friendsListName);
        TextView correo = (TextView) rowView.findViewById(R.id.friendsListMail);
        //asignando valor
        ImageView imageView = (ImageView) rowView.findViewById(R.id.friendsListPhoto);
        name.setText(perfiles.get(position).getUsername().toString());
        correo.setText(perfiles.get(position).getCorreo().toString());
        imageView.setImageResource(Integer.parseInt("2131165281"));
        return rowView;
    }


}
