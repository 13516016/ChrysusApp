package com.example.chrysus.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chrysus.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.PaymentViewHolder> {
    private ArrayList<Payment> paymentList;
    Context context;

    public PaymentAdapter(ArrayList<Payment> paymentList){
        this.paymentList = paymentList;
    }

    public PaymentAdapter(Context context, ArrayList<Payment> paymentList) {
        this.paymentList = paymentList;
        this.context = context;
    }

    public void setPaymentList(ArrayList<Payment> paymentList){
        this.paymentList = paymentList;
    }

    public ArrayList<Payment> getPaymentList() { return paymentList; }

    static class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView store;
        TextView status;
        TextView price;
        TextView date;
        public PaymentViewHolder(View view) {
            super(view);
            store = (TextView) view.findViewById(R.id.payment_store);
            status = (TextView) view.findViewById(R.id.payment_status);
            price = (TextView) view.findViewById(R.id.payment_price);
            date = (TextView) view.findViewById(R.id.payment_date);
        }
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsListView = inflater.inflate(R.layout.history_view, parent, false);
        return new PaymentViewHolder(newsListView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, int i) {
        Payment payment = paymentList.get(i);

        paymentViewHolder.store.setText(payment.getStore());
        paymentViewHolder.status.setText(payment.getStatus());
        paymentViewHolder.price.setText(payment.getPrice());
        paymentViewHolder.date.setText(payment.getDate());

    }

    @Override
    public int getItemCount() {
        return paymentList.size();
    }
}
