package com.annekay.android.cryptoconvert.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry;


import com.annekay.android.cryptoconvert.model.Crypto;

import java.security.Provider;

import static com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry.CONTENT_ITEM_TYPE;
import static com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry.CONTENT_LIST_TYPE;
import static com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry._ID;

/**
 * Created by HP on 11/7/2017.
 */

public class CryptoProvider extends ContentProvider {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = CryptoProvider.class.getSimpleName();
    // variable for the petsqlitehelper class
    CryptoDpHelper mCryptoDbHelper;
    private static final int CRYPTOS = 100;


    /**
     * URI matcher code for the content URI for a single pet in the pets table
     */
    private static final int CRYPTO_ID = 101;
    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        sUriMatcher.addURI(CryptoContract.CONTENT_AUTHORITY, CryptoContract.PATH_CRYPTOS, CRYPTOS);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        sUriMatcher.addURI(CryptoContract.CONTENT_AUTHORITY, CryptoContract.PATH_CRYPTOS + "/#", CRYPTO_ID);
    }

    /**
     * Initialize the provider and the database helper object.
     */
    @Override
    public boolean onCreate() {
        // TODO: Create and initialize a PetDbHelper object to gain access to the pets database.
        // Make sure the variable is a global variable, so it can be referenced from other
        // ContentProvider methods.
        mCryptoDbHelper = new CryptoDpHelper(getContext());
        return true;
    }

    /**
     * Perform the query for the given URI. Use the given projection, selection, selection arguments, and sort order.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mCryptoDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case CRYPTOS:
                cursor = database.query(CryptoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case CRYPTO_ID:
                selection = CryptoEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(CryptoEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("cannot query unknown uri" + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    /**
     * Insert new data into the provider with the given ContentValues.
     */
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CRYPTOS:
                return insertPet(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    /**
     * Insert a pet into the database with the given content values. Return the new content URI
     * for that specific row in the database.
     */
    private Uri insertPet(Uri uri, ContentValues values) {
        // Check that the name is not null
        String cryptoName = values.getAsString(CryptoEntry.COLUMN_CRYPTO_NAME);
        if (cryptoName == null) {
            throw new IllegalArgumentException("Name of Crypto Currency is required");
        }

        String baseCurrency = values.getAsString(CryptoEntry.COLUMN_BASE_CURRENCY);
        if (baseCurrency == null) {
            throw new IllegalArgumentException("Name of base currency is required");
        }

        // Check that the gender is valid
        Double baseCurrencyValue = values.getAsDouble(CryptoEntry.COLUMN_CURRENCY_VALUE);
        if (baseCurrencyValue == null) {
            throw new IllegalArgumentException("A valid value is required");
        }
        String currencySymbol = values.getAsString(CryptoEntry.COLUMN_SYMBOL);
        if (currencySymbol == null) {
            throw new IllegalArgumentException("currency symbol is required");
        }


        // TODO: Insert a new pet into the pets database table with the given ContentValues
        // Get writeable database
        SQLiteDatabase database = mCryptoDbHelper.getWritableDatabase();


        // Insert the new pet with the given values
        long id = database.insert(CryptoEntry.TABLE_NAME, null, values);
        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }
        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);

    }

    /**
     * Updates the data at the given selection and selection arguments, with the new ContentValues.
     */
    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CRYPTOS:
                return updatePet(uri, contentValues, selection, selectionArgs);
            case CRYPTO_ID:
                // For the PET_ID code, extract out the ID from the URI,
                // so we know which row to update. Selection will be "_id=?" and selection
                // arguments will be a String array containing the actual ID.
                selection = CryptoEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                return updatePet(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(CryptoEntry.COLUMN_CRYPTO_NAME)) {
            String cryptoName = values.getAsString(CryptoEntry.COLUMN_CRYPTO_NAME);
            if (cryptoName == null) {
                throw new IllegalArgumentException("input a Crypto currency name");
            }
        }

        // If the {@link PetEntry#COLUMN_PET_GENDER} key is present,
        // check that the gender value is valid.
        if (values.containsKey(CryptoEntry.COLUMN_BASE_CURRENCY)) {
            String baseCurrency = values.getAsString(CryptoEntry.COLUMN_BASE_CURRENCY);
            if (baseCurrency == null) {
                throw new IllegalArgumentException("input the base currency name");
            }
        }
        // If the {@link PetEntry#COLUMN_PET_WEIGHT} key is present,
        // check that the weight value is valid.
        if (values.containsKey(CryptoEntry.COLUMN_CURRENCY_VALUE)) {
            // Check that the weight is greater than or equal to 0 kg
            Double baseCurrencyValue = values.getAsDouble(CryptoEntry.COLUMN_CURRENCY_VALUE);
            if (baseCurrencyValue == null) {
                throw new IllegalArgumentException("a valid va;ue is required");
            }
        }

        // No need to check the breed, any value is valid (including null).

        // If there are no values to update, then don't try to update the database
        if (values.size() == 0) {
            return 0;
        }
        SQLiteDatabase database = mCryptoDbHelper.getWritableDatabase();

        getContext().getContentResolver().notifyChange(uri, null);
        // Insert the new pet with the given values
        return database.update(CryptoEntry.TABLE_NAME, values, selection, selectionArgs);
        // If the ID is -1, then the insertion failed. Log an error and return null.


    }


    /**
     * Delete the data at the given selection and selection arguments.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Get writeable database
        SQLiteDatabase database = mCryptoDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CRYPTOS:
                // Delete all rows that match the selection and selection args
                //getContext().getContentResolver().notifyChange(uri, null);
                return database.delete(CryptoEntry.TABLE_NAME, selection, selectionArgs);
            case CRYPTO_ID:
                // Delete a single row given by the ID in the URI
                selection = CryptoEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                getContext().getContentResolver().notifyChange(uri, null);
                return database.delete(CryptoEntry.TABLE_NAME, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

    }

    /**
     * Returns the MIME type of data for the content URI.
     */
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CRYPTOS:
                return CryptoEntry.CONTENT_LIST_TYPE;
            case CRYPTO_ID:
                return CryptoEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

}
