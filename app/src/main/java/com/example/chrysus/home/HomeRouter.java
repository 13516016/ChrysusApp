package com.example.chrysus.home;

import android.content.Context;
import android.content.Intent;

import com.example.chrysus.payment.activity.NFCPayActivity;
import com.example.chrysus.payment.activity.QRPayActivity;
import com.example.chrysus.payment.activity.ReceiveMoneyActivity;
import com.example.chrysus.payment.activity.SendMoneyActivity;

public class HomeRouter {
    private Context context;

    public HomeRouter(Context context){
        this.context = context;
    }

    public void navigateToQRPayActivity(){
        Intent intent = new Intent(context, QRPayActivity.class);
        context.startActivity(intent);
    }

    public void navigateToNFCPayActivity(){
        Intent intent = new Intent(context, NFCPayActivity.class);
        context.startActivity(intent);
    }

    public void navigateToSendMoneyActivity(){
        Intent intent = new Intent(context, SendMoneyActivity.class);
        context.startActivity(intent);
    }

    public void navigateToReceiveMoneyActivity(){
        Intent intent = new Intent(context, ReceiveMoneyActivity.class);
        context.startActivity(intent);
    }

}