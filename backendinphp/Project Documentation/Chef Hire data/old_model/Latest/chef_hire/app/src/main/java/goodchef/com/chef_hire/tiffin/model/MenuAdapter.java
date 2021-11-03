package goodchef.com.chef_hire.tiffin.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import goodchef.com.chef_hire.R;

/**
 * Created by santo on 8/24/2018.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder>  {


    private Context mContext;
    private List<MenuAlbum> menuAlbums;


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final MenuAlbum album = menuAlbums.get(position);


        holder.menu.setText(album.getMenu());
        holder.day.setText(album.getDay());



    }
    public MenuAdapter(Context mContext, List<MenuAlbum> menuAlbums) {
        this.mContext = mContext;
        this.menuAlbums = menuAlbums;
    }

    @Override
    public int getItemCount() {
        return menuAlbums.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day, menu;



        public MyViewHolder(View view) {
            super(view);


           day = (TextView) view.findViewById(R.id.day);
            menu = (TextView)view.findViewById(R.id.menu);


        }
    }

}
