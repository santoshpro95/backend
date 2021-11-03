package goodchef.com.chef_hire.delivery;

import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

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
import goodchef.com.chef_hire.delivery.model.TiffinAdapter;
import goodchef.com.chef_hire.delivery.model.TiffinAlbum;
import goodchef.com.chef_hire.tiffin.model.MyTiffinAlbum;

/**
 * Created by santo on 8/11/2018.
 */

public class Delivery_Tiffin extends Fragment {

    ACProgressFlower dialog;
    Base_url base_url;
    TextView msg;
    RecyclerView recycler_view;
    private TiffinAdapter adapter;
    private List<TiffinAlbum> albumlist;
    ImageView back;
    String  d_phone;
    ArrayList name_arr, status_arr, price_arr, user_addr, quantity_arr, order_id_arr, s_id_arr, time_arr;
    public static Delivery_Tiffin newInstance() {
        Delivery_Tiffin fragment = new Delivery_Tiffin();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.delivery_tiffin_order, container, false);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();



        name_arr = new ArrayList();
        status_arr = new ArrayList();
        price_arr= new ArrayList();
        quantity_arr= new ArrayList();
        order_id_arr = new ArrayList();
        s_id_arr = new ArrayList();
        user_addr = new ArrayList();
        time_arr = new ArrayList();

        msg = (TextView)view.findViewById(R.id.msg);
        back = (ImageView)view.findViewById(R.id.back);

        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new TiffinAdapter(getActivity(),albumlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), DeliveryScreen.class);
                startActivity(i);
                getActivity().finish();
            }
        });


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        d_phone = sp.getString("d_phone","no data");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        get_all_tiffin_orders();
    }




    public void get_all_tiffin_orders(){

        albumlist.clear();
        dialog.show();
        msg.setVisibility(View.GONE);
        String url = base_url.base_url +"delivery/all_active_tiffin.php";

        RequestParams RP = new RequestParams();


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();


                try {




                    String name = "";
                    String status = "";
                    String days = "";
                    String order_id = "";
                    String s_id = "";
                    String time = "";
                    String price = "";
                    String address = "";
                    String del_phone = "";

                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){


                        JSONObject jo = ja.getJSONObject(i);



                        if (!jo.isNull("name")){
                            name = jo.getString("name");
                        }
                        if (!jo.isNull("status")){
                            status = jo.getString("status");
                        }
                        if (!jo.isNull("address")){
                            address = jo.getString("address");
                        }
                        if (!jo.isNull("days")){
                            days = jo.getString("days");
                        }
                        if (!jo.isNull("order_id")){
                            order_id = jo.getString("order_id");
                        }
                        if (!jo.isNull("s_id")){
                            s_id = jo.getString("s_id");
                        }
                        if (!jo.isNull("time")){
                            time = jo.getString("time");
                        }
                        if (!jo.isNull("price")){
                            price = jo.getString("price");
                        }

                        if (!jo.isNull("del_phone")){
                            del_phone = jo.getString("del_phone");
                        }


                        if(del_phone.equalsIgnoreCase("") || del_phone.equalsIgnoreCase(del_phone) ){

                            name_arr.add(i,name);
                            status_arr.add(i, status);
                            price_arr.add(i, price);
                            user_addr.add(i, address);
                            quantity_arr.add(i, days);
                            order_id_arr.add(i,order_id);
                            s_id_arr.add(i, s_id);


                            try {
                                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                                Date newDate=df.parse(time);

                                SimpleDateFormat df2 = new SimpleDateFormat("dd MMM YYYY");
                                String current_date2 = df2.format(newDate);

                                time_arr.add(i,current_date2);


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            TiffinAlbum a = new TiffinAlbum(name_arr.get(i).toString(), status_arr.get(i).toString(),
                                    price_arr.get(i).toString(), order_id_arr.get(i).toString(), s_id_arr.get(i).toString(),
                                    time_arr.get(i).toString(), quantity_arr.get(i).toString(), user_addr.get(i).toString());

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
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getActivity(), "No Orders yet.", Toast.LENGTH_SHORT).show();

                adapter.notifyDataSetChanged();
                msg.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });



    }
}
