package com.example.firebase_mad;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "GoogleSignIn";
    TextView tvUserName;
    TextView tvUserEmail;
    ImageView userImageView;
    Button btnLogout;
    Button button3;

    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvUserName = findViewById(R.id.userName);
        tvUserEmail = findViewById(R.id.userEmail);
        userImageView = findViewById(R.id.userImage);
        btnLogout = findViewById(R.id.btnLogout);
        button3 = findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });


        SharedPreferences preferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username","");
        String userEmail = preferences.getString("userEmail", "");
        String userImageUrl = preferences.getString("userPhoto","");

        tvUserName.setText(userName);
        tvUserEmail.setText(userEmail);
        Glide.with(this).load(userImageUrl).into(userImageView);

        btnLogout.setOnClickListener(view -> {
            mAuth.getInstance().signOut();
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}