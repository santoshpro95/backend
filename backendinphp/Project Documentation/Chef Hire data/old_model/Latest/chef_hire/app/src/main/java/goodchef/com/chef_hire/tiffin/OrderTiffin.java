package goodchef.com.chef_hire.tiffin;

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
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.CheckoutScreen;
import goodchef.com.chef_hire.user.OrderDetailsScreen;
import goodchef.com.chef_hire.user.order_placed_dialog;

public class OrderTiffin extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    Button order_placed, view_menu;
    TextView edit_address, address_sh, namee, pricee,
            b_price, l_price, d_price, s_date, e_date;
    SharedPreferences sp;
    String  u_name,u_phone,u_address;

    ACProgressFlower dialog;
    Base_url base_url;
    ImageView imagee, back,call;


    CheckBox veg_only;
    Spinner no_days;
    ArrayList days_arr, no_days_arr;
    CheckBox breakfast,lunch,dinner;


    int meal_price = 0;
    int total = 0;
    int day = 0;
    int veg = 0;

    int break_fast = 0;
    int lun_ch = 0;
    int dinn_er = 0;
    String start_date;


    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;
    order_placed_dialog cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_tiffin);

        edit_address = (TextView)findViewById(R.id.edit_address);
        address_sh = (TextView)findViewById(R.id.address);
        namee = (TextView)findViewById(R.id.name);
        pricee = (TextView)findViewById(R.id.price);
        b_price = (TextView)findViewById(R.id.b_price);
        l_price = (TextView)findViewById(R.id.l_price);
        s_date = (TextView)findViewById(R.id.s_date);
        e_date = (TextView)findViewById(R.id.e_date);
        d_price = (TextView)findViewById(R.id.d_price);

        imagee = (ImageView)findViewById(R.id.image);
        call = (ImageView)findViewById(R.id.call);
        back = (ImageView)findViewById(R.id.back);
        no_days = (Spinner)findViewById(R.id.no_days);
        veg_only = (CheckBox)findViewById(R.id.veg_only);

        breakfast = (CheckBox)findViewById(R.id.breakfast);
        lunch = (CheckBox)findViewById(R.id.lunch);
        dinner = (CheckBox)findViewById(R.id.dinner);

        order_placed = (Button)findViewById(R.id.order_placed);
        view_menu= (Button)findViewById(R.id.view_menu);

        days_arr = new ArrayList();
        no_days_arr = new ArrayList();
        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        final String name = getIntent().getStringExtra("name");
        final String s_phone = getIntent().getStringExtra("phone");
        String breakfast_p = getIntent().getStringExtra("breakfast");
        String lunch_p = getIntent().getStringExtra("lunch");
        String dinner_p = getIntent().getStringExtra("dinner");
        String image = getIntent().getStringExtra("image");
        final String get_s_id = getIntent().getStringExtra("s_id");
        final String service_addr = getIntent().getStringExtra("service_addr");

        getconfig();

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
               R.layout.choose_days, no_days_arr);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        no_days.setAdapter(adp1);

        order_placed.setTextColor(getResources().getColor(R.color.white));
        order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
        order_placed.setEnabled(false);

        l_price.setText(lunch_p);
        b_price.setText(breakfast_p);
        d_price.setText(dinner_p);

        pricee.setText(""+total);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = s_phone;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        veg_only.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    veg = 1;
                }
                else {
                    veg = 0;
                }

            }
        });


        breakfast.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b){
                    meal_price = meal_price + Integer.parseInt(b_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    break_fast = 1;

                }else {
                    meal_price = meal_price - Integer.parseInt(b_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    break_fast = 0;
                }

                pricee.setText(""+total);
                if(total == 0){
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    order_placed.setEnabled(false);
                }
                else {
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                    order_placed.setEnabled(true);
                }
            }
        });


        lunch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    meal_price = meal_price + Integer.parseInt(l_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    lun_ch = 1;
                }
                else {
                    meal_price = meal_price - Integer.parseInt(l_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    lun_ch = 1;
                }

                pricee.setText(""+total);
                if(total == 0){
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    order_placed.setEnabled(false);
                }
                else {
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                    order_placed.setEnabled(true);
                }
            }
        });

        dinner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                if(b){
                    meal_price = meal_price + Integer.parseInt(d_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    dinn_er = 1;
                }

                else {
                    meal_price = meal_price - Integer.parseInt(d_price.getText().toString());
                    total = 0;
                    total = meal_price * day;
                    dinn_er = 0;

                }
                pricee.setText(""+total);
                if(total == 0){
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.light_grey));
                    order_placed.setEnabled(false);
                }
                else {
                    order_placed.setTextColor(getResources().getColor(R.color.white));
                    order_placed.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.color.colorPrimary));
                    order_placed.setEnabled(true);
                }
            }
        });




        no_days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub


                day = Integer.parseInt(days_arr.get(position).toString());

                if(position == 0){
                    total = 0;
                    total = meal_price * day;
                }
                else if(position == 1){
                    total = 0;
                    total = meal_price * day;
                }
                else {
                    total = 0;
                    total = meal_price * day;
                }

                pricee.setText(""+total);


                /*-----------------------------------current date and time----------------------------------------*/

                Calendar c = Calendar.getInstance();
                System.out.println("Current time => " + c.getTime());
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                String current_date = df.format(c.getTime());
                Date date = null;
                try {
                    date = df.parse(current_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*----------------------------------------delivery time-----------------------------------*/


                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(date);
                calendar1.add(Calendar.DATE, 1);
                String deivery_date = df.format(calendar1.getTime());
                Date newDate2= null;
                try {
                    newDate2 = df.parse(deivery_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*----------------------------------------ending time-----------------------------------*/

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, day);
                String end_date = df.format(calendar.getTime());
                Date newDate= null;
                try {
                    newDate = df.parse(end_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                /*----------------------------------------set time-----------------------------------*/

                SimpleDateFormat df2 = new SimpleDateFormat("dd MMM YYYY");
                String ending_date = df2.format(newDate);
                String starting_date = df2.format(newDate2);

                e_date.setText(ending_date);
                s_date.setText(starting_date);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {





            }
        });


        Glide.with(getApplicationContext()).load(image)
                .into(imagee);
        namee.setText(name);

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

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        u_address = sp.getString("u_address","no data");
        u_name = sp.getString("u_name","no data");
        u_phone = sp.getString("u_phone","no data");

        address_sh.setText(u_address);

        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_popup();
            }
        });

        order_placed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    Calendar c = Calendar.getInstance();
                    System.out.println("Current time => " + c.getTime());
                    SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
                    String current_date = df.format(c.getTime());
                    Date date = df.parse(current_date);

                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(date);
                    calendar1.add(Calendar.DATE, 1);
                    String deivery_date = df.format(calendar1.getTime());

                    start_date = deivery_date;

                    /*getting delivery address */


                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendar.add(Calendar.DATE, day);
                    String end_date = df.format(calendar.getTime());


                    order_service(get_s_id,u_phone,  start_date, name, end_date, ""+day, service_addr, s_phone);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


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

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void getconfig(){
        try {
            JSONObject obj = new JSONObject(base_url.config);

            JSONObject jo = obj.getJSONObject("tiffin");

            JSONArray ja = jo.getJSONArray("day");

            for(int i =0; i<ja.length(); i++){

                JSONObject frequency = ja.getJSONObject(i);
                String day = frequency.getString("frequency");

                days_arr.add(i, day);
                no_days_arr.add(i, day+" days");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void open_popup(){

        AlertDialog.Builder builder = new AlertDialog.Builder(OrderTiffin.this);
        builder.setTitle("Enter New Address");

// Set up the input
        final EditText input = new EditText(OrderTiffin.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText(address_sh.getText().toString());
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




    public void order_service(final String s_id, final String u_phone, String time, String name,
                              String end_date, String days, String pickup_addr, String service_phone){
        dialog.show();
        String url = base_url.base_url +"service/order_service.php";

        RequestParams RP = new RequestParams();
        RP.put("s_id", s_id);
        RP.put("time", time);
        RP.put("phone", u_phone);
        RP.put("veg_only", ""+veg);
        RP.put("lat", ""+currentLatitude);
        RP.put("log", ""+currentLongitude);

        RP.put("breakfast", ""+break_fast);
        RP.put("lunch", ""+lun_ch);
        RP.put("dinner", ""+dinn_er);

        RP.put("user_name", ""+u_name);
        RP.put("s_phone", ""+service_phone);

        RP.put("days", days);
        RP.put("name", name);
        RP.put("price",pricee.getText().toString() );

        RP.put("status", base_url.tiffin_active);
        RP.put("address", address_sh.getText().toString());
        RP.put("end_date", end_date);
        RP.put("pickup_addr", pickup_addr);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());
                dialog.dismiss();

                try {
                    String s_id = response.getString("s_id");
                    String order_id = response.getString("order_id");
                    show_message(s_id, order_id);

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

    public void show_message(final String s_id, final String order_id){


        cd   = new order_placed_dialog(OrderTiffin.this);
        cd.show();
        cd.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cd.dismiss();

                Intent i = new Intent(getApplicationContext(), MyTiffinDetails.class);
                i.putExtra("s_id", s_id);
                i.putExtra("order_id", order_id);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });
        cd.setCanceledOnTouchOutside(false);

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
                address_sh.setText(address);


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
