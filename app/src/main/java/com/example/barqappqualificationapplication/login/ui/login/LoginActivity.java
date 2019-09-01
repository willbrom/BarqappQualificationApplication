package com.example.barqappqualificationapplication.login.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.example.barqappqualificationapplication.BarqappApplication;
import com.example.barqappqualificationapplication.orders.view.MainActivity;
import com.example.barqappqualificationapplication.R;
import com.example.barqappqualificationapplication.SigninUserMutation;

import org.jetbrains.annotations.NotNull;

public class LoginActivity extends AppCompatActivity {

    private BarqappApplication application;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // make login-screen full-screen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        application = (BarqappApplication) getApplication();

        final EditText mobileNumberEditText = findViewById(R.id.mobile_number);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                authenticateUser(mobileNumberEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });
    }

    private void authenticateUser(String mobile, String password) {
        ApolloCall<SigninUserMutation.Data> signInMutationCall = application.getApolloClient().mutate(new SigninUserMutation(mobile, password));

        signInMutationCall.enqueue(new ApolloCall.Callback<SigninUserMutation.Data>() {
            @Override
            public void onResponse(@NotNull final Response<SigninUserMutation.Data> response) {
                if (response != null)
                    if (!response.hasErrors())
                        if (response.data() != null)
                            if (response.data().signinUser() != null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        updateUiWithUser(response.data().signinUser());
                                    }
                                });
                                return;
                            }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showLoginFailed(getResources().getString(R.string.invalid_signin));
                    }
                });
            }

            @Override
            public void onFailure(@NotNull final ApolloException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showLoginFailed(e.getMessage().toString());
                    }
                });
            }
        });
    }

    private void updateUiWithUser(SigninUserMutation.SigninUser signinUser) {
        String welcome = getString(R.string.welcome) + signinUser.user().name();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(this, errorString, Toast.LENGTH_SHORT).show();
    }
}
