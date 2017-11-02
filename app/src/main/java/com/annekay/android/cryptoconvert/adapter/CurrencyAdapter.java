package com.annekay.android.cryptoconvert.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.model.BTC;
import com.annekay.android.cryptoconvert.model.Crypto;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.id;
import static android.content.ContentValues.TAG;
import static android.media.CamcorderProfile.get;

/**
 * Created by HP on 10/28/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.Holder> {

    private final LayoutInflater mInflator;
    private List<Crypto> mCryptoList;

    public CurrencyAdapter(LayoutInflater inflator) {
        mInflator = inflator;
        mCryptoList = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflator.inflate(R.layout.crypto_values, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //holder.baseCurrency.setText(mCryptoList.get(position).getBTC().getUSD().toString());

        Double btcValue = mCryptoList.get(position).getbtcValue();
        Double ethValue = mCryptoList.get(position).getEthValue();
        String currency = mCryptoList.get(position).getCurrency();
        holder.baseCurrency.setText(currency);
        holder.textViewBtc.setText(btcValue.toString());
        holder.textViewEth.setText(ethValue.toString());

    }

    @Override
    public int getItemCount() {
        return mCryptoList.size();
    }

    public void addValues(List<Crypto> cryptoValues) {
        mCryptoList.clear();
        mCryptoList.addAll(cryptoValues);
        notifyDataSetChanged();
    }

    public void clearCryptoValues() {
        mCryptoList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView baseCurrency;
        TextView textViewEth;
        TextView textViewBtc;

        public Holder(View v) {
            super(v);
            baseCurrency = (TextView) v.findViewById(R.id.base_currency);
            textViewBtc = (TextView) v.findViewById(R.id.btc);
            textViewEth = (TextView) v.findViewById(R.id.eth);
        }
    }
}
