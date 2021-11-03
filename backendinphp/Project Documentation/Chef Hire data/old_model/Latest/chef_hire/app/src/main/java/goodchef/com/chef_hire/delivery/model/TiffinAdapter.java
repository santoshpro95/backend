package goodchef.com.chef_hire.delivery.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.delivery.TiffinOrderDetails;
import goodchef.com.chef_hire.tiffin.MyTiffinDetails;
import goodchef.com.chef_hire.tiffin.model.MyTiffinAlbum;

/**
 * Created by santo on 8/12/2018.
 */

public class TiffinAdapter extends RecyclerView.Adapter<TiffinAdapter.MyViewHolder> {

    public List<TiffinAlbum> tiffinAlbums;
    private Context mContext;
    Base_url base_url;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, status ,price,time, quantity, user_address;
        LinearLayout card;

        public MyViewHolder(View view) {
            super(view);

            base_url = new Base_url();
            name = (TextView) view.findViewById(R.id.name);
            quantity = (TextView)view.findViewById(R.id.quantity);
            status = (TextView)view.findViewById(R.id.status);
            price = (TextView) view.findViewById(R.id.price);
            card = (LinearLayout)view.findViewById(R.id.card);
            time = (TextView)view.findViewById(R.id.time);
            user_address = (TextView)view.findViewById(R.id.user_address);
        }
    }


    @Override
    public TiffinAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TiffinAdapter.MyViewHolder holder, int position) {

        final TiffinAlbum album = tiffinAlbums.get(position);

        holder.name.setText(album.getF_name());
        holder.status.setText(album.getF_status());
        holder.price.setText(album.getPrice());
        holder.user_address.setText(album.getAddress());
        holder.quantity.setText(album.getDays()+" days");
        holder.time.setText(album.getTime());

        if(album.getF_status().equalsIgnoreCase(base_url.tiffin_cancel) ){

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
                String chef_id = album.getChef_id();

                Intent i = new Intent(mContext, TiffinOrderDetails.class);
                i.putExtra("order_id", order_id);
                i.putExtra("s_id", chef_id);
                mContext.startActivity(i);



            }
        });
    }

    public TiffinAdapter(Context mContext, List<TiffinAlbum> tiffinAlbums) {
        this.mContext = mContext;
        this.tiffinAlbums = tiffinAlbums;
    }




    @Override
    public int getItemCount() {
        return tiffinAlbums.size();
    }
}
