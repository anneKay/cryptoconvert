package com.annekay.android.cryptoconvert.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.annekay.android.cryptoconvert.R;

import org.w3c.dom.Text;

public class ConverterActivity extends AppCompatActivity {
    String symbol, currencyCode, baseCurrency;
    Double cryptoValue, inputCryptoValue, result;
    Button cryptoButton, resultButton;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        cryptoButton = findViewById(R.id.crypto);
        resultButton = findViewById(R.id.result);
        inputText = findViewById(R.id.input_value);
        inputText.getText().toString();
        String inputCryptoString = inputText.getText().toString();
        cryptoButton.setText(getIntent().getStringExtra("currencyCode"));
        symbol=(getIntent().getStringExtra("cryptoSymbol"));
        baseCurrency = (getIntent().getStringExtra("cryptoCurrency"));
        String cryptoValueString = (getIntent().getStringExtra("cryptoValue"));
        if(cryptoValue != null){
        cryptoValue = Double.valueOf(cryptoValueString);
        inputCryptoValue= Double.valueOf(inputCryptoString);}

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               resultButton.setText(symbol+result);

            }
        });


        }

    public Double convert(){
        return result = cryptoValue * inputCryptoValue;
    }

}
