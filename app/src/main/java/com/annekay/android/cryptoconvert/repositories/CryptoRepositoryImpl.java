package com.annekay.android.cryptoconvert.repositories;

import android.app.ProgressDialog;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.annekay.android.cryptoconvert.model.ApiResponse;
import com.annekay.android.cryptoconvert.model.Crypto;
import com.annekay.android.cryptoconvert.service.CryptoApiService;
import com.annekay.android.cryptoconvert.service.CryptoUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.annekay.android.cryptoconvert.view.FeaturedCoinsFragment.LOG_TAG;

/**
 * Created by anneKay on 10/28/2017.
 */

public class CryptoRepositoryImpl {

//    public static final String BASE_URL = "https://min-api.cryptocompare.com";
//    private CryptoApiService mApiService = null;
//    Context mContext;
//    public Double btcValue, ethValue;
//    Double usdBtcValues, usdEthValues;
//    Double ngnBtcValues, ngnEthValues;
//    Double eurBtcValues, eurEthValues;
//    final List<Crypto> cryptoValues = new ArrayList<>();
//    CryptoUtil mCryptoUtil;
//    private String CRYPTOURL = "min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,NGN,BRL,BWP,GHC,HKD,JPY,KPW,CNY,DKK&extraParams=cryptocon"
//
//
////
////    public CryptoRepositoryImpl() {
////        Retrofit retrofit = new Retrofit.Builder()
////                .addConverterFactory(GsonConverterFactory.create())
////                .baseUrl(BASE_URL)
////                .build();
////        mApiService = retrofit.create(CryptoApiService.class);
////    }
//
//    public LiveData<ApiResponse> getCryptoValue() {
//        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
//        mCryptoUtil = new CryptoUtil(mApiService);
//        mCryptoUtil.getDataVolley("GETCALL",CRYPTOURL);
//        mApiService.notifySuccess(cryptoValues);
//                liveData.setValue(mApiService);
//            }
//
//            @Override
//            public void onFailure(Call<List<Issue>> call, Throwable t) {
//                liveData.setValue(new ApiResponse(t));
//            }
//        });
//        return liveData;
//    }
//        }

                }



