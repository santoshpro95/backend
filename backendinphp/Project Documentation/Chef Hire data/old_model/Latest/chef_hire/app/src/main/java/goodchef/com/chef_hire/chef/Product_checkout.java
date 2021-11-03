package goodchef.com.chef_hire.chef;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.CheckoutScreen;
import goodchef.com.chef_hire.user.OrderDetailsScreen;
import goodchef.com.chef_hire.user.order_placed_dialog;

public class Product_checkout extends AppCompatActivity {

    ImageView back, image;
    TextView edit_address, address, price, quantity,no_p, name;
    Button dec, inc, order_placed;
    SharedPreferences.Editor ee;
    Base_url base_url;
    SharedPreferences sp;
    int final_no = 1;
    ACProgressFlower dialog;
    order_placed_dialog cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_checkout);

        back = (ImageView)findViewById(R.id.back);
        image = (ImageView)findViewById(R.id.image);

        edit_address = (TextView)findViewById(R.id.edit_address);
        address = (TextView)findViewById(R.id.address);
        price = (TextView)findViewById(R.id.price);
        quantity = (TextView)findViewById(R.id.quantity);
        no_p  = (TextView)findViewById(R.id.no);
        name = (TextView)findViewById(R.id.name);

        dec = (Button)findViewById(R.id.dec);
        inc = (Button)findViewById(R.id.inc);
        order_placed = (Button)findViewById(R.id.order_placed);

        base_url = new Base_url();


        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        final String p_name = getIntent().getStringExtra("name");
        final String p_price = getIntent().getStringExtra("price");
        String p_image = getIntent().getStringExtra("image");

        price.setText(p_price);
        Glide.with(getApplicationContext()).load(p_image)
                .into(image);
        name.setText(p_name);
        quantity.setText("1 " + p_name);
        no_p.setText("1");

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  chef_address = sp.getString("c_address","no data");
        final String  chef_id = sp.getString("chef_id","no data");
        final String  chef_name = sp.getString("c_name","no data");
        final String  chef_phone = sp.getString("c_phone","no data");
        address.setText(chef_address);


        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Product_checkout.this);
                builder.setTitle("Enter New Address");

// Set up the input
                final EditText input = new EditText(Product_checkout.this);
                input.setText(address.getText().toString());
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String new_addr =  input.getText().toString();
                        update_api(chef_name, chef_id,new_addr);

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int no  = Integer.parseInt(no_p.getText().toString());
                if(no>1){
                    final_no = no - 1;
                    no_p.setText(""+final_no);

                   int final_price =  Integer.parseInt(p_price) * final_no;
                    price.setText(""+final_price);
                }
                quantity.setText( final_no+ " " + p_name);
            }
        });

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int no  = Integer.parseInt(no_p.getText().toString());
                    final_no = no + 1;
                    no_p.setText(""+final_no);
                    int final_price =  Integer.parseInt(p_price) * final_no;
                    price.setText(""+final_price);
                quantity.setText( final_no+ " " + p_name);
            }
        });

        order_placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                order_product(chef_name,chef_address, chef_id, chef_phone);
            }
        });

    }


    public void order_product(String c_name, String c_address, String chef_id, String c_phone){
        dialog.show();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        String current_date = df.format(c.getTime());

        c.add(Calendar.DATE, 1);
        String delivery_date = df.format(c.getTime());
        String url = base_url.base_url +"order_chef_product.php";

        RequestParams RP = new RequestParams();
        RP.put("p_name", name.getText().toString());
        RP.put("p_price", price.getText().toString());
        RP.put("chef_name", c_name);
        RP.put("chef_phone", c_phone);
        RP.put("chef_address", c_address);
        RP.put("chef_id",chef_id );
        RP.put("delivery_date", delivery_date);
        RP.put("order_date", current_date);
        RP.put("quantity", ""+final_no);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                dialog.dismiss();

                try {
                    String order_id = response.getString("order_id");
                    show_message(order_id);

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








    public void show_message(final String Order_id){


        cd   = new order_placed_dialog(Product_checkout.this);
        cd.show();
        cd.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd.dismiss();
                Intent i = new Intent(getApplicationContext(), MyOrderDetails.class);
                i.putExtra("order_id", Order_id);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
        cd.setCanceledOnTouchOutside(false);

    }


    public void update_api(final String name, final String chef_id, final String get_address){

        String url = base_url.base_url +"chef_account_update.php";

        RequestParams RP = new RequestParams();
        RP.put("name", name);
        RP.put("address", get_address);
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());

                ee = sp.edit();
                ee.putString("c_name",name);
                ee.putString("c_address",get_address);
                ee.apply();

                address.setText(get_address);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
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
