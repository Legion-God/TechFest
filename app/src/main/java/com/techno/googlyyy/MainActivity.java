package com.techno.googlyyy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

     Button google_sign;
     Button signOutbutton;
     Button coord_log;

     //TODO: add proper sign out option with Not you option in another activity linked to this,
    //TODO: add animation and progress bars.
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        google_sign = findViewById(R.id.google_sign_in);
        google_sign.setOnClickListener(this);

        signOutbutton = findViewById(R.id.sign_out_btn);
        signOutbutton.setOnClickListener(this);

        coord_log = findViewById(R.id.coordinator_signin);
        coord_log.setOnClickListener(this);

        //auth code
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

       // FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try{
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }catch(ApiException e){
                Log.i(TAG,"Google Sign in Failed");
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG,"FirebaseAuthWithGoogle:"+account.getId());



        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "You are Signed In!", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this, "Email: "+user.getEmail(), Toast.LENGTH_SHORT).show();

                    //On successful login go to event page
                    goToEvent();
                }else {
                    Toast.makeText(MainActivity.this, "You are fucked Up!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void log_out(){
        mAuth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(MainActivity.this, "Fucked Up!", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void goToCoordinator(){
        Intent coord_intent = new Intent(this, coordinator_login.class);
        startActivity(coord_intent);
    }

    private void goToEvent(){
        Intent event_intent = new Intent(this, event_menu.class);
        startActivity(event_intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.google_sign_in:
                signIn();
                break;
            case R.id.coordinator_signin:
                goToCoordinator();
                break;
            case R.id.sign_out_btn:
                log_out();
                break;

                default:
                    break;
        }

    }
}
