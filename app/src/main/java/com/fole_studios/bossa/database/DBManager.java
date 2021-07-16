package com.fole_studios.bossa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager
{

    private Context _context;
    private DatabaseHelper _databaseHelper;
    private SQLiteDatabase _database;

    public DBManager(Context context)
    {
        _context = context;
    }

    public DBManager open() throws SQLException
    {
        _databaseHelper = new DatabaseHelper(_context);
        _database = _databaseHelper.getWritableDatabase();

        return this;
    }

    public void close()
    {
        _databaseHelper.close();
    }

    public void insertProduct(String productName, int transactionId, int productSold, int productPrice)
    {
        ContentValues _contentValues = new ContentValues();
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_NAME, productName);
        _contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_ID, transactionId);
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_SOLD, productSold);
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_PRICE, productPrice);

        _database.insert(DatabaseHelper.TABLE_NAME_PRODUCT, null, _contentValues);
    }

    public void insertTransaction(int transactionId, int transactionTotal, int transactionSold, String confirmation)
    {
        ContentValues _contentValues = new ContentValues();
        _contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_ID, transactionId);
        _contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_TOTAL, transactionTotal);
        _contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_SOLD, transactionSold);
        _contentValues.put(DatabaseHelper.COLUMN_TRANSACTION_CONFIRMATION, confirmation);

        _database.insert(DatabaseHelper.TABLE_NAME_TRANSACTION, null, _contentValues);
    }

    public Cursor fetchTransaction()
    {
        String[] _columns = new String[] {DatabaseHelper.COLUMN_TRANSACTION_ID, DatabaseHelper.COLUMN_TRANSACTION_TOTAL,
                DatabaseHelper.COLUMN_TRANSACTION_SOLD, DatabaseHelper.COLUMN_TRANSACTION_CONFIRMATION};
        Cursor _cursor = _database.query(DatabaseHelper.TABLE_NAME_TRANSACTION, _columns, null, null, null, null, null);

        if(_cursor != null)
        {
            _cursor.moveToFirst();
        }
        return _cursor;
    }

    public  Cursor fetchProduct()
    {
        String[] _columns = new String[] {DatabaseHelper.COLUMN_PRODUCT_ID, DatabaseHelper.COLUMN_TRANSACTION_ID,
                                         DatabaseHelper.COLUMN_PRODUCT_NAME,
                                         DatabaseHelper.COLUMN_PRODUCT_SOLD, DatabaseHelper.COLUMN_PRODUCT_PRICE};
        Cursor _cursor = _database.query(DatabaseHelper.TABLE_NAME_PRODUCT, _columns, null, null, null, null, null);
        if(_cursor != null)
        {
            _cursor.moveToFirst();
        }
        return _cursor;
    }

    public Cursor fetchLatestProducts(int productId)
    {
        Cursor _cursor = _database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_PRODUCT + " WHERE " + DatabaseHelper.COLUMN_TRANSACTION_ID + " = '" + productId + "'", null);

        if(_cursor != null)
        {
            _cursor.moveToFirst();
        }
        return _cursor;
    }

    public Cursor fetchLatestTransaction(int transactionId)
    {
        Cursor _cursor = _database.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME_TRANSACTION + " WHERE " + DatabaseHelper.COLUMN_TRANSACTION_ID + " = '" + transactionId + "'", null);

        if(_cursor != null)
        {
            _cursor.moveToFirst();
        }
        return _cursor;
    }

    //TODO: future use
/*
    public int update(long productId, String productName, int productSold, int productPrice)
    {
        ContentValues _contentValues = new ContentValues();
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_NAME, productName);
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_SOLD, productSold);
        _contentValues.put(DatabaseHelper.COLUMN_PRODUCT_PRICE, productPrice);
        return _database.update(DatabaseHelper.TABLE_NAME, _contentValues, DatabaseHelper.COLUMN_PRODUCT_ID + " = " + productId, null);
    }
*/

    public void deleteProduct(long productId)
    {
        _database.delete(DatabaseHelper.TABLE_NAME_PRODUCT, DatabaseHelper.COLUMN_PRODUCT_ID + "=" + productId, null);
    }



}
