package com.fole_studios.bossa.models;

public class Store
{
    private String _storeName;
    private int _storeId;

    public Store(String storeName, int storeId)
    {
        _storeName = storeName;
        _storeId = storeId;
    }

    public String getStoreName()
    {
        return _storeName;
    }

    public void setStoreName(String storeName)
    {
        _storeName = storeName;
    }

    public int getStoreId()
    {
        return _storeId;
    }

    public void setStoreId(int storeId)
    {
        _storeId = storeId;
    }
}
