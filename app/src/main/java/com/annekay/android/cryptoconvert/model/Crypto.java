package com.annekay.android.cryptoconvert.model;


/**
 * Created by HP on 10/28/2017.
 */

public class Crypto {
    private Double mBtcValue;
    private Double mEthValue;
    private Double keyValue;
    private Double mCryptoValue;
    private BTC bTC;
    private ETH eTH;
    private String mCurrency, mCrypto;

    public Crypto(String currency, Double btcValue, Double ethValue) {

        this.mBtcValue = btcValue;
        this.mEthValue = ethValue;
        this.mCurrency = currency;

    }

    public Crypto (String currency, Double cryptoValue, String crypto){
        this.mCurrency = currency;
        this.mCryptoValue = cryptoValue;
        this.mCrypto = crypto;
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
    public Double getcryptoValue(){
        return mCryptoValue;
    }
    public String getCrypto(){
        return mCrypto;
    }
}
