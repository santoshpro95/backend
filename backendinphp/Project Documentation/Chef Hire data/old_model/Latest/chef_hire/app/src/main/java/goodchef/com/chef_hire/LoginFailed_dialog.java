package goodchef.com.chef_hire;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by samar on 12/15/2017.
 */

public class LoginFailed_dialog extends Dialog implements
        View.OnClickListener {

    public Activity c;
    public TextView ok;

    public LoginFailed_dialog(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_failed_dialog);

        ok = (TextView) findViewById(R.id.ok);
        ok.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok:
              dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

}