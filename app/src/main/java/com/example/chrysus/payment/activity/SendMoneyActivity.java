package com.example.chrysus.payment.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.chrysus.R;
import com.example.chrysus.payment.model.SendMoneyRequest;
import com.example.chrysus.payment.task.SendMoneyTask;
import com.example.chrysus.util.FirebaseAuthWrapper;

import static android.view.View.GONE;

public class SendMoneyActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private EditText amountEditText;
    private Button sendMoneyBtn;
    private View modal;
    private ProgressBar progressBar;


    private View.OnClickListener sendMoneyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phoneNumber = phoneNumberEditText.getText().toString();
            Integer amount = Integer.parseInt(amountEditText.getText().toString());
            Log.d("OnClick", "Huyu");
            SendMoneyRequest sendMoneyRequest = new SendMoneyRequest(phoneNumber, FirebaseAuthWrapper.getFirebaseAuthUid(), amount);
            new SendMoneyAsync().execute(sendMoneyRequest);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        Log.d("Test","Huehue");
        phoneNumberEditText = findViewById(R.id.send_money_phone_input);
        amountEditText = findViewById(R.id.send_money_amount_input);
        sendMoneyBtn = findViewById(R.id.send_btn);
        sendMoneyBtn.setOnClickListener(sendMoneyOnClickListener);
        modal = findViewById(R.id.modal);
        progressBar = findViewById(R.id.send_money_progress_bar);
    }

    @SuppressLint("StaticFieldLeak")
    private class SendMoneyAsync extends SendMoneyTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("SendMoney", "Pre Exe");
            modal.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean isPaymentSuccess) {
            super.onPostExecute(isPaymentSuccess);
            modal.setVisibility(GONE);
            progressBar.setVisibility(GONE);
            if (isPaymentSuccess){
                Toast.makeText(getApplicationContext(), "Payment success!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Payment failed!", Toast.LENGTH_SHORT).show();;
            }
        }
    }
}
