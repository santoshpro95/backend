package goodchef.com.chef_hire.tiffin;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import goodchef.com.chef_hire.tiffin.model.MenuAdapter;
import goodchef.com.chef_hire.tiffin.model.MenuAlbum;

public class ViewMenu extends AppCompatActivity {

    Base_url base_url;
TextView msg;
    ArrayList day_arr, menu_arr;
    RecyclerView recycler_view;
    private MenuAdapter adapter;
    private List<MenuAlbum> albumlist;


    ACProgressFlower dialog;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);


        base_url = new Base_url();
        msg = (TextView)findViewById(R.id.msg);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        dialog = new ACProgressFlower.Builder(ViewMenu.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();


        back = (ImageView)findViewById(R.id.back);


        day_arr = new ArrayList();
        menu_arr = new ArrayList();



        albumlist = new ArrayList<>();
        adapter = new MenuAdapter(getApplicationContext(), albumlist);



        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(adapter);

        String s_id = getIntent().getStringExtra("s_id");

        day_arr.add("monday");
        day_arr.add("tuesday");
        day_arr.add("wednesday");

        day_arr.add("thursday");
        day_arr.add("friday");
        day_arr.add("saturday");
        day_arr.add("sunday");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        get_menu_details(s_id);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public  void get_menu_details(String s_id){
        dialog.show();
        String url = base_url.base_url +"service/get_menu.php";

        RequestParams RP = new RequestParams();
        RP.put("s_id", s_id);

        AsyncHttpClient ac = new AsyncHttpClient();
        ac.post(url, RP, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.i("responce =", response.toString());

                //   Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show();

                try {


                    String menu = "";

                    JSONArray ja = response.getJSONArray("data");

                    JSONObject jo = ja.getJSONObject(0);

                    for(int i=0; i<7;i++){

                            if (!jo.isNull(day_arr.get(i).toString())){
                                menu = jo.getString(day_arr.get(i).toString());
                            }

                            menu_arr.add(i,menu);

                            MenuAlbum a = new MenuAlbum(day_arr.get(i).toString(), menu_arr.get(i).toString());

                            albumlist.add(a);
                    }

                    if(albumlist.size() == 0){
                        msg.setVisibility(View.VISIBLE);
                    }
                    else {
                        msg.setVisibility(View.GONE);
                    }
                    adapter.notifyDataSetChanged();
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
                msg.setVisibility(View.VISIBLE);
            }
        });
    }



}
