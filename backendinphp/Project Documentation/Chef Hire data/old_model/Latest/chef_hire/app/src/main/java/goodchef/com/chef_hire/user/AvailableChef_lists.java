package goodchef.com.chef_hire.user;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.model.CheflistAdapter;
import goodchef.com.chef_hire.user.model.CheflistAlbum;

public class AvailableChef_lists extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    String meal_id ="";
    String food_name ="";
    String food_price ="";
    String food_image ="";
    String food_items = "";
    int delivery_price;


    TextView msg;
    ImageView back;
    RecyclerView recycler_view;
    private CheflistAdapter adapter;
    private List<CheflistAlbum> albumlist;
    Base_url base_url;
    ArrayList name_arr, phone_arr, address_arr, rating_arr, distance_between_arr,type_arr,
            image_arr, email_arr, chef_id_arr, lat_arr, log_arr, delivery_arr,c_fcm_arr;

    ACProgressFlower dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_chef_lists);


        base_url = new Base_url();



        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


        name_arr = new ArrayList();
        phone_arr = new ArrayList();
        address_arr = new ArrayList();
        rating_arr  = new ArrayList();
        c_fcm_arr = new ArrayList();
        type_arr = new ArrayList();
        image_arr = new ArrayList();
        email_arr = new ArrayList();
        chef_id_arr = new ArrayList();
        delivery_arr = new ArrayList();

        lat_arr = new ArrayList();
        log_arr = new ArrayList();
        distance_between_arr = new ArrayList();

        back = (ImageView)findViewById(R.id.back);


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

        msg = (TextView)findViewById(R.id.msg);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new CheflistAdapter(getApplicationContext(),albumlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);

        getconfig();
        meal_id = getIntent().getStringExtra("meal_id");
        food_name = getIntent().getStringExtra("food_name");
        food_price = getIntent().getStringExtra("food_price");
        food_image = getIntent().getStringExtra("food_image");
        food_items = getIntent().getStringExtra("food_items");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  u_phone = sp.getString("u_phone","no data");

        get_cheflist(meal_id, u_phone );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void send_request(){

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String  u_address = sp.getString("u_address","no data");
        final String  u_name = sp.getString("u_name","no data");
        final String  u_phone = sp.getString("u_phone","no data");



        String url = base_url.base_url +"send_request.php";

        RequestParams RP = new RequestParams();
        RP.put("phone", u_phone);
        RP.put("name", u_name);
        RP.put("address", u_address);
        RP.put("lat", currentLatitude);
        RP.put("log", currentLongitude);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());


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

    public void getconfig(){
        try {
            JSONObject obj = new JSONObject(base_url.config);

            delivery_price = Integer.parseInt(obj.getString("delivery_price"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void get_cheflist(String meal_id, String phone){
        dialog.show();
        msg.setVisibility(View.GONE);
        String url = base_url.base_url +"chef_list.php";

        RequestParams RP = new RequestParams();
        RP.put("meal_id", meal_id);
        RP.put("phone", phone);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                //   Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {

                    String name = "";
                    String phone = "";
                    String address = "";
                    String rating = "";

                    String image = "";
                    String email = "";
                    String chef_id = "";
                    String lat = "";
                    String log = "";
                    String c_fcm = "";
                    String type = "";
                    JSONArray ja = response.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){

                        JSONObject jo = ja.getJSONObject(i);

                        if (!jo.isNull("name")){
                            name = jo.getString("name");
                        }
                        if (!jo.isNull("phone")){
                            phone = jo.getString("phone");
                        }

                        if (!jo.isNull("address")){
                            address = jo.getString("address");
                        }
                        if (!jo.isNull("rating")){
                            rating = jo.getString("rating");
                        }
                        if (!jo.isNull("type")){
                            type = jo.getString("type");
                        }

                        if (!jo.isNull("c_fcm")){
                            c_fcm = jo.getString("c_fcm");
                        }



                        if (!jo.isNull("image")){
                            image = jo.getString("image");
                        }
                        if (!jo.isNull("email")){
                            email = jo.getString("email");
                        }
                        if (!jo.isNull("chef_id")){
                            chef_id = jo.getString("chef_id");
                        }


                        if (!jo.isNull("lat")){
                            lat = jo.getString("lat");
                        }
                        if (!jo.isNull("log")){
                            log = jo.getString("log");
                        }


                        if(!lat.equalsIgnoreCase("") || !log.equalsIgnoreCase("")){

                            Location startPoint=new Location("locationA");
                            startPoint.setLatitude(Double.parseDouble(lat));
                            startPoint.setLongitude(Double.parseDouble(log));

                            Location endPoint=new Location("locationA");
                            endPoint.setLatitude(currentLatitude);
                            endPoint.setLongitude(currentLongitude);

                            double distance_between = startPoint.distanceTo(endPoint) / 1000;

                            long an = Math.round(distance_between);
                            int distance_bet = Integer.parseInt(""+an);
                            int  total_delivery = distance_bet * delivery_price;



                           if(distance_bet <= Integer.parseInt(base_url.chef_distance)) {

                                type_arr.add(i, type);
                                name_arr.add(i,name);
                                phone_arr.add(i,phone);
                                address_arr.add(i,address);
                                rating_arr.add(i,rating);
                                delivery_arr.add(i,total_delivery);

                                image_arr.add(i,image);
                                email_arr.add(i,email);
                                chef_id_arr.add(i,chef_id);
                                c_fcm_arr.add(i, c_fcm);
                                distance_between_arr.add(i, ""+distance_bet);



                                CheflistAlbum a = new CheflistAlbum(name_arr.get(i).toString(),phone_arr.get(i).toString(), address_arr.get(i).toString(),
                                        image_arr.get(i).toString(), rating_arr.get(i).toString(), email_arr.get(i).toString(),chef_id_arr.get(i).toString(),
                                        food_name, food_price, food_image,distance_between_arr.get(i).toString(),food_items, delivery_arr.get(i).toString(),
                                        c_fcm_arr.get(i).toString(), type_arr.get(i).toString());

                                albumlist.add(a);


                            }




                        }




                        if(albumlist.size() == 0) {
                            msg.setVisibility(View.VISIBLE);
                            send_request();
                        }




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
             //   Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();

                msg.setVisibility(View.VISIBLE);
                send_request();
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
        });



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

            //Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
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

       // Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
}
