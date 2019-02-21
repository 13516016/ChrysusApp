package com.example.chrysus.history;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;

import java.util.ArrayList;

public class HistoryController extends BaseController {
    private RecyclerView paymentRecyclerView;

    public HistoryController(Context context, View view) {
        super(context, view);
    }

    @Override
    public View initializeView() {
        super.initializeView();
        paymentRecyclerView.setHasFixedSize(true);
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        PaymentAdapter paymentDataAdapter = new PaymentAdapter(this.context, getPaymentsData());
        paymentRecyclerView.setAdapter(paymentDataAdapter);

        return this.view;
    }

    @Override
    public void registerView() {
        super.registerView();
        paymentRecyclerView = view.findViewById(R.id.completed_payment);
    }

    private ArrayList<Payment> getPaymentsData(){
        ArrayList<Payment> paymentsList = new ArrayList<>();
        paymentsList.add(new Payment("McDonald Dago", "Payment Completed", "1470000", "5 Jan 2019 16.33"));
        paymentsList.add(new Payment("Geprek Bensu Dago", "Payment Completed", "1300000", "3 December 2018 08.53"));
        return paymentsList;
    }
}
