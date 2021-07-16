package com.fole_studios.bossa.models;

public class Block
{
    Transaction _transaction;
    Product _product;

    public Block(Transaction transaction, Product product)
    {
        _transaction = transaction;
        _product = product;
    }

    public Transaction getTransaction()
    {
        return _transaction;
    }

    public void setTransaction(Transaction transaction)
    {
        _transaction = transaction;
    }

    public Product getProduct()
    {
        return _product;
    }

    public void setProduct(Product product)
    {
        _product = product;
    }
}
