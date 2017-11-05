package com.annekay.android.cryptoconvert.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.annekay.android.cryptoconvert.model.ApiResponse;
import com.annekay.android.cryptoconvert.repositories.CryptoRepository;
import com.annekay.android.cryptoconvert.repositories.CryptoRepositoryImpl;

/**
 * Created by HP on 10/28/2017.
 */

public class ValuesViewModel extends ViewModel {

    private MediatorLiveData<ApiResponse> mApiResponse;
    private CryptoRepository mCryptoRepository;
//
//    public ValuesViewModel() {
//        mApiResponse = new MediatorLiveData<>();
//        mCryptoRepository = new CryptoRepositoryImpl();
//    }
//
//    @NonNull
//    public LiveData<ApiResponse> getApiResponse() {
//        return mApiResponse;
//    }
//
//    public LiveData<ApiResponse> loadValues() {
//        mApiResponse.addSource(
//                mCryptoRepository.getCryptoValue(),
//                new Observer<ApiResponse>() {
//                    @Override
//                    public void onChanged(@Nullable ApiResponse apiResponse) {
//                        mApiResponse.setValue(apiResponse);
//                    }
//                }
//        );
//        return mApiResponse;
//    }

}
