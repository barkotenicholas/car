package com.bgstuffs.pabapp;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    // declaration of the views
    private Button bt_location;

    //a list to store all the products
    List<serviceCenteres> serviceCenteresList;
    private BillingProcessor billingProcessor;
    //the recyclerview
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        // connecting our activity with layout using inflater
        View view = inflater.inflate(R.layout.activity_home,container,false);

        //getting the recyclerView from xml
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //initializing the productList
        serviceCenteresList = new ArrayList<>();

        serviceCenteresList.add( new serviceCenteres(1,
                "Bhogal Motor Garage",
                "Phagwara, Punjab",
                4.4,
                "2.6 km",
                R.drawable.service1));

        serviceCenteresList.add( new serviceCenteres(2,
                "Raj Auto Service",
                "Jalandhar, Punjab",
                4.0,
                "3.9 km",
                R.drawable.service2));

        serviceCenteresList.add( new serviceCenteres(3,
                "Honda Service Station",
                "Jalandhar, Punjab",
                5.0,
                "6.3 km",
                R.drawable.service4));

        serviceCenteresList.add( new serviceCenteres(4,
                "Ravindra Auto Service",
                "Phagwara, Punjab",
                4.0,
                "6.6 km",
                R.drawable.service6));

        serviceCenteresList.add( new serviceCenteres(5,
                "Tata Motor Servive Center",
                "Jalandhar, Punjab",
                4.7,
                "6.8 km",
                R.drawable.service5));

        serviceCenteresList.add( new serviceCenteres(6,
                "Balwinder Motor Garage",
                "Phagwara, Punjab",
                4.3,
                "9.2 km",
                R.drawable.service3));



        // creating recylerView adapter
        shopAdapter adapter = new shopAdapter(this.getActivity(),serviceCenteresList);

        // setting adapter to recyclerView
        recyclerView.setAdapter(adapter);

        // initialization of views in fragment
        bt_location = view.findViewById(R.id.btLocation);
        billingProcessor = new BillingProcessor(getActivity(),"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAimYOGnBuxZXnU5GCiXsaWdSFW3ToKhiEOB25l1GvbGAVKdOksfAfkWFbi3aFz39Xpl61Ef7K/0kmUcb2yYBA4olyW8rFhlpRtIi1s4oIm1ZIaWUZ730jnejctr8XWVEFFCtnLbh9gS1wuzB4txu5xM1mjs3rQAZ1jO7NL96s1wwoFm30a9iNPxsUcEHTF/Dho+ufvXKnAGu8/SqVm3erQFzL0sTST/AY4Yw4o2ViDxqqe2l69GlJgYu9T7ccf/ZahQM25bS4v71iD5LrRMwQjDc4528UbWn6iqJCsKeS8cCICc3Oj5CLTJ/Pb12DbvfKkbdf0/LwQpn8HDguH9zhCQIDAQAB" , null, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(String productId, TransactionDetails details) {
                Toast.makeText(getActivity(),"purchased",Toast.LENGTH_LONG);

            }

            @Override
            public void onPurchaseHistoryRestored() {

            }

            @Override
            public void onBillingError(int errorCode, Throwable error) {

            }

            @Override
            public void onBillingInitialized() {

            }
        });
        bt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                billingProcessor.subscribe(getActivity(),"acup");

            }
        });

        return view;

    }
}
