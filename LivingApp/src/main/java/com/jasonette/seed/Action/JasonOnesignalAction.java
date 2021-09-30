package com.jasonette.seed.Action;

import com.onesignal.OneSignal;
import org.json.JSONObject;
import android.content.Context;
import com.jasonette.seed.Helper.JasonHelper;
import android.util.Log;

public class JasonOnesignalAction {

    public void set(final JSONObject action, JSONObject data, final JSONObject event, final Context context) {

        /*
         *  This function let you set the external onesignal id for the actual user
         *
         *            "type": "$onesignal.set",
         *            "options": {
         *              "externalid": "%id%",
         *            }
         */

        try {
            if (action.has("options")) {
                JSONObject options = action.getJSONObject("options");
                if(options.has("externalid")){
                    Log.d("user external id loggin", options.toString());
                            // REGISTER ID
                    String externalid = options.getString("externalid");
                    OneSignal.setExternalUserId(externalid);

                    Log.d("USER_EXT_ID_VAL", externalid);

                    JasonHelper.next("success", action, new JSONObject(), event, context);
                }
                else {
                    JSONObject err = new JSONObject();
                    err.put("message", "externalid is empty");
                    JasonHelper.next("error", action, err, event, context);
                }
            }
            else {
                JSONObject err = new JSONObject();
                err.put("message", "$onesignal.set has no options defined");
                JasonHelper.next("error", action, err, event, context);
            }
        } catch (Exception e) {
            Log.d("Warning", e.getStackTrace()[0].getMethodName() + " : " + e.toString());
        }
    }
}