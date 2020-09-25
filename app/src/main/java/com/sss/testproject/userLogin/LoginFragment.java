package com.sss.testproject.userLogin;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sss.testproject.R;
import com.sss.testproject.networking.RetrofitClient;
import com.sss.testproject.userLogin.loginModel.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.sss.testproject.utility.Constant.LOGIN_URL;

public class LoginFragment extends Fragment {


    private static final String TAG = "SignInFragment ";
    private EditText emailET, passwordET;
    private String email, password;
    private Button loginBtn;
    private LoginComplete loginComplete;
    private Context context;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        loginComplete= (LoginComplete) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        loginBtn = view.findViewById(R.id.signinBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailET.getText().toString();
                password = passwordET.getText().toString();
                Log.d(TAG, "onClick: "+password);
//                doSignIn(email, password);
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailET.setError("Please enter valid email");
                } else if (password.isEmpty()) {
                    passwordET.setError("Please enter password");
                }else {
                    doSignIn(email, password);
                }
            }
        });


        return view;
    }


    private void doSignIn(String email, String password) {

        Call<LoginResponse> call = RetrofitClient.getInstance(LOGIN_URL).getApiInterface().getUser(email, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse != null && loginResponse.getMessage().contains("Successful")) {
                        Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_LONG).show();

                        loginComplete.onLoginSuccess();
                        Log.d("signup", "onResponse: " + response.code());
                    }else {
                        Toast.makeText(context, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("signup", "server onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
                Toast.makeText(context, "Server busy !!! Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface LoginComplete {
        void onLoginSuccess();
    }
}