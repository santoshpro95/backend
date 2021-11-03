package goodchef.com.chef_hire.tiffin.model;

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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.tiffin.OrderTiffin;

/**
 * Created by santo on 8/20/2018.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.MyViewHolder>   {

    private Context mContext;
    private List<ServicesAlbum> servicesAlbums;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price, items, distance;
        public ImageView image;
        CardView card;

        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            items  = (TextView)view.findViewById(R.id.items);
            distance = (TextView)view.findViewById(R.id.distance);
            image = (ImageView) view.findViewById(R.id.image);
            card = (CardView)view.findViewById(R.id.card);

        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.more_services, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ServicesAlbum album = servicesAlbums.get(position);

        holder.name.setText(album.getName());
        holder.items.setText(album.getInfo());
        holder.distance.setText(album.getAddress());

        Glide.with(mContext).load(album.getImage())
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);


            holder.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(mContext, OrderTiffin.class);
                    i.putExtra("name",album.getName());
                    i.putExtra("phone",album.getPhone());
                    i.putExtra("breakfast",album.getBreakfast());
                    i.putExtra("lunch",album.getLunch());
                    i.putExtra("dinner",album.getDinner());
                    i.putExtra("image",album.getImage());
                    i.putExtra("s_id",album.getS_id());
                    i.putExtra("service_addr",album.getAddress());
                    mContext.startActivity(i);

                }
            });




    }


    public ServicesAdapter(Context mContext, List<ServicesAlbum> servicesAlbums) {
        this.mContext = mContext;
        this.servicesAlbums = servicesAlbums;
    }

    @Override
    public int getItemCount() {
        return servicesAlbums.size();
    }


}
