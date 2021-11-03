package goodchef.com.chef_hire.firebase;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import goodchef.com.chef_hire.Base_url;


public class FCMInitialService extends FirebaseInstanceIdService {


    private static final String TAG = "FCMInitialService";
    @Override
    public void onTokenRefresh() {
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.i(TAG, "FCM_device_Token    " + fcmToken);
    }
}

