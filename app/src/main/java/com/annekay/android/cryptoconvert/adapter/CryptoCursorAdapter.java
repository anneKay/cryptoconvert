package com.annekay.android.cryptoconvert.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.annekay.android.cryptoconvert.R;
import com.annekay.android.cryptoconvert.data.CryptoContract;
import com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry;


import com.annekay.android.cryptoconvert.model.Crypto;

/**
 * Created by HP on 11/8/2017.
 */

public class CryptoCursorAdapter extends CursorAdapter{

    public CryptoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // TODO: Fill out this method and return the list item view (instead of null)
        return LayoutInflater.from(context).inflate(R.layout.recent_items, parent, false);
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // TODO: Fill out this method
        TextView cryptoNameView = (TextView) view.findViewById(R.id.crypto_name);
        TextView currencyValueView = (TextView) view.findViewById(R.id.currency_value);
        TextView symbolView = view.findViewById(R.id.currency_symbol);

        // Find the columns of pet attributes that we're interested

        int nameColumnIndex = cursor.getColumnIndex(CryptoEntry.COLUMN_CRYPTO_NAME);
        int cryptoValueColumnIndex = cursor.getColumnIndex(CryptoEntry.COLUMN_CURRENCY_VALUE);
        int symbolColumnIndex = cursor.getColumnIndex(CryptoEntry.COLUMN_SYMBOL);

        // Read the pet attributes from the Cursor for the current pet
        String cryptoName = cursor.getString(nameColumnIndex);
        Double cryptoValue = cursor.getDouble(cryptoValueColumnIndex);
        String currencySymbol = cursor.getString(symbolColumnIndex);

        // Update the TextViews with the attributes for the current pet
        cryptoNameView.setText(cryptoName);
        currencyValueView.setText(cryptoValue.toString());
        symbolView.setText(currencySymbol);


    }
}
