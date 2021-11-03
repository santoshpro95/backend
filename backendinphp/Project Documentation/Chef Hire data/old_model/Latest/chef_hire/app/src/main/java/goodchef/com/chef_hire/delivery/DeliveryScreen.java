package goodchef.com.chef_hire.delivery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import goodchef.com.chef_hire.R;

public class DeliveryScreen extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor ee;
    BottomNavigationView mBottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_screen);


        mBottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ee = sp.edit();
        ee.putString("screen","delivery");
        ee.apply();

        mBottomNav.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                     @Override
                     public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                         android.support.v4.app.Fragment selectedFragment = null;

                         switch (item.getItemId()) {
                             case R.id.home:
                                 selectedFragment = Delivery_Home.newInstance();
                                 break;
                             case R.id.tiffin:
                                 selectedFragment = Delivery_Tiffin.newInstance();
                                 break;
                             case R.id.account:
                                 selectedFragment = Delivery_Account.newInstance();
                                 break;

                         }

                         FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                         transaction.replace(R.id.frame_layout, selectedFragment);
                         transaction.commit();
                         return true;
                     }
                 }
                );


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, Delivery_Home.newInstance());
        transaction.commit();



    }

    @Override
    public void onBackPressed() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeliveryScreen.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();


    }
}
