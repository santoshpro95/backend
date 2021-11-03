package goodchef.com.chef_hire.user;

import android.Manifest;
import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
import goodchef.com.chef_hire.LoginFailed_dialog;
import goodchef.com.chef_hire.MainActivity;
import goodchef.com.chef_hire.R;
import android.os.Vibrator;
public class UserLogin extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //Define a request code to send to Google Play services
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    private double currentLongitude;

    Base_url base_url;
    ACProgressFlower dialog;
    SharedPreferences sp;
    SharedPreferences.Editor ee;
    Vibrator v;
    int vibrate_time = 100;
    EditText phone, password;
    TextView login, signup, forgot_pass;
    ImageView close;
    LoginFailed_dialog cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


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


        phone = (EditText)findViewById(R.id.phone);
        password = (EditText)findViewById(R.id.password);

        login = (TextView)findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
        forgot_pass = (TextView)findViewById(R.id.forgot_pass);
        close = (ImageView)findViewById(R.id.close);



        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ee = sp.edit();


        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone_no = phone.getText().toString();
                String pass = password.getText().toString();



                    if(!phone_no.equalsIgnoreCase("")){

                        if(phone_no.length() == 10){
                            if(!pass.equalsIgnoreCase("")){
                                check_user_api(phone_no,pass);
                            }else {
                                v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(vibrate_time);
                                Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                                password.startAnimation(shake);
                                password.setError("Enter Password");
                            }
                        }
                        else {
                            phone.setError("Enter 10 digit Phone Number");
                            v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(vibrate_time);
                            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                            phone.startAnimation(shake);
                        }

                    }else {
                        phone.setError("Enter Phone Number");
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        phone.startAnimation(shake);

                    }




            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), UserRegisterScreen.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


    }



    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }

    public void check_user_api(String phone, String password){
        dialog.show();
        String url = base_url.base_url +"check_user.php";

        RequestParams RP = new RequestParams();
        RP.put("phone", phone);
        RP.put("password", password);
        RP.put("fcm",base_url.fcm_token);
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
                    String phone = "";
                    String address = "";
                    String user_type = "";




                    if (!response.isNull("name")){
                        name = response.getString("name");
                    }
                    if (!response.isNull("phone")){
                        phone = response.getString("phone");
                    }

                    if (!response.isNull("address")){
                        address = response.getString("address");
                    }
                    if (!response.isNull("user_type")){
                        user_type = response.getString("user_type");
                    }





                    ee.putString("u_name",name);
                    ee.putString("u_phone",phone);
                    ee.putString("u_address",address);


                    ee.apply();
                    dialog.dismiss();


                    if(user_type.equalsIgnoreCase("old")){
                        go_to_home();
                    }else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        show_message();
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
    public void show_message(){

        cd   = new LoginFailed_dialog(UserLogin.this);
        cd.show();
        cd.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cd.dismiss();
            }
        });
        cd.setCanceledOnTouchOutside(false);

    }

    public void go_to_home(){
        Intent i = new Intent(getApplicationContext(), User_HomeScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
