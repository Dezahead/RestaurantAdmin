package com.dcv3.fastfood.fastfoodadmin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {
    FragmentManager fm;
    FragmentTransaction ft;
    private EditText emailAddressET;
    private EditText passWordET;
    private Button loginButton;
    String restaurantId;

    public LoginActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        emailAddressET = (EditText)v.findViewById(R.id.Emailaddressfield);
        passWordET = (EditText) v.findViewById(R.id.passwordField);
        loginButton = (Button) v.findViewById(R.id.sendbutton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });

        return v;
    }

    private void login(){
        final String username = emailAddressET.getText().toString().trim();
        String password = passWordET.getText().toString().trim();

        // Validate the log in data
        boolean validationError = false;
        StringBuilder validationErrorMessage = new StringBuilder(getString(R.string.error_intro));
        if (username.length() == 0) {
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_email));
        }
        if (password.length() == 0) {
            if (validationError) {
                validationErrorMessage.append(getString(R.string.error_join));
            }
            validationError = true;
            validationErrorMessage.append(getString(R.string.error_blank_password));
        }
        validationErrorMessage.append(getString(R.string.error_end));

        // If there is a validation error, display the error
        if (validationError) {
            Toast.makeText(getActivity(), validationErrorMessage.toString(), Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // Set up a progress dialog
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage(getString(R.string.progress_login));
        dialog.show();
        // Call the Parse login method
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                dialog.dismiss();
                if (e != null) {
                    // Show the error message
                    Toast.makeText(getActivity(), "Incorrect Email or Password", Toast.LENGTH_LONG).show();
                } else {
                    restaurantId = user.getString("restaurantNo");
                    switchFragment(new CurrentOrdersFragment());
                }
            }
        });
    }

    //switch the fragments-DJ
    public void switchFragment(Fragment fr){
        Bundle bundle = new Bundle();
        bundle.putString("id", restaurantId);
        fr.setArguments(bundle);
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fr);
        ft.commit();
    }
}
