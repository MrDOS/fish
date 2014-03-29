package ca.acadiau.cs.comp4583.fish;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import ca.acadiau.cs.comp4583.fish.data.User;
import ca.acadiau.cs.comp4583.fish.data.persistence.LoginHandler;
import ca.acadiau.cs.comp4583.fish.data.persistence.SessionStorageService;
import ca.acadiau.cs.comp4583.fish.data.persistence.TrackMyFishLoginProvider;

/**
 * Interface for user logins.
 * 
 * @since 1.0.0
 * @author Samuel Coleman <105709c@acadiau.ca>
 */
public class LoginActivity extends Activity implements LoginHandler
{
    public static final String LOGIN_PREFS = "TMFPreferences";
    public static final String LOGIN_PREFS_USERNAME = "username";

    private SharedPreferences preferences;
    private String username;

    private ProgressDialog loginDialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /* Load access to the shared preferences. */
        this.preferences = getSharedPreferences(LoginActivity.LOGIN_PREFS, 0);

        /* Set the layout dependent on whether the user is logged in or not. */
        this.username = preferences.getString(LOGIN_PREFS_USERNAME, null);
        if (this.username == null)
            this.initializeLogin();
        else
            this.initializeLogout();
    }

    private void initializeLogin()
    {
        this.setContentView(R.layout.login);

        final Button backButton = (Button) this.findViewById(R.id.back_from_login_button);
        final Button loginButton = (Button) this.findViewById(R.id.login_button);
        final EditText usernameEditText = (EditText) this.findViewById(R.id.login_text_edit);
        final EditText passwordEditText = (EditText) this.findViewById(R.id.pass_text_edit);

        /* Just leave the activity when the user hits Back. */
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginActivity.this.finish();
            }
        });

        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginActivity.this.showLoginDialog();

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                TrackMyFishLoginProvider loginProvider = new TrackMyFishLoginProvider(
                        getString(R.string.trackmyfish_auth_endpoint));
                loginProvider.addLoginHandler(LoginActivity.this);
                loginProvider.validate(username, password);
            }
        });

        /* The Login button shouldn't be enabled until a username and password
         * have been filled out. */
        loginButton.setEnabled(false);

        /* Validates the eligibility of the use of the login button. */
        TextWatcher loginEligibilityWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                loginButton.setEnabled(usernameEditText.getText().length() > 0
                        && passwordEditText.getText().length() > 0);
            }
        };

        /* If either the username or password field are blank, we want to
         * disallow logging in. */
        (usernameEditText).addTextChangedListener(loginEligibilityWatcher);
        (passwordEditText).addTextChangedListener(loginEligibilityWatcher);
    }

    private void initializeLogout()
    {
        this.setContentView(R.layout.logout);

        ((TextView) this.findViewById(R.id.logged_in_text)).setText(
                String.format(getString(R.string.logged_in_text), this.username));

        ((Button) this.findViewById(R.id.back_from_login_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                LoginActivity.this.finish();
            }
        });

        ((Button) this.findViewById(R.id.logout_button)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SharedPreferences.Editor editor = LoginActivity.this.preferences.edit();
                editor.remove(LoginActivity.LOGIN_PREFS_USERNAME);
                editor.commit();

                LoginActivity.this.finish();
            }
        });
    }

    private void showLoginDialog()
    {
        this.loginDialog = ProgressDialog.show(this,
                getString(R.string.login_dialog_title),
                getString(R.string.login_dialog_message));
    }

    private void hideLoginDialog()
    {
        if (this.loginDialog != null)
            this.loginDialog.dismiss();
    }

    @Override
    public void onLoginSuccess(User user)
    {
        this.hideLoginDialog();

        SharedPreferences.Editor editor = this.preferences.edit();
        editor.putString(LoginActivity.LOGIN_PREFS_USERNAME, user.getUsername());
        editor.commit();

        /* Having logged in, we'll make sure the submission service starts in
         * order to push out any unsubmitted sessions. */
        startService(new Intent(this, SessionStorageService.class));

        this.finish();
    }

    @Override
    public void onLoginFailure(final int messageRes)
    {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                LoginActivity.this.hideLoginDialog();

                new AlertDialog.Builder(LoginActivity.this)
                        .setMessage(getString(messageRes))
                        .setPositiveButton(android.R.string.ok, null)
                        .create()
                        .show();
            }
        });
    }
}