package ht.ihsi.rgph.mobile.epc.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ht.ihsi.rgph.mobile.epc.constant.Constant;

/**
 * Created by ajordany on 3/31/2016.
 */
public class PreferenceUtility {

    public boolean CheckUserConnected(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userName = sharedPreferences.getString(Constant.prefKeyUserName,"Admin");
        String idGroupeUser = sharedPreferences.getString(Constant.prefKeyIdGroupeUser, "pass");
        Boolean _Val = false;
        if( userName.trim() != "" && idGroupeUser.trim() != "" ){
            _Val = true;
            ToastUtility.LogCat(context, "Utilisateur Connecté...");
        }
        return _Val;
    }
}
