package com.fole_studios.bossa.models;

public class Product
{
    private String _productName;
    private int _productSold;
    private String _productCost;

    public Product(String productName, int productSold, String productCost)
    {
        _productName = productName;
        _productSold = productSold;
        _productCost = productCost;
    }

    public String getProductName()
    {
        return _productName;
    }

    public void setProductName(String productName)
    {
        _productName = productName;
    }

    public int getProductSold()
    {
        return _productSold;
    }

    public void setProductSold(int productSold)
    {
        _productSold = productSold;
    }

    public String getProductCost()
    {
        return _productCost;
    }

    public void setProductCost(String productCost)
    {
        _productCost = productCost;
    }
}
