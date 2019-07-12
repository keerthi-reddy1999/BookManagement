package com.example.keerthireddyade.bookmanagement1;
//added optional changes
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    EditText nameEditText,authorEditText,date;
    Button saveBtn;
    ArrayList<ListItem> listItems=new ArrayList<>();
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lv=findViewById(R.id.list_view);
        adapter=new CustomAdapter(this,listItems);
        this.getSpacecrafts();
        //lv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog();
            }
        });
    }


    private void displayDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("SQLITE DATA");
        d.setContentView(R.layout.dialog_layout);

        nameEditText = (EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn = (Button) d.findViewById(R.id.saveBtn);
        authorEditText = (EditText) d.findViewById(R.id.AuthorEditTxt);
        date = (EditText) d.findViewById(R.id.DateCreated);
        // retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);


            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save(nameEditText.getText().toString(), authorEditText.getText().toString(), date.getText().toString());
                }
            });
        d.show();
    }

    //SAVE
    private void save(String name,String authorname,String date1)
    {
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        boolean saved=db.add(name,authorname,date1);
        if(saved)
        {
            nameEditText.setText("");
            authorEditText.setText("");
            date.setText("");
            getSpacecrafts();
        }else {
            Toast.makeText(this,"Unable To Save", Toast.LENGTH_SHORT).show();
        }
    }


    private void update(String newName,String authorn,String daten)
    {
        //GET ID OF SPACECRAFT
        int id=adapter.listItem.getId();

        //UPDATE IN DB
        DBAdapter db=new DBAdapter(this);
        db.openDB();
        boolean updated=db.update(newName,authorn,daten,id);
        db.closeDB();
        if(updated)
        {
            nameEditText.setText(newName);
            authorEditText.setText(authorn);
            date.setText(daten);
            getSpacecrafts();
        }else {
            Toast.makeText(this,"Unable To Update",Toast.LENGTH_SHORT).show();
        }

    }

    //RETRIEVE OR GETSPACECRAFTS
    public void getSpacecrafts()
    {
        listItems.clear();

        DBAdapter db=new DBAdapter(this);
        db.openDB();
        Cursor c=db.retrieve();
        ListItem listItem=null;

        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String authorname=c.getString(2);
            String datecreated=c.getString(3);
            listItem=new ListItem();
            listItem.setId(id);
            listItem.setName(name);
            listItem.setAuthorname(authorname);
            listItem.setDate(datecreated);
            listItems.add(listItem);
        }
            c.close();
        db.closeDB();
        lv.setAdapter(adapter);
    }



}