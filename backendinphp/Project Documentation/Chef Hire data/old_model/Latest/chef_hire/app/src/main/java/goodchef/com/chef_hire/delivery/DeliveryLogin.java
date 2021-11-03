package goodchef.com.chef_hire.delivery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

public class DeliveryLogin extends AppCompatActivity {


    EditText id_no;
    TextView login;
    ImageView close;
    Base_url base_url;
    ACProgressFlower dialog;
    SharedPreferences sp;
    SharedPreferences.Editor ee;
    Vibrator v;
    int vibrate_time = 100;
    LoginFailed_dialog cd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_login);


        id_no = (EditText)findViewById(R.id.id_no);
        login = (TextView)findViewById(R.id.login);
        close = (ImageView)findViewById(R.id.close);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ee = sp.edit();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = id_no.getText().toString();

                if(!id.equalsIgnoreCase("")){
                    if(id.length() == 4){
                        check_id(id);
                    }else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        id_no.setError("Enter 4 digit Id number");
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        id_no.startAnimation(shake);
                    }
                }else {
                    v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(vibrate_time);
                    id_no.setError("Enter Id number");
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    id_no.startAnimation(shake);
                }

            }
        });


    }

    public void check_id(String id){


        dialog.show();
        String url = base_url.base_url +"delivery/check_delivery.php";

        RequestParams RP = new RequestParams();
        RP.put("id_no", id);
        RP.put("fcm", base_url.fcm_token);

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

                    if (!response.isNull("user_type")){
                        user_type = response.getString("user_type");
                    }
                    if (!response.isNull("phone")){
                        phone = response.getString("phone");
                    }
                    if (!response.isNull("address")){
                        address = response.getString("address");
                    }
                    if (!response.isNull("name")){
                        name = response.getString("name");
                    }

                    ee.putString("d_name",name);
                    ee.putString("d_phone",phone);
                    ee.putString("d_address",address);

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



    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }


    public void go_to_home(){

        Intent i = new Intent(getApplicationContext(), DeliveryScreen.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public void show_message(){

        cd   = new LoginFailed_dialog(DeliveryLogin.this);
        cd.show();
        cd.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cd.dismiss();
            }
        });
        cd.setCanceledOnTouchOutside(false);


    }
}
