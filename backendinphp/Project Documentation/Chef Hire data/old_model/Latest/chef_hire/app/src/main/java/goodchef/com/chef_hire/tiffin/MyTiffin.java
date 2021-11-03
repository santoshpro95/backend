package goodchef.com.chef_hire.tiffin;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import goodchef.com.chef_hire.tiffin.model.MyTiffinAdapter;
import goodchef.com.chef_hire.tiffin.model.MyTiffinAlbum;

public class MyTiffin extends AppCompatActivity {
    Base_url base_url;
    ArrayList name_arr, status_arr, price_arr, user_addr, quantity_arr, order_id_arr, s_id_arr, time_arr;
    private MyTiffinAdapter adapter;
    private List<MyTiffinAlbum> albumlist;
    ACProgressFlower dialog;
    RecyclerView recyclerView;
    TextView msg;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tiffin);




        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(MyTiffin.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        back = (ImageView)findViewById(R.id.back);

        name_arr = new ArrayList();
        status_arr = new ArrayList();
        price_arr= new ArrayList();
        quantity_arr= new ArrayList();
        order_id_arr = new ArrayList();
        s_id_arr = new ArrayList();
        user_addr = new ArrayList();
        time_arr = new ArrayList();

        albumlist = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        msg = (TextView)findViewById(R.id.msg);


        adapter = new MyTiffinAdapter(getApplicationContext(), albumlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  phone_no = sp.getString("u_phone","no data");
        call_my_services(phone_no);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void call_my_services(String phone_no){

        albumlist.clear();
        msg.setVisibility(View.GONE);
        dialog.show();


        String url = base_url.base_url +"service/my_services.php";

        RequestParams RP = new RequestParams();
        RP.put("phone",phone_no);



        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                //   Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {




                    String name = "";
                    String status = "";
                    String days = "";
                    String order_id = "";
                    String s_id = "";
                    String time = "";
                    String price = "";
                    String address = "";

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

                        MyTiffinAlbum a = new MyTiffinAlbum(name_arr.get(i).toString(), status_arr.get(i).toString(),
                                price_arr.get(i).toString(), order_id_arr.get(i).toString(), s_id_arr.get(i).toString(),
                                time_arr.get(i).toString(), quantity_arr.get(i).toString(), user_addr.get(i).toString());

                        albumlist.add(a);


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
                msg.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();

            }
        });

        dialog.dismiss();

    }

}
