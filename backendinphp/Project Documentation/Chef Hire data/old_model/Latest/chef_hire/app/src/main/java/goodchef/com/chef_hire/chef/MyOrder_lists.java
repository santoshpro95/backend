package goodchef.com.chef_hire.chef;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import goodchef.com.chef_hire.chef.model.OrderAlbum;
import goodchef.com.chef_hire.chef.model.OrdersAdapter;
import goodchef.com.chef_hire.user.model.TrackingAdapter;
import goodchef.com.chef_hire.user.model.TrackingAlbum;

public class MyOrder_lists extends AppCompatActivity {
    RecyclerView recycler_view;
    private OrdersAdapter adapter;
    private List<OrderAlbum> albumlist;

    TextView msg;
    ImageView back;
    Base_url base_url;
    ArrayList name_arr, status_arr, price_arr, quantity_arr,
            chef_id_arr, order_id_arr,time_arr, user_addr;
    ACProgressFlower dialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_list);
        name_arr= new ArrayList();
        status_arr = new ArrayList();
        price_arr= new ArrayList();
        quantity_arr= new ArrayList();
        chef_id_arr = new ArrayList();
        order_id_arr= new ArrayList();
        time_arr= new ArrayList();
        user_addr = new ArrayList();

        base_url = new Base_url();

        back = (ImageView)findViewById(R.id.back);

        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();

        msg = (TextView)findViewById(R.id.msg);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new OrdersAdapter(getApplicationContext(),albumlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  chef_id = sp.getString("chef_id","no data");
        get_orders(chef_id);
    }


    public void get_orders(String chef_id){



        msg.setVisibility(View.GONE);
        String url = base_url.base_url +"chef_product_orders.php";

        RequestParams RP = new RequestParams();
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                //   Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {

                    String p_name = "";
                    String p_price = "";
                    String no = "";
                    String status = "";
                    String chf_address="";

                    String order_id = "";
                    String chef_id = "";

                    String time = "";

                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){


                        JSONObject jo = ja.getJSONObject(i);



                        if (!jo.isNull("p_name")){
                            p_name = jo.getString("p_name");
                        }
                        if (!jo.isNull("p_price")){
                            p_price = jo.getString("p_price");
                        }
                        if (!jo.isNull("no")){
                            no = jo.getString("no");
                        }

                        if (!jo.isNull("status")){
                            status = jo.getString("status");
                        }
                        if (!jo.isNull("chef_address")){
                            chf_address = jo.getString("chef_address");
                        }




                        if (!jo.isNull("order_id")){
                            order_id = jo.getString("order_id");
                        }
                        if (!jo.isNull("chef_id")){
                            chef_id = jo.getString("chef_id");
                        }
                        if (!jo.isNull("time")){
                            time = jo.getString("time");
                        }



                        name_arr.add(i,p_name);
                        status_arr.add(i,status);
                        price_arr.add(i,p_price);
                        quantity_arr.add(i, no);
                        order_id_arr.add(i,order_id);
                        chef_id_arr.add(i,chef_id);
                        user_addr.add(i, chf_address);



                        try {
                            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                            Date newDate=df.parse(time);

                            SimpleDateFormat df2 = new SimpleDateFormat("dd MMM YYYY");
                            String current_date2 = df2.format(newDate);

                            time_arr.add(i,current_date2);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        OrderAlbum a = new OrderAlbum(name_arr.get(i).toString(), status_arr.get(i).toString(),
                                price_arr.get(i).toString(), order_id_arr.get(i).toString(),
                                chef_id_arr.get(i).toString(),

                                time_arr.get(i).toString(),
                                quantity_arr.get(i).toString(),
                                user_addr.get(i).toString());



                        albumlist.add(a);
                        dialog.dismiss();

                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();

                dialog.dismiss();
                msg.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            }
        });

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
