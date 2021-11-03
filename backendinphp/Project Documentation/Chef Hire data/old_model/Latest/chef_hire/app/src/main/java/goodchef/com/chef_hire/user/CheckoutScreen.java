package goodchef.com.chef_hire.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.LoginFailed_dialog;
import goodchef.com.chef_hire.R;

public class CheckoutScreen extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    ImageView image, c_image;
    TextView f_name,items,  sh_address,edit_address,total,rating,
            f_price, no_meals, c_distance, c_name, delivery;
    Button order_placed,  inc, dec;
    SharedPreferences sp;
    Base_url base_url;
    String  u_name="";
    String  u_phone="";
    String  u_address="";

    int final_price = 0;
    int final_no = 0;

    ImageView back, call;
    order_placed_dialog cd;

    ACProgressFlower dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_screen);


        base_url = new Base_url();


        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
               // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


        f_name = (TextView)findViewById(R.id.food_name) ;
        items = (TextView)findViewById(R.id.items);
        edit_address = (TextView)findViewById(R.id.edit_address) ;
        c_distance = (TextView) findViewById(R.id.c_distance) ;
        c_name = (TextView) findViewById(R.id.c_name) ;
        rating = (TextView)findViewById(R.id.rating);
        total = (TextView)findViewById(R.id.total);
        delivery = (TextView)findViewById(R.id.delivery);

        call = (ImageView)findViewById(R.id.call);

        sh_address = (TextView)findViewById(R.id.address) ;
        f_price = (TextView)findViewById(R.id.food_price) ;
        no_meals = (TextView)findViewById(R.id.no_meals) ;
        image = (ImageView) findViewById(R.id.image) ;
        c_image = (ImageView)findViewById(R.id.c_image);
        back = (ImageView)findViewById(R.id.back);

        order_placed= (Button)findViewById(R.id.order_placed);
        inc= (Button)findViewById(R.id.inc);
        dec= (Button)findViewById(R.id.dec);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        u_address = sp.getString("u_address","no data");
        u_phone = sp.getString("u_phone","no data");
        u_name = sp.getString("u_name","no data");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        // Create the LocationRequest object
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


        final String food_name = getIntent().getStringExtra("food_name");
        final String food_price = getIntent().getStringExtra("food_price");
        String food_image = getIntent().getStringExtra("food_image");
        String food_items = getIntent().getStringExtra("food_items");
        final String delivery_price = getIntent().getStringExtra("delivery");
        final String name = getIntent().getStringExtra("name");
        final String phone = getIntent().getStringExtra("phone");
        final String c_address = getIntent().getStringExtra("address");
        final String distance = getIntent().getStringExtra("distance");
        final String c_fcm = getIntent().getStringExtra("c_fcm");
        String c_rating = getIntent().getStringExtra("rating");
        String chef_image = getIntent().getStringExtra("image");
        final String email = getIntent().getStringExtra("email");
        final String chef_id = getIntent().getStringExtra("chef_id");

        delivery.setText(delivery_price);
        sh_address.setText(u_address);
        rating.setText(c_rating);


        if(distance.equalsIgnoreCase("0")){
            c_distance.setText("few meters away");
        }else {
            c_distance.setText(distance+" kms away");
        }

        c_name.setText(name);
        items.setText(food_items);
        f_name.setText(food_name);


        no_meals.setText("1");
        f_price.setText(""+food_price);
        total.setText(""+(Integer.parseInt(food_price) + Integer.parseInt(delivery_price)));

        final_price = Integer.parseInt(food_price);
        final_no  = 1;

        Glide.with(getApplicationContext()).load(food_image)
                .into(image);

        Glide.with(getApplicationContext()).load(chef_image)
                .into(c_image);


        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                open_popup();
            }
        });



        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });


        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int no  = Integer.parseInt(no_meals.getText().toString());
               if(no<10){
                   final_no = no +1;
                   no_meals.setText(""+final_no);

                   final_price =  Integer.parseInt(food_price) * final_no;
                   f_price.setText(""+final_price);

                   total.setText(""+(final_price + Integer.parseInt(delivery_price)));
               }
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int no  = Integer.parseInt(no_meals.getText().toString());
                if(no>1){
                    final_no = no - 1;
                    no_meals.setText(""+final_no);

                    final_price =  Integer.parseInt(food_price) * final_no;
                    f_price.setText(""+final_price);

                    total.setText(""+(final_price + Integer.parseInt(delivery_price)));
                }

            }
        });



        order_placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.show();
                String user_name = u_name ;
                String user_phone = u_phone ;
                String user_address = sh_address.getText().toString() ;

                String chef_name = name ;
                String chef_phone = phone ;
                String chef_address = c_address ;

                String food_price = ""+final_price;

                String no_meals =   ""+final_no ;

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                String current_date = df.format(c.getTime());


                order_placed(user_name, user_phone, user_address, chef_name, chef_phone, chef_address,
                        chef_id, food_name, food_price, no_meals, current_date, delivery_price, c_fcm, email);


            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void order_placed(String user_name, String user_phone, String user_address, String chef_name, String chef_phone,
                             String chef_address, final String chef_id, String food_name,
                             String food_price, String no_meals, String time, String del_price,
                             final String c_fcm, final String email){





        String url = base_url.base_url +"chef_order.php";

        RequestParams RP = new RequestParams();
        RP.put("user_name", user_name);
        RP.put("user_phone", user_phone);
        RP.put("user_address", user_address);
        RP.put("fcm", base_url.fcm_token);
        RP.put("chef_name", chef_name);
        RP.put("chef_phone", chef_phone);
        RP.put("chef_address", chef_address);
        RP.put("chef_id", chef_id);
        RP.put("food_name", food_name);
        RP.put("food_price", food_price);
        RP.put("no_meals", no_meals);
        RP.put("time", time);
        RP.put("del_price", del_price);
        RP.put("lat", ""+currentLatitude);
        RP.put("log", ""+currentLongitude);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                notification(c_fcm);
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


    public  void notification(String fcm_token){
        String url = base_url.base_url +"notification.php";

        RequestParams RP = new RequestParams();
        RP.put("message", "New Order");
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


    public void show_message(final String Order_id){


        cd   = new order_placed_dialog(CheckoutScreen.this);
        cd.show();
        cd.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd.dismiss();
                Intent i = new Intent(getApplicationContext(), OrderDetailsScreen.class);
                i.putExtra("order_id", Order_id);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
        cd.setCanceledOnTouchOutside(false);

    }






    public void open_popup(){

        AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutScreen.this);
        builder.setTitle("Enter New Address");

// Set up the input
        final EditText input = new EditText(CheckoutScreen.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText(u_address);
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String new_address = input.getText().toString();
                update_api(u_name, new_address, u_phone);

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





    public void update_api(final String name, final String address, String phone){
        dialog.show();
        String url = base_url.base_url +"user_account_update.php";

        RequestParams RP = new RequestParams();
        RP.put("name", name);
        RP.put("address", address);
        RP.put("phone", phone);
        RP.put("lat", ""+currentLatitude);
        RP.put("log", ""+currentLongitude);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());


                SharedPreferences.Editor ee = sp.edit();
                ee.putString("u_address",address);
                ee.apply();
                sh_address.setText(address);

                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    protected void onResume() {
        super.onResume();
        //Now lets connect to the API
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

           // Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
 /*
             * Google Play services can resolve some errors it detects.
             * If the error has a resolution, try sending an Intent to
             * start a Google Play services activity that can resolve
             * error.
             */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                    /*
                     * Thrown if Google Play services canceled the original
                     * PendingIntent
                     */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
                /*
                 * If no resolution is available, display a dialog to the
                 * user with the error.
                 */
            Log.e("Error", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();

        //Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
}
