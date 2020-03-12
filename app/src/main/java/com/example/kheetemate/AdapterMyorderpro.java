package com.example.kheetemate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class AdapterMyorderpro extends RecyclerView.Adapter<AdapterMyorderpro.MyViewHolder> implements View.OnClickListener{
    private List<Myordercos> mapping;
    private Context context;

    @Override
    public void onClick(View v) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView fname,product,quantity,price,call,directions;


        public MyViewHolder(View view) {
            super(view);
            fname = (TextView) view.findViewById(R.id.fname);
            product = (TextView) view.findViewById(R.id.product);
            quantity = (TextView) view.findViewById(R.id.quantity);
            price = (TextView) view.findViewById(R.id.pricetotal);
            call = (TextView) view.findViewById(R.id.call);
            //directions = (TextView) view.findViewById(R.id.directions);
        }
    }


    public AdapterMyorderpro(Context context, List<Myordercos> mapping) {
        this.mapping = mapping;
        this.context = context;
    }

    @Override


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutforcostumerorders, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Myordercos data = mapping.get(position);
        holder.fname.setText(data.getfname());
        holder.product.setText(data.getproduct());
        holder.quantity.setText(data.getquantity());
        holder.price.setText(data.getprice());

        final LatLng sf=data.getlatlngsf();

        holder.directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+sf.latitude+","+sf.longitude));
                context.startActivity(intent1);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mapping.size();
    }

}