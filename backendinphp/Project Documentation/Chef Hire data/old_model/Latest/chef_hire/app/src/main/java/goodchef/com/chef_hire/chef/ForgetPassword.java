package goodchef.com.chef_hire.chef;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;

public class ForgetPassword extends AppCompatActivity {
    ImageView close;
    LinearLayout first, second, third;
    EditText phone, otp, new_password1, new_password2;
    TextView submit, req_otp, check_otp, msg;
    Base_url base_url;
    ACProgressFlower dialog;
    Vibrator v;
    int vibrate_time = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        close = (ImageView)findViewById(R.id.close);
        first = (LinearLayout)findViewById(R.id.first);
        second = (LinearLayout)findViewById(R.id.second);
        third = (LinearLayout)findViewById(R.id.third);

        phone = (EditText)findViewById(R.id.phone);
        otp = (EditText)findViewById(R.id.otp);
        new_password1 = (EditText)findViewById(R.id.new_password1);
        new_password2 = (EditText)findViewById(R.id.new_password2);

        submit = (TextView)findViewById(R.id.submit);
        req_otp = (TextView)findViewById(R.id.req_otp);
        check_otp = (TextView)findViewById(R.id.check_otp);
        msg = (TextView)findViewById(R.id.msg);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        first.setVisibility(View.VISIBLE);
        second.setVisibility(View.GONE);
        third.setVisibility(View.GONE);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String pass1 = new_password1.getText().toString();
                String pass2 = new_password2.getText().toString();


                if(!pass1.equalsIgnoreCase("")){

                    if(!pass2.equalsIgnoreCase("")){

                        if(pass2.equalsIgnoreCase(pass1)){
                            change_pass();

                        }else {
                            v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                            v.vibrate(vibrate_time);
                            new_password2.setError("Enter same password");
                            Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                            new_password2.startAnimation(shake);
                        }

                    }else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        new_password2.setError("Enter new Password");
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        new_password2.startAnimation(shake);
                    }

                }else {
                    v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(vibrate_time);
                    new_password1.setError("Enter new Password");
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    new_password1.startAnimation(shake);
                }


            }
        });

        check_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_otp_api();
            }
        });

        req_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone_no = phone.getText().toString();

                if(!phone_no.equalsIgnoreCase("")){

                    if(phone_no.length() == 10){
                        re_otp(phone_no);

                    }else {
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);
                        phone.setError("Enter 10 digit phone no");
                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        phone.startAnimation(shake);
                    }
                }
                else {
                    v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(vibrate_time);
                    phone.setError("Enter phone no");
                    Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                    phone.startAnimation(shake);
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


    public void change_pass(){

        final String pass = new_password1.getText().toString();
        final String phone_no = phone.getText().toString();

        dialog.show();
        String url = base_url.base_url+"chef_forgot_password.php";

        RequestParams RP = new RequestParams();
        RP.put("phone", phone_no);
        RP.put("password", pass);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());


                try {
                    String success = response.getString("success");
                    if(success.equalsIgnoreCase("true")){

                        Toast.makeText(getApplicationContext(), "Password Updated", Toast.LENGTH_LONG).show();
                        first.setVisibility(View.VISIBLE);
                        second.setVisibility(View.GONE);
                        third.setVisibility(View.GONE);
                    }else {
                        Toast.makeText(getApplicationContext(), "This number is not registered", Toast.LENGTH_LONG).show();
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
                dialog.dismiss();
            }
        });



    }

    public void check_otp_api(){

        dialog.show();
        String url = "https://control.msg91.com/api/verifyRequestOTP.php";

        RequestParams RP = new RequestParams();
        RP.put("authkey", base_url.otp_auth_key);
        RP.put("mobile", "+91"+phone.getText().toString());
        RP.put("otp", otp.getText().toString());

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.get(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());

                try {
                    String type = response.getString("type");

                    if(type.equalsIgnoreCase("success")){

                        first.setVisibility(View.GONE);
                        second.setVisibility(View.GONE);
                        third.setVisibility(View.VISIBLE);

                    }
                    else {
                        otp.setError("Wrong OTP");
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);

                        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                        otp.startAnimation(shake);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }

    public void re_otp(final String phone){
        dialog.show();

        String url = "http://control.msg91.com/api/sendotp.php";

        RequestParams RP = new RequestParams();
        RP.put("authkey", base_url.otp_auth_key);
        RP.put("message", "Your verification code is ##OTP##");
        RP.put("sender", "Chefooz");
        RP.put("mobile", "+91"+phone);

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

                        first.setVisibility(View.GONE);
                        second.setVisibility(View.VISIBLE);
                        third.setVisibility(View.GONE);
                        msg.setText("OTP send to "+phone);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Otp failed ! Try Again", Toast.LENGTH_LONG).show();
                        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(vibrate_time);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), ChefLogin.class);
        startActivity(i);
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
