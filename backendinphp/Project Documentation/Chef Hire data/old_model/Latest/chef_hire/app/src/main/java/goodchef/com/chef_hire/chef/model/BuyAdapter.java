package goodchef.com.chef_hire.chef.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.Product_checkout;
import goodchef.com.chef_hire.tiffin.OrderTiffin;
import goodchef.com.chef_hire.tiffin.model.ServicesAlbum;

/**
 * Created by santo on 8/20/2018.
 */

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.MyViewHolder>   {

    private Context mContext;
    private List<BuyAlbum> buyAlbums;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price;
        public ImageView image;
        CardView card;

        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            image = (ImageView) view.findViewById(R.id.image);
            card = (CardView)view.findViewById(R.id.card);

        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.buy_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final BuyAlbum album = buyAlbums.get(position);

        holder.name.setText(album.getName());
        holder.price.setText(album.getPrice());

        Glide.with(mContext).load(album.getImage())
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);


            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mContext, Product_checkout.class);
                    i.putExtra("name",album.getName());
                    i.putExtra("image",album.getImage());
                    i.putExtra("price",album.getPrice());
                    mContext.startActivity(i);

                }
            });

    }


    public BuyAdapter(Context mContext, List<BuyAlbum> buyAlbums) {
        this.mContext = mContext;
        this.buyAlbums = buyAlbums;
    }

    @Override
    public int getItemCount() {
        return buyAlbums.size();
    }


}
