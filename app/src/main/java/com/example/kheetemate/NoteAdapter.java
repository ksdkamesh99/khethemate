package com.example.kheetemate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>  {
    private  static final String TAG = "NoteAdapter";
    private ArrayList<farmer>farmers=new ArrayList<>();
    private Context mContext;

    public NoteAdapter(Context mContext,ArrayList<farmer> farmers) {
        this.mContext = mContext;
        this.farmers = farmers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layoutforcostumerorders,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
       // Picasso.get().load(farmers.get(position).getImaGe()).into(holder.imageView);
        holder.Name.setText("Name:"+farmers.get(position).getNaMe());
        holder.Production.setText("Product:"+farmers.get(position).getProDuct());
        holder.Description.setText("Description:"+farmers.get(position).getProDuct());
        holder.Price.setText("Price:"+farmers.get(position).getPriCe());
       final String Lat=farmers.get(position).getLaT();
      final  String Lng=farmers.get(position).getLnG();
        holder.directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+Lat+","+Lng));
                mContext.startActivity(intent1);


            }
        });

        /*holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext,farmers.get(position), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,Description.class);
                intent.putExtra("image_url",farmers.get(position).getImaGe());
                intent.putExtra("Category",farmers.get(position).getCateGory());
                intent.putExtra("Product",farmers.get(position).getProDuct());
                intent.putExtra("Description",farmers.get(position).getDescripTion());
                intent.putExtra("Price",farmers.get(position).getPriCe());
                intent.putExtra("Address",farmers.get(position).getAddRess());
                intent.putExtra("Name",farmers.get(position).getNaMe());
                intent.putExtra("PhoneNum",farmers.get(position).getPhoneNuM());
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return farmers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Name;
        TextView Production;
        TextView Price;
        TextView Description;
        TextView directions;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.fname);
            Production=itemView.findViewById(R.id.product);
            Price=itemView.findViewById(R.id.pricetotal);
            Description=itemView.findViewById(R.id.quantity);
            directions=itemView.findViewById(R.id.directions);
            parentLayout=itemView.findViewById(R.id.LinearLayout);
        }
    }

}


