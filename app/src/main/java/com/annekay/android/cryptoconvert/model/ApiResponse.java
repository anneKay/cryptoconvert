package com.annekay.android.cryptoconvert.model;

import java.util.List;

/**
 * Created by anneKay on 10/28/2017.
 */

public class ApiResponse {

    private Crypto cryptoValues;
    private Throwable error;

    public ApiResponse(Crypto cryptoValues) {
        this.cryptoValues = cryptoValues;
        this.error = null;
    }

    public ApiResponse(Throwable error) {
        this.error = error;
        this.cryptoValues = null;
    }

    public Crypto getCryptoValue() {
        return cryptoValues;
    }

    public Throwable getError() {
        return error;
    }
}

