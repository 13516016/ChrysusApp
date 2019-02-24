package com.example.chrysus.home;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chrysus.BaseController;
import com.example.chrysus.LocationTrack;
import com.example.chrysus.R;
import com.example.chrysus.home.adapter.NewsDataAdapter;
import com.example.chrysus.home.model.News;
import com.example.chrysus.home.model.User;
import com.example.chrysus.home.task.NewsAsyncTask;
import com.example.chrysus.home.task.UserAsyncTask;
import com.example.chrysus.util.ConfigReader;
import com.example.chrysus.util.SharedPrefWrapper;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.util.ArrayList;


public class HomeController extends BaseController {

    private HomeRouter homeRouter;
    private RecyclerView newsRecyclerView;
    private LinearLayout nfcPayLayout;
    private LinearLayout sendMoneyLayout;
    private LinearLayout newsSectionLayout;
    private NewsDataAdapter newsDataAdapter;
    private TextView userFullnameTV;
    private TextView userBalanceTV;
    private FirebaseAuth mAuth;

    private LocationTrack locationTrack;
    private TextView city;

    public HomeController(Context context, View view) {
        super(context, view);
    }

    @Override
    public View initializeView() {
        super.initializeView();
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false));
        newsDataAdapter = new NewsDataAdapter(this.context, newsRecyclerView, new ArrayList<News>());
        newsRecyclerView.setAdapter(newsDataAdapter);
        setLocation();
        return this.view;
    }

    @Override
    public void registerView() {
        super.registerView();
        newsRecyclerView = view.findViewById(R.id.news_rv);
        newsSectionLayout = view.findViewById(R.id.news_section);

        nfcPayLayout = view.findViewById(R.id.nfc_pay);
        sendMoneyLayout = view.findViewById(R.id.send_money);
        registerPaymentOnClickListener(new View[]{nfcPayLayout,  sendMoneyLayout});
        userFullnameTV = view.findViewById(R.id.user_fullname);
        userBalanceTV = view.findViewById(R.id.user_balance);
        homeRouter = new HomeRouter(this.context);
        mAuth = FirebaseAuth.getInstance();
    }

    private void registerPaymentOnClickListener(View[] paymentViews) {
        for (View view : paymentViews) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    routePayment(v);
                }
            });
        }
    }

    private void routePayment(View view) {
        switch (view.getId()) {
            case R.id.nfc_pay:
                homeRouter.navigateToNFCPayActivity();
                break;
            case R.id.send_money:
                homeRouter.navigateToSendMoneyActivity();
                break;
        }
    }

    public void getNewsData(){
        ArrayList<News> newsList = new ArrayList<>();
        try {
            String newsUrl = new ConfigReader(context).getValue("news_url");
            new NewsAsync().execute(newsUrl);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getUserData(){
        try {
            Log.d("test", mAuth.getUid());
            new UserAsync().execute(new ConfigReader(context).getValue("search_by_uid_url")+mAuth.getUid());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private class NewsAsync extends NewsAsyncTask {
        @Override
        protected void onPostExecute(ArrayList<News> news) {
            super.onPostExecute(news);
            newsDataAdapter.setNewsList(news);
            newsDataAdapter.notifyDataSetChanged();
        }
    }

    private class UserAsync extends UserAsyncTask {
        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            userBalanceTV.setText("Rp " + user.getStringBalance());
            userFullnameTV.setText(user.getName());
        }
    }

    public void setLocation() {
        locationTrack = new LocationTrack(this.context);
        if (locationTrack.canGetLocation()) {
            city = view.findViewById(R.id.city);
            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            Log.d("location", String.valueOf(longitude));
        } else {
            locationTrack.showSettingsAlert();
        }
    }

    public void toggleNewsSection(){
        if (!SharedPrefWrapper.getSettingsBoolean(context, "news")){
            newsSectionLayout.setVisibility(View.GONE);
        } else {
            newsSectionLayout.setVisibility(View.VISIBLE);
        }
    }
}
