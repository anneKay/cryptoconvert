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
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.getSelectedCrypto;
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.selectedCrypto;
import static com.annekay.android.cryptoconvert.view.FeaturedCoinsFragment.LOG_TAG;


/**
 * Created by HP on 11/1/2017.
 */

public class CryptoUtil {

    public static final String BASE_URL = "https://min-api.cryptocompare.com";
    private CryptoApiService mApiService = null;
    Context mContext;
    final List<Crypto> cryptoValues = new ArrayList<>();

    public CryptoUtil(CryptoApiService resultCallback, Context context) {
        mApiService = resultCallback;
        mContext = context;

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
                        // Extract the JSONObject associated with the key called "BTC"
                        JSONObject btcObject = response.getJSONObject("BTC".trim());
                        // Extract the JSONObject associated with the key called "BTC"
                        JSONObject ethObject = response.getJSONObject("ETH".trim());
//
                        //JSONObject cryptoObject = response.getJSONObject(selectedCrypto);

                        Iterator<?> btcKeys = btcObject.keys();
                        Iterator<?> ethKeys = ethObject.keys();

//                        if (btcKeys.hasNext() && ethKeys.hasNext()) {
//                            String btcKey = (String) btcKeys.next();
//                            String ethKey = (String) ethKeys.next();
//
//                            Crypto cryptoValue = new Crypto(btcKey, btcObject.getDouble(btcKey), ethObject.getDouble(ethKey));
//                            // adding crypto values to crypto array
//                            cryptoValues.add(cryptoValue);
//                        }else {
//                            String cryptoKey = selectedCrypto;
//
//                            Crypto cryptoValue = new Crypto(cryptoKey, cryptoObject.getDouble(cryptoKey));
//                            // adding crypto values to crypto array
//                            cryptoValues.add(cryptoValue);


                        while (btcKeys.hasNext() && ethKeys.hasNext()){
                            String btcKey = (String) btcKeys.next();
                            String ethKey = (String) ethKeys.next();

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
