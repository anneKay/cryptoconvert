package com.annekay.android.cryptoconvert.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import static com.annekay.android.cryptoconvert.view.RecentFragment.getCurrentValue;

import com.annekay.android.cryptoconvert.R;

import org.w3c.dom.Text;

public class ConverterActivity extends AppCompatActivity {
    String symbol, baseCurrency;
    Button cryptoButton, resultButton;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        cryptoButton = findViewById(R.id.crypto);
        resultButton = findViewById(R.id.result);
        inputText = findViewById(R.id.input_value);

        cryptoButton.setText(getIntent().getStringExtra("cryptoCode"));
        symbol=(getIntent().getStringExtra("cryptoSymbol"));
        baseCurrency = (getIntent().getStringExtra("cryptoCurrency"));

        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    resultButton.setText("Convert");
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });


        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputText.getText().toString().equals("")){
                    //resultButton.setText("Convert");
                    Toast.makeText(getApplicationContext(), "Please Input the value you wish to convert to", Toast.LENGTH_LONG).show();
                }else {
                    Double inputNumber = Double.parseDouble(inputText.getText().toString());
                    Double result = Math.floor(inputNumber * getCurrentValue());
                    resultButton.setText(baseCurrency + " " +result);
                }
            }
        });
    }


}
