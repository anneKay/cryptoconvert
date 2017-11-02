package com.annekay.android.cryptoconvert.view;


import android.app.Activity;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.ProgressBar;
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
public class FeaturedCoinsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView mRecyclerView;
    private final String CRYPTO_URL= "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,NGN,BRL,BWP,GHC,HKD,JPY,KPW,CNY,DKK&extraParams=cryptocon";

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;
    private TextView feedBackView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
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
        mErrorMessageDisplay = rootView.findViewById(R.id.tv_error_message_display);
        feedBackView = rootView.findViewById(R.id.feed_back);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swipe_container);
        //apply different colors to the swipe refresh layout
        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);

        mLoadingIndicator = rootView.findViewById(R.id.pb_loading_indicator);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        detectNetworkState();
        setupView();

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoadingIndicator.setVisibility(View.GONE);
    }
    @Override
    public void onRefresh() {
        detectNetworkState();

    }

    public void initVolleyCallback(){
       showWeatherDataView();
        mApiService = new CryptoApiService() {
            @Override
            public void notifySuccess(List<Crypto> mCryptoValues) {
                mLoadingIndicator.setVisibility(View.GONE);
                cryptoValues = mCryptoValues;
                mAdapter.addValues(cryptoValues);
                mSwipeRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mSwipeRefreshLayout.isRefreshing()) {
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }
                });

            }

            @Override
            public void notifyError(String requestType,VolleyError error) {
                showErrorMessage();
            }
        };
    }
    // method to detect network connectivity
    public void detectNetworkState() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            initVolleyCallback();
            mCryptoUtil = new CryptoUtil(mApiService,getContext());
            mCryptoUtil.getDataVolley("GETCALL", CRYPTO_URL);
        } else {

          showNetworkMessage();

        }
    }

    private void setupView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();
        mAdapter = new CurrencyAdapter(getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);
    }
    private void showWeatherDataView() {
        mErrorMessageDisplay.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.GONE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }
    private void showNetworkMessage(){
        mRecyclerView.setVisibility(View.GONE);
        mLoadingIndicator.setVisibility(View.GONE);
        feedBackView.setVisibility(View.VISIBLE);
        feedBackView.setText(R.string.feedback);

    }


}
