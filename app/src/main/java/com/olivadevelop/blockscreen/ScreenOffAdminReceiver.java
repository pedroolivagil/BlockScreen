package com.olivadevelop.blockscreen;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.olivadevelop.blockscreen.tools.Tools;

/**
 * Receiver class which shows notifications when the Device Administrator status
 * of the application changes.
 */
public class ScreenOffAdminReceiver extends DeviceAdminReceiver {


    @Override
    public void onEnabled(Context context, Intent intent) {
        Tools.Logger(context, R.string.admin_receiver_status_enabled);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Tools.Logger(context, R.string.admin_receiver_status_disabled);
    }

}
