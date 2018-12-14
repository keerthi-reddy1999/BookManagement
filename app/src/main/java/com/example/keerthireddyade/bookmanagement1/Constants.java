package com.example.keerthireddyade.bookmanagement1;

public class Constants {
    //COLUMNS
    static final String ROW_ID="id";
    static final String NAME="name";
    static final String AUTHOR_NAME="author_name";
    static final String DATE="date";


    //DB PROPERTIES
    static final String DB_NAME="hh_DB";
    static final String TB_NAME="hh_TB";
    static final int DB_VERSION=1;


    //CREATE TB STMT
     static final String CREATE_TABLE = "create table " + TB_NAME + "(" + ROW_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + AUTHOR_NAME + " TEXT NOT NULL, " + DATE + " TEXT );";


    //DROP TB STMT
    static final String DROP_TB="DROP TABLE IF EXISTS "+TB_NAME;
}