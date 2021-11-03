package goodchef.com.chef_hire.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.user.model.HomeAdapter;
import goodchef.com.chef_hire.user.model.HomeAlbum;
import goodchef.com.chef_hire.user.model.PosterAdapter;
/**
 * Created by santo on 8/11/2018.
 */

public class User_Home extends Fragment {

    Base_url base_url;

    ArrayList name_arr, price_arr, image_arr, items_arr,
            meal_id_arr, poster_arr;

    ImageView search;
    ViewPager pager;
    TextView msg;
    RecyclerView recycler_view;
    private HomeAdapter adapter;
    private List<HomeAlbum> albumlist;

    PosterAdapter posterAdapter;
    ACProgressFlower dialog;
    EditText data;

    RelativeLayout poster_tab;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    RelativeLayout location;
    TextView city_name;

    public static User_Home newInstance() {
        User_Home fragment = new User_Home();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.user_home, container, false);
        base_url = new Base_url();



        dialog = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();

        name_arr = new ArrayList();
        poster_arr = new ArrayList();
        price_arr = new ArrayList();
        image_arr  = new ArrayList();
        items_arr = new ArrayList();
        meal_id_arr = new ArrayList();


        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);
        poster_tab = (RelativeLayout)view.findViewById(R.id.poster_tab);
        pager = (ViewPager) view.findViewById(R.id.pager);
        search = (ImageView)view.findViewById(R.id.search);
        msg = (TextView)view.findViewById(R.id.msg);
        city_name = (TextView)view.findViewById(R.id.city_name);
        data = (EditText)view.findViewById(R.id.data);
        location = (RelativeLayout)view.findViewById(R.id.location);
        albumlist = new ArrayList<>();


        adapter = new HomeAdapter(getActivity(), albumlist);


        dotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);


        posterAdapter = new PosterAdapter();
        posterAdapter.setData(poster_arr,getActivity());
        pager.setAdapter(posterAdapter);
        pager.addOnPageChangeListener(viewPagerPageChangeListener);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);
        recycler_view.setNestedScrollingEnabled(false);

        getconfig();
        get_food_details("");

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String  city  = sp.getString("city_name","Location");

        if(city.equalsIgnoreCase("Location")){
            Intent i = new Intent(getActivity(), LocationScreen.class);
            startActivity(i);
            getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
            city_name.setText(city);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_data = data.getText().toString();
                get_food_details(get_data);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getActivity(), LocationScreen.class);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

            }
        });


        data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                get_food_details(charSequence.toString());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return view;
    }


    public void getconfig(){
        try {
            JSONObject obj = new JSONObject(base_url.config);

            JSONObject jo = obj.getJSONObject("user_home");

            JSONArray ja = jo.getJSONArray("pager");

            for(int i =0; i<ja.length(); i++){

                JSONObject pager = ja.getJSONObject(i);
                String image = pager.getString("image");
                poster_arr.add(i, image);

            }
            posterAdapter.notifyDataSetChanged();
            addBottomDots(0);

            String show_poster = obj.getString("poster");

            if(show_poster.equalsIgnoreCase("true")){
                poster_tab.setVisibility(View.VISIBLE);
            }
            else {
                poster_tab.setVisibility(View.GONE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void get_food_details(String query) {

        albumlist.clear();

        try {

            String name = "";
            String price = "";
            String image = "";
            String items = "";
            String meal_id = "";

            JSONObject obj = new JSONObject(base_url.user_home_food);

            JSONArray ja = obj.getJSONArray("data");


            for (int i = 0; i < ja.length(); i++) {


                JSONObject jo = ja.getJSONObject(i);

                if (!jo.isNull("name")) {
                    name = jo.getString("name");
                }
                if (!jo.isNull("price")) {
                    price = jo.getString("price");
                }

                if (!jo.isNull("image")) {
                    image = jo.getString("image");
                }
                if (!jo.isNull("items")) {
                    items = jo.getString("items");
                }

                if (!jo.isNull("meal_id")) {
                    meal_id = jo.getString("meal_id");
                }



            try{

                double profit = Integer.parseInt(price) * (Double.parseDouble(base_url.food_commision) / 100.0);
                double gross_price = Integer.parseInt(price) + profit;

                long price_final = Math.round(gross_price);

                if(name.toLowerCase().contains(query.toLowerCase())){

                    name_arr.add(i, name);
                    price_arr.add(i, ""+Integer.parseInt(""+price_final));
                    items_arr.add(i, items);
                    image_arr.add(i, image);
                    meal_id_arr.add(i, meal_id);

                    HomeAlbum a = new HomeAlbum(name_arr.get(i).toString(), items_arr.get(i).toString(),
                            price_arr.get(i).toString(), image_arr.get(i).toString(), meal_id_arr.get(i).toString());
                    albumlist.add(a);
                }

            }catch (Exception e){

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


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {

            addBottomDots(position);
            // changing the next button text 'NEXT' / 'GOT IT'
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

    };

    private void addBottomDots(int currentPage) {

        dots = new TextView[poster_arr.size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(getActivity());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            //dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dots[i].setTextColor(getActivity().getResources().getColor(R.color.light_grey));

            // dots[i].setBackground( getResources().getDrawable(R.drawable.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            //dots[currentPage].setBackground( getResources().getDrawable(R.drawable.dot_active));
            dots[currentPage].setTextColor(getActivity().getResources().getColor(R.color.white));
    }

}
