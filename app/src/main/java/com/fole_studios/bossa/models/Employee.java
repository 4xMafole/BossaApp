package com.fole_studios.bossa.models;

public class Employee
{
    private String _employeeName;
    private String _phoneNumber;
    private int _storeId;

    public Employee(String employeeName, String phoneNumber, int storeId)
    {
        _employeeName = employeeName;
        _phoneNumber = phoneNumber;
        _storeId = storeId;
    }

    public String getEmployeeName()
    {
        return _employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        _employeeName = employeeName;
    }

    public String getPhoneNumber()
    {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        _phoneNumber = phoneNumber;
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
