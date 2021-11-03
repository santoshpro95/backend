package goodchef.com.chef_hire.chef;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.model.AllFoodAdapter;
import goodchef.com.chef_hire.chef.model.AllFoodAlbum;

public class ChooseFood extends AppCompatActivity {

    Base_url base_url;

    ArrayList name_arr, price_arr, image_arr,
            items_arr, meal_id_arr, data;

    RecyclerView recycler_view;
    private AllFoodAdapter adapter;
    private List<AllFoodAlbum> albumlist;

    ACProgressFlower dialog;

    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_food);


        base_url = new Base_url();

        dialog = new ACProgressFlower.Builder(ChooseFood.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        name_arr = new ArrayList();
        price_arr = new ArrayList();
        image_arr  = new ArrayList();
        items_arr = new ArrayList();
        meal_id_arr = new ArrayList();

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        albumlist = new ArrayList<>();
        adapter = new AllFoodAdapter(ChooseFood.this, albumlist);
        back = (ImageView)findViewById(R.id.back);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               finish();
               overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });

        String resp = getIntent().getStringExtra("choose");

        data = new ArrayList<String>(Arrays.asList(resp.split(", ")));

       // Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_LONG).show();

        get_allFoods();



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }





    public void get_allFoods(){

        try {

            String name = "";
            String price = "";

            String image = "";
            String items = "";
            String meal_id = "";

            JSONObject obj = new JSONObject(base_url.chef_all_foods);
            JSONArray ja = obj.getJSONArray("data");


            for(int i=0; i<ja.length();i++){


                JSONObject jo = ja.getJSONObject(i);



                if (!jo.isNull("name")){
                    name = jo.getString("name");
                }
                if (!jo.isNull("price")){
                    price = jo.getString("price");
                }

                if (!jo.isNull("image")){
                    image = jo.getString("image");
                }
                if (!jo.isNull("items")){
                    items = jo.getString("items");
                }

                if (!jo.isNull("meal_id")){
                    meal_id = jo.getString("meal_id");
                }




                name_arr.add(i,name);
                price_arr.add(i,price);
                items_arr.add(i,items);
                image_arr.add(i,image);
                meal_id_arr.add(i,meal_id);


                AllFoodAlbum a = new AllFoodAlbum(name_arr.get(i).toString(),price_arr.get(i).toString(),
                        image_arr.get(i).toString(),items_arr.get(i).toString(),meal_id_arr.get(i).toString(),
                        data);
                albumlist.add(a);


            }
            adapter.notifyDataSetChanged();



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
