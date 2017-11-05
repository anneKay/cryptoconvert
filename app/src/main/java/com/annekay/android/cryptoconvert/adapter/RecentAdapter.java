package com.annekay.android.cryptoconvert.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.model.Crypto;
import com.annekay.android.cryptoconvert.view.RecentFragment;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static com.annekay.android.cryptoconvert.R.array.crypto;

/**
 * Created by HP on 11/3/2017.
 */

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.RecentAdapterViewHolder> {

    // private final LayoutInflater mInflator;
    private List<Crypto> mCryptoList;
    String currencyCode;
    private final Context mContext;
    private Double cryptoValue;
    private String cryptoString;
    String cryptoCurrency;


//    public RecentAdapter(LayoutInflater inflator) {
//        mInflator = inflator;
//        mCryptoList = new ArrayList<>();
//    }
    /**
     * The interface that receives onClick messages.
     */
    public interface RecentAdapterOnClickHandler {
        void onClick(String crypto, Double cryptoValue, String baseCurrency, String symbol);
    }

    final private RecentAdapterOnClickHandler mClickHandler;


    public RecentAdapter(@NonNull Context context, RecentAdapterOnClickHandler clickHandler) {
        mContext = context;
        mClickHandler = clickHandler;
        mCryptoList = new ArrayList<>();
    }

    @Override
    public RecentAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(mContext)
                .inflate(R.layout.recent_items, parent, false);

        view.setFocusable(true);

        return new RecentAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecentAdapterViewHolder holder, int position) {
        currencyCode = mCryptoList.get(position).getCurrency();
        cryptoValue = mCryptoList.get(position).getcryptoValue();
        holder.cryptoTextView.setText(cryptoValue.toString());
        cryptoCurrency =mCryptoList.get(position).getCrypto();
        holder.baseCurrency.setText(getCurrencySymbol());
        holder.dateTextView.setText(cryptoCurrency);

    }

    @Override
    public int getItemCount() {
       return mCryptoList.size();
    }

//    public void swapCursor(Cursor newCursor) {
//        mCursor = newCursor;
////      COMPLETED (12) After the new Cursor is set, call notifyDataSetChanged
//        notifyDataSetChanged();
//    }

    public void addValues(List<Crypto> cryptoValues) {
        mCryptoList.clear();
        mCryptoList.addAll(cryptoValues);
        notifyDataSetChanged();
    }

    public void clearCryptoValues() {
       // mCryptoList.clear();
        notifyDataSetChanged();
    }

    //   public class Holder extends RecyclerView.ViewHolder {
//
//
//        public Holder(View v) {
//            super(v);
//            // baseCurrency = (TextView) v.findViewById(R.id.base_currency);
//
//        }
//    }
    private String getCurrencySymbol(){
        //Currency currencySymbol = NumberFormat.getCurrencyInstance(new Locale("en", currencyCode)).getCurrency();
        Currency currencySymbol = Currency.getInstance(currencyCode);
        return currencySymbol.getSymbol();
    }

    class RecentAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //TextView baseCurrency;
        TextView cryptoTextView;
        TextView baseCurrency;
        TextView dateTextView;

        RecentAdapterViewHolder(View view) {
            super(view);

            cryptoTextView = view.findViewById(R.id.currency_symbol);
            baseCurrency = view.findViewById(R.id.currency_value);
            dateTextView = view.findViewById(R.id.date);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            List<Crypto> crypt = mCryptoList;
            mClickHandler.onClick(cryptoCurrency, cryptoValue, getCurrencySymbol(), currencyCode);
        }


    }

}



