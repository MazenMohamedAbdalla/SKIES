package com.example.skies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateBankAcc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bank_acc);

        Button backButton = (Button) findViewById(R.id.button2);
        Button update = (Button) findViewById(R.id.button);
        EditText cardNumber = (EditText) findViewById(R.id.editTextNumber2);
        EditText cardholder = (EditText) findViewById(R.id.editTextTextPersonName7);
        EditText expDate = (EditText) findViewById(R.id.editTextTextPersonName8);
        EditText cvv = (EditText) findViewById(R.id.editTextNumber3);
        String oldCardNumber = getIntent().getStringExtra("cardNumber");
        user use =  new user();

        // Back to BankSide page
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bankSide = new Intent(UpdateBankAcc.this,BankAccountSide.class);
                startActivity(bankSide);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(cardNumber.getText().toString())){
                    cardNumber.setError("This entry cannot be empty");
                }
                else if(TextUtils.isEmpty(cardholder.getText().toString())){
                    cardholder.setError("This entry cannot be empty");
                }
                else if(TextUtils.isEmpty(expDate.getText().toString())){
                    expDate.setError("This entry cannot be empty");
                }
                else if(TextUtils.isEmpty(cvv.getText().toString())){
                    cvv.setError("This entry cannot be empty");
                }
                else{
                    boolean updated = use.updateBankAccount(oldCardNumber,cardNumber.getText().toString(),cardholder.getText().toString(),expDate.getText().toString(),cvv.getText().toString());
                    if(updated){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdateBankAcc.this,"updated Successfully",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(UpdateBankAcc.this,"Enter valid data",Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });
    }
}