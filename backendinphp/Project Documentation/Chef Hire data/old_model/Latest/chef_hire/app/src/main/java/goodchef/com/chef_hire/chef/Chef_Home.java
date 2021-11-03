package goodchef.com.chef_hire.chef;

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
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.model.ChefOrderAdapter;
import goodchef.com.chef_hire.chef.model.ChefOrderAlbum;
import goodchef.com.chef_hire.user.model.CheflistAdapter;

/**
 * Created by santo on 8/11/2018.
 */

public class Chef_Home extends Fragment {

    ACProgressFlower dialog;
    RecyclerView recycler_view;
    private ChefOrderAdapter adapter;
    private List<ChefOrderAlbum> albumlist;
    Base_url base_url;
    TextView msg;
    ArrayList name_arr, status_arr, price_arr,quantity_arr,  order_id_arr, time_arr, user_address_arr;
    public static Chef_Home newInstance() {
        Chef_Home fragment = new Chef_Home();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.chef_home, container, false);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();



        name_arr = new ArrayList();
        status_arr = new ArrayList();
        price_arr = new ArrayList();
        quantity_arr = new ArrayList();
        order_id_arr = new ArrayList();
        user_address_arr = new ArrayList();
        time_arr = new ArrayList();

        msg = (TextView)view.findViewById(R.id.msg);

        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new ChefOrderAdapter(getActivity(),albumlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(albumlist.size()>0){
            albumlist.clear();
        }

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String  chef_id = sp.getString("chef_id","no data");

        check_chef_orders(chef_id);
    }

    public void check_chef_orders(String chef_id){

        dialog.show();
        msg.setVisibility(View.GONE);
        String url = base_url.base_url +"get_chef_orders.php";

        RequestParams RP = new RequestParams();
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                try {

                    String order_id = "";
                    String food_name = "";
                    String food_price = "";
                    String meals_no = "";
                    String food_status = "";
                    String time = "";
                    String user_address = "";

                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){


                        JSONObject jo = ja.getJSONObject(i);



                        if (!jo.isNull("order_id")){
                            order_id = jo.getString("order_id");
                        }
                        if (!jo.isNull("food_name")){
                            food_name = jo.getString("food_name");
                        }
                        if (!jo.isNull("user_address")){
                            user_address = jo.getString("user_address");
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




                        time_arr.add(i,time);
                        name_arr.add(i,food_name);
                        status_arr.add(i,food_status);

                        price_arr.add(i,food_price);
                        quantity_arr.add(i, meals_no);
                        order_id_arr.add(i,order_id);
                        user_address_arr.add(i, user_address);

                        ChefOrderAlbum a = new ChefOrderAlbum(name_arr.get(i).toString(), status_arr.get(i).toString()
                                , order_id_arr.get(i).toString(), time_arr.get(i).toString(), price_arr.get(i).toString(),
                                quantity_arr.get(i).toString(), user_address_arr.get(i).toString());



                        albumlist.add(a);

                        dialog.dismiss();


                    }




                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getActivity(), "No Orders yet.", Toast.LENGTH_SHORT).show();


                msg.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });



    }
}
