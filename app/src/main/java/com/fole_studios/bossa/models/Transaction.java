package com.fole_studios.bossa.models;

public class Transaction
{
    private String _transactionID;
    private String _confirmation;

    public Transaction(String transactionID, String confirmation)
    {
        _transactionID = transactionID;
        _confirmation = confirmation;
    }

    public String getTransactionID()
    {
        return _transactionID;
    }

    public void setTransactionID(String transactionID)
    {
        _transactionID = transactionID;
    }

    public String getConfirmation()
    {
        return _confirmation;
    }

    public void setConfirmation(String confirmation)
    {
        _confirmation = confirmation;
    }
}
