package ht.ihsi.rgph.mobile.epc.views.activity.Settings;

import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by JFDuverseau on 5/2/2016.
 */
public class AppCompatPreferenceActivity extends PreferenceActivity
{
    private AppCompatDelegate appCompatDelegate;

    private AppCompatDelegate getAppCompatDelegate()
    {
        if(appCompatDelegate == null){
            appCompatDelegate = AppCompatDelegate.create(this, null);
        }
        return appCompatDelegate;
    }//
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getAppCompatDelegate().installViewFactory();
        getAppCompatDelegate().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }//

    public ActionBar getSupportActionBar(){
        return getAppCompatDelegate().getSupportActionBar();
    }//
    //
    public  void setSupportActionBar(@Nullable Toolbar toolbar){
        getAppCompatDelegate().setSupportActionBar(toolbar);
    }//
    //
    /**
     * Called when activity start-up is complete (after {@link #onStart}
     * and {@link #onRestoreInstanceState} have been called).  Applications will
     * generally not implement this method; it is intended for system
     * classes to do final initialization after application code has run.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     * @see #onCreate
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getAppCompatDelegate().onPostCreate(savedInstanceState);
    }//

    /**
     * Returns a {@link MenuInflater} with this context.
     */
    @Override
    public MenuInflater getMenuInflater() {
        return getAppCompatDelegate().getMenuInflater();
    }//

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        getAppCompatDelegate().setContentView(layoutResID);
    }//

    @Override
    public void setContentView(View view) {
        getAppCompatDelegate().setContentView(view);
    }//

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        getAppCompatDelegate().setContentView(view, params);
    }//

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        getAppCompatDelegate().addContentView(view, params);
    }//

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getAppCompatDelegate().onPostResume();
    }//

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getAppCompatDelegate().setTitle(title);
    }//

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getAppCompatDelegate().onConfigurationChanged(newConfig);
    }//

    @Override
    protected void onStop() {
        super.onStop();
        getAppCompatDelegate().onStop();
    }//

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getAppCompatDelegate().onDestroy();
    }//

    @Override
    public void invalidateOptionsMenu() {
        getAppCompatDelegate().invalidateOptionsMenu();
    }
}
