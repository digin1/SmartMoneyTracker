package com.smt.smartmoneytracker.helper;

/**
 * Created by digin on 24-Dec-15.
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;


import com.smt.smartmoneytracker.LoginActivity;


public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();


    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "AndroidHiveLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public static final String KEY_UID ="sad";
    public static final String KEY_UNAME="dasd";
    public static final String KEY_UEMAIL="asdasda";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
    public void storinglogindata(String id,String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(KEY_IS_LOGGEDIN,true);
        //Storing id in pref
        editor.putString(KEY_UID,id);
        // Storing name in pref
        editor.putString(KEY_UNAME, name);
        // Storing email in pref
        editor.putString(KEY_UEMAIL, email);
        // commit changes
        editor.commit();
    }
    public String getUserid() {
        String ex_uid = pref.getString(KEY_UID, "default_id");
        return ex_uid;
    }
    public String getUsername() {
        String ex_uname = pref.getString(KEY_UNAME, "default_name");
        return ex_uname;
    }
    public String getUseremail(){
        String ex_uemail = pref.getString(KEY_UEMAIL,"default_email");
        return ex_uemail;
    }
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }
    }
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
//         Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }
}
