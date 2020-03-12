package com.example.kheetemate;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.LOCATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;
import static java.lang.String.valueOf;

public class NoteAdapter1 extends RecyclerView.Adapter<NoteAdapter1.ViewHolder> {
    private  static final String TAG = "NoteAdapter";

    private ArrayList<far> farmers=new ArrayList<>();
    private Context mContext;
    String lat2,long2;
    DatabaseReference ref;
    public NoteAdapter1(Context mContext,ArrayList<far> farmers ) {
        this.mContext = mContext;
        this.farmers = farmers;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.container_1,parent,false);
        NoteAdapter1.ViewHolder holder=new NoteAdapter1.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.Name.setText("Name:"+farmers.get(position).getNaMe());
        holder.Product.setText("Product:"+farmers.get(position).getProDuct());
        holder.Price.setText("Price:"+farmers.get(position).getPriCe()+"Rs/kg");


        Location startPoint=new Location("locationA");
        startPoint.setLatitude(Double.parseDouble(farmers.get(position).getLaT()));
        startPoint.setLongitude(Double.parseDouble(farmers.get(position).getLnG()));

       /* CostumerActivity cc=new CostumerActivity();
        String id=cc.getUserId();
        Log.d("hh",id);*/


       Location endPoint=new Location("locationA");
      //  Log.d("lat",String.valueOf(VerifyPhoneActivity.getlat()));
        endPoint.setLatitude(Double.parseDouble(farmers.get(position).getLaT1()));
        endPoint.setLongitude(Double.parseDouble(farmers.get(position).getLnG1()));

        double distance=startPoint.distanceTo(endPoint);
        final String qq="dist";
        Log.d(qq, String.valueOf(distance));
        holder.distance.append(": "+String.valueOf(distance)+" meters");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(mContext,Description.class);
                ii.putExtra("Name",farmers.get(position).getNaMe());
                ii.putExtra("image_url",farmers.get(position).getImage());
                ii.putExtra("Category",farmers.get(position).getCategory());
                ii.putExtra("Product",farmers.get(position).getProDuct());
                ii.putExtra("Description",farmers.get(position).getDescription());
                ii.putExtra("Price",farmers.get(position).getPriCe());
                ii.putExtra("Address",farmers.get(position).getAddress());
                ii.putExtra("PhoneNum",farmers.get(position).getPhone());
                mContext.startActivity(ii);
            }
        });
    }

    @Override
    public int getItemCount() {
        return farmers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Product;
        TextView Price;
        TextView Name,distance;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.tv3);
            Product=itemView.findViewById(R.id.tv4);
            Price=itemView.findViewById(R.id.tv5);
            distance=itemView.findViewById(R.id.tv6);
            parentLayout=itemView.findViewById(R.id.ParentLayout1);
        }
    }

}
