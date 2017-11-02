package com.annekay.android.cryptoconvert.model;


/**
 * Created by HP on 10/28/2017.
 */

public class Crypto {
    private Double mBtcValue;
    private Double mEthValue;
    private Double keyValue;
    private BTC bTC;
    private ETH eTH;
    private String mCurrency;

    public Crypto(String currency, Double btcValue, Double ethValue) {

        this.mBtcValue = btcValue;
        this.mEthValue = ethValue;
        this.mCurrency = currency;

    }

    public Double getbtcValue() {
        return mBtcValue;
    }

    public Double getEthValue() {
        return mEthValue;
    }

    public String getCurrency() {
        return mCurrency;
    }

}
