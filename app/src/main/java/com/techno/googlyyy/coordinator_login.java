package com.techno.googlyyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class coordinator_login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private Button coord_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_login);

        coord_login = findViewById(R.id.coord_sign_in);
        coord_login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //TODO: make an another activity to switch between coordinator accounts, something like "Not you?"
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!= null){
            //TODO: if current user is signed in go to coordinator edit activity
        }

    }

    public void authenticateUser(){
        EditText tempEmail = findViewById(R.id.coord_email);
        EditText tempPass = findViewById(R.id.coord_pass);

        String email = tempEmail.getText().toString();
        String password = tempPass.getText().toString();

        mAuth.signInWithEmailAndPassword(email ,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //TODO: Go to coordinator edit activity
                    Toast.makeText(coordinator_login.this, "Coordinator Signed In!", Toast.LENGTH_SHORT).show();
            }else{
                    Toast.makeText(coordinator_login.this, "Something's Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.coord_sign_in){
            authenticateUser();
        }
    }
}
