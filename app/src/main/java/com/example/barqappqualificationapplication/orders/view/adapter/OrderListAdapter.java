package com.example.barqappqualificationapplication.orders.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barqappqualificationapplication.R;
import com.example.barqappqualificationapplication.orders.model.OrderListModel;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderItemViewHolder> {

    public interface ItemClickListener {
        void onClick(OrderListModel recipeModel);
    }

    private List<OrderListModel> OrderModelList;
    private ItemClickListener itemClickListener;

    public OrderListAdapter(Context context) {
        itemClickListener = (ItemClickListener) context;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item_layout, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        holder.orderNumber.setText(OrderModelList.get(position).getOrderNumber());
        holder.orderDescription.setText(OrderModelList.get(position).getOrderDescription());
        holder.orderPrice.setText(OrderModelList.get(position).getOrderPrice());
        holder.orderStatus.setText(OrderModelList.get(position).getOrderStatus());
    }

    @Override
    public int getItemCount() {
        if (OrderModelList == null) return 0;
        return OrderModelList.size();
    }

    public void setOrderModelList(List<OrderListModel> orderModelList) {
        this.OrderModelList = orderModelList;
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView orderNumber;
        private TextView orderDescription;
        private TextView orderPrice;
        private TextView orderStatus;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            orderNumber = (TextView) itemView.findViewById(R.id.order_number);
            orderDescription = (TextView) itemView.findViewById(R.id.order_description);
            orderPrice = (TextView) itemView.findViewById(R.id.order_price);
            orderStatus = (TextView) itemView.findViewById(R.id.order_status);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(OrderModelList.get(getAdapterPosition()));
        }
    }
}
