package goodchef.com.chef_hire.tiffin;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import android.widget.CheckBox;
import android.widget.ImageView;
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
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;

public class MyTiffinDetails extends AppCompatActivity {
    ACProgressFlower dialog;
    Base_url base_url;
    ImageView back, call2 ;
    TextView view_menu, days_no,e_date, s_date, price, d_address, t_name, d_name, d_phone;
    CheckBox breakfast_s, lunch_s, dinner_s, veg_only;
    RelativeLayout delivery_assign;
    private Socket socket;

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
        setContentView(R.layout.activity_my_tiffin_details);

        socket.connect();
        socket.on("message", handleIncomingMessages);


        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(MyTiffinDetails.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        veg_only = (CheckBox)findViewById(R.id.veg_only);
        view_menu = (TextView) findViewById(R.id.view_menu);
        back = (ImageView)findViewById(R.id.back);
        call2 = (ImageView)findViewById(R.id.call2);

        delivery_assign = (RelativeLayout)findViewById(R.id.delivery_assign);
        days_no = (TextView)findViewById(R.id.days_no);
        e_date = (TextView)findViewById(R.id.e_date);
        s_date = (TextView)findViewById(R.id.s_date);
        price = (TextView)findViewById(R.id.price);
        d_address = (TextView)findViewById(R.id.address);
        t_name = (TextView)findViewById(R.id.name);
        d_name = (TextView)findViewById(R.id.d_name);
        d_phone = (TextView)findViewById(R.id.d_phone);

        breakfast_s = (CheckBox)findViewById(R.id.breakfast);
        lunch_s = (CheckBox)findViewById(R.id.lunch);
        dinner_s = (CheckBox)findViewById(R.id.dinner);

        final String get_order_id = getIntent().getStringExtra("order_id");
        final String get_s_id = getIntent().getStringExtra("s_id");

        tiffin_details(get_order_id);
        delivery_assign.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
        view_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ViewMenu.class);
                i.putExtra("s_id",get_s_id);
                startActivity(i);
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });


        call2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = d_phone.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);


            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                        tiffin_details(order_id_no);

                    } catch (JSONException e) {
                        // return;
                    }

                }
            });
        }
    };

    public void tiffin_details(String order_id){

        dialog.show();

        String url = base_url.base_url +"service/my_tiffin_details.php";

        RequestParams RP = new RequestParams();
        RP.put("order_id",order_id);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                //   Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {

                    String name = "";
                    String time = "";
                    String veg = "";

                    String breakfast = "";
                    String lunch = "";
                    String dinner = "";

                    String days = "";
                    String t_price = "";
                    String status = "";
                    String address = "";
                    String end_date = "";

                    String del_name = "";
                    String del_phone = "";

                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){

                        JSONObject jo = ja.getJSONObject(i);

                        if (!jo.isNull("name")){
                            name = jo.getString("name");
                        }
                        if (!jo.isNull("breakfast")){
                            breakfast = jo.getString("breakfast");
                        }

                        if (!jo.isNull("lunch")){
                            lunch = jo.getString("lunch");
                        }

                        if (!jo.isNull("dinner")){
                            dinner = jo.getString("dinner");
                        }

                        if (!jo.isNull("time")){
                            time = jo.getString("time");
                        }

                        if (!jo.isNull("veg")){
                            veg = jo.getString("veg");
                        }


                        if (!jo.isNull("days")){
                            days = jo.getString("days");
                        }
                        if (!jo.isNull("price")){
                            t_price = jo.getString("price");
                        }
                        if (!jo.isNull("status")){
                            status = jo.getString("status");
                        }
                        if (!jo.isNull("address")){
                            address = jo.getString("address");
                        }
                        if (!jo.isNull("end_date")){
                            end_date = jo.getString("end_date");
                        }

                        if (!jo.isNull("del_name")){
                            del_name = jo.getString("del_name");
                        }
                        if (!jo.isNull("del_phone")){
                            del_phone = jo.getString("del_phone");
                        }


                        if(del_name.equalsIgnoreCase("")){
                            delivery_assign.setVisibility(View.GONE);
                        }
                        else {
                            delivery_assign.setVisibility(View.VISIBLE);
                            d_phone.setText(del_phone);
                            d_name.setText(del_name);
                        }

                        if(veg.equalsIgnoreCase("1")){
                            veg_only.setChecked(true);
                        }
                        else {
                            veg_only.setChecked(false);
                        }

                        if(breakfast.equalsIgnoreCase("1")){
                            breakfast_s.setChecked(true);
                        }else {
                            breakfast_s.setChecked(false);
                        }


                        if(lunch.equalsIgnoreCase("1")){
                            lunch_s.setChecked(true);
                        }else {
                            lunch_s.setChecked(false);
                        }


                        if(dinner.equalsIgnoreCase("1")){
                            dinner_s.setChecked(true);
                        }else {
                            dinner_s.setChecked(false);
                        }

                        days_no.setText(days+" days");
                        t_name.setText(name);

                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                        Date st_date=df.parse(time);
                        Date en_date =df.parse(end_date);

                        SimpleDateFormat df2 = new SimpleDateFormat("dd MMM YYYY");
                        String current_date1 = df2.format(st_date);
                        String current_date2 = df2.format(en_date);

                        s_date.setText(current_date1);
                        e_date.setText(current_date2);
                        d_address.setText(address);
                        price.setText(t_price);

                        dialog.dismiss();
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
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

}
