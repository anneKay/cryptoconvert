package com.annekay.android.cryptoconvert.view;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.adapter.CurrencyAdapter;
import com.annekay.android.cryptoconvert.model.ApiResponse;
import com.annekay.android.cryptoconvert.model.Crypto;
import com.annekay.android.cryptoconvert.service.CryptoApiService;
import com.annekay.android.cryptoconvert.service.CryptoUtil;
import com.annekay.android.cryptoconvert.viewmodel.ValuesViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeaturedCoinsFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private final String CRYPTO_URL= "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,NGN,BRL,BWP,GHC,HKD,JPY,KPW,CNY,DKK&extraParams=cryptocon";
    private ProgressDialog mDialog;
    private CurrencyAdapter mAdapter;
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    CryptoApiService mApiService = null;
    CryptoUtil mCryptoUtil;
    List<Crypto> cryptoValues = new ArrayList<>();





    public FeaturedCoinsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.featured_layout, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        initVolleyCallback();
        mCryptoUtil = new CryptoUtil(mApiService,getContext());
        mCryptoUtil.getDataVolley("GETCALL", CRYPTO_URL);
        setupView();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initVolleyCallback(){
        mApiService = new CryptoApiService() {
            @Override
            public void notifySuccess(List<Crypto> mCryptoValues) {
                cryptoValues = mCryptoValues;
                mAdapter.addValues(cryptoValues);
            }

            @Override
            public void notifyError(String requestType,VolleyError error) {
            }
        };
    }

    private void setupView() {


        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(), LinearLayoutManager.VERTICAL
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mAdapter = new CurrencyAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }


    public void setProgress(boolean flag) {
        if (flag) {
            mDialog.show();
        } else {
            mDialog.dismiss();
        }
    }

}
