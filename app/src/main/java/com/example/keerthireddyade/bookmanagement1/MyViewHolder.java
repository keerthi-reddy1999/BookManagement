package com.example.keerthireddyade.bookmanagement1;

import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyViewHolder {

    TextView nameTxt,AuthorTxt,dateTxt;
    ImageButton im;
    public MyViewHolder(View v) {
        nameTxt= (TextView) v.findViewById(R.id.book_name);
        AuthorTxt=(TextView) v.findViewById(R.id.author_name);
        dateTxt=(TextView)v.findViewById(R.id._date);
im=(ImageButton) v.findViewById(R.id.image_button);


    }


}