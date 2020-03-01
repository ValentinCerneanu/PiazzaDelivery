package com.innovation.piazzadelivery.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.innovation.piazzadelivery.Domain.Order;
import com.innovation.piazzadelivery.Domain.OrderModel;
import com.innovation.piazzadelivery.R;
import com.innovation.piazzadelivery.Repository.UserRepository;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> implements ListAdapter {

    private ArrayList<Order> dataSet;
    private Context mContext;

    private FirebaseDatabase database;
    private DatabaseReference myRefToDatabase;

    private static class  ViewHolder{
        TextView storeNameTextView;
        TextView totalPriceTextView;
        TextView timeTextView;
        TextView addressTextView;
        TextView statusTextView;
        Button buttonPreia;
    }

    public OrderAdapter(ArrayList<Order> data, Context context) {
        super(context, R.layout.order_adapter, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Order dataModel = getItem(position);
        final OrderAdapter.ViewHolder viewHolder;

        if(convertView == null) {
            viewHolder = new OrderAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.order_adapter, parent, false);
            viewHolder.storeNameTextView = (TextView) convertView.findViewById(R.id.store_name);
            viewHolder.totalPriceTextView = (TextView) convertView.findViewById(R.id.total_price);
            viewHolder.timeTextView = (TextView) convertView.findViewById(R.id.time);
            viewHolder.addressTextView = (TextView) convertView.findViewById(R.id.address);
            viewHolder.statusTextView = (TextView) convertView.findViewById(R.id.status);
            viewHolder.buttonPreia = (Button) convertView.findViewById(R.id.button_preia);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderAdapter.ViewHolder) convertView.getTag();
        }

        viewHolder.storeNameTextView.setText("Magazin: " + dataModel.getStoreKey());
        viewHolder.totalPriceTextView.setText("Pret total: " + String.valueOf(dataModel.getTotalPrice()));
        viewHolder.timeTextView.setText("Plasata pe: " + dataModel.getTime());
        viewHolder.addressTextView.setText("Adresa de livrare: " + dataModel.getAddress());
        viewHolder.statusTextView.setText("Status comanda: " + dataModel.getStatus());

        if(dataModel.getStatus().equals(OrderModel.STATUS_PRELUATA)){
            viewHolder.buttonPreia.setEnabled(false);
        } else {
            viewHolder.buttonPreia.setEnabled(true);
        }

        viewHolder.buttonPreia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                myRefToDatabase = database.getReference("Orders");
                myRefToDatabase.child(dataModel.getOrderKey()).child(OrderModel.STATUS)
                        .setValue(OrderModel.STATUS_PRELUATA).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                myRefToDatabase.child(dataModel.getOrderKey()).child(OrderModel.DELIVERY_KEY)
                        .setValue(UserRepository.getInstance().getFirebareUserID()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });
            }
        });
        return convertView;
    }
}
