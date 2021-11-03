package goodchef.com.chef_hire.user.model;

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
import goodchef.com.chef_hire.user.CheckoutScreen;

/**
 * Created by santo on 8/12/2018.
 */

public class CheflistAdapter extends RecyclerView.Adapter<CheflistAdapter.MyViewHolder>{


    public List<CheflistAlbum> cheflistAlbums;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, distance,rating,address, type, delivery;
        CardView card;
        ImageView image;
        public MyViewHolder(View view) {
            super(view);


            name = (TextView) view.findViewById(R.id.name);
            distance = (TextView)view.findViewById(R.id.distance);
            delivery = (TextView)view.findViewById(R.id.delivery);
            rating = (TextView) view.findViewById(R.id.rating);
            address= (TextView)view.findViewById(R.id.address);
            type = (TextView)view.findViewById(R.id.type);
            image = (ImageView)view.findViewById(R.id.image);
            card = (CardView)view.findViewById(R.id.card);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cheflist_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final CheflistAlbum album = cheflistAlbums.get(position);

        holder.name.setText(album.getName());
        if(album.getType().equalsIgnoreCase("0")){
            holder.type.setText("Resturant");
        }
        else {
            holder.type.setText("Home");
        }


        if(album.getDelivery().equalsIgnoreCase("0")){
            holder.delivery.setText("Free Delivery");
        }else {
            holder.delivery.setText("Delivery charge "+album.getDelivery()+"/-");
        }

        if(album.getDistance_between().equalsIgnoreCase("0")){
            holder.distance.setText("few meters away");
        }else {
            holder.distance.setText(album.getDistance_between()+" kms away");
        }

        holder.rating.setText(album.getRating());
        holder.address.setText(album.getAddress());

        Glide.with(mContext).load(album.getImage())
                .into(holder.image);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String food_name = album.getFood_name();
                String food_price = album.getFood_price();
                String food_image = album.getFood_image();
                String distance = album.getDistance_between();

                String name = album.getName();
                String phone = album.getPhone();
                String address = album.getAddress();
                String rating = album.getRating();

                String image = album.getImage();
                String email = album.getEmail();
                String chef_id = album.getId();
                String food_items = album.getItems();
                String delivery_price = album.getDelivery();
                String c_fcm = album.getC_fcm();

                Intent i = new Intent(mContext, CheckoutScreen.class);
                i.putExtra("food_name",food_name);
                i.putExtra("food_price",food_price);
                i.putExtra("food_image",food_image);
                i.putExtra("food_items",food_items);
                i.putExtra("delivery",delivery_price);
                i.putExtra("address",address);
                i.putExtra("c_fcm",c_fcm);
                i.putExtra("name",name);
                i.putExtra("phone",phone);
                i.putExtra("distance",distance);
                i.putExtra("rating",rating);

                i.putExtra("image",image);
                i.putExtra("email",email);
                i.putExtra("chef_id",chef_id);
                mContext.startActivity(i);



            }
        });

    }


    public CheflistAdapter(Context mContext, List<CheflistAlbum> cheflistAlbums) {
        this.mContext = mContext;
        this.cheflistAlbums = cheflistAlbums;
    }



    @Override
    public int getItemCount() {
        return cheflistAlbums.size();
    }
}
