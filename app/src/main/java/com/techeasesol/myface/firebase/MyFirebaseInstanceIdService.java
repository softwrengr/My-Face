package com.techeasesol.myface.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.techeasesol.myface.utilities.GeneralUtils;
import com.techeasesol.myface.utilities.ShareUtils;


/**
 * Created by Asus on 10/4/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String TAG = "Firebase";
    public static String DEVICE_TOKEN;
    Context context;

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        DEVICE_TOKEN = refreshedToken;
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        if (refreshedToken.equals(null)) {
            // onTokenRefresh();
            refreshedToken = FirebaseInstanceId.getInstance().getToken();

        } else {
            ShareUtils.getEditor(this).putString("device_token", refreshedToken).commit();
        }

        sendRegistrationToServer(refreshedToken);
    }

    private String sendRegistrationToServer(String token) {
        return token;
    }
}
