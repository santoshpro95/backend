package goodchef.com.chef_hire.user.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.CheckoutScreen;
import goodchef.com.chef_hire.user.User_HomeScreen;

/**
 * Created by santo on 8/12/2018.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder>{


    public List<LocationAlbum> locationAlbums;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            image = (ImageView)view.findViewById(R.id.image);
        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_row, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final LocationAlbum album = locationAlbums.get(position);

        holder.name.setText(album.getName());

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext, album.getName(), Toast.LENGTH_LONG).show();

                holder.image.setVisibility(View.VISIBLE);
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor ee = sp.edit();
                ee.putString("city_name",album.getName());
                ee.apply();

               Intent i = new Intent(mContext, User_HomeScreen.class);
               mContext.startActivity(i);


            }
        });

    }


    public LocationAdapter(Context mContext, List<LocationAlbum> locationAlbums) {
        this.mContext = mContext;
        this.locationAlbums = locationAlbums;
    }

    @Override
    public int getItemCount() {
        return locationAlbums.size();
    }
}
