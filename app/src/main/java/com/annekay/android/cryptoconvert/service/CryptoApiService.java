package com.annekay.android.cryptoconvert.service;

import com.android.volley.VolleyError;
import com.annekay.android.cryptoconvert.model.BTC;
import com.annekay.android.cryptoconvert.model.Crypto;

import java.util.List;


import static com.android.volley.Request.Method.GET;

/**
 * Created by anneKay on 10/28/2017.
 */

public interface CryptoApiService {
    void notifySuccess(List<Crypto> cryptoValues);
    void notifyError(String requestType,VolleyError error);

}
