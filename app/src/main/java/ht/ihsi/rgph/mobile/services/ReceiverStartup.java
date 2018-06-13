package ht.ihsi.rgph.mobile.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ht.ihsi.rgph.mobile.views.activity.BatimentActivity;

/**
 * Created by JFDuverseau on 8/16/2016.
 */
public class ReceiverStartup extends BroadcastReceiver {
    /**
     * @param context The Context in which the receiver is running.
     * @param intent  The Intent being received.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if( intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED) ){
            Intent myIntent = new Intent(context, BatimentActivity.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(myIntent);
        }
        if( intent.getAction().equals(Intent.ACTION_ALL_APPS)){

        }
    }
}
