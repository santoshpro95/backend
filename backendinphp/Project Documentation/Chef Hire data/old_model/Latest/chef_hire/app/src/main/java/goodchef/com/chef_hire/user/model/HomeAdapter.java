package goodchef.com.chef_hire.user.model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.AvailableChef_lists;


/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeAlbum> homeAlbumList;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, items,price;
        public ImageView image;
        CardView card;



        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.name);
            //items = (TextView)view.findViewById(R.id.items);
            price = (TextView) view.findViewById(R.id.price);
            image = (ImageView) view.findViewById(R.id.image);
            card = (CardView)view.findViewById(R.id.card);

        }
    }


    public HomeAdapter(Context mContext, List<HomeAlbum> homeAlbumList) {
        this.mContext = mContext;
        this.homeAlbumList = homeAlbumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_home_row2, parent, false);

        return new MyViewHolder(itemView);
    }






    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HomeAlbum album = homeAlbumList.get(position);

        holder.name.setText(album.getName());
      //  holder.items.setText(album.getItems());
        holder.price.setText(album.getPrice());


        Glide.with(mContext).load(album.getImage())
               // .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);






        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String meal_id = album.getMeal_id();
                String food_name = album.getName();
                String food_price = album.getPrice();
                String food_image = album.getImage();
                String food_items = album.getItems();

                Intent i = new Intent(mContext, AvailableChef_lists.class);
                i.putExtra("meal_id",meal_id);
                i.putExtra("food_name",food_name);
                i.putExtra("food_items",food_items);
                i.putExtra("food_price",food_price);
                i.putExtra("food_image",food_image);
                mContext.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return homeAlbumList.size();
    }







}
