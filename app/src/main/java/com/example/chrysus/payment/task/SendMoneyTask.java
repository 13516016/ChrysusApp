package com.example.chrysus.payment.task;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import com.example.chrysus.home.model.News;
import com.example.chrysus.payment.model.SendMoneyRequest;
import com.example.chrysus.util.ConfigReader;
import com.example.chrysus.util.request.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SendMoneyTask extends AsyncTask<SendMoneyRequest, Void, Boolean> {


    @Override
    protected Boolean doInBackground(SendMoneyRequest... sendMoneyRequests) {
        Boolean paymentSuccess = false;
        try {
            String result;
            SendMoneyRequest sendMoneyRequest = sendMoneyRequests[0];
            String requestPayload = new JSONObject()
                    .put("sender_account", sendMoneyRequest.getSenderUid())
                    .put("receiver_phone", sendMoneyRequest.getReceiverPhoneNumber())
                    .put("amount", sendMoneyRequest.getAmount())
                    .toString();

            Log.d("Requestpayload", requestPayload);
            result = Request.post(SendMoneyRequest.getUrl(), requestPayload, "");
            paymentSuccess = isPaymentSuccess(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return paymentSuccess;
    }

    private Boolean isPaymentSuccess(String result) throws JSONException {
        Log.d("Result", result);
        JSONObject json = new JSONObject(result);
        return json.getBoolean("success");
    }

}
