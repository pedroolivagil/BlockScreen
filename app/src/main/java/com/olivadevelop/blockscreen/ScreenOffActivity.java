package com.olivadevelop.blockscreen;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.olivadevelop.blockscreen.tools.Tools;

/**
 * Main application activity
 */
public class ScreenOffActivity extends Activity implements View.OnClickListener {

    static final String LOG_TAG = "ScreenOffActivity";
    private Button btnGoToSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_off);
        turnScreenOffAndExit();
    }

    private void turnScreenOffAndExit() {
        // first lock screen
        boolean ok = turnScreenOff(getApplicationContext());

        // then provide feedback
        ((Vibrator) getSystemService(Context.VIBRATOR_SERVICE)).vibrate(50);

        // schedule end of activity
        if (ok) {
            final Activity activity = this;
            Thread t = new Thread() {
                public void run() {
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                    /* ignore this */
                    }
                    activity.finish();
                }
            };
            t.start();
        } else {
            btnGoToSettings = (Button) findViewById(R.id.btnGoToSettings);
            btnGoToSettings.setOnClickListener(this);
        }
    }

    /**
     * Turns the screen off and locks the device, provided that proper rights
     * are given.
     *
     * @param context - The application context
     */
    static boolean turnScreenOff(final Context context) {
        boolean retorno = false;
        DevicePolicyManager policyManager = (DevicePolicyManager) context
                .getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName adminReceiver = new ComponentName(context,
                ScreenOffAdminReceiver.class);
        boolean admin = policyManager.isAdminActive(adminReceiver);
        if (admin) {
            Log.i(LOG_TAG, "Going to sleep now.");
            policyManager.lockNow();
            retorno = true;
        } else {
            Log.i(LOG_TAG, "Not an admin");
            Tools.Logger(context, R.string.device_admin_not_enabled);
        }
        return retorno;
    }


    public void goToSettings() {
        startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnGoToSettings.getId()) {
            goToSettings();
            Tools.Logger(this,"Go to settings");
        }
    }
}
