package goodchef.com.chef_hire;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class Legal_PrivacyPolicy extends AppCompatActivity {

    WebView web;
    Base_url base_url;
    ACProgressFlower dialog;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal__privacy_policy);

        back = (ImageView)findViewById(R.id.back);

        base_url = new Base_url();
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        dialog.show();

        web = (WebView)findViewById(R.id.web);

        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        String url = base_url.base_url+"policy.html";
        web.loadUrl(url);

        dialog.dismiss();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

    }
}
