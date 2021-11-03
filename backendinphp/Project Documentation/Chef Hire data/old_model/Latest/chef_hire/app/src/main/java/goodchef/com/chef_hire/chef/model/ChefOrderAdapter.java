package goodchef.com.chef_hire.chef.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.ChefTracking;

/**
 * Created by santo on 8/13/2018.
 */

public class ChefOrderAdapter extends RecyclerView.Adapter<ChefOrderAdapter.MyViewHolder> {


    public List<ChefOrderAlbum> chefOrderAlbums;
    private Context mContext;
    Base_url base_url;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, status ,price,time, quantity, user_address;
        LinearLayout card;

        public MyViewHolder(View view) {
            super(view);


            base_url = new Base_url();
            name = (TextView) view.findViewById(R.id.name);
            status = (TextView)view.findViewById(R.id.status);
            price = (TextView) view.findViewById(R.id.price);
            card = (LinearLayout)view.findViewById(R.id.card);
            time = (TextView)view.findViewById(R.id.time);
            user_address = (TextView)view.findViewById(R.id.user_address);
            quantity= (TextView)view.findViewById(R.id.quantity);
        }
    }




    @Override
    public ChefOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ChefOrderAdapter.MyViewHolder holder, int position) {
        final ChefOrderAlbum album = chefOrderAlbums.get(position);

        holder.name.setText(album.getF_name());
        holder.status.setText(album.getF_status());

        double discount = Integer.parseInt(album.getPrice()) * (Double.parseDouble(base_url.food_commision) / 100.0);
        double gross_price = Integer.parseInt(album.getPrice()) - discount;


        holder.price.setText(""+gross_price);
        holder.quantity.setText(album.getQuantity()+" meals");
        holder.user_address.setText(album.getUser_addr());




        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
            Date newDate=df.parse(album.getDate());

            SimpleDateFormat df2 = new SimpleDateFormat("dd MMM YYYY");
            String current_date2 = df2.format(newDate);

            holder.time.setText(current_date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(album.getF_status().equalsIgnoreCase(base_url.food_delivered) ||
                album.getF_status().equalsIgnoreCase(base_url.food_cancel) ){

            holder.status.setTextColor(mContext.getResources().getColor(R.color.light_grey));
            holder.status.setBackground(ContextCompat.getDrawable(mContext, R.drawable.edittext_border));

        }else {

            holder.status.setTextColor(mContext.getResources().getColor(R.color.colorPrimary));
            holder.status.setBackground(ContextCompat.getDrawable(mContext, R.drawable.colorborder));
        }


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String order_id = album.getOrder_id();

                Intent i = new Intent(mContext, ChefTracking.class);
                i.putExtra("order_id", order_id);
                mContext.startActivity(i);

            }
        });
    }


    public ChefOrderAdapter(Context mContext,List<ChefOrderAlbum> trackingAlbums) {
        this.mContext = mContext;
        this.chefOrderAlbums = trackingAlbums;
    }


    @Override
    public int getItemCount() {
        return chefOrderAlbums.size() ;
    }
}
