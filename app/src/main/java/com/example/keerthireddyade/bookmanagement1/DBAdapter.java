package com.example.keerthireddyade.bookmanagement1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBAdapter {

    private final Context c;
    private SQLiteDatabase db;
    private DBHelper helper;

    public DBAdapter(Context c) {
        this.c = c;
        helper=new DBHelper(c);
    }
    //OPEN CON
    public void openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {

        }
    }
    //CLOSE DB
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {

        }
    }
    //SAVE
    public boolean add(String name,String author_name,String date)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constants.NAME,name);
            cv.put(Constants.AUTHOR_NAME,author_name);
            cv.put(Constants.DATE,date);
            long result=db.insert(Constants.TB_NAME,Constants.ROW_ID,cv);
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;
    }
    //SELECT
    public Cursor retrieve()
    {
        String[] columns={Constants.ROW_ID,Constants.NAME,Constants.AUTHOR_NAME,Constants.DATE};

        Cursor c=db.query(Constants.TB_NAME,columns,null,null,null,null,null);
        return c;
    }


    public boolean update(String newName,String authorname,String date,int id)
    {
        try
        {
            ContentValues cv=new ContentValues();
            cv.put(Constants.NAME,newName);
            cv.put(Constants.AUTHOR_NAME,authorname);
            cv.put(Constants.DATE,date);

            int result=db.update(Constants.TB_NAME,cv, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
            if(result>0)
            {
                return true;
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return false;

    }

    //DELETE/REMOVE
    public boolean delete(int id) {

        try {
            int result = db.delete(Constants.TB_NAME, Constants.ROW_ID + " =?", new String[]{String.valueOf(id)});
            if (result > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
}














