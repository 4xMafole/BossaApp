package com.fole_studios.bossa.background.beem;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public abstract class OTPDataTask extends AsyncTask<String, String , JSONObject> implements callBackOTP
{
    public static final String OTP_DATA_TASK = "OTPDataTask";
    private final WeakReference<Context> _contextWeakReference;

    public OTPDataTask(Context context)
    {
        _contextWeakReference = new WeakReference<>(context);
    }

    //Only retain a weak reference to this context

    @Override
    protected JSONObject doInBackground(String... strings)
    {
        OTP _otp = new OTP();

        return _otp.sendOtp(strings[0]);
    }

    @Override
    protected void onPostExecute(JSONObject response)
    {
        if(response != null)
        {
            try
            {
                Log.i(OTP_DATA_TASK, "Success: " + response.getJSONObject("data"));
                JSONObject _jData = response.getJSONObject("data");
                String _pinId = null;
                for(int i = 0; i < _jData.length(); i++)
                {
                    _pinId = _jData.getString("pinId");
                }

                onPinReceived(_pinId);
            }
            catch(JSONException jsonException)
            {
                jsonException.printStackTrace();
            }
        }
    }

    public abstract void onPinReceived(String pinId);
}
