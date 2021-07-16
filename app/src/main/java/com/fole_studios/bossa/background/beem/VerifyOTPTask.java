package com.fole_studios.bossa.background.beem;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public abstract class VerifyOTPTask extends AsyncTask<String, String , JSONObject> implements callBackOTP
{
    public static final String OTP_DATA_TASK = "OTPDataTask";
    private final WeakReference<Context> _contextWeakReference;

    public VerifyOTPTask(Context context)
    {
        _contextWeakReference = new WeakReference<>(context);
    }

    //Only retain a weak reference to this context

    @Override
    protected JSONObject doInBackground(String... strings)
    {
        OTP _otp = new OTP();

        return _otp.validateOTP(strings[0], strings[1]);
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
                int _code = 0;
                for(int i = 0; i < _jData.length(); i++)
                {
                    JSONObject _jDataString = _jData.getJSONObject("message");
                    for(int j = 0; j < _jDataString.length(); j++)
                    {
                        _code = _jDataString.getInt("code");
                    }
                }

                onPinReceived(String.valueOf(_code));
            }
            catch(JSONException jsonException)
            {
                jsonException.printStackTrace();
            }
        }
    }

    public abstract void onPinReceived(String pinId);
}
