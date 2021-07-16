package com.fole_studios.bossa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Products.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME_PRODUCT = "PRODUCTS";
    public static final String TABLE_NAME_TRANSACTION = "TRANSACTIONS";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_SOLD = "PRODUCT_SOLD";
    public static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    public static final String COLUMN_TRANSACTION_ID = "TRANSACTION_ID";
    public static final String COLUMN_TRANSACTION_TOTAL = "TRANSACTION_TOTAL";
    public static final String COLUMN_TRANSACTION_SOLD = "TRANSACTION_SOLD";
    public static final String COLUMN_TRANSACTION_CONFIRMATION = "TRANSACTION_CONFIRMATION";

    public static final String TABLE_NAME_EMPLOYEE = "EMPLOYEES";
    public static final String COLUMN_EMPLOYEE_ID = "EMPLOYEE_ID";
    public static final String COLUMN_EMPLOYEE_NAME = "EMPLOYEE_NAME";
    public static final String COLUMN_EMPLOYEE_PHONE = "EMPLOYEE_PHONE";
    public static final String COLUMN_EMPLOYEE_EMAIL = "EMPLOYEE_EMAIL";
    public static final String COLUMN_EMPLOYEE_STORE = "EMPLOYEE_STORE";
    public static final String COLUMN_EMPLOYEE_STORE_ID = "EMPLOYEE_STORE_ID";

    public static final String CREATE_TABLE_PRODUCT = "create table " + TABLE_NAME_PRODUCT + " ( "
                                                                    + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                    + COLUMN_TRANSACTION_ID + " TEXT, "
                                                                    + COLUMN_PRODUCT_NAME + " TEXT, "
                                                                    + COLUMN_PRODUCT_SOLD + " INTEGER, "
                                                                    + COLUMN_PRODUCT_PRICE + " INTEGER);";

    public static final String CREATE_TABLE_TRANSACTION = "create table " + TABLE_NAME_TRANSACTION + " ( "
                                                                        + COLUMN_TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                        + COLUMN_TRANSACTION_TOTAL + " INTEGER, "
                                                                        + COLUMN_TRANSACTION_SOLD + " INTEGER, "
                                                                        + COLUMN_TRANSACTION_CONFIRMATION + " TEXT);";

    public static final String CREATE_TABLE_EMPLOYEE = "create table " + TABLE_NAME_EMPLOYEE + " ( "
                                                                        + COLUMN_EMPLOYEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                        + COLUMN_EMPLOYEE_NAME + " TEXT, "
                                                                        + COLUMN_EMPLOYEE_PHONE + " TEXT, "
                                                                        + COLUMN_EMPLOYEE_EMAIL + " TEXT, "
                                                                        + COLUMN_EMPLOYEE_STORE_ID + " INTEGER, "
                                                                        + COLUMN_EMPLOYEE_STORE + " TEXT);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_PRODUCT);
        db.execSQL(CREATE_TABLE_TRANSACTION);
        db.execSQL(CREATE_TABLE_EMPLOYEE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EMPLOYEE);
        onCreate(db);
    }
}
