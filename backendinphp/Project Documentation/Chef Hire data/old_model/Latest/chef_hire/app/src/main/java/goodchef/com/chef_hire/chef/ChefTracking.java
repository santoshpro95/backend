package goodchef.com.chef_hire.chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

public class ChefTracking extends AppCompatActivity {

    TextView order_id_no,food_name,food_info,food_price,time_left,
            address, current_status, c_name, c_phone, d_name,d_phone ;
    Button accept;
    ImageView call, d_call;
    Base_url base_url;
    ACProgressFlower dialog;
    ImageView back;
    RelativeLayout delivery_addign;
    String user_fcm;
    ProgressBar progressBar;

    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    private Socket socket;
    Handler h;

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
        setContentView(R.layout.chef_orders);

        socket.connect();
        socket.on("message", handleIncomingMessages);

        h = new Handler();

        order_id_no = (TextView)findViewById(R.id.order_id);
        time_left = (TextView)findViewById(R.id.time_left);
        food_name = (TextView)findViewById(R.id.food_name);
        food_info = (TextView)findViewById(R.id.food_info);
        c_name = (TextView)findViewById(R.id.c_name);
        d_name = (TextView)findViewById(R.id.d_name);
        d_phone = (TextView)findViewById(R.id.d_phone);
        d_call = (ImageView)findViewById(R.id.d_call);
        delivery_addign = (RelativeLayout)findViewById(R.id.delivery_addign);
        c_phone = (TextView)findViewById(R.id.c_phone);
        food_price = (TextView)findViewById(R.id.food_price);
        address = (TextView)findViewById(R.id.address);
        current_status=  (TextView)findViewById(R.id.current_status);
        back = (ImageView)findViewById(R.id.back);
        accept = (Button) findViewById(R.id.accept);
        call = (ImageView)findViewById(R.id.call);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


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


        d_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = d_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone, null));
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = c_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",phone, null));
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

                user_notify(user_fcm, "Chef Accepted");
                accept_api(base_url.food_cook, order_id, current_date);
            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        h.removeCallbacksAndMessages (null);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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




    public void accept_api(final String f_status, final String order_id, String current_time){
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

                current_status.setText(f_status);

                accept.setText("Accepted");
                accept.setTextColor(getResources().getColor(R.color.white));
                accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                accept.setEnabled(false);

                send_notification();

                JSONObject sendText = new JSONObject();

                try{

                    sendText.put("order_id_no",order_id);
                    socket.emit("message", sendText);




                }catch(JSONException e){

                }

                dialog.dismiss();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    public void send_notification(){

        String url = base_url.base_url +"delivery/send_notifyto_all.php";

        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" responce =", response.toString());

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
                    final String order_time = jo.getString("date");
                    String f_name = jo.getString("food_name");
                    String f_price = jo.getString("food_price");
                    String meals_no = jo.getString("meals_no");
                    String f_status = jo.getString("status");
                    String lat = jo.getString("lat");
                    String log = jo.getString("log");
                    String u_fcm = jo.getString("user_fcm");
                    String del_name = jo.getString("del_name");
                    String del_phone = jo.getString("del_phone");

                    double discount = Integer.parseInt(f_price) * (Double.parseDouble(base_url.food_commision)/ 100.0);
                    double gross_price = Integer.parseInt(f_price) - discount;

                    user_fcm = u_fcm;

                    if(f_status.equalsIgnoreCase("waiting")){
                        accept.setText("Accept");
                        accept.setTextColor(getResources().getColor(R.color.white));
                        accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                        accept.setEnabled(true);

                    }
                    else {
                        accept.setText("Accepted");
                        accept.setTextColor(getResources().getColor(R.color.white));
                        accept.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                        accept.setEnabled(false);
                    }

                    if(del_name.equalsIgnoreCase("")){
                        delivery_addign.setVisibility(View.GONE);
                    }
                    else {
                        delivery_addign.setVisibility(View.VISIBLE);
                    }

                    c_name.setText(user_name);
                    c_phone.setText(user_phone);
                    order_id_no.setText("Order Id: "+order_idd);
                    address.setText(user_address);
                    food_name.setText(f_name);
                    food_info.setText(meals_no+" meals");
                    food_price.setText(""+gross_price);
                    current_status.setText(f_status);
                    d_phone.setText(del_phone);
                    d_name.setText(del_name);


                        if(f_status.equalsIgnoreCase(base_url.food_delivered)  ||
                            f_status.equalsIgnoreCase(base_url.food_cancel)){
                            time_left.setVisibility(View.GONE);
                            progressBar.setVisibility(View.GONE);
                        }
                        else {
                            progressBar.setMax(Integer.parseInt(base_url.delivery_time)*60 /2);
                            progressBar.setVisibility(View.VISIBLE);
                            time_left.setVisibility(View.VISIBLE);
                            call.setVisibility(View.VISIBLE);
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                                    Date date  = null;

                                    try {
                                        date = df.parse(order_time);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(date);
                                    calendar.add(Calendar.MINUTE, Integer.parseInt(base_url.delivery_time)*60 / 2);
                                    final String curr_time = df.format(calendar.getTime());

                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat dfm = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                                    String  current_date = dfm.format(c.getTime());

                                    String finl_time =  gettime_diff(curr_time,current_date);
                                    time_left.setText(finl_time+" min");

                                    if(!finl_time.equalsIgnoreCase("")){

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                            progressBar.setProgress(Integer.parseInt(finl_time),true);
                                        }
                                    }
                                    else {

                                        time_left.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.GONE);
                                    }


                                    h.postDelayed(this,1000);
                                }

                            },1000);


                        }


                    dialog.dismiss();

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
