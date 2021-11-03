package goodchef.com.chef_hire.chef.model;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.MyOrderDetails;
import goodchef.com.chef_hire.user.OrderDetailsScreen;
import goodchef.com.chef_hire.user.model.TrackingAlbum;

/**
 * Created by santo on 8/12/2018.
 */

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    public List<OrderAlbum> trackingAlbums;
    private Context mContext;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, status ,price,time, quantity, user_address;
        LinearLayout card;

        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.name);
            status = (TextView)view.findViewById(R.id.status);
            price = (TextView) view.findViewById(R.id.price);
            card = (LinearLayout)view.findViewById(R.id.card);
            time = (TextView)view.findViewById(R.id.time);
            quantity = (TextView)view.findViewById(R.id.quantity);
            user_address= (TextView)view.findViewById(R.id.user_address);
        }
    }


    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrdersAdapter.MyViewHolder holder, int position) {

        final OrderAlbum album = trackingAlbums.get(position);

        holder.name.setText(album.getF_name());
        holder.status.setText(album.getF_status());
        holder.price.setText(album.getPrice());
        holder.time.setText(album.getTime());
        holder.quantity.setText(album.getQuantity()+ " "+album.getF_name());
        holder.user_address.setText(album.getUser_addr());

        if(album.getF_status().equalsIgnoreCase("delivered") ){

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

                Intent i = new Intent(mContext, MyOrderDetails.class);
                i.putExtra("order_id", order_id);
                mContext.startActivity(i);



            }
        });

    }

    public OrdersAdapter(Context mContext, List<OrderAlbum> trackingAlbums) {
        this.mContext = mContext;
        this.trackingAlbums = trackingAlbums;
    }




    @Override
    public int getItemCount() {
        return trackingAlbums.size();
    }
}
