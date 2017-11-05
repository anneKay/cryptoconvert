package com.annekay.android.cryptoconvert.repositories;

import android.arch.lifecycle.LiveData;

import com.annekay.android.cryptoconvert.model.ApiResponse;

/**
 * Created by HP on 10/28/2017.
 */

public interface CryptoRepository {

    LiveData<ApiResponse> getCryptoValue();

}
