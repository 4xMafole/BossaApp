package com.fole_studios.bossa.background.beem;

import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;


public class OTPClient
{
    private String _otpPinId;
    private Context _context;
    private String _phoneNumber;
    public boolean _isPin;

    public OTPClient(Context context, String phoneNumber)
    {
        _context = context;
        _phoneNumber = phoneNumber;
    }

    public void run()
    {
        initOTP();
    }

    public void pinValidity(String pin)
    {
        verifyOTP(pin);
    }

    private void initOTP()
    {
        new OTPDataTask(_context)
        {
            @Override
            public void onPinReceived(String pinId)
            {
                _otpPinId = pinId;
            }
        }.execute(_phoneNumber);

    }

    private void verifyOTP(String pin)
    {
        new VerifyOTPTask(_context)
        {
            @Override
            public void onPinReceived(String code)
            {
                validateCode(code);
            }
        }.execute(_otpPinId, pin);
    }

    private boolean validateCode(String code)
    {
        int _code = Integer.parseInt(code);
        if(_code == 117)
        {
            //valid pin
            _isPin = true;
        }
        else if(_code == 114)
        {
            //invalid pin
            _isPin = false;
        }

        return _isPin;
    }

}
