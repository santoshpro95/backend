package goodchef.com.chef_hire.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.model.LocationAdapter;
import goodchef.com.chef_hire.user.model.LocationAlbum;

public class LocationScreen extends AppCompatActivity {

    TextView msg;
    EditText data;
    Base_url base_url;
    RecyclerView recycler_view;
    ArrayList city_arr;

    private LocationAdapter adapter;
    private List<LocationAlbum> albumlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_screen);

        msg = (TextView)findViewById(R.id.msg);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        data = (EditText)findViewById(R.id.data);

        base_url = new Base_url();
        albumlist = new ArrayList<>();
        city_arr = new ArrayList();

        adapter = new LocationAdapter(getApplicationContext(), albumlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);


        data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                get_location(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        get_location("");


    }


    public void get_location(String query){
        albumlist.clear();
        try {

            JSONObject obj = new JSONObject(base_url.location);
            JSONArray city = obj.getJSONArray("city");

            for(int i = 0;  i< city.length(); i++){

                JSONObject city_name = city.getJSONObject(i);
                String c_name = city_name.getString("name");

                if(c_name.toLowerCase().contains(query.toLowerCase())){

                    city_arr.add(i,c_name);
                    LocationAlbum a = new LocationAlbum(city_arr.get(i).toString());
                    albumlist.add(a);
                }

            }

            adapter.notifyDataSetChanged();

            if(albumlist.size() == 0){
                msg.setVisibility(View.VISIBLE);
                msg.setText("No Value for "+"'"+data.getText().toString()+"'");
            }
            else {
                msg.setVisibility(View.GONE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

}
