package goodchef.com.chef_hire.tiffin.model;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import goodchef.com.chef_hire.R;

/**
 * Created by santo on 8/24/2018.
 */

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.MyViewHolder>  {


    private Context mContext;
    ArrayList state;
    private ItemClickListener mClickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.state_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


        holder.name.setText(state.get(position).toString());


    }

    public String getItem(int id) {
        return state.get(id).toString();
    }

    public StateAdapter(Context mContext, ArrayList state) {
        this.mContext = mContext;
        this.state = state;
    }

    @Override
    public int getItemCount() {
        return state.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            view.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }


}
