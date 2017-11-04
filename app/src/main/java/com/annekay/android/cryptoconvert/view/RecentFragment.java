package com.annekay.android.cryptoconvert.view;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.getSelectedCrypto;
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.getSelectedCurrency;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.adapter.CurrencyAdapter;
import com.annekay.android.cryptoconvert.adapter.RecentAdapter;
import com.annekay.android.cryptoconvert.model.ApiResponse;
import com.annekay.android.cryptoconvert.model.Crypto;
import com.annekay.android.cryptoconvert.service.CryptoApiService;
import com.annekay.android.cryptoconvert.service.CryptoUtil;
import com.annekay.android.cryptoconvert.service.RecentUtil;
import com.annekay.android.cryptoconvert.viewmodel.ValuesViewModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {
    FloatingActionButton fab;
    RecentAdapter adapter;
    private RecyclerView mRecyclerView;
    CryptoApiService mApiService = null;
    RecentUtil recentUtil;
    String CRYPTO_URL;
    List<Crypto> cryptoValues = new ArrayList<>();
    private String mSelectedCrypto, mSelectedCurrency;
    private static String cryptoValue, cryptoCurrency;


    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recent_layout, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);

        fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(getActivity(), CreateCardActivity.class);
                startActivity(intent);
            }
        });

        if(getSelectedCrypto() != null ) {
            mSelectedCrypto = getSelectedCrypto().trim();
            mSelectedCurrency = getSelectedCurrency().trim();
            cryptoValue = mSelectedCrypto;
            cryptoCurrency = mSelectedCurrency;
            CRYPTO_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms="+mSelectedCrypto+"&tsyms="+mSelectedCurrency;
            Toast.makeText(getActivity().getApplicationContext(), mSelectedCrypto + mSelectedCurrency, Toast.LENGTH_LONG).show();

          initVolleyCallback();
            recentUtil = new RecentUtil(mApiService, getContext());
            recentUtil.getDataVolley("GETCALL", CRYPTO_URL);
            setupView();
        }
        return rootView;
    }

    private void setupView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        adapter = new RecentAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(adapter);
    }

    public void initVolleyCallback(){

        mApiService = new CryptoApiService() {
            @Override
            public void notifySuccess(List<Crypto> mCryptoValues) {
                cryptoValues = mCryptoValues;
                adapter.addValues(cryptoValues);

            }
            @Override
            public void notifyError(String requestType,VolleyError error) {

            }
        };
    }

    public static String getCryptoValue(){
        return cryptoValue;
    }

    public static String getCryptoCurrency(){
        return cryptoCurrency;
    }
}
