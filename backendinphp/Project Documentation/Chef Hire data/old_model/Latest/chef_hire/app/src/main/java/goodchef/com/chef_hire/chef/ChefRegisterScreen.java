package goodchef.com.chef_hire.chef;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import org.json.JSONException;
import org.json.JSONObject;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.MainActivity;
import goodchef.com.chef_hire.R;

public class ChefRegisterScreen extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    EditText name, address, email,password, phone;
    TextView go ;
    LinearLayout  home,resturant;
    ImageView close, r_tick, h_tick;

    Base_url base_url;
    ACProgressFlower dialog;
    SharedPreferences sp;
    SharedPreferences.Editor ee;
    Vibrator v;
    int vibrate_time = 100;
    int chef_type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_register_screen);


        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        name = (EditText) findViewById(R.id.name);
        phone = (EditText)findViewById(R.id.phone);
        r_tick = (ImageView)findViewById(R.id.r_tick);
        h_tick = (ImageView)findViewById(R.id.h_tick);
        home = (LinearLayout)findViewById(R.id.home);
        resturant = (LinearLayout)findViewById(R.id.resturant);
        address = (EditText)findViewById(R.id.address);
        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        close = (ImageView)findViewById(R.id.close);

        go = (TextView) findViewById(R.id.go);


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
        ee = sp.edit();


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chef_type = 1;
                h_tick.setVisibility(View.VISIBLE);
                r_tick.setVisibility(View.GONE);
                v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(vibrate_time);
            }
        });

        resturant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chef_type = 0;
                h_tick.setVisibility(View.GONE);
                r_tick.setVisibility(View.VISIBLE);
                v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(vibrate_time);
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String f_name = name.getText().toString();
                String addr = address.getText().toString();
                String chef_email = email.getText().toString();
                String pass = password.getText().toString();
                String phone_no = phone.getText().toString();


                if(!f_name.equalsIgnoreCase("") ) {

                    if (!addr.equalsIgnoreCase("")) {

                        if (!pass.equalsIgnoreCase("")) {

                            if (!phone_no.equalsIgnoreCase("")) {

                             if(phone_no.length() == 10){
                                 String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                                 if (!chef_email.equalsIgnoreCase("") ) {

                                     if(chef_email.matches(emailPattern)){
                                         send_otp(phone_no);
                                     }
                                     else {
                                         v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                         v.vibrate(vibrate_time);
                                         email.setError("Enter valid Email id");
                                         Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                                         email.startAnimation(shake);
                                     }


                                 } else {
                                     v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                     v.vibrate(vibrate_time);
                                     email.setError("Enter valid Email id");
                                     Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                                     email.startAnimation(shake);
                                 }

                             }else {
                                 v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                 v.vibrate(vibrate_time);
                                 phone.setError("Enter 10 digit phone no");
                                 Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                                 phone.startAnimation(shake);
                             }
                            }  else {
                                v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(vibrate_time);
                                phone.setError("Enter phone no");
                                Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                                phone.startAnimation(shake);
                            }
                        }   else{
                            v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(vibrate_time);
                            password.setError("Enter Password");
                            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                            password.startAnimation(shake);
                        }
                    }    else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        address.setError("Enter Address");
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        address.startAnimation(shake);
                    }
                }   else{
                    v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(vibrate_time);
                    name.setError("Enter Name");
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    name.startAnimation(shake);
                }




            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChefLogin.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


    }

    public void send_otp(final String phone_no){

        //http://control.msg91.com/api/sendotp.php?authkey=232556AXCzb7PpS5b796451&message=Your verification code is ##OTP##&sender=Checkchef&mobile=+918826417738

dialog.show();
        String url = "http://control.msg91.com/api/sendotp.php";

        RequestParams RP = new RequestParams();
        RP.put("authkey", base_url.otp_auth_key);
        RP.put("message", "Your verification code is ##OTP##");
        RP.put("sender", "Chefooz");
        RP.put("mobile", "+91"+phone_no);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.get(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());
                dialog.dismiss();

                try {
                    String type = response.getString("type");

                    if(type.equalsIgnoreCase("success")){

                        open_alert_box(phone_no);
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Otp failed ! Try Again", Toast.LENGTH_LONG).show();
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        dialog.dismiss();
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

    public void open_alert_box(final String phone_no){


        AlertDialog.Builder builder = new AlertDialog.Builder(ChefRegisterScreen.this);
        builder.setTitle("Enter OTP send to "+phone_no);

// Set up the input
        final EditText input = new EditText(ChefRegisterScreen.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText("");
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                check_otp(phone_no, input.getText().toString());

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


    public void check_otp(final String phone_no, String otp){
//https://control.msg91.com/api/verifyRequestOTP.php?authkey=2&mobile=as&otp=sd


        dialog.show();
        String url = "https://control.msg91.com/api/verifyRequestOTP.php";

        RequestParams RP = new RequestParams();
        RP.put("authkey", base_url.otp_auth_key);
        RP.put("mobile", "+91"+phone_no);
        RP.put("otp", otp);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.get(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());

                try {
                    String type = response.getString("type");
                    if(type.equalsIgnoreCase("success")) {

                        save_api(phone.getText().toString(),name.getText().toString(), address.getText().toString(),
                                email.getText().toString(),password.getText().toString());
                    }
                    else {

                        Toast.makeText(getApplicationContext(), "Wrong OTP", Toast.LENGTH_LONG).show();
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        dialog.dismiss();
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


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), ChefLogin.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }

    public void save_api(String phonee, String name, String addr, String email, String password){

        String url = base_url.base_url +"chef_reg.php";

        RequestParams RP = new RequestParams();
        RP.put("phone", phonee);
        RP.put("name", name);
        RP.put("address", addr);
        RP.put("fcm", base_url.fcm_token);
        RP.put("password", password);
        RP.put("email", email);
        RP.put("type", ""+chef_type);
        RP.put("lat", ""+currentLatitude);
        RP.put("log", ""+currentLongitude);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    String name = "";
                    String phone_no = "";
                    String address = "";
                    String email = "";
                    String chef_id = "";
                    String user = "";


                    if (!response.isNull("name")){
                        name = response.getString("name");
                    }
                    if (!response.isNull("phone")){
                        phone_no = response.getString("phone");
                    }

                    if (!response.isNull("address")){
                        address = response.getString("address");
                    }
                    if (!response.isNull("email")){
                        email = response.getString("email");
                    }
                    if (!response.isNull("chef_id")){
                        chef_id = response.getString("chef_id");
                    }

                    if (!response.isNull("user")){
                        user = response.getString("user");
                    }

                    if(user.equalsIgnoreCase("new")){
                        ee.putString("c_name",name);
                        ee.putString("c_phone",phone_no);
                        ee.putString("c_address",address);
                        ee.putString("c_email",email);
                        ee.putString("chef_id",chef_id);

                        ee.apply();

                        Intent i = new Intent(getApplicationContext(), Chef_HomeScreen.class);
                        startActivity(i);
                        finish();
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    }
                    else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        phone.setError("This number is already used.");
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        phone.startAnimation(shake);
                    }


                    dialog.dismiss();

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

        //Toast.makeText(this, currentLatitude + " WORKS " + currentLongitude + "", Toast.LENGTH_LONG).show();
    }
}
