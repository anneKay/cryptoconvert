package com.annekay.android.cryptoconvert.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.annekay.android.cryptoconvert.R;

public class CreateCardActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner cryptoSpinner, baseCurrencySpinner;
    private ArrayAdapter<CharSequence> cryptoAdapter, baseAdapter;
    private String mSelectedCrypto, mSelectedCurrency;
    public static String selectedCrypto, selectedCurrency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        Button createCard = findViewById(R.id.creat_button);

        setUpCryptoSpinner();
        setUpBaseSpinner();


        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCard();
            }
        });

    }


    private void setUpCryptoSpinner() {

        cryptoSpinner = findViewById(R.id.crypto_spinner);
        cryptoSpinner.setOnItemSelectedListener(this);
        cryptoAdapter = ArrayAdapter.createFromResource(this, R.array.crypto, android.R.layout.simple_spinner_item);
        //specify the layout to choose for the drop down list
        cryptoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //apply the adapter to the spinner
        cryptoSpinner.setAdapter(cryptoAdapter);
    }

    private void setUpBaseSpinner() {
        baseCurrencySpinner = findViewById(R.id.base_spinner);

        baseCurrencySpinner.setOnItemSelectedListener(this);

        baseAdapter = ArrayAdapter.createFromResource(this, R.array.currency_arrays, android.R.layout.simple_spinner_item);
        //specify the layout to choose for the drop down list
        baseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        baseCurrencySpinner.setAdapter(baseAdapter);
    }

    public void onItemSelected(AdapterView parent, View view, int pos, long id) {


        switch (parent.getId()) {
            case R.id.crypto_spinner:
                //Get the selected values
                mSelectedCrypto = parent.getSelectedItem().toString();
                selectedCrypto = (mSelectedCrypto.substring(mSelectedCrypto.lastIndexOf(",") + 1));
                break;
            case R.id.base_spinner:
                mSelectedCurrency = parent.getSelectedItem().toString();
                selectedCurrency = (mSelectedCurrency.substring(mSelectedCurrency.lastIndexOf(",") + 1));
                break;
        }
    }


    private void createCard() {
        if (!mSelectedCrypto.equals("(Select Crypto Currency)") && !mSelectedCurrency.equals("(Select Your Currency)")){
            int position = 0;
            Intent intent = new Intent(CreateCardActivity.this, MainActivity.class);
            intent.putExtra("recent_position", position);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Please choose the Currency You wish to convert", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public static String getSelectedCrypto(){
        return selectedCrypto;
    }
    public static String getSelectedCurrency(){
        return selectedCurrency;
    }
}