package com.annekay.android.cryptoconvert.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by HP on 11/7/2017.
 */

public class CryptoContract {

    public static final String CONTENT_AUTHORITY = "com.annekay.android.cryptoconvert";
    public static final String PATH_CRYPTOS = "cryptos";
    private static final Uri BASE_URL = Uri.parse("content://com.annekay.android.cryptoconvert");

    private CryptoContract(){}

    public static final class PetEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URL, PATH_CRYPTOS);
        public static final String TABLE_NAME = "cryptos";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_CRYPTO_NAME = "name";
        public static final String COLUMN_CURRENCY = "basecurrency";
        public static final String COLUMN_CURRENCY_VALUE = "cryptovalue";
        public static final String COLUMN_SYMBOL = "currencysymbol";
        /**
         * The MIME type of the {@link #CONTENT_URI} for a list of pets.
         */
        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CRYPTOS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single pet.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CRYPTOS;
    }

}
