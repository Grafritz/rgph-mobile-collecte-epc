package ht.ihsi.rgph.mobile.epc.services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.epc.utilities.ToastUtility;

/**
 * Created by JFDuverseau on 5/31/2017.
 */
public class GPSService extends Service implements LocationListener {
    // saving the context for later use
    private final Context mContext;

    // if GPS is enabled
    boolean isGPSEnabled = false;
    // if Network is enabled
    boolean isNetworkEnabled = false;
    // if Location co-ordinates are available using GPS or Network
    public boolean isLocationAvailable = false;

    // Location and co-ordinates coordinates
    Location mLocation;
    double mLatitude;
    double mLongitude;
    String message;

    // Minimum time fluctuation for next update (in milliseconds)
    private static final long TIME = 30000;
    // Minimum distance fluctuation for next update (in meters)
    private static final long DISTANCE = 20;

    // Declaring a Location Manager
    protected LocationManager mLocationManager;

    public GPSService(Context context) {
        this.mContext = context;
        mLocationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
    }

    /**
     * Returs the Location
     *
     * @return Location or null if no location is found
     */
    public Location getLocation() {
        try {
            // Getting GPS status
            isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // If GPS enabled, get latitude/longitude using GPS Services
            if (isGPSEnabled) {
                /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return null;
                }*/
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME, DISTANCE, this);
                if (mLocationManager != null) {
                    mLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();
                        isLocationAvailable = true; // setting a flag that
                        // location is available
                        return mLocation;
                    }
                }
            }

            // If we are reaching this part, it means GPS was not able to fetch
            // any location
            // Getting network status
            isNetworkEnabled = mLocationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isNetworkEnabled) {
                mLocationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, TIME, DISTANCE, this);
                if (mLocationManager != null) {
                    mLocation = mLocationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (mLocation != null) {
                        mLatitude = mLocation.getLatitude();
                        mLongitude = mLocation.getLongitude();

                        isLocationAvailable = true; // setting a flag that
                        // location is available
                        return mLocation;
                    }
                }
            }
            // If reaching here means, we were not able to get location neither
            // from GPS not Network,
            if (!isGPSEnabled) {
                // so asking user to open GPS
                askUserToOpenGPS();
            }

        } catch (Exception ex) {
            ToastUtility.LogCat("Exception-getLocation(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ToastUtility.ToastMessage(mContext, "Exception-getLocation(): getMessage: " + ex.getMessage() + " \n toString: " + ex.toString());
            ex.printStackTrace();
        }
        // if reaching here means, location was not available, so setting the
        // flag as false
        isLocationAvailable = false;
        return null;
    }

    /**
     * get latitude
     *
     * @return latitude in double
     */
    public double getLatitude() {
        if (mLocation != null) {
            mLatitude = mLocation.getLatitude();
        }
        return mLatitude;
    }

    /**
     * get longitude
     *
     * @return longitude in double
     */
    public double getLongitude() {
        if (mLocation != null) {
            mLongitude = mLocation.getLongitude();
        }
        return mLongitude;
    }

    /**
     * close GPS to save battery
     */
    public void closeGPS() {
        if (mLocationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mLocationManager.removeUpdates(GPSService.this);
        }
    }

    /**
     * show settings to open GPS
     */
    public void askUserToOpenGPS() {
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mContext);
        // Setting Dialog Title
        mAlertDialog.setTitle(mContext.getString(R.string.msg_GPSNotAvailable))
                //.setIcon(R.drawable.arrow_left)
                .setMessage( mContext.getString(R.string.msg_ActiverLeGPS))
                .setPositiveButton(mContext.getString(R.string.label_OpenSettings), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                })
                .setNegativeButton(mContext.getString(R.string.label_Quit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

    /**
     * Gives you complete address of the location
     *
     * @return complete address in String
     */
    public String getLocationAddress() {
        if (isLocationAvailable) {
            Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
            // Get the current location from the input parameter list
            // Create a list to contain the result address
            List<Address> addresses = null;
            try {
            /*
             * Return 1 address.
             */
                addresses = geocoder.getFromLocation(mLatitude, mLongitude, 1);
            } catch (IOException e1) {
                ToastUtility.LogCat("IOException-getLocationAddress(): getMessage: " + e1.getMessage() + " \n toString: " + e1.toString());
                ToastUtility.ToastMessage(mContext,"IOException-getLocationAddress(): getMessage: " + e1.getMessage() + " \n toString: " + e1.toString());
                e1.printStackTrace();
                return ("IO Exception trying to get address:" + e1);
            } catch (IllegalArgumentException e2) {
                // Error message to post in the log
                String errorString = "Illegal arguments "
                        + Double.toString(mLatitude) + " , "
                        + Double.toString(mLongitude)
                        + " passed to address service";

                ToastUtility.LogCat("IllegalArgumentException-getLocationAddress(): getMessage: " + e2.getMessage() + " \n toString: " + e2.toString() + " \n errorString: " + errorString);
                ToastUtility.ToastMessage(mContext,"IllegalArgumentException-getLocationAddress(): getMessage: " + e2.getMessage() + " \n toString: " + e2.toString() + " \n errorString: " + errorString);
                e2.printStackTrace();
                return errorString;
            }
            // If the reverse geocode returned an address
            if (addresses != null && addresses.size() > 0) {
                // Get the first address
                Address address = addresses.get(0);
            /*
             * Format the first line of address (if available), city, and
             * country name.
             */
                String addressText = String.format(
                        "%s, %s, %s",
                        // If there's a street address, add it
                        address.getMaxAddressLineIndex() > 0 ? address
                                .getAddressLine(0) : "",
                        // Locality is usually a city
                        address.getLocality(),
                        // The country of the address
                        address.getCountryName());
                // Return the text
                ToastUtility.LogCat(addressText);
                return addressText;
            } else {
                message = "No address found by the service: Note to the developers, If no address is found by google itself, there is nothing you can do about it.";
                ToastUtility.LogCat(message);
                ToastUtility.ToastMessage(mContext, message);
                return message;
            }
        } else {
            message = "Location Not available";
            ToastUtility.LogCat(message);
            ToastUtility.ToastMessage(mContext, message);
            return message;
        }

    }
    /**
     * Called when the location has changed.
     * <p/>
     * <p> There are no restrictions on the use of the supplied Location object.
     *
     * @param location The new location, as a Location object.
     */
    @Override
    public void onLocationChanged(Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    /**
     * Called when the provider is enabled by the user.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderEnabled(String provider) {

    }

    /**
     * Called when the provider is disabled by the user. If requestLocationUpdates
     * is called on an already disabled provider, this method is called
     * immediately.
     *
     * @param provider the name of the location provider associated with this
     *                 update.
     */
    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
