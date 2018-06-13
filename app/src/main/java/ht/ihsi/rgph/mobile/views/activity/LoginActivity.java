package ht.ihsi.rgph.mobile.views.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import ht.ihsi.rgph.mobile.BuildConfig;
import ht.ihsi.rgph.mobile.R;
import ht.ihsi.rgph.mobile.constant.Constant;
import ht.ihsi.rgph.mobile.models.PersonnelModel;
import ht.ihsi.rgph.mobile.utilities.ToastUtility;
import ht.ihsi.rgph.mobile.utilities.Tools;
import ht.ihsi.rgph.mobile.views.activity.Utils.ProgressGenerator;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements ProgressGenerator.OnCompleteListener, View.OnClickListener, TextView.OnEditorActionListener {

    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Context context;
    public TextView tv_CopyRight, tv_titre;

    public BootstrapButton btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init(Constant.FORM_DATA_MANAGER);

        tv_CopyRight = (TextView)this.findViewById(R.id.tv_CopyRight);
        tv_CopyRight.setText(getString(R.string.msg_Developpeur) + "  -|- Ver. " + BuildConfig.VERSION_NAME);

        tv_titre = (TextView)this.findViewById(R.id.tv_titre);
        tv_titre.setText(getString(R.string.app_name) + "\nVer." + BuildConfig.VERSION_NAME);

        context = LoginActivity.this;
        mEmailView = (EditText) findViewById(R.id.tv_UserName);
        mPasswordView = (EditText) findViewById(R.id.tv_Password);
        mPasswordView.setOnEditorActionListener(this);

        btnSignIn = (BootstrapButton) findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

    }//

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                LoginConnexion();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == Constant.imeActionId_EtReponse_6){
            LoginConnexion();
            return true;
        }
        return false;
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void LoginConnexion() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.msg_Username_Obligatoire));
            focusView = mEmailView;
            cancel = true;
        }else
        if (TextUtils.isEmpty(password)) {
            // Check for a valid password, if the user entered one.
            mPasswordView.setError(getString(R.string.msg_MotDePasse_Obligatoire));
            focusView = mPasswordView;
            cancel = true;
        }else
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !Tools.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.msg_MotDePasse_TropCourt));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            new UserLoginTask(email, password).execute();
        }
    }

    //region NOT USE
    /*
     * Shows the progress UI and hides the login form.
     */

    /*@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }*/

    @Override
    public void onComplete() {

    }
    //endregion

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
        private final String mEmail;
        private final String mPassword;
        private PersonnelModel personnelModel = null;
        private ProgressDialog nDialog;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            nDialog = new ProgressDialog(context);
            //nDialog.setTitle("Checking Network");
            nDialog.setMessage(getString(R.string.msg_WaitAMinit));
            nDialog.setIndeterminate(false);
            nDialog.setCancelable(false);
            nDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                //Thread.sleep(2000);
                personnelModel = formDataMngr.getPersonnelInfo(mEmail, mPassword);
                if(personnelModel == null ) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            try {
                if (nDialog != null) {
                    nDialog.dismiss();
                }

                if (success) {
                    if (personnelModel != null) {
                        if (!personnelModel.getEstActif()) {
                            message = getString(R.string.label_CompteUtilisateur) + " " + getString(R.string.msg_Inactif);
                            ToastUtility.LogCat(message);
                            ToastUtility.ToastMessage(context, message, Constant.GravityCenter);
                            mEmailView.setError(message);
                            mEmailView.requestFocus();
                        } else {
                            personnelModel.setIsConnected(true);

                            message = "Bienvenu: " + personnelModel.getPrenom() + " " + personnelModel.getNom();
                            ToastUtility.LogCat(message);
                            ToastUtility.ToastMessage(context, message, Constant.GravityBottom);
                            Tools.StoreInfoPresonnel_PreferenceManager(context, personnelModel);
                            finish();
                        }
                    } else {
                        message = getString(R.string.label_CompteUtilisateur) + " et/ou " + getString(R.string.label_MotDePasse)
                                + " " + getString(R.string.label_Incorrect);
                        ToastUtility.LogCat(message);
                        ToastUtility.ToastMessage(context, message, Constant.GravityCenter);
                        mEmailView.setError(message);
                        mEmailView.requestFocus();
                    }
                } else {
                    message = getString(R.string.msg_UserName_ou_MotDePasse_Incorrect);
                    ToastUtility.ToastMessage(context, message, Constant.GravityCenter);
                    mPasswordView.setError(message);
                    mPasswordView.requestFocus();
                }
            } catch (Exception e) {
                message =getString(R.string.msg_UserName_ou_MotDePasse_Incorrect);
                ToastUtility.ToastMessage(context, message, Constant.GravityCenter);
                mPasswordView.setError(message);
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
}

