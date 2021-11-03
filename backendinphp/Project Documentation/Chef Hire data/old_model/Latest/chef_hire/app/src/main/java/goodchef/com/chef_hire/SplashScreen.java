package goodchef.com.chef_hire;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.iid.FirebaseInstanceId;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import goodchef.com.chef_hire.chef.Chef_HomeScreen;
import goodchef.com.chef_hire.delivery.DeliveryScreen;
import goodchef.com.chef_hire.user.User_HomeScreen;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SplashScreen extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 23;
    private int requestCode;
    private String[] permissions;
    private int[] grantResults;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    String current_date;
    Base_url base_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        base_url = new Base_url();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
        current_date = df.format(c.getTime());
    }


    public void init(){

        base_url.fcm_token = FirebaseInstanceId.getInstance().getToken();
        get_config();


      }



            public void goTo_screen(){

                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
                String data = sp.getString("screen", "no data");

                if(data.equalsIgnoreCase("no data")){
                    go_to_intro();
                }
                else if(data.equalsIgnoreCase("chef")){
                    go_to_chef_dashboard();
                }
                else if(data.equalsIgnoreCase("user")){
                    go_to_user_dashboard();

                }
                else if(data.equalsIgnoreCase("delivery")){
                    go_to_delivery_dashboard();

                }
                else if(data.equalsIgnoreCase("new")){
                    go_to_dashboard();

                }

            }


    public void  get_config(){

        String url = base_url.base_url +"config.json?test="+current_date;
        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                base_url.config = response.toString();

                try {
                    JSONObject status = response.getJSONObject("status");
                    JSONArray tiffin = status.getJSONArray("tiffin");
                    JSONArray food = status.getJSONArray("food");



                    JSONObject active = tiffin.getJSONObject(0);
                    String tiffin_active = active.getString("status");
                    JSONObject cancel = tiffin.getJSONObject(1);
                    String tiffin_cancel = cancel.getString("status");

                    JSONObject cook = food.getJSONObject(0);
                    String food_cook = cook.getString("status");
                    JSONObject ride = food.getJSONObject(1);
                    String food_ride = ride.getString("status");
                    JSONObject f_cancel = food.getJSONObject(2);
                    String food_cancel = f_cancel.getString("status");
                    JSONObject deli = food.getJSONObject(3);
                    String food_delivered = deli.getString("status");

                    String food_commision = response.getString("food_commision");
                    String tiffin_commision = response.getString("tiffin_commision");
                    String state_arr = response.getString("state");
                    String help_line = response.getString("help_line");

                    String delivery_distance = response.getString("delivery_distance");
                    String delivery_time = response.getString("delivery_time");
                    String location = response.getString("location");


                    base_url.location = location;
                    base_url.delivery_time = delivery_time;
                    base_url.chef_distance = delivery_distance;
                    base_url.help_line = help_line;
                    base_url.state = state_arr;
                    base_url.tiffin_active = tiffin_active;
                    base_url.tiffin_cancel= tiffin_cancel;

                    base_url.food_cook = food_cook;
                    base_url.food_ride = food_ride;
                    base_url.food_cancel = food_cancel;
                    base_url.food_delivered = food_delivered;
                    base_url.food_commision = food_commision;
                    base_url.tiffin_commision = tiffin_commision;

                    get_all_foods();
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




public void  get_chef_foods(){

    String url = base_url.base_url +"show_all_foods.php?test="+current_date;
    RequestParams RP = new RequestParams();

    AsyncHttpClient ac = new AsyncHttpClient();
    ac.post(url, RP, new JsonHttpResponseHandler() {

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            super.onSuccess(statusCode, headers, response);
            Log.i("responce =", response.toString());


            base_url.chef_all_foods = response.toString();
            get_chef_products();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            super.onFailure(statusCode, headers, responseString, throwable);
            //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
        }
    });



}





    public void  get_chef_products(){

        String url = base_url.base_url +"all_chef_products.php?test="+current_date;
        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                base_url.all_chef_products = response.toString();
                goTo_screen();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });



    }
    public void get_all_tiffin(){

        String url = base_url.base_url +"service/all_service.php?test="+current_date;
        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());


                base_url.user_tiffin = response.toString();

                get_chef_foods();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });





    }

    public void get_all_foods(){

        String url = base_url.base_url +"food_details.php?test="+current_date;
        RequestParams RP = new RequestParams();

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                base_url.user_home_food = response.toString();

                get_all_tiffin();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });


    }




    @Override
    protected void onStart() {
        super.onStart();


        if (checkPlayServices()) {

            if(isLocationEnabled(getApplicationContext())){
                if (checkPermission2()) {

                    init();
                }
                else {

                    requestPermission();
                }
            }
            else{
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        }



    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }


    private boolean checkPermission2() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);

        return result == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        this.requestCode = requestCode;
        this.permissions = permissions;
        this.grantResults = grantResults;


        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted){

                        init();
                    }else {


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }
                    }
                }

                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashScreen.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }



    public void go_to_user_dashboard(){
        Intent i = new Intent(getApplicationContext(), User_HomeScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }


    public void go_to_chef_dashboard(){
        Intent i = new Intent(getApplicationContext(), Chef_HomeScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void go_to_delivery_dashboard(){

        Intent i = new Intent(getApplicationContext(), DeliveryScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void go_to_intro(){
        Intent i = new Intent(getApplicationContext(), IntroScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void go_to_dashboard(){

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

    }
}
