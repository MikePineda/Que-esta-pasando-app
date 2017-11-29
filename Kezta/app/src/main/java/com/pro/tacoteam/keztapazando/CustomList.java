package com.pro.tacoteam.keztapazando;

/**
 * Created by mikep on 13/11/2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class CustomList extends ArrayAdapter<String>{
//mike
    List<Post> post;



    private final Activity context;
    private final Integer[] imageId;
    //MIKEEEE
    public CustomList(Activity context,
                      List<Post> posts, Integer[] imageId) {
        //noob se usa porque por alguna razon me pide un arreglo de strings para iterar, asi que hice un arreglo dummy del tama;o de mi lista,
        // pero al momento de insertar valores en las views uso Posts
        super(context, R.layout.list_single,new String[posts.size()] );
        this.context = context;
        this.post = posts;
        this.imageId = imageId;

    }

    ///
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        //buscando los textviews para asignarles valor
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView txtMsg = (TextView) rowView.findViewById(R.id.txt2);
        //asignando valor
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        try{
            txtTitle.setText(post.get(position).getNombre().toString());
            txtMsg.setText(post.get(position).getMensaje().toString());
        }catch(IndexOutOfBoundsException e){
            txtTitle.setText("error");
            txtMsg.setText("error");
        }

        imageView.setImageResource(Integer.parseInt("2131165281"));
        return rowView;
    }
}