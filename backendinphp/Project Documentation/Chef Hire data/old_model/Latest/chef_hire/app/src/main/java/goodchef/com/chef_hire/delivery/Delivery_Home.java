package goodchef.com.chef_hire.delivery;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import goodchef.com.chef_hire.chef.model.ChefOrderAdapter;
import goodchef.com.chef_hire.chef.model.ChefOrderAlbum;
import goodchef.com.chef_hire.delivery.model.FoodOrderAdapter;
import goodchef.com.chef_hire.delivery.model.FoodOrderAlbum;

/**
 * Created by santo on 8/11/2018.
 */

public class Delivery_Home extends Fragment {

    ACProgressFlower dialog;
    RecyclerView recycler_view;
    private FoodOrderAdapter adapter;
    private List<FoodOrderAlbum> albumlist;
    Base_url base_url;
    TextView msg;
    ArrayList name_arr, status_arr, price_arr, order_id_arr, time_arr, address_arr, meals_arr;

    String  d_phone;

    public static Delivery_Home newInstance() {
        Delivery_Home fragment = new Delivery_Home();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.delivery_food_order, container, false);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();



        name_arr = new ArrayList();
        status_arr = new ArrayList();
        price_arr = new ArrayList();
        order_id_arr = new ArrayList();
        time_arr = new ArrayList();
        address_arr = new ArrayList();
        meals_arr = new ArrayList();

        msg = (TextView)view.findViewById(R.id.msg);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new FoodOrderAdapter(getActivity(),albumlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        d_phone = sp.getString("d_phone","no data");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        albumlist.clear();
        get_all_food_orders();

    }

    public void get_all_food_orders(){
        dialog.show();
        msg.setVisibility(View.GONE);

        String url = base_url.base_url +"delivery/all_active_orders.php";
        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("food_order =", response.toString());


                try {

                    albumlist.clear();

                    String order_id = "";
                    String food_name = "";
                    String food_price = "";
                    String meals_no = "";
                    String food_status = "";
                    String time = "";
                    String user_address = "";
                    String del_phone = "";


                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){


                        JSONObject jo = ja.getJSONObject(i);



                        if (!jo.isNull("order_id")){
                            order_id = jo.getString("order_id");
                        }
                        if (!jo.isNull("food_name")){
                            food_name = jo.getString("food_name");
                        }

                        if (!jo.isNull("food_price")){
                            food_price = jo.getString("food_price");
                        }
                        if (!jo.isNull("meals_no")){
                            meals_no = jo.getString("meals_no");
                        }

                        if (!jo.isNull("status")){
                            food_status = jo.getString("status");
                        }
                        if (!jo.isNull("time")){
                            time = jo.getString("time");
                        }

                        if (!jo.isNull("user_address")){
                            user_address = jo.getString("user_address");
                        }

                        if (!jo.isNull("del_phone")){
                            del_phone = jo.getString("del_phone");
                        }

                        if(del_phone.equalsIgnoreCase("") || del_phone.equalsIgnoreCase(del_phone) ){

                            address_arr.add(i, user_address);
                            meals_arr.add(i, meals_no);
                            time_arr.add(i,time);
                            name_arr.add(i,food_name);
                            status_arr.add(i,food_status);
                            price_arr.add(i,food_price);
                            order_id_arr.add(i,order_id);

                            FoodOrderAlbum a = new FoodOrderAlbum(name_arr.get(i).toString(), status_arr.get(i).toString()
                                    , order_id_arr.get(i).toString(), time_arr.get(i).toString(), price_arr.get(i).toString(),
                                    address_arr.get(i).toString(), meals_arr.get(i).toString());

                            albumlist.add(a);

                        }


                        if(albumlist.size() ==0){
                            msg.setVisibility(View.VISIBLE);
                        }

                    }
                    adapter.notifyDataSetChanged();

                    dialog.dismiss();





                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                msg.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });


    }
}
