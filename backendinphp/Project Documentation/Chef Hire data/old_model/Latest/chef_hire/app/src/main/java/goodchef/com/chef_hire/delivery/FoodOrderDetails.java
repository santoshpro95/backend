package goodchef.com.chef_hire.delivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class FoodOrderDetails extends AppCompatActivity {


    ImageView back, c_call, u_call;
    Button accept, delivered;
    TextView food_name,food_quantity,status, c_name, c_phone, c_address,
            u_name, u_phone, u_address, food_price, time_left;

    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    Base_url base_url;
    ACProgressFlower dialog;
    private Socket socket;
    Handler h;
ProgressBar progressBar;
    String user_fcm;
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
        setContentView(R.layout.delivery_food_details);


        socket.connect();
        socket.on("message", handleIncomingMessages);

        h = new Handler();

        c_call = (ImageView)findViewById(R.id.c_call);
        u_call = (ImageView)findViewById(R.id.u_call);
        back = (ImageView)findViewById(R.id.back);
        time_left = (TextView)findViewById(R.id.time_left);

        accept = (Button)findViewById(R.id.accept);
        delivered = (Button)findViewById(R.id.delivered);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        food_name = (TextView)findViewById(R.id.food_name);
        food_quantity = (TextView)findViewById(R.id.food_quantity);
        status = (TextView)findViewById(R.id.status);
        c_name = (TextView)findViewById(R.id.c_name);
        c_phone = (TextView)findViewById(R.id.c_phone);
        c_address = (TextView)findViewById(R.id.c_address);
        u_name = (TextView)findViewById(R.id.u_name);
        u_phone = (TextView)findViewById(R.id.u_phone);
        u_address = (TextView)findViewById(R.id.u_address);
        food_price = (TextView)findViewById(R.id.food_price);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        delivered.setText("Deliver");
        delivered.setTextColor(getResources().getColor(R.color.white));
        delivered.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
        delivered.setEnabled(false);

        final String order_id = getIntent().getStringExtra("order_id");
        get_order_details(order_id);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                h.removeCallbacksAndMessages (null);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        u_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = u_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        c_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = c_name.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                String current_date = df.format(c.getTime());
                user_notify(user_fcm, "Delivery boy assigned.");
                status_update(base_url.food_ride, order_id, current_date);


            }
        });

        delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                String current_date = df.format(c.getTime());
                user_notify(user_fcm, "Food Delivered, Have a nice day :)");
                status_update(base_url.food_delivered, order_id, current_date);
            }
        });


    }

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



    public void status_update(final String f_status, final String order_id, String current_time){
        dialog.show();
        String url = base_url.base_url +"status_update.php";

        RequestParams RP = new RequestParams();
        RP.put("status", f_status);
        RP.put("order_id", order_id);
        RP.put("cur_time", current_time);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" responce =", response.toString());

                status.setText(f_status);

                if(f_status.equalsIgnoreCase(base_url.food_ride)){
                    accept.setText("Accepted");
                    accept.setTextColor(getResources().getColor(R.color.white));
                    accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    accept.setEnabled(false);

                    delivered.setText("Deliver");
                    delivered.setTextColor(getResources().getColor(R.color.white));
                    delivered.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                    delivered.setEnabled(true);
                }
                if(f_status.equalsIgnoreCase(base_url.food_delivered)){
                    delivered.setText("Delivered");
                    delivered.setTextColor(getResources().getColor(R.color.white));
                    delivered.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    delivered.setEnabled(false);

                    accept.setText("Accepted");
                    accept.setTextColor(getResources().getColor(R.color.white));
                    accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    accept.setEnabled(false);
                }

                assign_delivery(order_id);

                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });

    }
    public  void user_notify(String fcm_token, String message){
        String url = base_url.base_url +"notification.php";

        RequestParams RP = new RequestParams();
        RP.put("message", message);
        RP.put("token", fcm_token);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void assign_delivery(final String order_id){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  d_name = sp.getString("d_name","no data");
        final String  d_phone = sp.getString("d_phone","no data");

        String url = base_url.base_url +"delivery/food_deliver_assign.php";

        RequestParams RP = new RequestParams();
        RP.put("name",d_name );
        RP.put("phone",d_phone );
        RP.put("order_id", order_id);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());


                JSONObject sendText = new JSONObject();

                try{

                    sendText.put("order_id_no",order_id);
                    socket.emit("message", sendText);

                }catch(JSONException e){

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void get_order_details(String order_id){

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

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                try {

                    JSONArray jsonArray = response.getJSONArray("data");
                    JSONObject jo = jsonArray.getJSONObject(0);

                    String order_idd = jo.getString("order_id");
                    String user_address = jo.getString("user_address");
                    String user_name = jo.getString("user_name");
                    String user_phone = jo.getString("user_phone");
                    String chef_name = jo.getString("chef_name");
                    String chef_phone = jo.getString("chef_phone");
                    String chef_id = jo.getString("chef_id");
                    String order_time = jo.getString("date");
                    String f_name = jo.getString("food_name");
                    String f_price = jo.getString("food_price");
                    String meals_no = jo.getString("meals_no");
                    String f_status = jo.getString("status");
                    String u_fcm = jo.getString("user_fcm");
                    String lat = jo.getString("lat");
                    String log = jo.getString("log");
                    String chef_address= jo.getString("chef_address");

                    food_name.setText(f_name);
                    food_quantity.setText(meals_no+" meals");
                    status.setText(f_status);
                    food_price.setText(f_price);

                    user_fcm = u_fcm;
                    c_name.setText(chef_name);
                    c_phone.setText(chef_phone);
                    c_address.setText(chef_address);

                    u_name.setText(user_name);
                    u_phone.setText(user_phone);
                    u_address.setText(user_address);


                    if(f_status.equalsIgnoreCase(base_url.food_ride)) {
                        accept.setText("Accepted");
                        accept.setTextColor(getResources().getColor(R.color.white));
                        accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                        accept.setEnabled(false);

                        delivered.setText("Deliver");
                        delivered.setTextColor(getResources().getColor(R.color.white));
                        delivered.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                        delivered.setEnabled(true);


                    }


                    dialog.dismiss();

                    try {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                        Date date = df.parse(order_time);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.MINUTE, Integer.parseInt(base_url.delivery_time)*60);
                        final String curr_time = df.format(calendar.getTime());

                        progressBar.setMax(Integer.parseInt(base_url.delivery_time) *60);
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


            }
        });

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

}
