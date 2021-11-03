package goodchef.com.chef_hire.chef;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import goodchef.com.chef_hire.Base_url;
import goodchef.com.chef_hire.R;
import goodchef.com.chef_hire.chef.model.BuyAdapter;
import goodchef.com.chef_hire.chef.model.BuyAlbum;

public class Buylists extends Fragment {




    public static Buylists newInstance() {
        Buylists fragment = new Buylists();
        return fragment;
    }


    ImageView back;
    RecyclerView recycler_view;
    private BuyAdapter adapter;
    private List<BuyAlbum> albumlist;
    ArrayList name_arr, image_arr, price_arr;
    Base_url base_url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_all_foodslists, container, false);

        back = (ImageView)view.findViewById(R.id.back);
        recycler_view = (RecyclerView)view.findViewById(R.id.recycler_view);

        name_arr = new ArrayList();
        image_arr = new ArrayList();
        price_arr = new ArrayList();
        base_url = new Base_url();
        albumlist = new ArrayList<>();
        adapter = new BuyAdapter(getActivity(),albumlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), Chef_HomeScreen.class);
                startActivity(i);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });


        get_products();

        return view;
    }



    public void get_products(){
        try {
                    String name = "";
                    String image = "";
                    String price = "";

                    JSONObject obj = new JSONObject(base_url.all_chef_products);
                    JSONArray ja = obj.getJSONArray("data");

                    for (int i = 0; i < ja.length(); i++) {

                        JSONObject jo = ja.getJSONObject(i);

                        if (!jo.isNull("name")) {
                            name = jo.getString("name");
                        }
                        if (!jo.isNull("image")) {

                            image = jo.getString("image");

                        }
                        if (!jo.isNull("price")) {
                            price = jo.getString("price");
                        }


                        name_arr.add(i, name);
                        image_arr.add(i, image);
                        price_arr.add(i, price);

                        BuyAlbum a = new BuyAlbum(name_arr.get(i).toString(), price_arr.get(i).toString()
                                , image_arr.get(i).toString());

                        albumlist.add(a);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



    }

