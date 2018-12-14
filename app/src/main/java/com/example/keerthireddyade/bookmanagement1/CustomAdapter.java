package com.example.keerthireddyade.bookmanagement1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<ListItem> listItems;
    LayoutInflater inflater;
    ListItem listItem;
    DBAdapter db;

    public CustomAdapter(Context c, ArrayList<ListItem> listItems) {
        this.c = c;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }
        MyViewHolder holder=new MyViewHolder(convertView);

      holder.nameTxt.setText(listItems.get(position).getName());

        holder.AuthorTxt.setText(listItems.get(position).getAuthorname());

       holder.dateTxt.setText(listItems.get(position).getDate());


        /*BIND DATA
        MyViewHolder holder=new MyViewHolder(convertView);
        holder.nameTxt.setText(spacecrafts.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, spacecrafts.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        */


        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                /*
                Delete d=new Delete();
                d.delete();
                // Gets the database in write mode
                db.delete(getSelectedItemID());
                */
                //Toast.makeText(c, , Toast.LENGTH_SHORT).show();
                //Log.i("log", String.valueOf(spacecraft.getId()));
                DBAdapter db = new DBAdapter(c);
                db.openDB();
                boolean deleted = db.delete(listItems.get(position).getId());
                db.closeDB();
                listItems.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
