package goodchef.com.chef_hire.delivery;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.Legal_PrivacyPolicy;
import goodchef.com.chef_hire.MainActivity;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.Chef_HomeScreen;

/**
 * Created by santo on 8/11/2018.
 */

public class Delivery_Account extends Fragment {



    public static Delivery_Account newInstance() {
        Delivery_Account fragment = new Delivery_Account();
        return fragment;
    }

    TextView name, phone, welcome,logout, address;
    ImageView  call, back;
    LinearLayout legal;

    Base_url base_url;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.delivery_account, container, false);
        base_url = new Base_url();

        name = (TextView)view.findViewById(R.id.name);
        welcome = (TextView)view.findViewById(R.id.welcome);
        phone = (TextView)view.findViewById(R.id.phone);
        address = (TextView)view.findViewById(R.id.address);
        legal = (LinearLayout)view.findViewById(R.id.legal);
        call = (ImageView)view.findViewById(R.id.call);
        back = (ImageView)view.findViewById(R.id.back);
        logout = (TextView) view.findViewById(R.id.logout);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String  delivery_name = sp.getString("d_name","no data");
        final String  delivery_address = sp.getString("d_address","no data");
        final String  delivery_phone = sp.getString("d_phone","no data");

        name.setText(delivery_name);
        welcome.setText("Welcome "+delivery_name);
        address.setText(delivery_address);
        phone.setText(delivery_phone);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), DeliveryScreen.class);
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

        return view;
    }



    @Override
    public void onStart() {
        super.onStart();


    }
}
