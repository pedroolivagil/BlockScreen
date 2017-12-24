package com.olivadevelop.blockscreen.tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Oliva on 22/12/2017.
 */

public class Tools {

    public static void Logger(Context c, String text) {
        Toast.makeText(c, text, Toast.LENGTH_LONG).show();
    }

    public static void Logger(Context c, int idText) {
        Toast.makeText(c, c.getString(idText), Toast.LENGTH_LONG).show();
    }
}
