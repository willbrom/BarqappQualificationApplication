package com.example.barqappqualificationapplication.orders.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.barqappqualificationapplication.BarqappApplication;
import com.example.barqappqualificationapplication.BarqappFetchOrdersQuery;
import com.example.barqappqualificationapplication.R;
import com.example.barqappqualificationapplication.orders.model.OrderListModel;
import com.example.barqappqualificationapplication.orders.view.adapter.OrderListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OrderListAdapter.ItemClickListener {

    private RecyclerView orderRecyclerView;
    private OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderRecyclerView = findViewById(R.id.order_recyclerView);
        orderListAdapter = new OrderListAdapter(this);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        orderRecyclerView.setHasFixedSize(true);
        orderRecyclerView.setAdapter(orderListAdapter);

        getOrderListItems();
    }

    private void getOrderListItems() {
        List<OrderListModel> orderModelList = new ArrayList<>();
        String[] orderNumberArray = getResources().getStringArray(R.array.order_number_array);
        String[] orderDescriptionArray = getResources().getStringArray(R.array.order_description_array);
        String[] orderPriceArray = getResources().getStringArray(R.array.order_price_array);
        String[] orderStatusArray = getResources().getStringArray(R.array.order_status_array);

        for (int i = 0; i < orderNumberArray.length; i++) {
            orderModelList.add(new OrderListModel(orderNumberArray[i], orderDescriptionArray[i],
                    orderPriceArray[i], orderStatusArray[i]));
        }

        orderListAdapter.setOrderModelList(orderModelList);
        orderListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(OrderListModel order) {
        Intent intent = new Intent(this, OrderDescriptionActivity.class);
        startActivity(intent);
    }
}
