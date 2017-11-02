package com.annekay.android.cryptoconvert.view;


import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import android.widget.TextView;
import android.widget.Toast;

import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.adapter.CurrencyAdapter;
import com.annekay.android.cryptoconvert.model.ApiResponse;
import com.annekay.android.cryptoconvert.model.Crypto;
import com.annekay.android.cryptoconvert.viewmodel.ValuesViewModel;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private ProgressDialog mDialog;
    private CurrencyAdapter mAdapter;
    private ValuesViewModel mViewModel;



    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.featured_layout, container, false);
//        mViewModel = ViewModelProviders.of(getActivity()).get(ValuesViewModel.class);
//        setupView();
//
//
//        mViewModel.loadValues();
//        // Handle changes emitted by LiveData
//        mViewModel.getApiResponse().observe(getActivity(), new Observer<ApiResponse>() {
//            @Override
//            public void onChanged(@Nullable ApiResponse apiResponse) {
//                if (apiResponse.getError() != null) {
//                    handleError(apiResponse.getError());
//                } else {
//                    handleResponse(apiResponse.getCryptoValue());
//                }
//            }
//        });
//         TextView tv = rootView.findViewById(R.id.tv);
//        tv.setText("Recent Fragment");
        return rootView;
    }


}
