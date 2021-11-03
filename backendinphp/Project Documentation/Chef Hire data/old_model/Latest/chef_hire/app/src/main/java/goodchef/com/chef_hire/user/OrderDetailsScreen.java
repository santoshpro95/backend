package goodchef.com.chef_hire.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;

public class OrderDetailsScreen extends AppCompatActivity {

    TextView order_id,status,food_name,time_left,delivery,total,d_name, d_phone,
            address, food_info, c_name, c_phone, food_price;
    RelativeLayout delivery_assign;
    ImageView call, back, call2;
    Handler h;
    Base_url base_url;
    ACProgressFlower dialog;
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    private Socket socket;

    ProgressBar progressBar;

    {
        try{
            // socket = IO.socket("http://192.168.0.105:3000");
            //https://shrouded-everglades-68970.herokuapp.com
            socket = IO.socket("https://shrouded-everglades-68970.herokuapp.com");
            // socket url
        }catch(URISyntaxException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_order_details);

        socket.connect();
        socket.on("message", handleIncomingMessages);


        h = new Handler();

        order_id = (TextView)findViewById(R.id.order_id);
        time_left = (TextView)findViewById(R.id.time_left);
        status = (TextView)findViewById(R.id.status);
        d_name=  (TextView)findViewById(R.id.d_name);
        d_phone = (TextView)findViewById(R.id.d_phone);
        total = (TextView)findViewById(R.id.total);
        food_name = (TextView)findViewById(R.id.food_name);
        c_name = (TextView) findViewById(R.id.c_name);
        food_price = (TextView)findViewById(R.id.food_price);
        back = (ImageView)findViewById(R.id.back);
        call2 = (ImageView)findViewById(R.id.call2);
        delivery_assign = (RelativeLayout)findViewById(R.id.delivery_assign);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        address = (TextView)findViewById(R.id.address);
        delivery = (TextView)findViewById(R.id.delivery);
        food_info = (TextView)findViewById(R.id.food_info);
        c_phone = (TextView)findViewById(R.id.c_phone);
        call = (ImageView)findViewById(R.id.call);



        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        final String order_id_no = getIntent().getStringExtra("order_id");
        order_id.setText("Order Id: "+order_id_no);

        get_order_details(order_id_no);

        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",d_phone.getText().toString() , null));
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",c_phone.getText().toString() , null));
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
               h.removeCallbacksAndMessages (null);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        h.removeCallbacksAndMessages (null);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }

    private Emitter.Listener handleIncomingMessages = new Emitter.Listener(){
        @Override
        public void call(final Object... args){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    String order_id_no;

                    try {

                        order_id_no = data.getString("order_id_no").toString();

                        get_order_details(order_id_no);

                    } catch (JSONException e) {
                        // return;
                    }

                }
            });
        }
    };


    public String gettime_diff(String start, String end){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        String fi_time="";


        try {
            Date s_date = dateFormat.parse(start);
            Date e_date = dateFormat.parse(end);

            SimpleDateFormat df = new SimpleDateFormat("dd/MM");
            String only_date = df.format(e_date.getTime());

            long diffMs = s_date.getTime() - e_date.getTime();

            // long diffSec = diffMs / 1000;
            //  long min = diffSec / 60;
            // long sec = diffSec % 60;

            StringBuffer text = new StringBuffer("");


            if((diffMs / DAY) >30){
                text.append(only_date);
            }

             if (diffMs > DAY) {
                text.append(diffMs / DAY).append("");
                diffMs %= DAY;
            }
             if (diffMs > HOUR) {
                text.append(diffMs / HOUR).append("");
                diffMs %= HOUR;
            }
             if (diffMs > MINUTE) {
                text.append(diffMs / MINUTE).append("");
                diffMs %= MINUTE;
            }
           else if (diffMs > SECOND) {
                text.append(diffMs / SECOND).append("");
                diffMs %= SECOND;
            }

            System.out.println(text.toString());
            // System.out.println("The difference is "+min+" minutes and "+sec+" seconds.");
            // Toast.makeText(getActivity(),text.toString(),Toast.LENGTH_LONG).show();
            fi_time =text.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return fi_time;
    }

    public  void get_order_details(String order_id){
        dialog.show();
        String url = base_url.base_url +"order_details.php";

        RequestParams RP = new RequestParams();
        RP.put("order_id", order_id);



        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    JSONObject jo = jsonArray.getJSONObject(0);

                    String order_id = jo.getString("order_id");
                    String user_address = jo.getString("user_address");
                    String chef_name = jo.getString("chef_name");
                    String chef_phone = jo.getString("chef_phone");
                    String chef_id = jo.getString("chef_id");
                    String f_name = jo.getString("food_name");
                    String f_price = jo.getString("food_price");
                    String meals_no = jo.getString("meals_no");
                    String f_status = jo.getString("status");
                    String rating = jo.getString("rating");
                    String order_time = jo.getString("date");
                    String del_name = jo.getString("del_name");
                    String del_phone = jo.getString("del_phone");
                    String del_price = jo.getString("del_price");

                    try {

                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

                        Date date = df.parse(order_time);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.MINUTE, Integer.parseInt(base_url.delivery_time)*60);
                        final String curr_time = df.format(calendar.getTime());


                        if(f_status.equalsIgnoreCase(base_url.food_delivered) ||
                                f_status.equalsIgnoreCase(base_url.food_cancel) ){

                            time_left.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);

                        }else {
                            progressBar.setMax(60 * Integer.parseInt(base_url.delivery_time));
                            time_left.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.VISIBLE);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat dfm = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                                    final String  current_date = dfm.format(c.getTime());
                                    String finl_time =  gettime_diff(curr_time,current_date);
                                    time_left.setText(finl_time+ " Min");

                                    h.postDelayed(this,1000);

                                    if(!finl_time.equalsIgnoreCase("")){
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            progressBar.setProgress(Integer.parseInt(finl_time), true);
                                        }
                                    }else {
                                        time_left.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }

                            },1000);

                        }


                        if( f_status.equalsIgnoreCase(base_url.food_delivered) ){
                            if( rating.equalsIgnoreCase("0")){
                                give_rating(chef_id, order_id);
                            }
                        }

                        if( del_name.equalsIgnoreCase("") ){
                            delivery_assign.setVisibility(View.GONE);
                        }else {
                            delivery_assign.setVisibility(View.VISIBLE);
                        }


                    c_phone.setText(chef_phone);

                    int total_price =  Integer.parseInt(f_price) + Integer.parseInt(del_price);

                    total.setText(""+total_price);
                    delivery.setText(del_price);
                    d_name.setText(del_name);
                    d_phone.setText(del_phone);
                    food_name.setText(f_name);
                    food_info.setText(meals_no+" meals");
                    food_price.setText(f_price);
                    status.setText(f_status);
                    address.setText(user_address);
                    c_name.setText(chef_name);


                    dialog.dismiss();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    public void give_rating(final String chef_id, final String Order_id){

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsScreen.this);
        builder.setTitle("Give Rating to the Chef.");
        builder.setItems(new CharSequence[]
                        {"Bad", "Good", "Excellent", "Cancel"},
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                             rating_api("1.0",Order_id,chef_id);
                                break;
                            case 1:
                                rating_api("3.0",Order_id,chef_id);
                                break;
                            case 2:
                                rating_api("5.0",Order_id,chef_id);
                                break;
                            case 3:

                                break;
                        }
                    }
                });
        builder.create().show();




    }

    public void rating_api(String rating, String order_id, String chef_id ){


        dialog.show();
        String url = base_url.base_url +"chef_rating.php";

        RequestParams RP = new RequestParams();
        RP.put("order_id", order_id);
        RP.put("chef_id", chef_id);
        RP.put("rating", rating);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("order_cancel responce =", response.toString());


                dialog.dismiss();




            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });


    }


}
