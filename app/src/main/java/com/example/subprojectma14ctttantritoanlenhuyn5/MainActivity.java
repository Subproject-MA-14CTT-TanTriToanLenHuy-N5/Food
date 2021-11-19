package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
        if (acct != null) {
            EditText editText = findViewById(R.id.edtEmail);
            editText.setText(acct.getDisplayName());
        }

        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            startActivity(new Intent(this, Screen_Home.class));
            finish();
        }else {

        }
    }

    public void showHidePass(View view){
        EditText editText = findViewById(R.id.edtPassword);
        TextView textView = findViewById(R.id.tvShowHidePass);
        if(view.getId() == R.id.tvShowHidePass){
            if(editText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                textView.setText("Hide");
                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                textView.setText("Show");
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}