package org.apache.cordova.gpsplugin;

import org.json.JSONArray;
import org.json.JSONException;
import android.content.Context;
import org.json.JSONObject;
import android.location.Location;
import android.widget.Toast;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

public class GPSTrack extends CordovaPlugin {

    public static final String GPS_DETAILS = "gpsdetails";
    public static final String GPS_INFO = "gpsinfo";
    public static final String CHANGE_GPS = "changegps";
    public static int gpsstatus;

    public GPSTrack() {
    }

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        try {
            if (GPS_INFO.equals(action)) {
                GPSManager gps = new GPSManager(context);
                if (gps.canGetLocation == false) {
                    gpsstatus = 0;
                } else {
                    gpsstatus = 1;
                }
                JSONObject jSONObject = new JSONObject();
                Toast.makeText(context.getApplicationContext(), gpsstatus, Toast.LENGTH_LONG).show();
                jSONObject.put("status", GPSTracker.gpsstatus);
                callbackContext.success(jSONObject);
                return true;
            } else if (CHANGE_GPS.equals(action)) {
                GPSManager gps = new GPSManager(context);
                gps.showSettingsAlert();
                callbackContext.success();
                return true;
            }
            if (GPS_DETAILS.equals(action)) {
                GPSManager gps = new GPSManager(context);
                Location location = gps.getLocDetails();
                JSONObject jSONObject = new JSONObject();
                Toast.makeText(context.getApplicationContext(), ""+location.getLatitude(), Toast.LENGTH_LONG).show();
                jSONObject.put("lat", location.getLatitude());
                jSONObject.put("long", location.getLongitude());
                callbackContext.success(jSONObject);
                return true;
            } else {
                callbackContext.error("Invalid action");
                Toast.makeText(context, "Invalid action", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (JSONException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
