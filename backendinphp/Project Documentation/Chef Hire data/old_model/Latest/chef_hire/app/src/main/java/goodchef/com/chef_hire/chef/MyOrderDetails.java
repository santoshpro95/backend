package goodchef.com.chef_hire.chef;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;

public class MyOrderDetails extends AppCompatActivity {

    ImageView back;
    TextView  address, price, quantity,status, name;
    Base_url base_url;
    ACProgressFlower dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order_details);

        base_url = new Base_url();

        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        back = (ImageView)findViewById(R.id.back);

        address = (TextView)findViewById(R.id.address);
        status = (TextView)findViewById(R.id.status);
        price = (TextView)findViewById(R.id.price);
        quantity = (TextView)findViewById(R.id.quantity);
        name = (TextView)findViewById(R.id.name);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


        String order_id = getIntent().getStringExtra("order_id");
        order_details_api(order_id);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void order_details_api(final String order_id){

        String url = base_url.base_url +"chef_product_details.php";

        RequestParams RP = new RequestParams();
        RP.put("order_id", order_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());



                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    JSONObject jo = jsonArray.getJSONObject(0);


                    String p_name = "";
                    String p_price = "";
                    String no = "";
                    String status_p = "";
                    String chf_address="";

                    String order_id = "";
                    String chef_id = "";

                    String time = "";


                        if (!jo.isNull("p_name")) {
                            p_name = jo.getString("p_name");
                        }
                        if (!jo.isNull("p_price")) {
                            p_price = jo.getString("p_price");
                        }
                        if (!jo.isNull("no")) {
                            no = jo.getString("no");
                        }

                        if (!jo.isNull("status")) {
                            status_p = jo.getString("status");
                        }
                        if (!jo.isNull("chef_address")) {
                            chf_address = jo.getString("chef_address");
                        }


                        if (!jo.isNull("order_id")) {
                            order_id = jo.getString("order_id");
                        }
                        if (!jo.isNull("chef_id")) {
                            chef_id = jo.getString("chef_id");
                        }
                        if (!jo.isNull("time")) {
                            time = jo.getString("time");
                        }

                    address.setText(chf_address);
                        name.setText(p_name);
                        price.setText(p_price);
                    quantity.setText(no + " " + p_name);
                    status.setText(status_p);


                    if(status_p.equalsIgnoreCase("delivered") ){

                        status.setTextColor(getResources().getColor(R.color.light_grey));
                        status.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittext_border));

                    }else {

                        status.setTextColor(getResources().getColor(R.color.colorPrimary));
                        status.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.colorborder));
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
