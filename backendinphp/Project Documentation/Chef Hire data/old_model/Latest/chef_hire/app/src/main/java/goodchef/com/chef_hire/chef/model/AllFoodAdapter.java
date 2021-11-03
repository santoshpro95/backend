package goodchef.com.chef_hire.chef.model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;

/**
 * Created by santo on 8/13/2018.
 */

public class AllFoodAdapter extends RecyclerView.Adapter<AllFoodAdapter.MyViewHolder> {

    public List<AllFoodAlbum> allFoodAlbums;
    private Context mContext;


    int i =0;
    Base_url base_url;
    ACProgressFlower dialog;
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, price,items;
        CheckBox check;
        ImageView image;


        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            items = (TextView)view.findViewById(R.id.items);
            price = (TextView) view.findViewById(R.id.price);

            check = (CheckBox) view.findViewById(R.id.check);
            image = (ImageView)view.findViewById(R.id.image);

            base_url = new Base_url();
            dialog = new ACProgressFlower.Builder(mContext)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    // .text("Title is here")
                    .fadeColor(Color.DKGRAY).build();

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_food_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        final AllFoodAlbum album = allFoodAlbums.get(position);



        holder.name.setText(album.getF_name());
        holder.items.setText(album.getItems());
        holder.price.setText(album.getF_price());

        Glide.with(mContext).load(album.getImage())
               // .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.image);


        ArrayList choose_list = album.getData();
        holder.check.setChecked(false);


        if(choose_list.size() > 0){

            if(album.getF_name().equalsIgnoreCase(choose_list.get(i).toString())){
                holder.check.setChecked(true);
                if(i < choose_list.size() - 1){
                    i = i+1;
                }
            }
            else {
                holder.check.setChecked(false);
            }

        }


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String  chef_id = sp.getString("chef_id","no data");


        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b){
                    check_food(album.getMeal_id(), chef_id);

                }else {
                    uncheck_food(album.getMeal_id(), chef_id);
                }

            }
        });


    }





    public void check_food(String meal_id, String chef_id){


        dialog.show();
        String url = base_url.base_url +"choose_foods.php";

        RequestParams RP = new RequestParams();
        RP.put("meal_id", meal_id);
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());


               dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });




    }



    public void uncheck_food(String meal_id, String chef_id){

        dialog.show();
        String url = base_url.base_url +"unselect_foods.php";

        RequestParams RP = new RequestParams();
        RP.put("meal_id", meal_id);
        RP.put("chef_id", chef_id);



        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());


                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });





    }





    public AllFoodAdapter(Context mContext,List<AllFoodAlbum> allFoodAlbums) {
        this.mContext = mContext;
        this.allFoodAlbums = allFoodAlbums;
    }


    @Override
    public int getItemCount() {
        return allFoodAlbums.size();
    }



}
