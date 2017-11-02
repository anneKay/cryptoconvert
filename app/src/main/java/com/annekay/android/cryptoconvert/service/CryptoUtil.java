package com.annekay.android.cryptoconvert.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.annekay.android.cryptoconvert.model.Crypto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.annekay.android.cryptoconvert.view.FeaturedCoinsFragment.LOG_TAG;


/**
 * Created by HP on 11/1/2017.
 */

public class CryptoUtil {

    public static final String BASE_URL = "https://min-api.cryptocompare.com";
    private CryptoApiService mApiService = null;
    Context mContext;
    public Double btcValue, ethValue;
    Double usdBtcValues, usdEthValues;
    Double ngnBtcValues, ngnEthValues;
    Double eurBtcValues, eurEthValues;
    final List<Crypto> cryptoValues = new ArrayList<>();

    public CryptoUtil(CryptoApiService resultCallback, Context context) {
        mApiService = resultCallback;
        mContext = context;

    }

    public void postDataVolley(final String requestType, String url, JSONObject sendObj) {
        final List<Crypto> cryptoValues = new ArrayList<>();

        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(url, sendObj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (mApiService != null)
                        mApiService.notifySuccess(cryptoValues);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (mApiService != null)
                        mApiService.notifyError(requestType, error);
                }
            });

            queue.add(jsonObj);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void getDataVolley(final String requestType, String url){

        try {
            RequestQueue queue = Volley.newRequestQueue(mContext);

            JsonObjectRequest jsonObj = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(mApiService != null)

                        Log.d(LOG_TAG, response.toString());

                    // Parsing json
                    try {
                        // Extract the JSONArray associated with the key called "items",
                        // which represents a list of items (or earthquakes).
                        JSONObject btcObject = response.getJSONObject("BTC".trim());
                        JSONObject ethObject = response.getJSONObject("ETH".trim());

                        Iterator<?> btcKeys = btcObject.keys();
                        Iterator<?> ethKeys = ethObject.keys();

                        while (btcKeys.hasNext() && ethKeys.hasNext()){
                            String btcKey = (String) btcKeys.next();
                            String ethKey = (String) ethKeys.next();


//                        // for (int i = 0; i < cryptoObject.length(); i++) {
//
//                        // Extract the value for the key called "avatar_url"
//                        usdBtcValues = btcObject.getDouble("USD");
//
//                        // Extract the value for the key called "login"
//                        ngnBtcValues = btcObject.getDouble("NGN");
//
//                        // Extract the value for the key called "html_url"
//                        eurBtcValues = btcObject.getDouble("EUR");
//                        // Create a new object with the developer items
//                        usdEthValues = ethObject.getDouble("USD");
//                        ngnEthValues = ethObject.getDouble("NGN");
//                        eurEthValues = ethObject.getDouble("EUR");
//                        Double []ethValues  = {usdEthValues, ngnEthValues, eurEthValues};
//                        Double [] btcValues = {usdBtcValues, ngnBtcValues, eurBtcValues};

                        Crypto cryptoValue = new Crypto(btcKey, btcObject.getDouble(btcKey), ethObject.getDouble(ethKey));
                        // adding developer to developer's array
                        cryptoValues.add(cryptoValue);
                        }

                        mApiService.notifySuccess(cryptoValues);
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(mApiService != null)
                        mApiService.notifyError(requestType, error);
                }
            });

            queue.add(jsonObj);

        }catch(Exception e){
            e.printStackTrace();

        }
    }


}
