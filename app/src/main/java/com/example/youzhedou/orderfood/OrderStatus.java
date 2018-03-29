package com.example.youzhedou.orderfood;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.youzhedou.orderfood.Common.Common;
import com.example.youzhedou.orderfood.Model.Food;
import com.example.youzhedou.orderfood.Model.Request;
import com.example.youzhedou.orderfood.ViewHolder.FoodViewHolder;
import com.example.youzhedou.orderfood.ViewHolder.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderStatus extends AppCompatActivity {

    public RecyclerView recyclerView;
    public  RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference requests;

    FirebaseRecyclerAdapter<Request,OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        database=FirebaseDatabase.getInstance();
        requests = database.getReference("Request");
        recyclerView = (RecyclerView) findViewById(R.id.list_orders);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadOrders(Common.currentUser.getTel());
    }

    private void loadOrders(String tel) {
        adapter = new FirebaseRecyclerAdapter<Request, OrderViewHolder>(
                Request.class,
                R.layout.order_layout,
                OrderViewHolder.class,
                requests.orderByChild("tel").equalTo(tel)
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Request model, int position) {
                viewHolder.textOrderId.setText(adapter.getRef(position).getKey());
                viewHolder.textOrderStatus.setText("Status:"+convertCodeToStatus(model.getStatus()));
                viewHolder.textOrderAddress.setText(model.getAddress());
                viewHolder.textOrderTel.setText(model.getTel());
                viewHolder.textOrderPrice.setText("Total: $"+model.getTotal());
            }
        };
        recyclerView.setAdapter(adapter);
    }


    private String convertCodeToStatus(String status) {
        if(status.equals("0")){
            return "Placed";
        } else if(status.equals("1")){
            return "Shipping";
        }
        else{
            return "Received";
        }
    }
}
