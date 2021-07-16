package com.fole_studios.bossa.background.beem;

import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OTP
{
    public static final String OTP = "OTP";
    private final String REQUEST_URL = "https://apiotp.beem.africa/v1/request";
    private final String VERIFY_URL = "https://apiotp.beem.africa/v1/verify";
    private final int BEEM_APP_ID = 202;
    private final String API_KEY = "7ecbd4b86ecefb49";
    private final String SECRET_KEY = "YmIwYzk3ZTc2MGI4YzNlYmYwZGRhMDJhZjFlOGI5ZDdiYjI2Nzg5ZWJkZGFjOGU0ZmU3YjNiNDEwMjc1MjNiOQ==";

    public JSONObject sendOtp(String recipient)
    {
        OkHttpClient _client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(30, TimeUnit.MINUTES) // write timeout
                .readTimeout(30, TimeUnit.MINUTES) // read timeout
                .build();

        MediaType _mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\n" + " \"msisdn\": \"" + recipient + "\"\n" + "}");
        RequestBody _body = RequestBody.create(_mediaType, "{\"appId\":"+ BEEM_APP_ID + ",\"msisdn\":\""+ recipient +"\"}");

        Request _request = new Request.Builder()
                .url(REQUEST_URL)
                .post(_body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Credentials.basic(API_KEY, SECRET_KEY))
                .build();

        JSONObject _jsonResponse = null;
        try (Response response = _client.newCall(_request).execute())
        {

            if(response.body() != null)
            {
                String _responseBody = response.body().string();
                _jsonResponse = new JSONObject(_responseBody);
            }
            // ... do something with response

        }
        catch (IOException | JSONException e)
        {
            // ... handle IO exception
            e.printStackTrace();
        }

        return _jsonResponse;
    }

    public JSONObject validateOTP(String pinId, String pin)
    {
        OkHttpClient _client = new OkHttpClient();
        MediaType _mediaType = MediaType.parse("application/json");
        RequestBody _body = RequestBody.create(_mediaType, "{\"pinId\":\""+ pinId +"\",\"pin\":\""+ pin +"\"}");

        Request _request = new Request.Builder()
                .url(VERIFY_URL)
                .post(_body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", Credentials.basic(API_KEY, SECRET_KEY))
                .build();

        JSONObject _jsonResponse = null;
        try (Response response = _client.newCall(_request).execute())
        {

            if(response.body() != null)
            {
                String _responseBody = response.body().string();
                _jsonResponse = new JSONObject(_responseBody);
                //Code 117(Success) 114(Error)

            }
            // ... do something with response

        } catch (IOException | JSONException e)
        {
            // ... handle IO exception
            e.printStackTrace();
        }

        return _jsonResponse;
    }

}
