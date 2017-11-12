package com.annekay.android.cryptoconvert.view;

import android.content.ContentUris;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.content.Intent;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.getSelectedCrypto;
import static com.annekay.android.cryptoconvert.view.CreateCardActivity.getSelectedCurrency;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.annekay.android.cryptoconvert.adapter.CryptoCursorAdapter;
import com.annekay.android.cryptoconvert.data.CryptoContract.CryptoEntry;

import com.android.volley.VolleyError;
import com.annekay.android.cryptoconvert.R;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{
    FloatingActionButton fab;
    //CryptoCursorAdapter adapter;
    CryptoCursorAdapter adapter;
    private Uri currentCryptoUri;
    TextView textView;

    private RecyclerView mRecyclerView;
    private static Double currentCryptoValue;
    private final int CRYPTO_LOADER_ID = 11;

    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recent_layout, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        textView = rootView.findViewById(R.id.empty_view);

        fab = rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
                Intent intent = new Intent(getActivity(), CreateCardActivity.class);
                startActivity(intent);
            }
        });
        setupView();

            adapter = new CryptoCursorAdapter(getActivity(), new CryptoCursorAdapter.CryptoCursorAdapterOnClickHandler() {
                @Override
                public void onClick(View v, int position, int id) {
                    Intent intent = new Intent(getContext(), ConverterActivity.class);
                    currentCryptoUri = ContentUris.withAppendedId(CryptoEntry.CONTENT_URI, id);
                    Toast.makeText(getActivity(), currentCryptoUri.toString(), Toast.LENGTH_SHORT).show();
                    intent.setData(currentCryptoUri);
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                int id = (int) viewHolder.itemView.getTag();
                Uri uri = ContentUris.withAppendedId(CryptoEntry.CONTENT_URI, id);
                getActivity().getContentResolver().delete(uri, null, null);
                adapter.removeItem(id, getActivity());
                Toast.makeText(getActivity(), uri.toString(), Toast.LENGTH_SHORT).show();
                getActivity().getSupportLoaderManager().restartLoader(CRYPTO_LOADER_ID, null, RecentFragment.this).forceLoad();

                adapter.notifyDataSetChanged();
                if (adapter.getItemCount() == 0){
                    textView.setText("No Cards Yet");

                }
            }
        }).attachToRecyclerView(mRecyclerView);

        getActivity().getSupportLoaderManager().initLoader(CRYPTO_LOADER_ID, null, this);
        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = {
                CryptoEntry._ID,
                CryptoEntry.COLUMN_CRYPTO_NAME,
                CryptoEntry.COLUMN_CURRENCY_VALUE,
                CryptoEntry.COLUMN_SYMBOL
        };

        return  new CursorLoader(getActivity(), CryptoEntry.CONTENT_URI,
                projection, null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        adapter.notifyDataSetChanged();
        textView.setVisibility(View.GONE);

    }


    @Override
    public void onLoaderReset(Loader loader) {
        adapter.swapCursor(null);

    }
//
    private void setupView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false)
        );
        mRecyclerView.hasFixedSize();

    }
    private void deletePet() {
           int rowsDeleted = getActivity().getApplicationContext().getContentResolver().delete(currentCryptoUri, null, null);

            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(getActivity(), "delete unsuccessful", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(getActivity(), "delete successful",
                        Toast.LENGTH_SHORT).show();
            }
    }

    public static Double getCurrentValue(){
        return currentCryptoValue;
    }

}
