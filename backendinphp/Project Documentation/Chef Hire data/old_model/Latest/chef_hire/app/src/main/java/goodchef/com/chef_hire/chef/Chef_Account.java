package goodchef.com.chef_hire.chef;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.Legal_PrivacyPolicy;
import goodchef.com.chef_hire.MainActivity;
import goodchef.com.chef_hire.R;

/**
 * Created by santo on 8/11/2018.
 */

public class Chef_Account extends Fragment {



    public static Chef_Account newInstance() {
        Chef_Account fragment = new Chef_Account();
        return fragment;
    }

    private String new_name = "";
    private String  new_addr = "";
    Spinner available;
    TextView name, info, edit_name, welcome,logout,
            edit_address, address, choose_foods;
    ImageView call, back;
    String c_name;

    ImageView image;
    RelativeLayout choose_food_cook, my_orders;
    ArrayList available_arr, data;
    LinearLayout legal;
    SharedPreferences sp;
    SharedPreferences.Editor ee;
    Base_url base_url;
    Vibrator v;
    int vibrate_time = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.chef_account, container, false);

        name = (TextView)view.findViewById(R.id.name);
        welcome = (TextView)view.findViewById(R.id.welcome);
        info = (TextView)view.findViewById(R.id.info);
        address = (TextView)view.findViewById(R.id.address);
        legal = (LinearLayout)view.findViewById(R.id.legal);
        call = (ImageView)view.findViewById(R.id.call);
        back = (ImageView)view.findViewById(R.id.back);
        image = (ImageView)view.findViewById(R.id.image);
        choose_food_cook = (RelativeLayout)view.findViewById(R.id.choose_food_cook);
        my_orders = (RelativeLayout)view.findViewById(R.id.my_orders);
        edit_address = (TextView)view.findViewById(R.id.edit_address);
        logout = (TextView) view.findViewById(R.id.logout);
        choose_foods = (TextView)view.findViewById(R.id.choose_foods);

        available = (Spinner)view.findViewById(R.id.available);

        sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ee = sp.edit();
        final String  chef_id = sp.getString("chef_id","no data");
        final String  chef_name = sp.getString("c_name","no data");
        final String  chef_address = sp.getString("c_address","no data");

        edit_name = (TextView) view.findViewById(R.id.edit_name);
        available_arr = new ArrayList();
        data = new ArrayList();

        base_url = new Base_url();


        available_arr.add(0,"Busy");
        available_arr.add(1, "Availabe");
        available_arr.add(2, "Not Available");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getActivity(),
                R.layout.choose_days, available_arr);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        available.setAdapter(adp1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), Chef_HomeScreen.class);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });
        legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), Legal_PrivacyPolicy.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phone = base_url.help_line;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        choose_food_cook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(),  ChooseFood.class);
                i.putExtra("choose",choose_foods.getText().toString());
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });

        my_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(),  MyOrder_lists.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });

        edit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Enter New Address");

// Set up the input
                final EditText input = new EditText(getActivity());
                input.setText(chef_address);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_addr = input.getText().toString();
                        update_api(chef_name, chef_id,new_addr );

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

        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Enter New Name");

// Set up the input
                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_TEXT );
                input.setText(chef_name);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new_name = input.getText().toString();
                        update_api(new_name, chef_id,chef_address );

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

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
                SharedPreferences.Editor ee = sp.edit();
                ee.remove("screen");
                ee.commit();
                Intent i = new Intent(getActivity(),MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });




        available.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i ==0){ // busy

                    update_status("0",chef_id );
                }
                else if(i ==1){ // available
                    update_status("1",chef_id );



                }else {  // not availabele
                    update_status("2",chef_id );
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        return view;
    }


    public void get_meal_names(String chef_id){

        data.clear();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");

        String url = base_url.base_url +"check_foods_status.php?test="+df.format(c.getTime());
        RequestParams RP = new RequestParams();
        RP.put("chef_id", chef_id);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());
                //   Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONArray ja = response.getJSONArray("data");

                    for(int j=0; j<ja.length(); j++){
                        JSONObject jo = ja.getJSONObject(j);
                        String name = jo.getString("name");
                        data.add(j,name);
                    }

                    String all_foods = TextUtils.join(", ", data);
                    choose_foods.setText(all_foods);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();

                choose_foods.setText("You did not choose any foods");
                v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(vibrate_time);
                Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
                choose_food_cook.startAnimation(shake);
            }
        });




    }



    public void update_api(final String name, final String chef_id, final String address){

        String url = base_url.base_url +"chef_account_update.php";

        RequestParams RP = new RequestParams();
        RP.put("name", name);
        RP.put("address", address);
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i(" update responce =", response.toString());


                ee.putString("c_name",name);
                ee.putString("c_address",address);
                ee.apply();

                check_verification(chef_id);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //  Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void update_status(final String chef_status, String chef_id){


        String url = base_url.base_url +"change_chef_status.php";

        RequestParams RP = new RequestParams();
        RP.put("available", chef_status);
        RP.put("chef_id", chef_id);


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




    public void check_verification(final String chef_id){



        String url = base_url.base_url +"chef_details.php";

        RequestParams RP = new RequestParams();
        RP.put("chef_id", chef_id);


        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("login responce =", response.toString());

                // Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    JSONArray ja = response.getJSONArray("data");
                    JSONObject jo = ja.getJSONObject(0);


                    String chef_name = "";
                    String chef_phone = "";
                    String chef_email = "";
                    String chef_image = "";
                    String verify = "";
                    String status = "";
                    String rating = "";
                    String distance = "";
                    String get_address = "";

                    if (!jo.isNull("name")){
                        chef_name = jo.getString("name");
                    }
                    if (!jo.isNull("verify")){
                        verify = jo.getString("verify");
                    }
                    if (!jo.isNull("status")){
                        status = jo.getString("status");
                    }

                    if (!jo.isNull("rating")){
                        rating = jo.getString("rating");
                    }

                    if (!jo.isNull("address")){
                        get_address = jo.getString("address");
                    }

                    if (!jo.isNull("phone")){
                        chef_phone = jo.getString("phone");
                    }
                    if (!jo.isNull("email")){
                        chef_email = jo.getString("email");
                    }
                    if (!jo.isNull("distance")){
                        distance = jo.getString("distance");
                    }

                    if (!jo.isNull("image")){
                        chef_image = jo.getString("image");
                    }


                        Glide.with(getActivity()).load(chef_image)
                                // .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .into(image);


                    if(status.equalsIgnoreCase("0")){

                        available.setSelection(0);
                    }
                    else if(status.equalsIgnoreCase("1")) {
                        available.setSelection(1);
                    }else {
                        available.setSelection(2);
                    }

                    if(verify.equalsIgnoreCase("0")){
                        welcome.setText("Not Verified !");
                        welcome.setTextColor(Color.RED);
                    }
                    else {
                        welcome.setTextColor(Color.BLACK);
                        welcome.setText("Welcome " +chef_name);
                    }

                    c_name = chef_name;
                    name.setText(chef_name+" ("+rating+")");
                    info.setText(chef_phone+"("+chef_email+")");
                    address.setText(get_address);


                    get_meal_names(chef_id);



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
    public void onStart() {
        super.onStart();
        SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String  chef_id = sp2.getString("chef_id","no data");
        check_verification(chef_id);   // check verification

    }
}
