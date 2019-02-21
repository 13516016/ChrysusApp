package com.example.chrysus.history;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chrysus.BaseController;
import com.example.chrysus.R;

import java.util.ArrayList;

public class HistoryController extends BaseController {
    private RecyclerView newsRecyclerView;

    public HistoryController(Context context, View view) {
        super(context, view);
    }

    @Override
    public View initializeView() {
        super.initializeView();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        PaymentAdapter paymentDataAdapter = new PaymentAdapter(this.context, getPaymentsData());
        newsRecyclerView.setAdapter(paymentDataAdapter);

        return this.view;
    }

    @Override
    public void registerView() {
        super.registerView();
        newsRecyclerView = view.findViewById(R.id.completed_payment);
    }

    private ArrayList<Payment> getPaymentsData(){
        ArrayList<Payment> paymentsList = new ArrayList<>();
        paymentsList.add(new Payment("McDonald Dago", "Completed Payment", "1470000", "5 Jan 2019"));
        return paymentsList;
    }
}
