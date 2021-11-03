package goodchef.com.chef_hire.tiffin;

import android.Manifest;
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
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import goodchef.com.chef_hire.tiffin.model.ServicesAdapter;
import goodchef.com.chef_hire.tiffin.model.ServicesAlbum;
import goodchef.com.chef_hire.tiffin.model.StateAdapter;
import goodchef.com.chef_hire.user.User_HomeScreen;

public class TiffinLists extends Fragment implements  StateAdapter.ItemClickListener {


    Base_url base_url;
    ArrayList name_arr, breakfast_arr, lunch_arr, dinner_arr, image_arr,distance_arr,
            info_arr, s_id_arr, phone_arr, address_arr, lat_arr, log_arr, state_arr;
    private ServicesAdapter adapter;
    private StateAdapter stateadapter;
    private List<ServicesAlbum> albumlist;
    ACProgressFlower dialog;
    RecyclerView recyclerView, state_list;
    TextView msg, tiffin;
    ImageView back;



    public static TiffinLists newInstance() {
        TiffinLists fragment = new TiffinLists();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_more_service, container, false);

        msg = (TextView)view.findViewById(R.id.msg);
        back = (ImageView)view.findViewById(R.id.back);




        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


        name_arr = new ArrayList();
        lunch_arr = new ArrayList();
        distance_arr = new ArrayList();
        dinner_arr = new ArrayList();
        address_arr = new ArrayList();
        lat_arr = new ArrayList();
        log_arr = new ArrayList();
        state_arr = new ArrayList();

        breakfast_arr = new ArrayList();
        image_arr  = new ArrayList();
        info_arr = new ArrayList();
        phone_arr = new ArrayList();
        s_id_arr = new ArrayList();

        albumlist = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        state_list = (RecyclerView)view.findViewById(R.id.state_list);
        tiffin = (TextView)view.findViewById(R.id.tiffin);

        adapter = new ServicesAdapter(getActivity(), albumlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        stateadapter = new StateAdapter(getActivity(), state_arr);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        state_list.setLayoutManager(horizontalLayoutManagaer);
        state_list.setItemAnimator(new DefaultItemAnimator());

        stateadapter.setClickListener(this);
        state_list.setAdapter(stateadapter);

        try {
            JSONArray jsonArray = new JSONArray(base_url.state);

            for(int i=0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String state = jsonObject.getString("name");
                state_arr.add(i, state);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        call_all_services("");
        tiffin.setText("All Tiffin Service");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), User_HomeScreen.class);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });


        return view;

    }

    public void call_all_services(String state){
        albumlist.clear();

                try {

                    String name = "";
                    String breakfast = "";
                    String lunch = "";
                    String dinner = "";
                    String phone = "";
                    String image = "";
                    String s_id = "";
                    String info = "";
                    String address = "";
                    String lat = "";
                    String log = "";
                    String get_state = "";

                    JSONObject obj = new JSONObject(base_url.user_tiffin);

                    JSONArray ja = obj.getJSONArray("data");


                    for(int i=0; i<ja.length();i++){


                        JSONObject jo = ja.getJSONObject(i);



                        if (!jo.isNull("name")){
                            name = jo.getString("name");
                        }
                        if (!jo.isNull("breakfast")){
                            breakfast = jo.getString("breakfast");
                        }

                        if (!jo.isNull("lunch")){
                            lunch = jo.getString("lunch");
                        }

                        if (!jo.isNull("dinner")){
                            dinner = jo.getString("dinner");
                        }


                        if (!jo.isNull("image")){
                            image = jo.getString("image");
                        }

                        if (!jo.isNull("s_id")){
                            s_id = jo.getString("s_id");
                        }
                        if (!jo.isNull("info")){
                            info = jo.getString("info");
                        }
                        if (!jo.isNull("address")){
                            address = jo.getString("address");
                        }
                        if (!jo.isNull("lat")){
                            lat = jo.getString("lat");
                        }
                        if (!jo.isNull("log")){
                            log = jo.getString("log");
                        }

                        if (!jo.isNull("state")){
                            get_state = jo.getString("state");
                        }
                        if (!jo.isNull("phone")){
                            phone = jo.getString("phone");
                        }

                        if(get_state.toLowerCase().contains(state.toLowerCase())){

                            double b_profit = Integer.parseInt(breakfast) * (Double.parseDouble(base_url.tiffin_commision) / 100.0);
                            double gross_price = Integer.parseInt(breakfast) + b_profit;
                            long breakfast_final = Math.round(gross_price);
                            breakfast_arr.add(i,""+Integer.parseInt(""+breakfast_final));

                            double l_profit = Integer.parseInt(lunch) * (Double.parseDouble(base_url.tiffin_commision) / 100.0);
                            double gross_price2 = Integer.parseInt(lunch) + l_profit;
                            long lunch_final = Math.round(gross_price2);
                            lunch_arr.add(i, ""+Integer.parseInt(""+lunch_final));

                            double d_profit = Integer.parseInt(dinner) * (Double.parseDouble(base_url.tiffin_commision) / 100.0);
                            double gross_price3 = Integer.parseInt(dinner) + d_profit;
                            long dinner_final = Math.round(gross_price3);
                            dinner_arr.add(i, ""+Integer.parseInt(""+dinner_final));

                            name_arr.add(i,name);
                            address_arr.add(i, address);
                            lat_arr.add(i,lat);
                            log_arr.add(i, log);

                            image_arr.add(i,image);
                            info_arr.add(i,info);
                            s_id_arr.add(i, s_id);
                            phone_arr.add(i, phone );

                            ServicesAlbum a = new ServicesAlbum(name_arr.get(i).toString(),
                                    breakfast_arr.get(i).toString(),  lunch_arr.get(i).toString(), dinner_arr.get(i).toString() ,
                                    image_arr.get(i).toString(), info_arr.get(i).toString(), s_id_arr.get(i).toString(),
                                    phone_arr.get(i).toString(), address_arr.get(i).toString());

                            albumlist.add(a);
                        }

                    }
                    adapter.notifyDataSetChanged();

                    if(albumlist.size() == 0){
                        msg.setVisibility(View.VISIBLE);
                        msg.setText("No Tiffin available in '"+ state+"'");
                    }
                    else {
                        msg.setVisibility(View.GONE);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


    }


    @Override
    public void onItemClick(View view, int position) {
            String state = stateadapter.getItem(position) ;
            call_all_services(state);
        tiffin.setText("Tiffin service in '"+ state+"'");

    }
}
